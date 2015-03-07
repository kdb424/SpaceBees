package spacebees.main;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import spacebees.bees.BeeManager;
import spacebees.main.utils.CraftingManager;
import spacebees.main.utils.IMCManager;
import spacebees.main.utils.LocalizationManager;
import spacebees.main.utils.VersionInfo;
import spacebees.main.utils.compat.*;
import spacebees.world.WorldGeneratorHandler;
import forestry.api.apiculture.hives.IHiveDescription;
import forestry.api.apiculture.hives.IHiveRegistry;

@Mod(
		modid=VersionInfo.ModName,
		dependencies=VersionInfo.Depends
)
public class SpaceBees
{

	@Mod.Instance(VersionInfo.ModName)
	public static SpaceBees object;
	
	@SidedProxy(serverSide="spacebees.main.CommonProxy", clientSide="spacebees.main.ClientProxy")
	public static CommonProxy proxy;

	//TODO fix this
	//public GUIHandler guiHandler;
	private String configsPath;
	private Config modConfig;
	private WorldGeneratorHandler worldHandler;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		this.configsPath = event.getModConfigurationDirectory().getAbsolutePath();
		this.modConfig = new Config(event.getSuggestedConfigurationFile());
		
		// Compatibility Helpers setup time.
		ForestryHelper.preInit();
		ExtraBeesHelper.preInit();
		ThermalExpansionHelper.preInit();
		RedstoneArsenalHelper.preInit();
			
		this.modConfig.setupBlocks();
		this.modConfig.setupItems();
		
		LocalizationManager.setupLocalizationInfo();
		
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		ForestryHelper.init();
		ExtraBeesHelper.init();
		ThermalExpansionHelper.init();
		RedstoneArsenalHelper.init();

		worldHandler = new WorldGeneratorHandler();
		GameRegistry.registerWorldGenerator(worldHandler, 0);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		ForestryHelper.postInit();
		ExtraBeesHelper.postInit();
		ThermalExpansionHelper.postInit();
		RedstoneArsenalHelper.postInit();
		
		//TODO fix this
		//this.guiHandler = new GUIHandler();
		//NetworkRegistry.INSTANCE.registerGuiHandler(this, this.guiHandler);
		
		proxy.registerRenderers();

		BeeManager.ititializeBees();
		
		this.modConfig.saveConfigs();
		
		CraftingManager.setupCrafting();
		CraftingManager.registerLiquidContainers();
		
		VersionInfo.doVersionCheck();
	}
	
	@Mod.EventHandler
	public void handleIMCMessage(IMCEvent event)
	{
		IMCManager.handle(event);
	}
	
	public static Config getConfig()
	{
		return object.modConfig;
	}
}
