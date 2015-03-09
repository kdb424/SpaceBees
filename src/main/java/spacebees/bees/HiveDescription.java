package spacebees.bees;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import cpw.mods.fml.common.network.internal.FMLMessage;
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
import spacebees.world.feature.HiveGenMars;
import spacebees.world.feature.HiveGenNether;
import spacebees.world.feature.HiveGenOblivion;
import spacebees.world.feature.HiveGenUnderground;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;

public enum HiveDescription implements IHiveDescription {
	MOON(HiveType.MOON, 3.0f, HiveManager.genHelper.tree()), MARS(
			HiveType.MARS, 3.0f, HiveManager.genHelper.tree()),

	// TODO Get matadata block properly so it properly spawns
	// MOON(HiveType.MOON, 3.0f,
	// HiveManager.genHelper.ground(GCBlocks.blockMoon)),
	// MARS(HiveType.MARS, 3.0f,
	// HiveManager.genHelper.ground(GCBlocks.blockMoon)),
	// MARS(HiveType.MARS, 2.0f, new HiveGenUnderground(10, 10, 6)) {
	// @Override
	// public void postGen(World world, int x, int y, int z)
	// {
	// super.postGen(world, x, y, z);
	// Random random = world.rand;
	// FeatureOreVein.redstoneGen.generateVein(world, random, x + 1, y, z, 2);
	// FeatureOreVein.redstoneGen.generateVein(world, random, x - 1, y, z, 2);
	// FeatureOreVein.redstoneGen.generateVein(world, random, x, y + 1, z, 2);
	// FeatureOreVein.redstoneGen.generateVein(world, random, x, y - 1, z, 2);
	// FeatureOreVein.redstoneGen.generateVein(world, random, x, y, z + 1, 2);
	// FeatureOreVein.redstoneGen.generateVein(world, random, x, y, z - 1, 2);
	// }
	// },
	;

	private static boolean logSpawns = Config.logHiveSpawns;

	private final HiveType hiveType;
	private final float genChance;
	private final List<BiomeDictionary.Type> biomes = new ArrayList<BiomeDictionary.Type>();
	private final IHiveGen hiveGen;

	private HiveDescription(HiveType hiveType, float genChance, IHiveGen hiveGen) {
		this.hiveType = hiveType;
		this.genChance = genChance;
		this.hiveGen = hiveGen;
	}

	public static void initHiveData() {

	}

	@Override
	public IHiveGen getHiveGen() {
		return hiveGen;
	}

	@Override
	public Block getBlock() {
		return Config.hive;
	}

	@Override
	public int getMeta() {
		return hiveType.ordinal();
	}

	@Override
	public boolean isGoodBiome(BiomeGenBase biome) {
		BiomeDictionary.Type[] types = BiomeDictionary.getTypesForBiome(biome);
		for (BiomeDictionary.Type type : types) {
			if (biomes.contains(type)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isGoodHumidity(EnumHumidity humidity) {
		return true;
	}

	@Override
	public boolean isGoodTemperature(EnumTemperature temperature) {
		return true;
	}

	@Override
	public float getGenChance() {
		return genChance;
	}

	@Override
	public void postGen(World world, int x, int y, int z) {
		if (logSpawns) {
			LogHelper.info("Spawned "
					+ this.toString().toLowerCase(Locale.ENGLISH)
					+ " hive at: X " + x + ", Y: " + y + ", Z: " + z);
		}
	}
}
