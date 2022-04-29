package br.com.ecommerce.superstore.produtos.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotFoundExceptionDetails {
    private LocalDateTime timestamp;
    private Integer status;
    private String title;
    private String details;
    private String message;

}
