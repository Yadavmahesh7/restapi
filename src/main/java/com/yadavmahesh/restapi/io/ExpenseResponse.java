package com.yadavmahesh.restapi.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenceResponse {
    private String expenseid;

    private String name;

    private Date date;

    private String note;

    private String category;

    private BigDecimal amount;
}
