package com.nohim.employment.management.system.payload.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApiResponse <T>{

    private String responseMessage;

    private T responseData;

    public ApiResponse(String responseMessage, T responseData) {
        this.responseMessage = responseMessage;
        this.responseData = responseData;
    }
}
