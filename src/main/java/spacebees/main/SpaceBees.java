package spacebees.main;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

import spacebees.bees.BeeManager;
import spacebees.client.gui.GUIHandler;
import spacebees.main.utils.CraftingManager;
import spacebees.main.utils.IMCManager;
import spacebees.main.utils.LogHelper;
import spacebees.main.utils.VersionInfo;
import spacebees.main.utils.compat.ModHelper;

@Mod(
		modid = VersionInfo.ModName,
		dependencies = VersionInfo.Depends,
		guiFactory = VersionInfo.GUI_FACTORY_CLASS
)
public class SpaceBees
{

	@Mod.Instance(VersionInfo.ModName)
	public static SpaceBees object;

	@SidedProxy(serverSide = "spacebees.main.CommonProxy", clientSide = "spacebees.main.ClientProxy")
	public static CommonProxy proxy;

	public GUIHandler guiHandler;
	private String configsPath;
	private Config modConfig;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		this.configsPath = event.getModConfigurationDirectory().getAbsolutePath();
		this.modConfig = new Config(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(modConfig);

		// Compatibility Helpers setup time.
		ModHelper.preInit();
			
		this.modConfig.setupBlocks();
		this.modConfig.setupItems();
		
		// LocalizationManager.setupLocalizationInfo();
		
		LogHelper.info("Preinit completed");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		ModHelper.init();

		LogHelper.info("Init completed");
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		ModHelper.postInit();

		this.guiHandler = new GUIHandler();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, this.guiHandler);

		proxy.registerRenderers();

		BeeManager.ititializeBees();

		this.modConfig.saveConfigs();

		CraftingManager.setupCrafting();
		CraftingManager.registerLiquidContainers();

		VersionInfo.doVersionCheck();
		LogHelper.info("Postinit completed");
	}

	@Mod.EventHandler
	public void handleIMCMessage(IMCEvent event)
	{
		IMCManager.handle(event);
	}

}
