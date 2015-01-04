package net.predictblty.model;

import java.util.List;
import java.util.Map;

/**
 * Created by berkgokden on 1/4/15.
 */
public class Query {
    private String state;
    private String idField;
    private Map<String, Object> options;
    private List<Map<String, Object>> elements;

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

    public List<Map<String, Object>> getElements() {
        return elements;
    }

    public void setElements(List<Map<String, Object>> elements) {
        this.elements = elements;
    }

    public String getIdField() {
        return idField;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }
}
