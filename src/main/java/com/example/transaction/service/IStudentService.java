package com.example.transaction.service;

import com.example.transaction.dto.StudentRequest;
import com.example.transaction.model.Student;

public interface IStudentService {
    Student createStudent(StudentRequest request);
    Student findById(String id);
    Student updateStudentRequired(String id);
    Student updateStudentRequiredNew(String id);
    Student updateStudentNested(String id);
    void deleteStudent(String id);
}
