package io.arrogantprogrammer;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@RegisterRestClient(configKey = "reactive-pies")
public interface ReactivePieClient {

    @GET
    @Path("/veg")
    Uni<List<String>> getVeg();

    @GET
    @Path("/protein")
    Uni<List<String>> getProtein();

    @GET
    @Path("/filling")
    Uni<List<String>> getFilling();
}
