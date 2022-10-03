package io.arrogantprogrammer;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@RegisterRestClient(configKey = "reactive-pints")
public interface ReactivePintClient {

    @GET
    @Path("/beers")
    Uni<List<Beer>> getBeersPage(@QueryParam("page") int page);

    @GET
    @Path("/beers")
    Uni<List<Beer>> getBeers();
}
