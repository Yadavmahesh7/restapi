package com.yadavmahesh.restapi.io;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.aspectj.bridge.IMessage;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseRequest {

    @NotBlank(message = "Expenses Name is Required")
    @Size(min = 3,message = "Expenses Name should be at list 3 Charcters")
    private String name;

    @NotNull(message = "Expenses Date is Required")
    private Date date;

    private String note;

    @NotBlank(message = "Expenses Category is Required")
    private String category;

    @NotNull(message = "Expenses Amount is Required")
    private BigDecimal amount;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
