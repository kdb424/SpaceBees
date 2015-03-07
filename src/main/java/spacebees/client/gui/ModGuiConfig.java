package spacebees.client.gui;

import cpw.mods.fml.client.config.GuiConfig;
import spacebees.main.Config;
import spacebees.main.utils.VersionInfo;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class ModGuiConfig extends GuiConfig
{
	public ModGuiConfig(GuiScreen guiScreen)
	{
		super(guiScreen,
				new ConfigElement(Config.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
				VersionInfo.ModName,
				false,
				false,
				GuiConfig.getAbridgedConfigPath(Config.configuration.toString()));
	}
}
