package net.vksn.jenkins.plugins.hideable;

import hudson.model.BooleanParameterValue;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 * Created by timii on 2/3/14.
 */
public class HideableBooleanParameterValue extends BooleanParameterValue {

    private boolean hidden;

    @DataBoundConstructor
    public HideableBooleanParameterValue(String name, boolean value, String description, boolean hidden) {
        super(name, value, description);
    }

    public boolean getHidden() { return this.hidden; }
    public void setHidden(boolean hidden) {this.hidden = hidden; }
}
