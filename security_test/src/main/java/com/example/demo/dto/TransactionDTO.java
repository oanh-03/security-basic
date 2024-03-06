package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Phuong Oanh
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO {

    private String transactionId;

    private String sourceAccount; // tk nguồn

    private String destinationAccount; // tk đích

    private float inDebt;

    private float have;

}

