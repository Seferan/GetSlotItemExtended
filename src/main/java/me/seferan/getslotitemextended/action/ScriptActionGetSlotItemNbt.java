package me.seferan.getslotitemextended.action;

import me.seferan.getslotitemextended.util.ModuleInfo;
import net.eq2online.macros.scripting.api.APIVersion;
import net.eq2online.macros.scripting.api.IMacro;
import net.eq2online.macros.scripting.api.IMacroAction;
import net.eq2online.macros.scripting.api.IReturnValue;
import net.eq2online.macros.scripting.api.IScriptActionProvider;
import net.eq2online.macros.scripting.api.ReturnValue;
import net.eq2online.macros.scripting.parser.ScriptAction;
import net.eq2online.macros.scripting.parser.ScriptContext;
import net.eq2online.macros.scripting.parser.ScriptCore;
import net.eq2online.util.Game;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

@APIVersion(ModuleInfo.API_VERSION)
public class ScriptActionGetSlotItemNbt extends ScriptAction {

	public ScriptActionGetSlotItemNbt() {
		super(ScriptContext.MAIN, "getslotitemnbt");
	}

	@Override
	public void onInit() {
		this.context.getCore().registerScriptAction(this);
	}

	@Override
	public IReturnValue execute(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String rawParams,
			String[] params) {
		String itemID = "unknown";
		String tag = "unknownTag";
		NBTTagCompound nbtTagCompound = null;
		int stackSize = 0;
		int damage = 0;
		if (params.length > 0) {
			int slotId = Math.max(0, ScriptCore.tryParseInt(provider.expand(macro, params[0], false), 0));
			ItemStack slotStack = this.slotHelper.getSlotStack(slotId);
			if (slotStack == null) {
				itemID = Game.getItemName(null);
			} else {
				itemID = Game.getItemName(slotStack.getItem());
				stackSize = slotStack.getCount();
				damage = slotStack.getItemDamage();
				nbtTagCompound = slotStack.getTagCompound();
				tag = nbtTagCompound == null ? "null" : nbtTagCompound.toString();
			}
		}
		ReturnValue retVal = new ReturnValue(itemID);
		if (params.length > 1) {
			provider.setVariable(macro, provider.expand(macro, params[1], false), itemID);
		}
		if (params.length > 2) {
			provider.setVariable(macro, provider.expand(macro, params[2], false), stackSize);
		}
		if (params.length > 3) {
			provider.setVariable(macro, provider.expand(macro, params[3], false), damage);
		}
		if (params.length > 4) {
			provider.setVariable(macro, provider.expand(macro, params[4], false), tag);
		}
		return retVal;

	}
}