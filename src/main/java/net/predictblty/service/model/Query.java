package net.predictblty.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by berkgokden on 1/4/15.
 */
@JsonInclude(Include.NON_NULL)
public class Query {
    private String state;
    private String request;
    private String idField;
    private String collection;
    private Map<String, Object> options;
    private List<Map<String, Serializable>> elements;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Map<String, Object> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Object> options) {
        this.options = options;
    }

    public List<Map<String, Serializable>> getElements() {
        return elements;
    }

    public void setElements(List<Map<String, Serializable>> elements) {
        this.elements = elements;
    }

    public String getIdField() {
        return idField;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }
}
