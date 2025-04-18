package com.example.transaction.service.impl;

import com.example.transaction.exception.FakeException;
import com.example.transaction.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private StudentService studentService;

    /**
     * Tests the REQUIRED propagation level by updating a student's name.
     *
     * @param id the unique identifier of the student to be updated
     * @return the updated Student object after being processed by the service
     */
    public Student testRequired(String id) {
        return studentService.updateStudentRequired(id);
    }

    /**
     * Tests the REQUIRED_NEW propagation level by updating a student's name.
     *
     * @param id the unique identifier of the student to be updated
     * @return the updated Student object after being processed by the service
     */
    public Student testRequiredNew(String id) {
        Student requiredStudent = studentService.updateStudentRequired(id);
        Student requiredNewStudent = studentService.updateStudentRequiredNew(id);
        throw new FakeException();
    }

    /**
     * Tests the NESTED propagation level by updating a student's name.
     *
     * @param id1, id2 the unique identifier of the students to be updated
     */
    public void testRequiredNested(String id1, String id2) {
        Student requiredNewStudent = studentService.updateStudentRequiredNew(id1);
        Student nestedStudent = studentService.updateStudentNested(id2);
    }
}
