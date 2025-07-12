package com.example.spring_craft_ms.exception.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DataNotFoundException extends Exception {
    private static final long serialVersionUID = 4L;

    private final String errorField;
    private final String errorMessage;
}
