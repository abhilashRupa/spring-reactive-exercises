package com.nuvyra.exercise.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {

    private int errorCode;
    private String message;

}
