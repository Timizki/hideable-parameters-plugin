package net.vksn.jenkins.plugins.hideableParameters;

import com.sun.org.apache.xpath.internal.operations.Bool;
import hudson.EnvVars;
import hudson.model.AbstractBuild;
import hudson.model.BooleanParameterValue;
import hudson.model.ParameterValue;
import hudson.util.VariableResolver;
import org.kohsuke.stapler.DataBoundConstructor;

import java.util.Locale;
import java.util.logging.Logger;

/**
 * Created by timii on 2/3/14.
 */
public class HideableBooleanParameterValue extends ParameterValue {
    private final static Logger log = Logger.getLogger(HideableBooleanParameterValue.class.getName());
    private boolean hidden;
    private final boolean value;

    @DataBoundConstructor
    public HideableBooleanParameterValue(String name, boolean value, String description, boolean hidden) {
        super(name, description);
        this.value = value;
        this.setHidden(hidden);
        log.severe("Creating HideabpleParameterValue with value =" + value +" name="+name+" hidden="+hidden);
    }

    public HideableBooleanParameterValue(BooleanParameterValue value, boolean hidden) {
        this(value.getName(), value.value, value.getDescription(), hidden);
    }

    @Override
    public VariableResolver<String> createVariableResolver(AbstractBuild<?, ?> build) {
        log.severe("creteVariableResolver in HideableBooleanParameterValue");
        return new VariableResolver<String>() {
            public String resolve(String name) {
                return HideableBooleanParameterValue.this.name.equals(name) ? Boolean.toString(value) : null;
            }
        };
    }

    @Override
    public void buildEnvVars(AbstractBuild<?,?> build, EnvVars env) {
        log.severe("buildEnvVars in hideableParameterValue");
        env.put(name,Boolean.toString(value));
        env.put(name.toUpperCase(Locale.ENGLISH),Boolean.toString(value)); // backward compatibility pre 1.345
    }

    @Override
    public boolean equals(Object o) {

        if(o != null && o instanceof HideableBooleanParameterValue) {
            HideableBooleanParameterValue that = (HideableBooleanParameterValue)o;
            if(that.getHidden() == this.hidden
                && that.getName() == this.getName()
                && that.getValue() == this.getValue()) {
                return true;
            }
            return false;
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
    public boolean getValue() { return this.value; }
}
