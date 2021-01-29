package com.spacerockman.eric.Utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response {
    private boolean isOk;
    private int status;
    private String message;
    private Object data;
}
