package net.vksn.jenkins.plugins.hideable;

import hudson.Extension;
import hudson.model.BooleanParameterDefinition;
import hudson.model.ParameterValue;
import hudson.model.StringParameterDefinition;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

/**
 * Created by timii on 2/3/14.
 */
public class HideableBooleanParameterDefinition extends BooleanParameterDefinition {

    public boolean hidden;

    @DataBoundConstructor
    public HideableBooleanParameterDefinition(String name, boolean value, String description, boolean hidden) {
        super(name, value, description);
        this.hidden = hidden;
    }

    @Override
    public ParameterValue createValue(StaplerRequest req, JSONObject jsonObject) {
        HideableBooleanParameterValue value = req.bindJSON(HideableBooleanParameterValue.class, jsonObject);
        value.setHidden(isHidden());
        return value;
    }

    @Override
    public ParameterValue createValue(String value) {
        String strValue = value;
        if(strValue == null) {
            strValue = "false";
        }
        return new HideableBooleanParameterValue(getName(),Boolean.valueOf(strValue), getDescription(), isHidden());
    }

    @Extension
    public static class DefinitionDescription extends StringParameterDefinition.DescriptorImpl {
        @Override
        public String getDisplayName() {
            return "Hideable Boolean Parameter";
        }
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
