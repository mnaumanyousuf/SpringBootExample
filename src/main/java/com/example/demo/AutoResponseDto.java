package com.example.demo;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class AutoResponseDto <T>{

    public AutoResponseDto()
    {
        Warnings = new ArrayList<>();
        Errors = new ArrayList<String>();
        Success = true;
        StatusCode = HttpStatus.OK;
    }

    public AutoResponseDto(boolean success)
    {
        Warnings = new ArrayList<String>();
        Errors = new ArrayList<String>();
        Success = success;
        StatusCode = HttpStatus.OK;
    }

    public AutoResponseDto(boolean success, String message)
    {
        Warnings = new ArrayList<String>();
        Errors = new ArrayList<String>();
        Success = success;
        Message = message;
        StatusCode = HttpStatus.OK;
    }

    public boolean Success;
    public T Result;
    public String Message;
    public List<String> Errors;
    public List<String> Warnings;
    public HttpStatus StatusCode;
    public String Exception;



    public void AddWarning(String content)
    {
        Warnings.add(content);
    }

    public void AddError(List<String> errorList)
    {
        Errors.addAll(errorList);
        StatusCode = HttpStatus.BAD_REQUEST;
        Success = false;
    }

    public void AddMessage(String content)
    {
        Message = content;
    }

    public void SetStatusCode(HttpStatus code) {
        StatusCode = code;
    }
}
