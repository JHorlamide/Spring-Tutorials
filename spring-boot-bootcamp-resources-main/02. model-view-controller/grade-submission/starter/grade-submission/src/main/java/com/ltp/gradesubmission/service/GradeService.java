package com.ltp.gradesubmission.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltp.gradesubmission.Constants;
import com.ltp.gradesubmission.Grade;
import com.ltp.gradesubmission.repository.GradeRepository;

@Service
public class GradeService {

  @Autowired
  GradeRepository gradeRepository;

  public void submitGrade(Grade grade) {
    int index = getGradeIndex(grade.getId());

    if (index == Constants.NOT_FOUND) {
      gradeRepository.addGrade(grade);
    } else {
      gradeRepository.updateGrade(index, grade);
    }
  }

  public List<Grade> getGrades() {
    return gradeRepository.getGrades();
  }

  public Grade getGrade(int index) {
    return gradeRepository.getGrade(index);
  }

  public Grade getGradeById(String gradeId) {
    int index = getGradeIndex(gradeId);
    return index == Constants.NOT_FOUND ? new Grade() : getGrade(index);
  }

  public Integer getGradeIndex(String id) {
    for (int i = 0; i < getGrades().size(); i++) {
      if (getGrade(i).getId().equals(id))
        return i;
    }

    return Constants.NOT_FOUND;
  }
}
