package spacebees.main;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import spacebees.bees.BeeManager;
import spacebees.main.utils.CraftingManager;
import spacebees.main.utils.IMCManager;
import spacebees.main.utils.LogHelper;
import spacebees.main.utils.VersionInfo;
import spacebees.main.utils.compat.ModHelper;
import spacebees.world.feature.HiveGenMars;
import spacebees.world.feature.HiveGenMoon;

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
		
		MinecraftForge.EVENT_BUS.register(new HiveGenMoon());
		MinecraftForge.EVENT_BUS.register(new HiveGenMars());

		LogHelper.info("Init completed");
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		ModHelper.postInit();


		proxy.registerRenderers();

		BeeManager.ititializeBees();

		this.modConfig.saveConfigs();

		CraftingManager.setupCrafting();
		CraftingManager.registerLiquidContainers();

		VersionInfo.doVersionCheck();
		net.minecraftforge.common.BiomeDictionary.registerAllBiomes();
		System.out.println(net.minecraftforge.common.BiomeDictionary.isBiomeRegistered(102));
		LogHelper.info("Postinit completed");
	}

	@Mod.EventHandler
	public void handleIMCMessage(IMCEvent event)
	{
		IMCManager.handle(event);
	}

}
