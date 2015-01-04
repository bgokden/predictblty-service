package net.predictblty;

import net.predictblty.model.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by berkgokden on 1/4/15.
 */
@Path("json")
public class JsonResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Query getIt() {
        Query q = new Query();
        q.setState("Got it!");
        return q;
    }

    @POST @Path("{domain}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Query query(@PathParam("domain") String domain, Query query) {
        Query q = new Query();
        q.setState("Got it!:"+query.getState());
        return q;
    }
}
