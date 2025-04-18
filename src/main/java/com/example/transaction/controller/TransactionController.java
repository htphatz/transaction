package com.example.transaction.controller;

import com.example.transaction.dto.StudentRequest;
import com.example.transaction.model.Student;
import com.example.transaction.service.impl.PlatformTransactionManagerService;
import com.example.transaction.service.impl.StudentService;
import com.example.transaction.service.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private PlatformTransactionManagerService platformTransactionManagerService;

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
        return ResponseEntity.ok(transactionService.testRequired(id));
    }

    @PutMapping("/required-new/{id}")
    public ResponseEntity<Student> updateStudentRequiredNew(@PathVariable("id") String id) {
        return ResponseEntity.ok(transactionService.testRequiredNew(id));
    }

    @PutMapping("/nested/{id1}/{id2}")
    public ResponseEntity<Void> updateStudentNested(@PathVariable("id1") String id1, @PathVariable("id2") String id2) {
        transactionService.testRequiredNested(id1, id2);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/platform-transaction-manager")
    public ResponseEntity<Void> testPlatformTransactionManager() {
        platformTransactionManagerService.testCustomTransaction();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id1}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") String id)  {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Delete success");
    }
}
