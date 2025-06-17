package com.example.learnhub.exceptions.details;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ErrorDetails {

    private String title;
    private int status;
    private String detail;
    private long timestamp;

}

