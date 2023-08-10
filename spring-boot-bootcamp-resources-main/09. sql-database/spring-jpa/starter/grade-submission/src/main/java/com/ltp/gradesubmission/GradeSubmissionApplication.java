package com.ltp.gradesubmission;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.repository.CourseRepository;
import com.ltp.gradesubmission.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class GradeSubmissionApplication implements CommandLineRunner {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    public static void main(String[] args) {
        SpringApplication.run(GradeSubmissionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Student[] students = new Student[]{
                new Student("Olamide Jubril", LocalDate.parse("1990-07-31")),
                new Student("Harry Potter", LocalDate.parse("1990-07-31")),
                new Student("Henry Granger", LocalDate.parse("1990-07-31")),
        };

        for (Student student : students) {
            studentRepository.save(student);
        }

        Course[] courses = new Course[]{
                new Course("Java Programming", "Java-code-3DAKE", "You learning how to program in Java"),
                new Course("Data Design", "DB-code-3RAKE", "You learning how to design database schemas"),
                new Course("Version control", "Git-code-F3AKE", "You learning how to push code to github"),
        };

        for (Course course : courses) {
            courseRepository.save(course);
        }
    }
}
