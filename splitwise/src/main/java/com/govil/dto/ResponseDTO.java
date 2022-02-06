package com.govil.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO<T> {

    public StatusCode statusCode;
    T data;

}
