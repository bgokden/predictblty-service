package net.predictblty.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import net.predictblty.machinelearning.mlalgorithm.MLAlgorithm;
import net.predictblty.machinelearning.mlalgorithm.mlalgorithmimpl.UserBasedCollaborativeFilteringRecommendationAlgorithm;
import net.predictblty.machinelearning.mlcommon.Classification;
import net.predictblty.machinelearning.mlcommon.UnclassifiedFeature;
import net.predictblty.service.model.Query;
import net.predictblty.service.model.Result;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by berkgokden on 1/4/15.
 */
@Path("domains")
public class DomainResource {

    private @Autowired HazelcastInstance hazelcastInstance;

    @GET @Path("{domain}/{collection}")
//    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN, MediaType.TEXT_PLAIN})
    public Result get(@PathParam("domain") String domain
            , @PathParam("collection") String collection
            , @DefaultValue("1") @QueryParam("id") String id
            , @DefaultValue("recommend") @QueryParam("method") String method) {
        Result result = new Result();
        result.setState("Got it!");
        IMap<String, Map<String, Object>>  domains = hazelcastInstance.getMap("domains");
        Map<String, Object> domainDetail = domains.get(domain);
        if (domainDetail == null) {
            domainDetail = generateDomain(domain);
            domains.put(domain, domainDetail);
        }

        MLAlgorithm mlAlgorithm = new UserBasedCollaborativeFilteringRecommendationAlgorithm(hazelcastInstance);
        mlAlgorithm.setPrefix(domain+"/"+collection);
        IMap<String, Map<String, Serializable>>  collectionMap = hazelcastInstance.getMap(domain+"/"+collection);
        Map<String, Serializable> featureMap = collectionMap.get(id);
        final UnclassifiedFeature unclassifiedFeature = new UnclassifiedFeature();
        unclassifiedFeature.setFeatureMap(featureMap);
        List<UnclassifiedFeature> predictList = new ArrayList<UnclassifiedFeature>();
        predictList.add(unclassifiedFeature);

        try {
            Collection<Classification> classificationList = mlAlgorithm.predict(predictList);
            result.setClassifications(classificationList);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

    @POST @Path("{domain}/{collection}")
//    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Result post(@PathParam("domain") String domain
            , @PathParam("collection") String collection
            , Query query) {
        Result q = new Result();
        q.setState("Got it!:"+query.getState());
        List<Map<String, Serializable>> elemets = query.getElements();
        IMap<String, Map<String, Serializable>>  map = hazelcastInstance.getMap(domain+"/"+collection);
        for (Map<String, Serializable> elemet : elemets) {
           map.put(String.valueOf(elemet.get(query.getIdField())), elemet );
        }
        return q;
    }

    public boolean hasWriteAccessTo(String domain, String collection) {
        return true;
    }

    public boolean hasReadAccessTo(String domain, String collection) {
        return true;
    }

    private Map<String, Object> generateDomain(String domain) {
        return null;
    }
}
