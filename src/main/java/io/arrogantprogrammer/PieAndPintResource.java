package io.arrogantprogrammer;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/pieandpint")
@Produces(MediaType.APPLICATION_JSON)
public class PieAndPintResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PieAndPintResource.class);

    @Inject
    @RestClient
    ReactivePieClient reactivePieClient;

    @Inject
    @RestClient
    ReactivePintClient reactivePintClient;

    @GET
    public Uni<String> randomPieAndPint() {
        return Uni.combine().all().unis(
                randomPie(),
                randomBeer()
        ).asTuple().map(t -> {
            return String.format("%s served with a %s", t.getItem1(), t.getItem2().name + " " + t.getItem2().tagline);
        });
    }

    @GET @Path("/pie")
    public Uni<String> randomPie() {

        return Uni.<String>combine()
                .all()
                .unis(
                        reactivePieClient.getProtein().onItem().transform(proteins -> {
                            return proteins.get(new Random().nextInt(proteins.size()));
                        }),
                        reactivePieClient.getVeg().onItem().transform(vegetables -> {
                            return vegetables.get(new Random().nextInt(vegetables.size()));
                        }),
                        reactivePieClient.getFilling().onItem().transform(fillings -> {
                            return fillings.get(new Random().nextInt(fillings.size()));
                        })                        )
                .asTuple()
                .map(t -> {
                    return String.format("%s and %s with %s pie", t.getItem1(), t.getItem2(), t.getItem3());
                });
    }

    @GET@Path("/pint")
    public Uni<Beer> randomBeer() {

        return Multi.createBy()
                .repeating()
                .uni(AtomicInteger::new, page ->
                        reactivePintClient.getBeersPage(page.incrementAndGet())
                                .onFailure()
                                .recoverWithUni((Uni.createFrom().item(Collections.emptyList())))
                )
                .until(List::isEmpty)
                .onItem()
                .disjoint()
                .collect()
                .asList()
                .onItem()
                .transform(beerList -> {
                    return(Beer) beerList.get(new Random().nextInt(beerList.size()));
                });
    }


}
