package com.lightr.lblog.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GlobalErrorDto implements Serializable {
    private Object msg;

}
