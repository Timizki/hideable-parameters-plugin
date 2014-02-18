package net.vksn.jenkins.plugins.hideableParameters;

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
        this.setHidden(hidden);
    }

    public HideableBooleanParameterValue(BooleanParameterValue value, boolean hidden) {
        this(value.getName(), value.value, value.getDescription(), hidden);
    }

    @Override
    public boolean equals(Object o) {
        if(o != null && o instanceof HideableBooleanParameterValue) {
            HideableBooleanParameterValue that = (HideableBooleanParameterValue)o;
            return (that.getHidden() == this.hidden
                && that.getName() == this.getName()
                && that.getValue() == this.getValue());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result += (value ? 1 : 0);
        result += (hidden ? 1 : 0);
        result += name.hashCode();
        result += name.length();
        return result * 31;
    }

    @Override
    public String toString() {
        return "(HidableBooleanParameterValue) [" + getName() + "=" + value + "]";
    }

    public boolean getHidden() { return this.hidden; }
    public void setHidden(boolean hidden) {this.hidden = hidden; }
    public boolean getValue() { return super.value; }
}
