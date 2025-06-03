package com.yadavmahesh.restapi.service;

import com.yadavmahesh.restapi.dto.ExpenseDTO;
import com.yadavmahesh.restapi.io.ExpenseResponse;

import java.util.List;
/*Service interface for Expence Module
*@Author Mahesh Yadav
*
 */

public interface ExpenseService {

    /**
     * it will fetch the Expenses From Database
     * @return list
     */

    List<ExpenseDTO> getAllExpenses();

    /*
     *It will fetch The Single  expences details from database
     * @param expenseid
     * @return ExpenseDTO
     *
     */
    ExpenseDTO getExpenseByExpenseId(String expenseId);

    /**
     *It will delete the expences  from database
     * @param expenseId
     * @return void
     *
     **/
    void deleteExpenseByExpenseId(String expenseId);

    /**
     *It will save the expenses  details to database
     * @param expenseDTO
     * @return ExpenseDTO
     **/
    ExpenseDTO saveExpenseseDetails(ExpenseDTO expenseDTO);
}
