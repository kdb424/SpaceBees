package spacebees.main;

import java.io.File;
import java.lang.reflect.Field;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import spacebees.block.*;
import spacebees.block.types.HiveType;
import spacebees.item.ItemCapsule;
import spacebees.item.ItemComb;
import spacebees.item.ItemDrop;
import spacebees.item.ItemSpaceHive;
import spacebees.item.ItemSpaceHiveFrame;
import spacebees.item.ItemMiscResources;
import spacebees.item.ItemMoonDial;
import spacebees.item.ItemNugget;
import spacebees.item.ItemPollen;
import spacebees.item.ItemPropolis;
import spacebees.item.ItemWax;
import spacebees.item.types.CapsuleType;
import spacebees.item.types.HiveFrameType;
import spacebees.item.types.NuggetType;
import spacebees.item.types.WaxType;
import spacebees.main.utils.LocalizationManager;
import spacebees.main.utils.LogHelper;
import spacebees.main.utils.VersionInfo;
import spacebees.storage.BackpackDefinition;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import forestry.api.apiculture.BeeManager;
import forestry.api.storage.BackpackManager;
import forestry.api.storage.EnumBackpackType;

/**
 * A class to hold some data related to mod state & functions.
 *
 * @author MysteriousAges
 */
public class Config {
	public static boolean drawParticleEffects;
	public static boolean disableUpdateNotification;
	public static boolean useImpregnatedStickInTools;
	public static boolean moonDialShowsPhaseInText;
	public static boolean doSpecialHiveGen;
	public static String thaumaturgeExtraItems;
	public static int capsuleStackSizeMax;
	public static boolean logHiveSpawns;

	public static boolean forestryDebugEnabled;

	public static BlockHive hive;

	public static ItemComb combs;
	public static ItemWax wax;
	public static ItemPropolis propolis;
	public static ItemDrop drops;
	public static ItemPollen pollen;
	public static ItemMiscResources miscResources;
	public static ItemNugget nuggets;
	public static ItemMoonDial moonDial;

	// ----- Liquid Capsules --------------------
	public static ItemCapsule spaceCapsule;
	public static ItemCapsule voidCapsule;

	// ----- Apiary Frames ----------------------
	public static ItemSpaceHiveFrame hiveFrameSpace;
	public static ItemSpaceHiveFrame hiveFrameResilient;
	public static ItemSpaceHiveFrame hiveFrameGentle;
	public static ItemSpaceHiveFrame hiveFrameMetabolic;
	public static ItemSpaceHiveFrame hiveFrameNecrotic;
	public static ItemSpaceHiveFrame hiveFrameTemporal;
	public static ItemSpaceHiveFrame hiveFrameOblivion;

	// ----- Backpacks ------------------------------------------
	public static Item thaumaturgeBackpackT1;
	public static Item thaumaturgeBackpackT2;
	public static BackpackDefinition thaumaturgeBackpackDef;

	// ----- Forestry Blocks ------------------------------------
	public static Block fHiveBlock;
	public static Block fAlvearyBlock;
	// ----- Forestry Items -------------------------------------
	public static Item fBeeComb;
	public static Item fHoneydew;
	public static Item fHoneyDrop;
	public static Item fPollen;
	public static Item fCraftingResource;

	// ----- Config State info ----------------------------------
	public static Configuration configuration;
	public static boolean extraBeesActive;

	public Config(File configFile) {
		configuration = new Configuration(configFile);
		configuration.load();
		this.doMiscConfig();

		forestryDebugEnabled = (new File("./config/forestry/DEBUG.ON"))
				.exists();
		configuration.save();
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(
			ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equalsIgnoreCase(VersionInfo.ModName)) {
			if (configuration.hasChanged()) {
				configuration.save();
			}
		}
	}

	public void saveConfigs() {
		configuration.save();
	}

	public void setupBlocks() {

		hive = new BlockHive();
		GameRegistry.registerBlock(hive, ItemSpaceHive.class, "hive");

		for (HiveType t : HiveType.values()) {
			hive.setHarvestLevel("scoop", 0, t.ordinal());
		}

		LogHelper
				.info("Replacing stupid-block with 'Here,  have some delicious textures' ItemBlock. This is 100%% normal.");
		/*
		 * Item.itemsList[hive.blockID] = null; Item.itemsList[hive.blockID] =
		 * new ItemMultiTextureTile(hive.blockID - 256, hive,
		 * HiveType.getAllNames());
		 */
	}

	public void setupItems() {
		combs = new ItemComb();
		wax = new ItemWax();
		propolis = new ItemPropolis();
		drops = new ItemDrop();
		miscResources = new ItemMiscResources();

		// Make Aromatic Lumps a swarmer inducer. Chance is /1000.
//		BeeManager.inducers.put(
//				miscResources.getStackForType(ResourceType.AROMATIC_LUMP), 95);

		spaceCapsule = new ItemCapsule(CapsuleType.SPACE, capsuleStackSizeMax);
		voidCapsule = new ItemCapsule(CapsuleType.VOID, capsuleStackSizeMax);
		pollen = new ItemPollen();

		hiveFrameSpace = new ItemSpaceHiveFrame(HiveFrameType.Space);
		hiveFrameResilient = new ItemSpaceHiveFrame(HiveFrameType.RESILIENT);
		hiveFrameGentle = new ItemSpaceHiveFrame(HiveFrameType.GENTLE);
		hiveFrameMetabolic = new ItemSpaceHiveFrame(HiveFrameType.METABOLIC);
		hiveFrameNecrotic = new ItemSpaceHiveFrame(HiveFrameType.NECROTIC);
		hiveFrameTemporal = new ItemSpaceHiveFrame(HiveFrameType.TEMPORAL);
		hiveFrameOblivion = new ItemSpaceHiveFrame(HiveFrameType.OBLIVION);

		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR,
				new WeightedRandomChestContent(
						new ItemStack(hiveFrameOblivion), 1, 1, 18));
		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY,
				new WeightedRandomChestContent(
						new ItemStack(hiveFrameOblivion), 1, 3, 23));

//		moonDial = new ItemMoonDial();

		nuggets = new ItemNugget();

//		OreDictionary.registerOre("beeComb", new ItemStack(combs, 1,
//				OreDictionary.WILDCARD_VALUE));
//		OreDictionary.registerOre("waxSpace",
//				wax.getStackForType(WaxType.SPACE));
//		OreDictionary.registerOre("nuggetIron",
//				nuggets.getStackForType(NuggetType.IRON));
//		OreDictionary.registerOre("nuggetCopper",
//				nuggets.getStackForType(NuggetType.COPPER));
//		OreDictionary.registerOre("nuggetTin",
//				nuggets.getStackForType(NuggetType.TIN));
//		OreDictionary.registerOre("nuggetSilver",
//				nuggets.getStackForType(NuggetType.SILVER));
//		OreDictionary.registerOre("nuggetLead",
//				nuggets.getStackForType(NuggetType.LEAD));
//		OreDictionary.registerOre("shardDiamond",
//				nuggets.getStackForType(NuggetType.DIAMOND));
//		OreDictionary.registerOre("shardEmerald",
//				nuggets.getStackForType(NuggetType.EMERALD));
//		OreDictionary.registerOre("shardApatite",
//				nuggets.getStackForType(NuggetType.APATITE));

//		String item;
//		for (NuggetType type : NuggetType.values()) {
//			LogHelper.info("Found nugget of type " + type.toString());
//			item = type.toString().toLowerCase();
//			item = Character.toString(item.charAt(0)).toUpperCase()
//					+ item.substring(1);
//			if (OreDictionary.getOres("ingot" + item).size() <= 0) {
//				if (OreDictionary.getOres("shard" + item).size() <= 0) {
//					LogHelper.info("Disabled nugget " + type.toString());
//					type.setInactive();
//				}
//			}
//
//		}

		GameRegistry.registerItem(nuggets, "beeNugget");
	}

	private void doMiscConfig() {
		Property p;

		// Pull config from Forestry via reflection
		Field f;
		try {
			f = Class.forName("forestry.core.config.Config").getField(
					"enableParticleFX");
			drawParticleEffects = f.getBoolean(null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// p = configuration.get("general", "backpack.thaumaturge.active",
		// true);
		// p.comment = "Set to false to disable the Thaumaturge backpack";
		// thaumaturgeBackpackActive = p.getBoolean(true);

		p = configuration.get("general", "capsuleStackSize", 64);
		p.comment = "Allows you to edit the stack size of the capsules in SpacecBees if using GregTech, \n"
				+ "or the reduced capsule size in Forestry & Railcraft. Default: 64";
		capsuleStackSizeMax = p.getInt();

		p = configuration.get("general", "disableVersionNotification", false);
		p.comment = "Set to true to stop ThaumicBees from notifying you when new updates are available. (Does not supress critical updates)";
		disableUpdateNotification = p.getBoolean(false);

		p = configuration.get("general", "useImpregnatedStickInTools", false);
		p.comment = "Set to true to make Thaumium Grafter & Scoop require impregnated sticks in the recipe.";
		useImpregnatedStickInTools = p.getBoolean(false);

		p = configuration.get("general", "moonDialShowText", false);
		p.comment = "set to true to show the current moon phase in mouse-over text.";
		moonDialShowsPhaseInText = p.getBoolean(false);

		p = configuration.get("general", "doSpecialHiveGen", true);
		p.comment = "Set to false if you hate fun and do not want special hives generating in Magic biomes.";
		doSpecialHiveGen = p.getBoolean(true);

		// Debug
		p = configuration.get("debug", "logHiveSpawns", false);
		p.comment = "Enable to see exact locations of SpaceBees hive spawns.";
		logHiveSpawns = p.getBoolean();
	}

}
