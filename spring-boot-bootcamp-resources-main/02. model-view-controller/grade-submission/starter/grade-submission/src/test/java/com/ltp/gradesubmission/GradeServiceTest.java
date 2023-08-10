package com.ltp.gradesubmission;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ltp.gradesubmission.repository.GradeRepository;
import com.ltp.gradesubmission.service.GradeService;

@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {

  @Mock
  private GradeRepository gradeRepository;

  @InjectMocks
  private GradeService gradeService;

  @Test
  public void getGradesFromRepoTest() {
    // Arrange
    when(gradeRepository.getGrades()).thenReturn(Arrays.asList(
      new Grade("Olamide", "Chemistry", "A+"),
      new Grade("Jubril", "Physics", "B+")
    ));

    // Act: Call the method you want to test
    List<Grade> result = gradeService.getGrades();

    // Assert
    assertEquals("Olamide", result.get(0).getStudentName());
    assertEquals("Physics", result.get(1).getSubject());
  }

  @Test
  public void getGradeIndexTest() {
    // Arrange
    Grade grade = new Grade("Olamide", "Chemistry", "A+");
    when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
    when(gradeRepository.getGrade(0)).thenReturn(grade);

    // Act: Call the method you want to test
    int validGrade = gradeService.getGradeIndex(grade.getId());
    int invalidGrade = gradeService.getGradeIndex("1234");

    // Assert
    assertEquals(0, validGrade);
    assertEquals(Constants.NOT_FOUND, invalidGrade);
  }

  @Test
  public void getGradeByIdTest() {
    // Arrange
    Grade grade = new Grade("Olamide", "Chemistry", "A+");
    when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
    when(gradeRepository.getGrade(0)).thenReturn(grade);

    // Act
    Grade validGrade = gradeService.getGradeById(grade.getId());

    // Assert
    assertEquals(grade, validGrade);
  }

  @Test
  public void addGradeTest() {
    // Arrange
    Grade grade = new Grade("Olamide", "Chemistry", "A+");
    when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
    when(gradeRepository.getGrade(0)).thenReturn(grade);

    // Act
    Grade newGrade = new Grade("Jubril", "Arithmancy", "C+");
    gradeService.submitGrade(newGrade);

    // Assert
    verify(gradeRepository, times(1)).addGrade(newGrade);
  }

  @Test
  public void updateGradeTest() {
    // Arrange
    Grade grade = new Grade("Olamide", "Chemistry", "A+");
    when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
    when(gradeRepository.getGrade(0)).thenReturn(grade);

    //Act
    grade.setScore("B+");
    gradeService.submitGrade(grade);

    // Assert
    verify(gradeRepository, times(1)).updateGrade(0, grade);
  }
}
