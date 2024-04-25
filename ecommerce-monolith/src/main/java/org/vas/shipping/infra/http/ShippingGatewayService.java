package org.vas.shipping.infra.http;

import java.util.Map;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.vas.order.core.domain.Order;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/http")
@RegisterRestClient(configKey = "shipping-gateway")
public interface ShippingGatewayService {
    @POST
    @Path("/201")
    Map<String, String> ship(Order order);
}
