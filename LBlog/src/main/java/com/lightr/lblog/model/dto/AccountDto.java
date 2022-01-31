package com.lightr.lblog.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountDto implements Serializable {
    private int userId;
    private String name;
    private String token;
}
