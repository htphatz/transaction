package com.example.transaction.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentRequest {
    private String name;
    private String address;
}
