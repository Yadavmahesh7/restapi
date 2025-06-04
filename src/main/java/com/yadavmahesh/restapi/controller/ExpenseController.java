package com.yadavmahesh.restapi.controller;

import com.yadavmahesh.restapi.dto.ExpenseDTO;
import com.yadavmahesh.restapi.io.ExpenseRequest;
import com.yadavmahesh.restapi.io.ExpenseResponse;
import com.yadavmahesh.restapi.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/*
 *This is Controller Class for Expence module
 * @author Mahesh Yadav
 *
 * */

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@Validated
public class ExpenseController {
    private final ExpenseService expenseService;
    private final ModelMapper modelMapper;
    /*
    *It will fetch  expences from database
    * @return list
    *
     */
    @GetMapping("/expenses")
   public List<ExpenseResponse>getExpenses()
    {
        List<ExpenseDTO>list=expenseService.getAllExpenses();
        List<ExpenseResponse>response=list.stream().map(expenseDTO -> mapToExpenseResponse(expenseDTO)).collect(Collectors.toList());

          return response;
    }

    /*
     *It will fetch the single expences from database
     * @param expenseId
     * @return ExpenseResponse
     *
     */
    @GetMapping("/expenses/{expenseId}")
    public ExpenseResponse getExpenseById(@PathVariable String expenseId)

    {
        ExpenseDTO expenseDTO=expenseService.getExpenseByExpenseId(expenseId);

        return mapToExpenseResponse(expenseDTO);

    }
    @ResponseStatus()
    @DeleteMapping("/expenses/{expenseId}")
   public void deleteExpenseByExpenseById(@PathVariable String expenseId){
    expenseService.deleteExpenseByExpenseId(expenseId);


    }
    /*
    *It will save the expenses  details to database
    * @param expenseRequest
    * return ExpenseResponse
     */
    @ResponseStatus(HttpStatus.CREATED)
      @PostMapping("/expenses")
    public ExpenseResponse saveExpensesDetails(@Valid @RequestBody ExpenseRequest expenseRequest){
        ExpenseDTO expenseDTO=mapToExpenseDTO(expenseRequest);
        expenseDTO=expenseService.saveExpenseseDetails(expenseDTO);
        return mapToExpenseResponse(expenseDTO);
    }

    /**
     *It will Update the expenses  details to database
     * @param updateRequest
     * @param expenseId
     * @return ExpenseDTO
     **/
    @PutMapping("/expenses/{expenseId}")
    public ExpenseResponse updateExpenseDetails(@RequestBody ExpenseRequest updateRequest, @PathVariable String expenseId){
        ExpenseDTO updatedExpenseDTO=mapToExpenseDTO(updateRequest);
        updatedExpenseDTO=expenseService.updateExpenseDetails(updatedExpenseDTO,expenseId);
        return mapToExpenseResponse(updatedExpenseDTO);
    }


    /*
     *Mapper Method to map values from Expense Request to expense dto
     * @param expenseRequest
     * return ExpenseDTO
     */
    private ExpenseDTO mapToExpenseDTO(@Valid ExpenseRequest expenseRequest) {
        return modelMapper.map(expenseRequest, ExpenseDTO.class);
    }

    /*
    *Mapper Method for Converting expense dto object to expense responce
    * @param expenseDTO
    * @return ExpenseResponse
     */
    private ExpenseResponse mapToExpenseResponse(ExpenseDTO expenseDTO)
    {
        return modelMapper.map(expenseDTO, ExpenseResponse.class);
    }
}
