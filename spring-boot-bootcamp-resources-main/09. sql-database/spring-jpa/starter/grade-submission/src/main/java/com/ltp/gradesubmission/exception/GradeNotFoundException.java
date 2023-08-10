package com.ltp.gradesubmission.exception;

public class GradeNotFoundException extends RuntimeException{
    public GradeNotFoundException(Long studentId, Long courseId) {
        super("The grade with studentId '" + studentId + " and courseId" + courseId + "' does not exist in our record");
    }
}
