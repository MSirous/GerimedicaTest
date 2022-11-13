package com.fileupload.gerimedica.service;

import com.fileupload.gerimedica.repository.GerimedicaRepository;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fileupload.gerimedica.helper.CSVHelper;
import com.fileupload.gerimedica.model.Gerimedica;

@Service
public class CSVService {
  @Autowired
  GerimedicaRepository repository;

  public void save(MultipartFile file) {
    try {
      List<Gerimedica> gerimedicas = CSVHelper.csvGerimedicas(file.getInputStream());
      repository.saveAll(gerimedicas);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load() {
    List<Gerimedica> gerimedicas = repository.findAll();

    ByteArrayInputStream in = CSVHelper.GerimedicasToCSV(gerimedicas);
    return in;
  }

//  getAllTutorials
  public List<Gerimedica> getAllGerimedicas() {
    return repository.findAll();
  }
}
