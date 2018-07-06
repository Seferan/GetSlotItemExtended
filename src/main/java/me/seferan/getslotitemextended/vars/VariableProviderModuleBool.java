package me.seferan.getslotitemextended.vars;

import me.seferan.getslotitemextended.util.ModuleInfo;
import net.eq2online.macros.scripting.api.APIVersion;
import net.eq2online.macros.scripting.parser.ScriptContext;
import net.eq2online.macros.scripting.api.IVariableProvider;

import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
/**
 * <p>The {@link APIVersion} annotation must match the target API version for
 * this module, we provide a central location to update the version by storing
 * it in the {@link ModuleInfo} class.</p>
 */
@APIVersion(ModuleInfo.API_VERSION)
public class VariableProviderModuleBool implements IVariableProvider {

    private Map<String, Integer> clipboardIntVars;
    
	@Override
    public void onInit()
    {
      ScriptContext.MAIN.getCore().registerVariableProvider(this);
	  this.clipboardIntVars = new HashMap<String, Integer>();
	  this.clipboardIntVars.put("MODULEGETSLOTITEMEXT", Integer.valueOf(1));
    }
	
	@Override
	public void updateVariables(boolean clock) {}
	
    @Override
    public Object getVariable(String variableName)
    {
		if (this.clipboardIntVars.containsKey(variableName))
		{
			return this.clipboardIntVars.get(variableName);
		}
      return null;
    }
    
	@Override
	public Set<String> getVariables()
	{
		Set<String> varUnion = new HashSet(this.clipboardIntVars.keySet());
		return varUnion;
	}
}
