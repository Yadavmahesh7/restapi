package com.yadavmahesh.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseDTO {
        private String expenseId;

        private String name;

        private Date date;

        private String note;

        private String category;

        private BigDecimal amount;

       private Timestamp createdAt;

       private Timestamp updatedAt;
}
