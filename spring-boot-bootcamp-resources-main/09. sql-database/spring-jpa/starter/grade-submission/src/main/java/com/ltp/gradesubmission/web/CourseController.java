package com.ltp.gradesubmission.web;

import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ltp.gradesubmission.entity.Course;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {
    CourseService courseService;

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        Course createdCourse = courseService.getCourse(id);
        return ResponseEntity.ok(createdCourse);
    }

    @PostMapping
    public ResponseEntity<Course> saveCourse(@Valid @RequestBody Course course) {
        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getCourses() {
        return new ResponseEntity<>(courseService.getCourses(), HttpStatus.OK);
    }

    @PutMapping("/{courseId}/student/{studentId}")
    public ResponseEntity<Course> enrollStudentToCourse(
        @PathVariable Long courseId, @PathVariable Long studentId) {
        return new ResponseEntity<>(courseService.addStudentToCourse(studentId, courseId), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Set<Student>> getEnrolledStudents(@PathVariable Long studentId) {
        return  new ResponseEntity<Set<Student>>(courseService.getEnrolledStudents(studentId), HttpStatus.OK);
    }
}
