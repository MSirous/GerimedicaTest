package com.fileupload.gerimedica.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "file_db")
public class Gerimedica {

  @Id
  @Column(name = "code")
  private long code;

  @Column(name = "source")
  private String source;

  @Column(name = "code_list_code")
  private String codeListCode;

  @Column(name = "display_value")
  private String displayValue;

  @Column(name = "long_description")
  private String longDescription;

  @Column(name = "from_date")
  private LocalDate fromDate;

  @Column(name = "to_date")
  private LocalDate toDate;

  @Column(name = "sorting_priority")
  private Integer sortingPriority;

  public Gerimedica() {

  }

  public Gerimedica(long code, String source, String codeListCode, String displayValue,
      String longDescription, LocalDate fromDate, LocalDate toDate, Integer sortingPriority) {
    this.code = code;
    this.source = source;
    this.codeListCode = codeListCode;
    this.displayValue = displayValue;
    this.longDescription = longDescription;
    this.fromDate = fromDate;
    this.toDate = toDate;
    this.sortingPriority = sortingPriority;
  }

  public long getCode() {
    return code;
  }

  public void setCode(long code) {
    this.code = code;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getCodeListCode() {
    return codeListCode;
  }

  public void setCodeListCode(String codeListCode) {
    this.codeListCode = codeListCode;
  }

  public String getDisplayValue() {
    return displayValue;
  }

  public void setDisplayValue(String displayValue) {
    this.displayValue = displayValue;
  }

  public String getLongDescription() {
    return longDescription;
  }

  public void setLongDescription(String longDescription) {
    this.longDescription = longDescription;
  }

  public LocalDate getFromDate() {
    return fromDate;
  }

  public void setFromDate(LocalDate fromDate) {
    this.fromDate = fromDate;
  }

  public LocalDate getToDate() {
    return toDate;
  }

  public void setToDate(LocalDate toDate) {
    this.toDate = toDate;
  }

  public Integer getSortingPriority() {
    return sortingPriority;
  }

  public void setSortingPriority(Integer sortingPriority) {
    this.sortingPriority = sortingPriority;
  }

  @Override
  public String toString() {
    return "Gerimedica{" +
        "code=" + code +
        ", source='" + source + '\'' +
        ", codeListCode='" + codeListCode + '\'' +
        ", displayValue='" + displayValue + '\'' +
        ", longDescription='" + longDescription + '\'' +
        ", fromDate=" + fromDate +
        ", toDate=" + toDate +
        ", sortingPriority=" + sortingPriority +
        '}';
  }
}
