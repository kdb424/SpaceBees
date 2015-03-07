package spacebees.bees;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;
import forestry.api.apiculture.hives.HiveManager;
import forestry.api.apiculture.hives.IHiveDescription;
import forestry.api.apiculture.hives.IHiveGen;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.apiculture.worldgen.HiveGen;
import forestry.apiculture.worldgen.HiveGenTree;
import spacebees.block.types.HiveType;
import spacebees.main.Config;
import spacebees.main.utils.BlockUtil;
import spacebees.main.utils.LogHelper;
import spacebees.world.feature.FeatureOreVein;
import spacebees.world.feature.HiveGenNether;
import spacebees.world.feature.HiveGenOblivion;
import spacebees.world.feature.HiveGenUnderground;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;

public enum HiveDescription implements IHiveDescription {
//	MOON(HiveType.MOON, 3.0f, HiveManager.genHelper.tree());
	MOON(HiveType.MOON, 1.0f, HiveManager.genHelper.ground(GCBlocks.blockMoon));
	


	private static boolean logSpawns = Config.logHiveSpawns;

	private final HiveType hiveType;
	private final float genChance;
	private final List<BiomeDictionary.Type> biomes = new ArrayList<BiomeDictionary.Type>();
	private final IHiveGen hiveGen;

	private HiveDescription(HiveType hiveType, float genChance, IHiveGen hiveGen)
	{
		this.hiveType = hiveType;
		this.genChance = genChance;
		this.hiveGen = hiveGen;
	}

	public static void initHiveData()
	{

		//TODO Fix this
		MOON.biomes.add(BiomeDictionary.Type.HOT);
		MOON.biomes.add(BiomeDictionary.Type.COLD);
		MOON.biomes.add(BiomeDictionary.Type.WET);
		MOON.biomes.add(BiomeDictionary.Type.DRY);
		MOON.biomes.add(BiomeDictionary.Type.SAVANNA);
		MOON.biomes.add(BiomeDictionary.Type.CONIFEROUS);
		MOON.biomes.add(BiomeDictionary.Type.JUNGLE);
		MOON.biomes.add(BiomeDictionary.Type.SPOOKY);
		MOON.biomes.add(BiomeDictionary.Type.DEAD);
		MOON.biomes.add(BiomeDictionary.Type.LUSH);
		MOON.biomes.add(BiomeDictionary.Type.NETHER);
		MOON.biomes.add(BiomeDictionary.Type.END);
		MOON.biomes.add(BiomeDictionary.Type.MUSHROOM);
		MOON.biomes.add(BiomeDictionary.Type.MAGICAL);
		MOON.biomes.add(BiomeDictionary.Type.OCEAN);
		MOON.biomes.add(BiomeDictionary.Type.RIVER);
		MOON.biomes.add(BiomeDictionary.Type.WATER);
		MOON.biomes.add(BiomeDictionary.Type.MESA);
		MOON.biomes.add(BiomeDictionary.Type.FOREST);
		MOON.biomes.add(BiomeDictionary.Type.PLAINS);
		MOON.biomes.add(BiomeDictionary.Type.HILLS);
		MOON.biomes.add(BiomeDictionary.Type.SWAMP);
		MOON.biomes.add(BiomeDictionary.Type.SANDY);
		MOON.biomes.add(BiomeDictionary.Type.SNOWY);
		MOON.biomes.add(BiomeDictionary.Type.WASTELAND);
		MOON.biomes.add(BiomeDictionary.Type.BEACH);

	}

	@Override
	public IHiveGen getHiveGen()
	{
		return hiveGen;
	}

	@Override
	public Block getBlock()
	{
		return Config.hive;
	}

	@Override
	public int getMeta()
	{
		return hiveType.ordinal();
	}

	@Override
	public boolean isGoodBiome(BiomeGenBase biome)
	{
		BiomeDictionary.Type[] types = BiomeDictionary.getTypesForBiome(biome);
		for (BiomeDictionary.Type type : types)
		{
			if (biomes.contains(type))
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isGoodHumidity(EnumHumidity humidity)
	{
		return true;
	}

	@Override
	public boolean isGoodTemperature(EnumTemperature temperature)
	{
		return true;
	}

	@Override
	public float getGenChance()
	{
		return genChance;
	}

	@Override
	public void postGen(World world, int x, int y, int z)
	{
		if (logSpawns)
		{
			LogHelper.info("Spawned " + this.toString().toLowerCase(Locale.ENGLISH) + " hive at: X " + x + ", Y: " + y + ", Z: " + z);
		}
	}
}
