package org.example.com.warehousemanager.model.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    @NotBlank
    private final int status;
    @NotBlank
    private final String error;
    @NotBlank
    private final String message;
}
