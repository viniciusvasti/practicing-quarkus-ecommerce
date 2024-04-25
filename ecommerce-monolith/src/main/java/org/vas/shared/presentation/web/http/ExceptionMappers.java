package org.vas.shared.presentation.web.http;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.Status;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.vas.shared.presentation.dtos.ErrorResponseDTO;
import jakarta.ws.rs.ClientErrorException;

class ExceptionMappers {

    @ServerExceptionMapper
    public RestResponse<ErrorResponseDTO> mapClientErrorException(ClientErrorException ex) {
        return RestResponse.status(Status.fromStatusCode(ex.getResponse().getStatus()),
                new ErrorResponseDTO(ex.getMessage()));
    }

    @ServerExceptionMapper
    public RestResponse<ErrorResponseDTO> mapIllegalArgumentException(IllegalArgumentException ex) {
        return RestResponse.status(Status.BAD_REQUEST, new ErrorResponseDTO(ex.getMessage()));
    }

}
