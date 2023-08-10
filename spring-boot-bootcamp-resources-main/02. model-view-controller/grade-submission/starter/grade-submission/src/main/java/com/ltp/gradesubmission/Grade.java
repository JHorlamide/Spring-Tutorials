package com.ltp.gradesubmission;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

public class Grade {

  @NotBlank(message = "Student name is required")
  private String studentName;

  @NotBlank(message = "Please provide the subject")
  private String subject;

  @ValidScore(message = "Score must be a letter grade")
  private String score;

  private String id;

  public Grade(String studentName, String subject, String score) {
    this.studentName = studentName;
    this.subject = subject;
    this.score = score;
    this.id = UUID.randomUUID().toString();
  }

  public Grade() {
    this.id = UUID.randomUUID().toString();
  }

  public String getStudentName() {
    return this.studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public String getSubject() {
    return this.subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getScore() {
    return this.score;
  }

  public void setScore(String score) {
    this.score = score;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
