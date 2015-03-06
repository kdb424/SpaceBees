package spacebees.main;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;
import forestry.api.apiculture.BeeManager;
import forestry.api.storage.BackpackManager;
import forestry.api.storage.EnumBackpackType;
import forestry.api.storage.IBackpackDefinition;
import spacebees.block.BlockHive;
import spacebees.block.types.HiveType;
import spacebees.item.*;
import spacebees.item.types.*;
import spacebees.main.utils.LocalizationManager;
import spacebees.storage.BackpackDefinition;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * A class to hold some data related to mod state & functions.
 * @author MysteriousAges
 *
 */
public class Config
{
	public boolean	DrawParticleEffects;	
	public boolean	BeeInfusionsAdded;
	public boolean	AddThaumcraftItemsToBackpacks;
	public boolean	DisableUpdateNotification;
	public boolean	AreMagicPlanksFlammable;
	public boolean	UseImpregnatedStickInTools;
	public boolean	MoonDialShowsPhaseInText;
	public boolean	DoSpecialHiveGen;
	public String	ThaumaturgeExtraItems;
	public int		CapsuleStackSizeMax;
	public boolean	DoHiveRetrogen;
	public boolean	ForceHiveRegen;
	
	public float	MagnetBaseRange;
	public float	MagnetLevelMultiplier;
	public int		MagnetMaxLevel;
	
	public boolean	ForestryDebugEnabled;

	public static BlockHive hive;
	
	public static ItemComb combs;
	public static ItemWax wax;
	public static ItemPropolis propolis;
	public static ItemDrop drops;
	public static ItemPollen pollen;
	public static ItemMiscResources miscResources;
	public static ItemNugget nuggets;
	public static ItemMoonDial moonDial;
	
	//----- Liquid Capsules --------------------
	public static ItemCapsule magicCapsule;
	public static ItemCapsule voidCapsule;
	
	//----- Apiary Frames ----------------------
//	public static ItemMagicHiveFrame hiveFrameMagic;
//	public static ItemMagicHiveFrame hiveFrameResilient;
//	public static ItemMagicHiveFrame hiveFrameGentle;
//	public static ItemMagicHiveFrame hiveFrameMetabolic;
//	public static ItemMagicHiveFrame hiveFrameNecrotic;
//	public static ItemMagicHiveFrame hiveFrameTemporal;
//	public static ItemMagicHiveFrame hiveFrameOblivion;
	
	//----- Backpacks ------------------------------------------
	public static Item thaumaturgeBackpackT1;
	public static Item thaumaturgeBackpackT2;
	public static BackpackDefinition thaumaturgeBackpackDef;

	//----- Forestry Blocks ------------------------------------
	public static Block fHiveBlock;
	public static Block fAlvearyBlock;
	//----- Forestry Items -------------------------------------
	public static Item fBeeComb;
	public static Item fHoneydew;
	public static Item fHoneyDrop;
	public static Item fPollen;
	public static Item fCraftingResource;
	//---ThermalExpansion Items ---
	public static ItemStack teEnderiumBlock;
	public static ItemStack teElectrumBlock;
	public static ItemStack teInvarBlock;
	public static ItemStack teNickelBlock;
	public static ItemStack tePlatinumBlock;
	public static ItemStack teBronzeBlock;	
	public static ItemStack teEnderiumNugget;
	public static ItemStack teInvarNugget;
	public static ItemStack teElectrumNugget;
	public static ItemStack teNickelNugget;
	public static ItemStack tePlatinumNugget;
	public static ItemStack teDustCryotheum;
	public static ItemStack teDustBlizz;
	public static ItemStack teDustPyrotheum;
	public static ItemStack teDustSulfur;
	public static ItemStack teDustPlatinum;
	public static FluidStack teFluidGlowstone;
	public static FluidStack teFluidCoal;
	public static FluidStack teFluidRedstone;
	public static FluidStack teFluidEnder;
	public static ItemStack rsaFluxNugget;
	public static ItemStack rsaFluxBlock;
		
	
	

	//----- Config State info ----------------------------------
	private Configuration configuration;
	
	public Config(File configFile)
	{
		this.configuration = new Configuration(configFile);
		this.configuration.load();
		this.doMiscConfig();
		
		this.ForestryDebugEnabled = (new File("./config/forestry/DEBUG.ON")).exists();
	}
	
	public void saveConfigs()
	{
		this.configuration.save();
	}

	public void setupBlocks()
	{
		


		hive = new BlockHive();
		GameRegistry.registerBlock(hive, "hive");

		for (HiveType t : HiveType.values())
		{
			hive.setHarvestLevel("scoop", t.ordinal(), 0);
		}

		FMLLog.info("Replacing stupid-block with 'Here,  have some delicious textures' ItemBlock. This is 100%% normal.");
		/*Item.itemsList[hive.blockID] = null;
		Item.itemsList[hive.blockID] = new ItemMultiTextureTile(hive.blockID - 256, hive, HiveType.getAllNames());*/
	}
	
	public void setupItems()
	{		
		combs = new ItemComb();
		wax = new ItemWax();
		propolis = new ItemPropolis();
		drops = new ItemDrop();
		miscResources = new ItemMiscResources();
		
		// Make Aromatic Lumps a swarmer inducer. Chance is /1000.
//		BeeManager.inducers.put(miscResources.getStackForType(ResourceType.AROMATIC_LUMP), 95);
		
		{

		}

//		magicCapsule = new ItemCapsule(CapsuleType.MAGIC, this.CapsuleStackSizeMax);
		pollen = new ItemPollen();
		
		{

		}
		
//		hiveFrameMagic = new ItemMagicHiveFrame(HiveFrameType.MAGIC);
//		hiveFrameResilient = new ItemMagicHiveFrame(HiveFrameType.RESILIENT);
//		hiveFrameGentle = new ItemMagicHiveFrame(HiveFrameType.GENTLE);
//		hiveFrameMetabolic = new ItemMagicHiveFrame(HiveFrameType.METABOLIC);
//		hiveFrameNecrotic = new ItemMagicHiveFrame(HiveFrameType.NECROTIC);
//		hiveFrameTemporal = new ItemMagicHiveFrame(HiveFrameType.TEMPORAL);
//		hiveFrameOblivion = new ItemMagicHiveFrame(HiveFrameType.OBLIVION);
		
//		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(hiveFrameOblivion), 1, 1, 18));
//		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(hiveFrameOblivion), 1, 3, 23));

		
//		voidCapsule = new ItemCapsule(CapsuleType.VOID, this.CapsuleStackSizeMax);

		{
			
		}
		
		moonDial = new ItemMoonDial();
		
		nuggets = new ItemNugget();
		
		
		OreDictionary.registerOre("beeComb", new ItemStack(combs, 1, OreDictionary.WILDCARD_VALUE));
//		OreDictionary.registerOre("waxMagical", wax.getStackForType(WaxType.MAGIC));
//		OreDictionary.registerOre("waxMagical", wax.getStackForType(WaxType.AMNESIC));
		OreDictionary.registerOre("nuggetIron", nuggets.getStackForType(NuggetType.IRON));
		OreDictionary.registerOre("nuggetCopper", nuggets.getStackForType(NuggetType.COPPER));
		OreDictionary.registerOre("nuggetTin", nuggets.getStackForType(NuggetType.TIN));
		OreDictionary.registerOre("nuggetSilver", nuggets.getStackForType(NuggetType.SILVER));
		OreDictionary.registerOre("nuggetLead", nuggets.getStackForType(NuggetType.LEAD));
		OreDictionary.registerOre("shardDiamond", nuggets.getStackForType(NuggetType.DIAMOND));
		OreDictionary.registerOre("shardEmerald", nuggets.getStackForType(NuggetType.EMERALD));
		OreDictionary.registerOre("shardApatite", nuggets.getStackForType(NuggetType.APATITE));
	}
	
	private void doMiscConfig()
	{
		Property p;
		
		// Pull config from Forestry via reflection
		Field f;
		try
		{
			f = Class.forName("forestry.core.config.Config").getField("enableParticleFX");
			this.DrawParticleEffects = f.getBoolean(null);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		

		p = configuration.get("general", "capsuleStackSize", 64);
		p.comment = "Allows you to edit the stack size of the capsules in MagicBees if using GregTech, \n" +
				"or the reduced capsule size in Forestry & Railcraft. Default: 64";
		this.CapsuleStackSizeMax = p.getInt();
		
		p = configuration.get("general", "disableVersionNotification", false);
		p.comment = "Set to true to stop ThaumicBees from notifying you when new updates are available. (Does not supress critical updates)";
		this.DisableUpdateNotification = p.getBoolean(false);
		
		
		p = configuration.get("general", "moonDialShowText", false);
		p.comment = "set to true to show the current moon phase in mouse-over text.";
		this.MoonDialShowsPhaseInText = p.getBoolean(false);
		
		p = configuration.get("general", "doSpecialHiveGen", true);
		p.comment = "Set to false if you hate fun and do not want special hives generating in Magic biomes.";
		this.DoSpecialHiveGen = p.getBoolean(true);
		
		p = configuration.get("Retrogen", "doHiveRetrogen", false);
		p.comment = "Set to true to enable retroactive worldgen of Magic Bees hives.";
		this.DoHiveRetrogen = p.getBoolean(false);
		
		p = configuration.get("Retrogen", "forceHiveRegen", false);
		p.comment = "Set to true to force a regeneration of Magic Bees hives. Will set config option to false after parsed. (Implies doHiveRetrogen=true)";
		this.ForceHiveRegen = p.getBoolean(false);
		
		if (this.ForceHiveRegen)
		{
			FMLLog.info("Space Bees will aggressively regenerate hives in all chunks for this game instance. Config option set to false.");
			p.set(false);
			this.DoHiveRetrogen = true;
		}
		else if (this.DoHiveRetrogen)
		{
			FMLLog.info("Space Bees will attempt to regenerate hives in chunks that were generated before the mod was added.");
		}

	}

}
