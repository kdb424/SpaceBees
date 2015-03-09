package spacebees.main.utils;

import cpw.mods.fml.common.registry.GameRegistry;
import spacebees.item.ItemCapsule;
import spacebees.item.types.CombType;
import spacebees.item.types.DropType;
import spacebees.item.types.FluidType;
import spacebees.item.types.NuggetType;
import spacebees.item.types.PollenType;
import spacebees.item.types.PropolisType;
import spacebees.item.types.WaxType;
import spacebees.main.Config;
import spacebees.main.utils.compat.ForestryHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import forestry.api.recipes.RecipeManagers;

public class CraftingManager {
	public static void registerLiquidContainers() {
		registerLiquidContainer(Config.spaceCapsule);
		registerLiquidContainer(Config.voidCapsule);
	}

	public static void setupCrafting() {
		// Broken up into seperate sections to make things a bit easier to find.
		setupVanillaCrafting();
		setupCentrifugeRecipes();
		setupCarpenterRecipes();
	}

	private static void setupVanillaCrafting() {
		ItemStack input;
		ItemStack output;

		// Space capsules
		output = new ItemStack(Config.spaceCapsule);
		output.stackSize = 4;
		GameRegistry.addRecipe(new ShapedOreRecipe(output, new Object[] {
				"WWW", 'W', "waxSpace" }));

		// "bottling" Intellect drops
//		GameRegistry.addRecipe(
//				new ItemStack(Items.experience_bottle),
//				new Object[] { "DDD", "DBD", "DDD", 'D',
//						Config.drops.getStackForType(DropType.INTELLECT), 'B',
//						Items.glass_bottle });
//
//		GameRegistry.addRecipe(new ItemStack(Config.hiveFrameTemporal),
//				new Object[] { "sPs", "PfP", "sPs", 's', Blocks.sand, 'P',
//						Config.pollen.getStackForType(PollenType.PHASED), 'f',
//						Config.hiveFrameSpace });
//
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(
//				Config.moonDial), new Object[] { "DqD", "qrq", "DqD", 'r',
//				Items.redstone, 'q', Items.quartz, 'D', "dyeGreen" }));
//
//		if (OreDictionary.getOres("ingotCopper").size() <= 0) {
//			NuggetType.COPPER.setInactive();
//		} else {
//			GameRegistry.addRecipe(new ShapedOreRecipe(OreDictionary.getOres(
//					"ingotCopper").get(0), new Object[] { "xxx", "xxx", "xxx",
//					'x', "nuggetCopper" }));
//		}
//		if (OreDictionary.getOres("ingotTin").size() <= 0) {
//			NuggetType.TIN.setInactive();
//		} else {
//			GameRegistry.addRecipe(new ShapedOreRecipe(OreDictionary.getOres(
//					"ingotTin").get(0), new Object[] { "xxx", "xxx", "xxx",
//					'x', "nuggetTin" }));
//		}
//		if (OreDictionary.getOres("ingotSilver").size() <= 0) {
//			NuggetType.SILVER.setInactive();
//		} else {
//			GameRegistry.addRecipe(new ShapedOreRecipe(OreDictionary.getOres(
//					"ingotSilver").get(0), new Object[] { "xxx", "xxx", "xxx",
//					'x', "nuggetSilver" }));
//		}
//		if (OreDictionary.getOres("ingotLead").size() <= 0) {
//			NuggetType.LEAD.setInactive();
//		} else {
//			GameRegistry.addRecipe(new ShapedOreRecipe(OreDictionary.getOres(
//					"ingotLead").get(0), new Object[] { "xxx", "xxx", "xxx",
//					'x', "nuggetLead" }));
//		}
//
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(
//				Items.iron_ingot), new Object[] { "xxx", "xxx", "xxx", 'x',
//				"nuggetIron" }));
//
//		GameRegistry.addRecipe(new ShapedOreRecipe(
//				new ItemStack(Items.diamond), new Object[] { "xxx", "xxx",
//						"xxx", 'x', "shardDiamond" }));
//
//		GameRegistry.addRecipe(new ShapedOreRecipe(
//				new ItemStack(Items.emerald), new Object[] { "xxx", "xxx",
//						"xxx", 'x', "shardEmerald" }));
//
//		GameRegistry.addRecipe(new ShapedOreRecipe(ItemInterface
//				.getItemStack("apatite"), new Object[] { "xxx", "xxx", "xxx",
//				'x', Config.nuggets.getStackForType(NuggetType.APATITE) }));
	}

	private static void setupCentrifugeRecipes() {
		ItemStack beeswax = ItemInterface.getItemStack("beeswax");
		ItemStack propolis = ItemInterface.getItemStack("propolis");
		propolis.setItemDamage(ForestryHelper.Propolis.MOON.ordinal());

		// 20 is the 'average' time to centrifuge a comb.
//		RecipeManagers.centrifugeManager.addRecipe(
//				20,
//				Config.combs.getStackForType(CombType.MUNDANE),
//				new ItemStack[] { beeswax,
//						ItemInterface.getItemStack("honeyDrop"),
//						Config.wax.getStackForType(WaxType.SPACE) }, new int[] {
//						90, 60, 10 });

	}

	private static void setupCarpenterRecipes() {
		ItemStack input;
		ItemStack output;

//		output = ItemInterface.getItemStack("Forestry", "candle", 24);
//		RecipeManagers.carpenterManager.addRecipe(30, new FluidStack(
//				FluidRegistry.WATER, 600), null, output, new Object[] { " S ",
//				"WWW", "WWW", 'W', Config.wax, 'S', Items.string });
//
//		output = ItemInterface.getItemStack("Forestry", "candle", 6);
//		input = ItemInterface.getItemStack("craftingMaterial");
//		input.setItemDamage(ForestryHelper.CraftingMaterial.SILK_WISP.ordinal()); // Set
//																					// to
//																					// Silk
//																					// Wisp
//		RecipeManagers.carpenterManager.addRecipe(30, new FluidStack(
//				FluidRegistry.WATER, 600), null, output, new Object[] { "WSW",
//				'W', Config.wax, 'S', input });
//
//		RecipeManagers.carpenterManager.addRecipe(
//				30,
//				FluidRegistry.getFluidStack("honey", 1000),
//				null,
//				output,
//				new Object[] { " J ", "PDP", " J ", 'P',
//						ItemInterface.getItemStack("pollen"), 'J',
//						ItemInterface.getItemStack("royalJelly"), 'D',
//						Config.drops.getStackForType(DropType.ENCHANTED) });

	}

	private static void registerLiquidContainer(ItemCapsule baseCapsule) {
		ItemStack empty = new ItemStack(baseCapsule, 1, 0);
		ItemStack filled;
		FluidStack liquid = null;

		for (FluidType fluidType : FluidType.values()) {
			switch (fluidType) {
			case EMPTY:
				liquid = null;
				break;
			case WATER:
				liquid = new FluidStack(FluidRegistry.WATER,
						baseCapsule.getType().capacity);
				break;
			case LAVA:
				liquid = new FluidStack(FluidRegistry.LAVA,
						baseCapsule.getType().capacity);
				break;
			default:
				liquid = FluidRegistry.getFluidStack(fluidType.liquidID,
						baseCapsule.getType().capacity);
				break;
			}

			if (liquid != null) {
				filled = new ItemStack(baseCapsule, 1, fluidType.ordinal());
				FluidContainerRegistry.registerFluidContainer(liquid, filled,
						empty);

				// Register with Squeezer/Bottler
				// RecipeManagers.bottlerManager.addRecipe(5, liquid, empty,
				// filled); Outdated?
				RecipeManagers.squeezerManager.addRecipe(10,
						new ItemStack[] { filled }, liquid,
						Config.wax.getStackForType(WaxType.SPACE), 20);
				fluidType.available = true;
			}
		}
		// Empty will be set to unavailable. Obviously, it always is.
		FluidType.EMPTY.available = true;
	}
}