package com.example.transaction.service.impl;

import com.example.transaction.dto.StudentRequest;
import com.example.transaction.exception.FakeException;
import com.example.transaction.exception.NotFoundException;
import com.example.transaction.model.Student;
import com.example.transaction.repository.StudentRepository;
import com.example.transaction.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(StudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setAddress(request.getAddress());
        return studentRepository.save(student);
    }

    @Override
    public Student findById(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with id " + id + " not found"));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Student updateStudentRequired(String id) {
        Student existingStudent = findById(id);
        existingStudent.setName("required");
        updateStudentRequiredNew(id);
        throw new FakeException();
//        return studentRepository.save(existingStudent);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Student updateStudentRequiredNew(String id) {
        Student existingStudent = findById(id);
        existingStudent.setName("required_new");
        return studentRepository.save(existingStudent);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public Student updateStudentNested(String id) {
        Student existingStudent = findById(id);
        existingStudent.setName("nested");
        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(String id) {
        Student existingStudent = findById(id);
        studentRepository.delete(existingStudent);
    }
}
