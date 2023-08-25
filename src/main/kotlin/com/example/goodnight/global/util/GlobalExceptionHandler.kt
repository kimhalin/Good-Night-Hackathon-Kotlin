package com.example.goodnight.global.util

import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = [HttpMessageNotReadableException::class])
    @ResponseBody
    fun dtoTypeMissMatchException(exception: HttpMessageNotReadableException): ResponseEntity<ExceptionDto> {
        val msg = when (val causeException = exception.cause) {
            is InvalidFormatException -> {
                "입력 받은 ${causeException.value} 를 ${causeException.targetType} 으로 변환중 에러가 발생했습니다."
            }
            is MismatchedInputException -> {
                "Parameter is missing"
            }
            else -> "요청을 역직렬화 하는과정에서 예외가 발생했습니다."
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ExceptionDto(message = msg, value = null)
        )
    }
}

data class ExceptionDto(
    val message: String = "default Msg",
    val field: String = "default field",
    val value: Any?,
)