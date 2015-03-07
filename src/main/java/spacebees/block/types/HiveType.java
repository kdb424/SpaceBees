package spacebees.block.types;

import java.util.ArrayList;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import forestry.api.apiculture.IHiveDrop;

import spacebees.bees.BeeGenomeManager;
import spacebees.bees.BeeSpecies;
import spacebees.bees.HiveDrop;
import spacebees.item.types.CombType;
import spacebees.main.CommonProxy;
import spacebees.main.Config;
import spacebees.main.utils.compat.ForestryHelper;

public enum HiveType
{
//	CURIOUS("curious", 12, true),
//	UNUSUAL("unusual", 12, true),
//	RESONANT("resonant", 12, true),
//	DEEP("deep", 4, false),
//	INFERNAL("infernal", 15, false),
//	OBLIVION("oblivion", 7, false),
	//TODO Learn what numbers do
	MOON("moon", 12, true)
	;
	
	private static String[] nameList;
	
	private String name;
	public boolean show;
	private int lightLevel;
	private ArrayList<IHiveDrop> drops;

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public static HiveType getHiveFromMeta(int meta)
	{
		HiveType type = MOON;
		
		if (meta > 0 && meta < HiveType.values().length)
		{
			type = HiveType.values()[meta];
		}
		
		return type;
	}
	
	public static void initHiveData()
	{
		ItemStack[] combs = new ItemStack[] { Config.combs.getStackForType(CombType.MUNDANE) };
		HiveDrop valiantDrop = new HiveDrop(BeeGenomeManager.addRainResist(ForestryHelper.getTemplateForestryForSpecies("Valiant")), combs, 5);

//		MOON.drops.add(new HiveDrop(BeeSpecies.MOON.getGenome(), combs, 80).setIgnoblePercentage(0.7f));
//		//MOON.drops.add(new HiveDrop(BeeGenomeManager.addRainResist(BeeSpecies.MOON.getGenome()), combs, 15));
//		MOON.drops.add(valiantDrop);
//
//		UNUSUAL.drops.add(new HiveDrop(BeeSpecies.UNUSUAL.getGenome(), combs, 80).setIgnoblePercentage(0.7f));
//		UNUSUAL.drops.add(new HiveDrop(BeeGenomeManager.addRainResist(BeeSpecies.UNUSUAL.getGenome()), combs, 15));
//		UNUSUAL.drops.add(valiantDrop);
//
//		RESONANT.drops.add(new HiveDrop(BeeSpecies.SORCEROUS.getGenome(), combs, 80).setIgnoblePercentage(0.7f));
//		RESONANT.drops.add(new HiveDrop(BeeGenomeManager.addRainResist(BeeSpecies.SORCEROUS.getGenome()), combs, 20));
//		RESONANT.drops.add(valiantDrop);
//
		combs = new ItemStack[] { Config.combs.getStackForType(CombType.MOON) };
		MOON.drops.add(new HiveDrop(BeeSpecies.MOON.getGenome(), combs, 80).setIgnoblePercentage(0.65f));
//		DEEP.drops.add(new HiveDrop(BeeGenomeManager.addRainResist(BeeSpecies.ATTUNED.getGenome()), combs, 20));
//		MOON.drops.add(valiantDrop);
//
//		combs = new ItemStack[] { Config.combs.getStackForType(CombType.MOLTEN), new ItemStack(Items.glowstone_dust, 6) };

//		INFERNAL.drops.add(new HiveDrop(BeeSpecies.INFERNAL.getGenome(), combs, 80).setIgnoblePercentage(0.5f));
//		INFERNAL.drops.add(new HiveDrop(ForestryHelper.getTemplateForestryForSpecies("Steadfast"), combs, 3));
//
//		combs = new ItemStack[] { Config.combs.getStackForType(CombType.FORGOTTEN), new ItemStack(Items.ender_pearl, 1) };
//
//		OBLIVION.drops.add(new HiveDrop(BeeSpecies.OBLIVION.getGenome(), combs, 80));
//		OBLIVION.drops.add(new HiveDrop(ForestryHelper.getTemplateForestryForSpecies("Steadfast"), combs, 9));
	}
	
	private HiveType(String hiveName, int light, boolean visible)
	{
		this.name = hiveName;
		this.lightLevel = light;
		this.show = visible;
		this.drops = new ArrayList<IHiveDrop>();
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerIcons(IIconRegister register)
	{
		for (HiveType type : HiveType.values())
		{
			type.icons = new IIcon[2];

			type.icons[0] = register.registerIcon(CommonProxy.DOMAIN + ":beehive." + type.ordinal() + ".top");
			type.icons[1] = register.registerIcon(CommonProxy.DOMAIN + ":beehive." + type.ordinal() + ".side");
		}
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconForSide(int side)
	{
		IIcon i = this.icons[0];
		
		if (side != 0 && side != 1)
		{
			i = this.icons[1];
		}
		
		return i;
	}
	
	public int getLightValue()
	{
		return this.lightLevel;
	}

	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int fortune)
	{
		ArrayList<ItemStack> hiveDrops = new ArrayList<ItemStack>();
		int dart;
		
		// Get a princess.
		int throttle = 0;
		while (hiveDrops.size() <= 0 && throttle < 10)
		{
			++throttle;
			dart = world.rand.nextInt(100);
			for (IHiveDrop drop : drops)
			{
				if (dart <= drop.getChance(world, x, y, z))
				{
					hiveDrops.add(drop.getPrincess(world, x, y, z, fortune));
					break;
				}
			}
		}
		
		// Get a drone, maybe.
		dart = world.rand.nextInt(100);
		for (IHiveDrop drop : drops)
		{
			if (dart <= drop.getChance(world, x, y, z))
			{
				hiveDrops.addAll(drop.getDrones(world, x, y, z, fortune));
				break;
			}
		}
		
		// Get additional drops.
		dart = world.rand.nextInt(100);
		for (IHiveDrop drop : drops)
		{
			if (dart <= drop.getChance(world, x, y, z))
			{
				hiveDrops.addAll(drop.getAdditional(world, x, y, z, fortune));
			}
		}
		
		return hiveDrops;
	}
	
	public static String[] getAllNames()
	{
		return (nameList == null) ? nameList = generateNames() : nameList;
	}
	
	private static String[] generateNames()
	{
		String[] names = new String[values().length];
		for (int i = 0; i < names.length; ++i)
		{
			names[i] = values()[i].name;
		}
		return names;
	}
}
