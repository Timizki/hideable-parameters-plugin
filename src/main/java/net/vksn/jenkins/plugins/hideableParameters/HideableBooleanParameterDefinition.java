package net.vksn.jenkins.plugins.hideableParameters;

import hudson.Extension;
import hudson.model.*;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import java.util.logging.Logger;

/**
 * Created by timii on 2/3/14.
 */
public class HideableBooleanParameterDefinition extends SimpleParameterDefinition {
    private final static Logger log = Logger.getLogger(HideableBooleanParameterDefinition.class.getName());

    private final boolean hidden;
    private final boolean defaultValue;

    @DataBoundConstructor
    public HideableBooleanParameterDefinition(String name, boolean defaultValue, String description, boolean hidden) {
        super(name,description);
        this.defaultValue = defaultValue;
        this.hidden = hidden;
        log.severe(
                String.format(
                        "Creating new %S value=%S, hidden=%S",
                        HideableBooleanParameterDefinition.class.getName(), new Boolean(this.defaultValue), new Boolean(this.hidden)
                )
        );
    }

    public HideableBooleanParameterDefinition(BooleanParameterDefinition definition, boolean hidden) {
        this(definition.getName(), definition.isDefaultValue(), definition.getDescription(), hidden);
    }

    @Override
    public ParameterValue createValue(StaplerRequest req, JSONObject jsonObject) {
        HideableBooleanParameterValue value = req.bindJSON(HideableBooleanParameterValue.class, jsonObject);
        value.setHidden(hidden);
        return value;
    }

    @Override
    public ParameterValue createValue(String value) {
        log.severe("creating HideableBooleanParameterValue with value "+value);
        return new HideableBooleanParameterValue(getName(), Boolean.valueOf(value), getDescription(), hidden);
    }


    @Override
    public ParameterValue getDefaultParameterValue() {
        log.severe("returning HideableBooleanParameters default value with value " + defaultValue);
        return new HideableBooleanParameterValue(getName(),this.defaultValue, getDescription(), hidden);
    }

    @Extension
    public static class DefinitionDescription extends BooleanParameterDefinition.DescriptorImpl {
        @Override
        public String getDisplayName() {
            return "Hideable Boolean Parameter";
        }

        @Override
        public String getHelpFile() {
            return "/help/parameter/boolean.html";
        }
    }

    public boolean isDefaultValue() {
        return defaultValue;
    }

    public boolean isHidden() {
        return this.hidden;
    }
}
