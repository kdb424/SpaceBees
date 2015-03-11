package spacebees.bees;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import spacebees.block.types.HiveType;
import spacebees.main.Config;
import spacebees.main.utils.LogHelper;
import forestry.api.apiculture.hives.HiveManager;
import forestry.api.apiculture.hives.IHiveDescription;
import forestry.api.apiculture.hives.IHiveGen;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;

public enum HiveDescription implements IHiveDescription {
	MOON(HiveType.MOON, 3.0f, HiveManager.genHelper.tree()),
	MARS(HiveType.MARS, 3.0f, HiveManager.genHelper.tree()),
	ASTEROID(HiveType.ASTEROID, 3.0f, HiveManager.genHelper.tree()),
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
