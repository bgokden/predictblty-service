package net.predictblty.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import net.predictblty.service.model.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * Created by berkgokden on 1/4/15.
 */
@Path("json")
public class JsonResource {

    private @Autowired HazelcastInstance hazelcastInstance;

    @POST @Path("{domain}/lookup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Query lookup(@PathParam("domain") String domain, Query query) {
        Query q = new Query();
        q.setState("Got it!");
        List<Map<String, Object>> elemets = query.getElements();
        IMap<String, Map<String, Object>>  map = hazelcastInstance.getMap(domain+"."+query.getCollection());
        for (int i = 0; i < elemets.size(); i++) {
            Map<String, Object> element= map.get(String.valueOf(elemets.get(i).get(query.getIdField())));
            elemets.set(i, element);
        }
        q.setElements(elemets);
        return q;
    }

    @POST @Path("{domain}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Query query(@PathParam("domain") String domain, Query query) {
        Query q = new Query();
        q.setState("Got it!:"+query.getState());
        List<Map<String, Object>> elemets = query.getElements();
        IMap<String, Map<String, Object>>  map = hazelcastInstance.getMap(domain+"."+query.getCollection());
        for (Map<String, Object> elemet : elemets) {
           map.put(String.valueOf(elemet.get(query.getIdField())), elemet );
        }
        return q;
    }
}
