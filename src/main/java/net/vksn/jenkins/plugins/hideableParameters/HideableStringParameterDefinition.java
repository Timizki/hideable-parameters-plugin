package net.vksn.jenkins.plugins.hideableParameters;

import hudson.Extension;
import hudson.model.ParameterValue;
import hudson.model.StringParameterDefinition;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

/**
 * Created by timii on 2/3/14.
 */
public class HideableStringParameterDefinition extends StringParameterDefinition {

    @DataBoundConstructor
    public HideableStringParameterDefinition(String name, String value, String description, boolean hidden) {
        super(name, value, description);
        this.hidden = hidden;
    }

    private boolean hidden;

    @Extension
    public static class DefinitionDescriptor extends DescriptorImpl {

        @Override
        public String getDisplayName() {
            return "Hideable String Parameter";
        }
    }

    @Override
    public ParameterValue createValue(StaplerRequest req, JSONObject jsonObject) {
        HideableStringParameterValue value = req.bindJSON(HideableStringParameterValue.class, jsonObject);
        value.setHidden(isHidden());
        return value;
    }

    @Override
    public ParameterValue createValue(String value) {
        return new HideableStringParameterValue(getName(), value, getDescription(), isHidden());
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
