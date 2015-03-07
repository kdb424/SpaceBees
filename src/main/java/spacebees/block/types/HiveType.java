package spacebees.block.types;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import spacebees.item.types.CombType;
import spacebees.main.CommonProxy;
import spacebees.world.feature.FeatureHive;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.apiculture.IHiveDrop;
import spacebees.bees.BeeGenomeManager;
import spacebees.bees.BeeSpecies;
import spacebees.bees.HiveDrop;
import spacebees.main.*;
import spacebees.main.utils.compat.ForestryHelper;

public enum HiveType
{
	LUNAR("lunar", 12, true),
//	UNUSUAL("unusual", 12, true),
//	RESONANT("resonant", 12, true),
//	DEEP("deep", 4, false),
//	INFERNAL("infernal", 15, false),
//	OBLIVION("oblivion", 7, false),
	;
	
	private static String[] nameList;
	
	private String name;
	public boolean show;
	private int lightLevel;
	private ArrayList<IHiveDrop> drops;
	private ArrayList<BiomeDictionary.Type> validBiomes;
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public static HiveType getHiveFromMeta(int meta)
	{
		HiveType type = LUNAR;
		
		if (meta > 0 && meta < HiveType.values().length)
		{
			type = HiveType.values()[meta];
		}
		
		return type;
	}
	
	public static void initHiveData()
	{
		ItemStack[] combs = new ItemStack[] {Config.combs.getStackForType(CombType.MOON)};
		HiveDrop valiantDrop = new HiveDrop(BeeGenomeManager.addRainResist(ForestryHelper.getTemplateForestryForSpecies("Valiant")), combs, 5);

		//TODO Move this to moon
		LUNAR.validBiomes.add(Type.FOREST);
		LUNAR.validBiomes.add(Type.JUNGLE);
		LUNAR.validBiomes.add(Type.HILLS);
		LUNAR.drops.add(new HiveDrop(BeeSpecies.MOON.getGenome(), combs, 80).setIgnoblePercentage(0.7f));
		LUNAR.drops.add(new HiveDrop(BeeGenomeManager.addRainResist(BeeSpecies.MOON.getGenome()), combs, 15));
		LUNAR.drops.add(valiantDrop);
		
	}
	
	private HiveType(String hiveName, int light, boolean visible)
	{
		this.name = hiveName;
		this.lightLevel = light;
		this.show = visible;
		this.drops = new ArrayList<IHiveDrop>();
		this.validBiomes = new ArrayList<BiomeDictionary.Type>();
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
	
	private HiveType()
	{
		this.drops = new ArrayList<IHiveDrop>();
	}
	
	public void addDrop(IHiveDrop drop)
	{
		this.drops.add(drop);
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
	
	public void generateHive(World world, Random random, int chunkX, int chunkZ, boolean initialGen)
	{
		if (spawnsInBiome(world.getBiomeGenForCoordsBody(chunkX * 16, chunkZ * 16)))
		{
			switch (this)
			{
			case LUNAR:
				for (int i = 0; i < 3; ++i)
				{
					int coordX = chunkX * 16 + random.nextInt(16);
					int coordZ = chunkZ * 16 + random.nextInt(16);
					if (FeatureHive.generateHiveLunar(world, random, coordX, coordZ, initialGen))
					{
						break;
					}
				}
				break;
			
			}
		}
	}
	
	public boolean spawnsInBiome(BiomeGenBase biomeGen)
	{
		boolean found = false;
		BiomeDictionary.Type[] types = BiomeDictionary.getTypesForBiome(biomeGen);
		for (int i = 0; i < types.length; ++i)
		{
			if (this.validBiomes.contains(types[i]))
			{
				found = true;
				break;
			}
		}
		
		return found;
	}
}
