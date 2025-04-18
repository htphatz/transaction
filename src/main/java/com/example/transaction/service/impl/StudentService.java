package com.example.transaction.service.impl;

import com.example.transaction.dto.StudentRequest;
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

    /**
     * Creates a new student based on the provided request data.
     *
     * @param request the StudentRequest object containing the details of the student to be created
     * @return the newly created Student object after being saved to the repository
     */
    @Override
    public Student createStudent(StudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setAddress(request.getAddress());
        return studentRepository.save(student);
    }

    /**
     * Retrieves a student by their unique identifier.
     *
     * @param id the unique identifier of the student to be retrieved
     * @return the Student object if found
     * @throws NotFoundException if no student with the given id is found
     */
    @Override
    public Student findById(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with id " + id + " not found"));
    }

    /**
     * Updates an existing student's name to "required" using the REQUIRED propagation level.
     *
     * @param id the unique identifier of the student to be updated
     * @return the updated Student object after being saved to the repository
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Student updateStudentRequired(String id) {
        Student existingStudent = findById(id);
        existingStudent.setName("required");
        return studentRepository.save(existingStudent);
    }

    /**
     * Updates an existing student's name to "required_new" using the REQUIRED_NEW propagation level.
     *
     * @param id the unique identifier of the student to be updated
     * @return the updated Student object after being saved to the repository
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Student updateStudentRequiredNew(String id) {
        Student existingStudent = findById(id);
        existingStudent.setName("required_new");
        return studentRepository.save(existingStudent);
    }

    /**
     * Updates an existing student's name to "nested" using the NESTED propagation level.
     *
     * @param id the unique identifier of the student to be updated
     * @return the updated Student object after being saved to the repository
     */
    @Override
    @Transactional(propagation = Propagation.NESTED)
    public Student updateStudentNested(String id) {
        Student existingStudent = findById(id);
        existingStudent.setName("nested");
        return studentRepository.save(existingStudent);
    }

    /**
     * Deletes a student by their unique identifier.
     *
     * @param id the unique identifier of the student to be deleted
     * @throws NotFoundException if no student with the given id is found
     */
    @Override
    public void deleteStudent(String id) {
        Student existingStudent = findById(id);
        studentRepository.delete(existingStudent);
    }
}
