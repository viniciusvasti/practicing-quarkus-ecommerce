package org.vas.payment.infra.http;

import java.util.Map;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.vas.payment.core.domain.Payment;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/http")
@RegisterRestClient(configKey = "payment-gateway")
public interface PaymentGatewayService {
    @POST
    @Path("/201")
    Map<String, String> charge(Payment payment);
}
