package net.vksn.jenkins.plugins.hideableParameters;

import hudson.Extension;
import hudson.model.BooleanParameterDefinition;
import hudson.model.ParameterValue;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

/**
 * Created by timii on 2/3/14.
 */
public class HideableBooleanParameterDefinition extends BooleanParameterDefinition {
     private final boolean hidden;

    @DataBoundConstructor
    public HideableBooleanParameterDefinition(String name, boolean defaultValue, String description, boolean hidden) {
        super(name, defaultValue, description);
        this.hidden = hidden;
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
        return new HideableBooleanParameterValue(getName(), Boolean.valueOf(value), getDescription(), hidden);
    }


    @Override
    public HideableBooleanParameterValue getDefaultParameterValue() {
        return new HideableBooleanParameterValue(super.getDefaultParameterValue(), hidden);
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
        return super.isDefaultValue();
    }

    public boolean isHidden() {
        return this.hidden;
    }
}
