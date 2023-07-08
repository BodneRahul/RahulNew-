package com.myblog.blogapp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResponceNotFoundException extends RuntimeException{

    private String resource;
    private String fieldName;
    private long fieldValue;

    public String getResource() {
        return resource;
    }

    public String getFieldName() {
        return fieldName;
    }

    public long getFieldValue() {
        return fieldValue;
    }

    public ResponceNotFoundException(String resource, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : '%s'", resource, fieldName, fieldValue));
        this.resource = resource;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }


}

