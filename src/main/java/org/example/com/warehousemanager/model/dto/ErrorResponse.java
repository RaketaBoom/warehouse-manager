package org.example.com.warehousemanager.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    @NotBlank
    @Schema(description = "HTTP status code")
    private final int status;
    @NotBlank
    @Schema(description = "Error title")
    private final String error;
    @NotBlank
    @Schema(description = "Detailed error message")
    private final String message;
}
