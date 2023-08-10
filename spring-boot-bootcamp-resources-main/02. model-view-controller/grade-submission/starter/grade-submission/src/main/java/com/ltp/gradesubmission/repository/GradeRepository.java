package com.ltp.gradesubmission.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ltp.gradesubmission.Grade;

@Repository
public class GradeRepository {
  private final List<Grade> studentGrads = new ArrayList<>();

  public List<Grade> getGrades() {
    return studentGrads;
  }

  public Grade getGrade(int index) {
    return studentGrads.get(index);
  }

  public void addGrade(Grade grade) {
    studentGrads.add(grade);
  }

  public void updateGrade(int index, Grade grade) {
    studentGrads.set(index, grade);
  }
}
