package com.example.transaction.controller;

import com.example.transaction.dto.StudentRequest;
import com.example.transaction.model.Student;
import com.example.transaction.service.impl.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody StudentRequest request) {
        return ResponseEntity.ok(studentService.createStudent(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(studentService.findById(id));
    }

    @PutMapping("/required/{id}")
    public ResponseEntity<Student> updateStudentRequired(@PathVariable("id") String id) {
        return ResponseEntity.ok(studentService.updateStudentRequired(id));
    }

    @PutMapping("/required-new/{id}")
    public ResponseEntity<Student> updateStudentRequiredNew(@PathVariable("id") String id) {
        return ResponseEntity.ok(studentService.updateStudentRequiredNew(id));
    }

    @PutMapping("/nested/{id}")
    public ResponseEntity<Student> updateStudentNested(@PathVariable("id") String id) {
        return ResponseEntity.ok(studentService.updateStudentNested(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> createStudent(@PathVariable("id") String id)  {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Delete success");
    }
}
