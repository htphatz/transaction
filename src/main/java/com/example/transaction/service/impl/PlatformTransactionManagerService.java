package com.example.transaction.service.impl;

import com.example.transaction.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.UUID;

@Service
@Transactional
public class PlatformTransactionManagerService {
    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    public void testCustomTransaction() {
        // Config TransactionTemplate
        transactionTemplate.setTransactionManager(platformTransactionManager);
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);

        transactionTemplate.executeWithoutResult(status -> {
            Student student = new Student();
            student.setId(UUID.randomUUID().toString());
            student.setName("name");
            student.setAddress("address");

            entityManager.persist(student);
        });
    }
}
