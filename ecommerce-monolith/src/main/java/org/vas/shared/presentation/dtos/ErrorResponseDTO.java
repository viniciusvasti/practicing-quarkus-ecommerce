package org.vas.shared.presentation.dtos;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "ErrorResponse", description = "A reponse object for HTTP errors")
public record ErrorResponseDTO(String message) {
}