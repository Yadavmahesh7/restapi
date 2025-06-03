package com.yadavmahesh.restapi.repository;

import com.yadavmahesh.restapi.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for EXpense REsource
 * @author Mahesh Yadav
 */
public interface ExpenseRepository extends JpaRepository<ExpenseEntity,Long> {
    /**
     * It will find the single expense from database*
     * @param expenseId
     * @return Optional
     */

    Optional<ExpenseEntity> findByExpenseId(String expenseId);

    List<ExpenseEntity> id(long id);
}
