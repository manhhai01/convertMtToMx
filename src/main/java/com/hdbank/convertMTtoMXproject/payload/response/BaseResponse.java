package com.hdbank.convertMTtoMXproject.payload.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse {
    private int status;
    private String message;
    private Object data;
}
