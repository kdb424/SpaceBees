package spacebees.main.utils;

import cpw.mods.fml.common.registry.GameRegistry;
import forestry.api.recipes.RecipeManagers;
import spacebees.item.ItemCapsule;
import spacebees.item.types.*;
import spacebees.main.Config;
import spacebees.main.utils.compat.ForestryHelper;
import spacebees.main.utils.compat.ThermalExpansionHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CraftingManager
{
	public static void registerLiquidContainers()
	{
//		registerLiquidContainer(Config.magicCapsule);
//		registerLiquidContainer(Config.voidCapsule);
	}
	
	public static void setupCrafting()
	{
		// Broken up into seperate sections to make things a bit easier to find.
		setupVanillaCrafting();
		setupCentrifugeRecipes();
		setupCarpenterRecipes();
	}
	
	private static void setupVanillaCrafting()
	{
		ItemStack input;
		ItemStack output;

		// Magic capsules
//		output = new ItemStack(Config.magicCapsule); output.stackSize = 4;
//		GameRegistry.addRecipe(new ShapedOreRecipe(output, new Object[] {
//				"WWW",
//				'W', "waxMagical"
//		}));



		// "bottling" Intellect drops
//		GameRegistry.addRecipe(new ItemStack(Items.experience_bottle), new Object[] {
//			"DDD", "DBD", "DDD",
//			'D', Config.drops.getStackForType(DropType.INTELLECT),
//			'B', Items.glass_bottle
//		});
//
//		GameRegistry.addRecipe(new ItemStack(Blocks.soul_sand, 4), new Object[] {
//			"SwS", "wDw", "SwS",
//			'S', Blocks.sand,
//			'D', Blocks.dirt,
//			'w', Config.wax.getStackForType(WaxType.SOUL)
//		});
//		GameRegistry.addRecipe(new ItemStack(Blocks.soul_sand, 4), new Object[] {
//			"wSw", "SDS", "wSw",
//			'S', Blocks.sand,
//			'D', Blocks.dirt,
//			'w', Config.wax.getStackForType(WaxType.SOUL)
//		});



		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Config.moonDial), new Object[] {
			"DqD", "qrq", "DqD",
			'r', Items.redstone,
			'q', Items.quartz,
			'D', "dyeGreen"
		}));



		if (OreDictionary.getOres("ingotCopper").size() <= 0)
		{
			NuggetType.COPPER.setInactive();
		}
		else
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(OreDictionary.getOres("ingotCopper").get(0), new Object[] {
				"xxx", "xxx", "xxx",
				'x', "nuggetCopper"
			}));
		}
		if (OreDictionary.getOres("ingotTin").size() <= 0)
		{
			NuggetType.TIN.setInactive();
		}
		else
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(OreDictionary.getOres("ingotTin").get(0), new Object[] {
				"xxx", "xxx", "xxx",
				'x', "nuggetTin"
			}));
		}
		if (OreDictionary.getOres("ingotSilver").size() <= 0)
		{
			NuggetType.SILVER.setInactive();
		}
		else
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(OreDictionary.getOres("ingotSilver").get(0), new Object[] {
				"xxx", "xxx", "xxx",
				'x', "nuggetSilver"
			}));
		}
		if (OreDictionary.getOres("ingotLead").size() <= 0)
		{
			NuggetType.LEAD.setInactive();
		}
		else
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(OreDictionary.getOres("ingotLead").get(0), new Object[] {
				"xxx", "xxx", "xxx",
				'x', "nuggetLead"
			}));
		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.iron_ingot), new Object[] {
			"xxx", "xxx", "xxx",
			'x', "nuggetIron"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.diamond), new Object[] {
			"xxx", "xxx", "xxx",
			'x', "shardDiamond"
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.emerald), new Object[] {
			"xxx", "xxx", "xxx",
			'x', "shardEmerald"
		}));


//		output = Config.voidCapsule.getCapsuleForLiquid(FluidType.EMPTY);
//		output.stackSize = 4;

									
		;
		}


		

	private static void setupCentrifugeRecipes()
	{
		


		if (ThermalExpansionHelper.isActive())
		{

//			RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.TE_DESTABILIZED),
//					new ItemStack[] {Config.wax.getStackForType(WaxType.MAGIC), Config.drops.getStackForType(DropType.DESTABILIZED)},
//					new int[] {50, 22});

		}

	}

	private static void setupCarpenterRecipes()
	{
		ItemStack input;
		ItemStack output;


	}

	private static void registerLiquidContainer(ItemCapsule baseCapsule)
	{
		ItemStack empty = new ItemStack(baseCapsule, 1, 0);
		ItemStack filled;
		FluidStack liquid = null;

		for (FluidType fluidType : FluidType.values())
		{
			switch (fluidType)
			{
				case EMPTY:
					liquid = null;
					break;
				case WATER:
					liquid = new FluidStack(FluidRegistry.WATER, baseCapsule.getType().capacity);
					break;
				case LAVA:
					liquid = new FluidStack(FluidRegistry.LAVA, baseCapsule.getType().capacity);
					break;
				default:
					liquid = FluidRegistry.getFluidStack(fluidType.liquidID, baseCapsule.getType().capacity);
					break;
			}

			if (liquid != null)
			{
				filled = new ItemStack(baseCapsule, 1, fluidType.ordinal());
				FluidContainerRegistry.registerFluidContainer(liquid, filled, empty);

				// Register with Squeezer
//				RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] {filled} , liquid,
//						Config.wax.getStackForType(WaxType.MAGIC), 20);
//				fluidType.available = true;
			}
		}
		// Empty will be set to unavailable. Obviously, it always is.
		FluidType.EMPTY.available = true;
	}
}