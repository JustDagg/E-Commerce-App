package com.dagg.dto;

import lombok.Data;

/**
 * @author Dagg
 * @created 27/10/2023
 */

@Data
public class ResponseOrderDTO {

    private float amount;

    private int invoiceNumber;

    private String date;

    private String OrderDescription;

//    private int orderId;

}
