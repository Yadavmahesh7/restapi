package com.yadavmahesh.restapi.service.impl;

import com.yadavmahesh.restapi.dto.ExpenseDTO;
import com.yadavmahesh.restapi.entity.ExpenseEntity;
import com.yadavmahesh.restapi.exceptions.ResourceNotFoundExeption;
import com.yadavmahesh.restapi.io.ExpenseResponse;
import com.yadavmahesh.restapi.repository.ExpenseRepository;
import com.yadavmahesh.restapi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implement for Expense Module
 * @Author Mahesh Yadav
 */

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ModelMapper modelMapper;
    /**
     * it will fetch the Expenses From Database
     * @return list
     */
    @Override
    public List<ExpenseDTO> getAllExpenses() {
        List<ExpenseEntity>list=expenseRepository.findAll();
        List<ExpenseDTO>listOfExpense=list.stream().map(expenseEntity -> mapToExpenseDTO(expenseEntity)).collect(Collectors.toList());
        return listOfExpense ;
    }

    /**
     * it will fetch the Single Expenses details  From Database
     * @param expenseId
     * @return ExpenseDTO
     */

    @Override
    public ExpenseDTO getExpenseByExpenseId(String expenseId) {
        ExpenseEntity optionalExpense=expenseRepository.findByExpenseId(expenseId)
                .orElseThrow(()-> new ResourceNotFoundExeption("Expense not found for the expense id"+expenseId));

        return mapToExpenseDTO(optionalExpense);
    }

    /*
     *It will delete the expences  from database
     * @param expenseid
     * @return void
     *
     */

    @Override
    public void deleteExpenseByExpenseId(String expenseId) {
        ExpenseEntity expenseEntity=expenseRepository.findByExpenseId(expenseId)
                .orElseThrow(() -> new ResourceNotFoundExeption ("Expense not found for expense id"+expenseId));
        expenseRepository.delete(expenseEntity);
        }

    /**
     *It will save the expenses  details to database
     * @param expenseDTO
     * return ExpenseDTO
     **/
    @Override
    public ExpenseDTO saveExpenseseDetails(ExpenseDTO expenseDTO) {
        ExpenseEntity newExpenseseEntity=mapToExpensesEntity(expenseDTO);
        newExpenseseEntity.setExpenseId(UUID.randomUUID().toString());
        newExpenseseEntity=expenseRepository.save(newExpenseseEntity);
        return mapToExpenseDTO(newExpenseseEntity);
    }

    @Override
    public ExpenseDTO updateExpenseDetails(ExpenseDTO expenseDTO, String expenseId) {
        ExpenseEntity existingExpense=getExpenseEntity(expenseId);
        ExpenseEntity updatedExpenseEntity=mapToExpensesEntity(expenseDTO);
        updatedExpenseEntity.setId(existingExpense.getId());
        updatedExpenseEntity.setExpenseId(existingExpense.getExpenseId());
        updatedExpenseEntity.setCategory(existingExpense.getCategory());
        updatedExpenseEntity.setUpdatedAt(existingExpense.getUpdatedAt());
        updatedExpenseEntity.setCreatedAt(existingExpense.getCreatedAt());
        updatedExpenseEntity=expenseRepository.save(updatedExpenseEntity);
        return mapToExpenseDTO(updatedExpenseEntity);
    }


    /**
     * Fetch an expense entity by ID.
     * @param expenseId Expense ID
     * @return ExpenseEntity
     */
    private ExpenseEntity getExpenseEntity(String expenseId) {
        return expenseRepository.findByExpenseId(expenseId)
                .orElseThrow(() -> new ResourceNotFoundExeption ("Expense not found for expense id"+expenseId));
    }


    /**
     * Mapper Method to Map values from Expense dto to Expense entity
     * @param expenseDTO
     * @return ExpenseEntity
     */
    private ExpenseEntity mapToExpensesEntity(ExpenseDTO expenseDTO) {
        return modelMapper.map(expenseDTO, ExpenseEntity.class);
    }

    /**
     * Mapper Method to Convert Expense entity to expense DTO
     * @param expenseEntity
     * @return ExpenseDTO
     */
    private ExpenseDTO mapToExpenseDTO(ExpenseEntity expenseEntity) {
        return modelMapper.map(expenseEntity, ExpenseDTO.class);
    }

}

