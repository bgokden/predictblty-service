package net.predictblty.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import net.predictblty.machinelearning.mlcommon.Classification;

import java.util.Collection;
import java.util.Map;

/**
 * Created by berkgokden on 1/22/15.
 */
@JsonInclude(Include.NON_NULL)
public class Result {
    private String state;
    private String description;
    private Map<String, Object> options;
    private Collection<Classification> classifications;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Object> options) {
        this.options = options;
    }

    public Collection<Classification> getClassifications() {
        return classifications;
    }

    public void setClassifications(Collection<Classification> classifications) {
        this.classifications = classifications;
    }
}
