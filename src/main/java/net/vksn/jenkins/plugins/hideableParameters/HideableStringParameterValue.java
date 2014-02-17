package net.vksn.jenkins.plugins.hideableParameters;

import hudson.model.StringParameterValue;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 * Created by timii on 2/3/14.
 */
public class HideableStringParameterValue extends StringParameterValue {

    private boolean hidden;


    @DataBoundConstructor
    public HideableStringParameterValue(String name, String value, String description, boolean hidden) {
        super(name,value, description);
        this.hidden = hidden;
    }

    public boolean getHidden() { return hidden; }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
