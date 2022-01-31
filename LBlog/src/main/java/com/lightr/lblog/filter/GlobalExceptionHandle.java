package com.lightr.lblog.filter;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.lightr.lblog.model.dto.GlobalErrorDto;
import com.lightr.lblog.util.Result;
import org.apache.shiro.ShiroException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandle {


    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException e) {
        return Result.Failure(400, e.getMessage(), null);
    }

    @ExceptionHandler(value = ShiroException.class)
    public Result handler(ShiroException e) {
        return Result.Failure(401, e.getMessage(), null);
    }

    @ExceptionHandler(value = MybatisPlusException.class)
    public Result handler(MybatisPlusException e) {
        return Result.Failure(402, e.getMessage(), null);
    }


    @ExceptionHandler(value = {BindException.class, ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public Result handler(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        GlobalErrorDto errorDto = new GlobalErrorDto();
        errorDto.setMsg(collect.get(0));
        return Result.Failure(401, "请求错误!", errorDto);
    }
}

