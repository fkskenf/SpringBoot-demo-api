package com.blog.demo.controller.testRequest;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TransferSalaryRequest {

    @NotBlank
    private String account;
    @Positive
    private int companyNo;

    private int totalAmt;

    @NotNull
    @Valid
    private List<Accounts> deptAccounts;

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Accounts {
        @Positive
        private int employeeNo;
        @NotBlank
        private String bankCode;

        private int transferAmt;

    }
}
