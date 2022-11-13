package com.fileupload.gerimedica.helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

import com.fileupload.gerimedica.model.Gerimedica;

public class CSVHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = {"code","source", "codeListCode",
      "displayValue", "longDescription", "fromDate", "toDate", "sortingPriority"};

  public static boolean hasCSVFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public static List<Gerimedica> csvGerimedicas(InputStream is) {
    //noinspection deprecation
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Gerimedica> gerimedicas = new ArrayList<Gerimedica>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
        Gerimedica gerimedica = new Gerimedica(
              Long.parseLong(csvRecord.get("code")),
              csvRecord.get("source"),
              csvRecord.get("codeListCode"),
              csvRecord.get("displayValue"),
              csvRecord.get("longDescription"),
              LocalDate.parse(csvRecord.get("fromDate")),
              LocalDate.parse(csvRecord.get("toDate")),
            Integer.parseInt(csvRecord.get("sortingPriority"))
            );

        gerimedicas.add(gerimedica);
      }

      return gerimedicas;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }

  public static ByteArrayInputStream GerimedicasToCSV(List<Gerimedica> gerimedicas) {
    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
      for (Gerimedica gerimedica : gerimedicas) {
        List<? extends Serializable> data = Arrays.asList(
              String.valueOf(gerimedica.getCode()),
            gerimedica.getSource(),
            gerimedica.getCodeListCode(),
            gerimedica.getDisplayValue(),
            gerimedica.getLongDescription(),
            String.valueOf(gerimedica.getFromDate()),
            String.valueOf(gerimedica.getToDate()),
            Integer.valueOf(gerimedica.getSortingPriority())
            );

        csvPrinter.printRecord(data);
      }

      csvPrinter.flush();
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
    }
  }

}
