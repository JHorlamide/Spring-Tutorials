package com.ltp.gradesubmission.exception;

public class StudentNotEnrolledException extends RuntimeException{
    public StudentNotEnrolledException(Long studentId, Long courseId) {
        super("The grade with studentId '" + studentId + " and courseId" + courseId + "' does not exist in our record");
    }
}
