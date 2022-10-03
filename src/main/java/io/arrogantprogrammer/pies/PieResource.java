package io.arrogantprogrammer.pies;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/")
public class PieResource {

        List<String> vegList = new ArrayList<String>(){{
            add("cabbage");
            add("onion");
            add("leek");
            add("potato");
            add("butternut squash");
            add("mushroom");
            add("carrots");
        }};

        List<String> proteinList = new ArrayList<>(){{
            add("steak");
            add("steak and ale");
            add("fish");
            add("chorizo");
        }};

        List<String> fillingList = new ArrayList<>(){{
            add("ale");
            add("stilton");
            add("cream");
        }};

        @GET
        @Path("/veg")
        public Response allVeg() {

            return Response.ok().entity(vegList).build();
        }

        @GET
        @Path("/protein")
        public Response allProteins() {
            return Response.ok().entity(proteinList).build();

        }

        @GET
        @Path("/filling")
        public Response allFillings() {
            return Response.ok().entity(fillingList).build();

        }
}
