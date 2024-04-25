package org.vas.shared.presentation.web.http;

import java.util.UUID;
import org.jboss.logging.MDC;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;
import io.quarkus.logging.Log;
import jakarta.ws.rs.container.ContainerRequestContext;

public class RequestFilter {
    @ServerRequestFilter
    public void filter(ContainerRequestContext requestContext) {
        MDC.put("request.id", UUID.randomUUID().toString());
        Log.debugf("Request %s %s", requestContext.getMethod(),
                requestContext.getUriInfo().getPath());
    }
}
