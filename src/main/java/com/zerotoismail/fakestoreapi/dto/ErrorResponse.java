package com.zerotoismail.fakestoreapi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorResponse {
    private int statusCode;
    private String message;
    private long timestamp;
}
