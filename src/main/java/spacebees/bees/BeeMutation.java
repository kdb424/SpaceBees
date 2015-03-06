package spacebees.bees;

import java.util.ArrayList;
import java.util.Collection;

import spacebees.main.Config;
import spacebees.main.utils.LocalizationManager;
import spacebees.main.utils.MoonPhase;
import spacebees.main.utils.compat.ExtraBeesHelper;
import spacebees.main.utils.compat.ForestryHelper;
import spacebees.main.utils.compat.ThermalExpansionHelper;
import spacebees.main.utils.compat.RedstoneArsenalHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.FMLLog;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.IBeeMutation;
import forestry.api.apiculture.IBeeRoot;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IGenome;

public class BeeMutation implements IBeeMutation
{	
	public static void setupMutations()
	{
		IAlleleBeeSpecies baseA, baseB;
		BeeMutation mutation;
		
		// Forestry + These -> Common
		
//		BeeSpecies[] magicMundane = new BeeSpecies[] { BeeSpecies.MYSTICAL, BeeSpecies.SORCEROUS, BeeSpecies.UNUSUAL, BeeSpecies.ATTUNED };
//		String[] forestryMundane = new String[] { "Forest", "Meadows", "Modest", "Wintry", "Tropical", "Marshy" };
//		String[] binnieMundane = new String[] { "marble", "rock", "water", "basalt" };
//		
//		for (BeeSpecies species : magicMundane)
//		{
//			for (String str : forestryMundane)
//			{
//				new BeeMutation(species, Allele.getBaseSpecies(str), ForestryHelper.getTemplateForestryForSpecies("Common"), 15);
//			}
//			if (ExtraBeesHelper.isActive())
//			{
//				for (String str : binnieMundane)
//				{
//					FMLLog.info("Registering %s", str);
//					try {
//						new BeeMutation(species, Allele.getExtraSpecies(str), ForestryHelper.getTemplateForestryForSpecies("Common"), 15);
//					} catch (Exception e) {
//						FMLLog.info("Unable to register! This mutation will not be available.");
//					}
//				}
//			}
//			new BeeMutation(species, Allele.getBaseSpecies("Common"), ForestryHelper.getTemplateForestryForSpecies("Cultivated"), 12);
//			new BeeMutation(species, Allele.getBaseSpecies("Cultivated"), BeeSpecies.ELDRITCH, 12);
//		}
		
//		new BeeMutation(Allele.getBaseSpecies("Cultivated"), BeeSpecies.ELDRITCH, BeeSpecies.ESOTERIC, 10);
//		new BeeMutation(BeeSpecies.ELDRITCH, BeeSpecies.ESOTERIC, BeeSpecies.MYSTERIOUS, 8);
//		new BeeMutation(BeeSpecies.ESOTERIC, BeeSpecies.MYSTERIOUS, BeeSpecies.ARCANE, 8)
//			.setMoonPhaseBonus(MoonPhase.WAXING_CRESCENT, MoonPhase.WAXING_GIBBOUS, 1.2f);
		

		
		
		if (RedstoneArsenalHelper.isActive())
		{
//			new BeeMutation(BeeSpecies.TE_ELECTRUM, BeeSpecies.TE_DESTABILIZED, BeeSpecies.RSA_FLUXED, 10)			
//				.setBlockRequired("blockElectrumFlux");
		}
		
		if (ThermalExpansionHelper.isActive())
		{
//			new BeeMutation(BeeSpecies.TIN, BeeSpecies.COPPER, BeeSpecies.TE_BRONZE, 12)
//				.setBlockRequired("blockBronze");
//			
//			new BeeMutation(BeeSpecies.GOLD, BeeSpecies.SILVER, BeeSpecies.TE_ELECTRUM, 10)
//				.setBlockRequired("blockElectrum");
//			
//			new BeeMutation(BeeSpecies.IRON, BeeSpecies.ESOTERIC, BeeSpecies.TE_NICKEL, 14)
//				.setBlockRequired("blockNickel");
//			
//			new BeeMutation(BeeSpecies.IRON, BeeSpecies.TE_NICKEL, BeeSpecies.TE_INVAR, 14)
//				.setBlockRequired("blockInvar");
//			
//			new BeeMutation(BeeSpecies.TE_NICKEL, BeeSpecies.TE_INVAR, BeeSpecies.TE_PLATINUM, 10)
//				.setBlockRequired("blockPlatinum");
//			
//			new BeeMutation(BeeSpecies.TIN, BeeSpecies.COPPER, BeeSpecies.TE_BRONZE, 12)
//				.setBlockRequired("blockBronze");
//			
//			new BeeMutation(BeeSpecies.SPITEFUL, BeeSpecies.TIN, BeeSpecies.TE_COAL, 12)
//				.setBlockRequired(Blocks.coal_ore);
//			
//			new BeeMutation(BeeSpecies.SPITEFUL, Allele.getBaseSpecies("Industrious"), BeeSpecies.TE_DESTABILIZED, 12)
//				.setBlockRequired(Blocks.redstone_ore);
//			
//			new BeeMutation(BeeSpecies.SMOULDERING, BeeSpecies.INFERNAL, BeeSpecies.TE_LUX, 12)
//				.setBlockRequired(Blocks.glowstone);
//			
//			new BeeMutation(BeeSpecies.SMOULDERING, Allele.getBaseSpecies("Austere"), BeeSpecies.TE_DANTE, 12)
//				.setBiomeRequired(BiomeDictionary.Type.NETHER);
//			
//			new BeeMutation(BeeSpecies.TE_DANTE, BeeSpecies.TE_COAL, BeeSpecies.TE_PYRO, 8)
//				.setBiomeRequired(BiomeDictionary.Type.NETHER);
//			
//			new BeeMutation(BeeSpecies.SKULKING, Allele.getBaseSpecies("Wintry"), BeeSpecies.TE_BLIZZY, 12);
//
//			new BeeMutation(BeeSpecies.TE_BLIZZY, Allele.getBaseSpecies("Icy"), BeeSpecies.TE_GELID, 8);
//
//			new BeeMutation(BeeSpecies.TE_PLATINUM, BeeSpecies.OBLIVION, BeeSpecies.TE_WINSOME, 12);
//
//			new BeeMutation(BeeSpecies.TE_WINSOME, BeeSpecies.TE_COAL, BeeSpecies.TE_ENDEARING, 8)
//				.setBlockRequired("blockEnderium");	
		}
	}
	
	private IAllele parent1;
	private IAllele parent2;
	private IAllele mutationTemplate[];
	private int baseChance;
	private boolean isSecret;
	private boolean isMoonRestricted;
	private MoonPhase moonPhaseStart;
	private MoonPhase moonPhaseEnd;
	private float moonPhaseMutationBonus;
	private boolean nodeRequired;
	private double nodeRange;
	private boolean requiresBlock;
	private Block requiredBlock;
	private int requiredBlockMeta;
	private String requiredBlockOreDictEntry;
	private String requiredBlockName;
	private BiomeDictionary.Type requiredBiomeType;
	
	public BeeMutation(IAlleleBeeSpecies species0, IAlleleBeeSpecies species1, BeeSpecies resultSpecies, int percentChance)
	{
		this(species0, species1, resultSpecies.getGenome(), percentChance);
	}

	public BeeMutation(IAlleleBeeSpecies species0, IAlleleBeeSpecies species1, IAllele[] resultSpeciesGenome, int percentChance)
	{
		this.parent1 = species0;
		this.parent2 = species1;
		this.mutationTemplate = resultSpeciesGenome;
		this.baseChance = percentChance;
		this.isSecret = false;
		this.isMoonRestricted = false;
		this.moonPhaseMutationBonus = -1f;
		this.requiresBlock = false;
		this.requiredBlockMeta = OreDictionary.WILDCARD_VALUE;
		this.requiredBlockOreDictEntry = null;
		this.requiredBiomeType = null;
		this.requiredBlockName = null;
		
		BeeManager.beeRoot.registerMutation(this);
	}

	@Override
	public float getChance(IBeeHousing housing, IAllele allele0, IAllele allele1, IGenome genome0, IGenome genome1)
	{
		float finalChance = 0f;
		float chance = this.baseChance * 1f;
		
		if (this.arePartners(allele0, allele1))
		{
			// This mutation applies. Continue calculation.
			if (this.moonPhaseStart != null && this.moonPhaseEnd != null)
			{
				// Only occurs during the phases.
				if (this.isMoonRestricted && !MoonPhase.getMoonPhase(housing.getWorld()).isBetween(this.moonPhaseStart, this.moonPhaseEnd))
				{
					chance = 0;
				}
				else if (this.moonPhaseMutationBonus != -1f)
				{
					// There is a bonus to this mutation during moon phases...
					if (MoonPhase.getMoonPhase(housing.getWorld()).isBetween(this.moonPhaseStart, this.moonPhaseEnd))
					{
						chance = (int)(chance * this.moonPhaseMutationBonus);
					}
				}
			}
			
			if (this.requiresBlock)
			{
				Block blockBelow;
				int blockMeta;
				int i = 1;
				do
				{
					blockBelow = housing.getWorld().getBlock(housing.getXCoord(), housing.getYCoord() - i,
							housing.getZCoord());
					blockMeta = housing.getWorld().getBlockMetadata(housing.getXCoord(), housing.getYCoord() - i, housing.getZCoord());
					++i;
				}
				while (blockBelow != null && (blockBelow instanceof IBeeHousing || blockBelow == Config.fAlvearyBlock));
				
				if (this.requiredBlockOreDictEntry != null)
				{
					int dicId = OreDictionary.getOreID(new ItemStack(blockBelow, 1, blockMeta));
					if (dicId != -1)
					{
						if (!OreDictionary.getOreName(dicId).equals(this.requiredBlockOreDictEntry))
						{
							chance = 0;
						}
					}
					else
					{
						chance = 0;
					}
				}
				else if (this.requiredBlock != blockBelow || (this.requiredBlockMeta != OreDictionary.WILDCARD_VALUE &&
						this.requiredBlockMeta != blockMeta))
				{
					chance = 0;
				}
			}
			
			if (this.requiredBiomeType != null)
			{
				BiomeDictionary.Type[] types = BiomeDictionary.getTypesForBiome(housing.getWorld().getBiomeGenForCoords(housing.getXCoord(), housing.getZCoord()));
				boolean found = false;
				for (int i = 0; i < types.length; ++i)
				{
					if (this.requiredBiomeType == types[i])
					{
						found = true;
						break;
					}
				}
				if (!found)
				{
					chance = 0;
				}
			}
			
			finalChance = Math.round(chance
					* housing.getMutationModifier((IBeeGenome) genome0,
							(IBeeGenome) genome1, chance)
					* BeeManager.beeRoot.getBeekeepingMode(housing.getWorld())
							.getMutationModifier((IBeeGenome) genome0,
									(IBeeGenome) genome1, chance));
		}
		
		return finalChance;
	}

	@Override
	public IAllele getAllele0()
	{
		return parent1;
	}

	@Override
	public IAllele getAllele1()
	{
		return parent2;
	}

	@Override
	public IAllele[] getTemplate()
	{
		return mutationTemplate;
	}

	@Override
	public float getBaseChance()
	{
		return baseChance;
	}

	@Override
	public boolean isPartner(IAllele allele)
	{
		return parent1.getUID().equals(allele.getUID()) || parent2.getUID().equals(allele.getUID());
	}

	@Override
	public IAllele getPartner(IAllele allele)
	{
		IAllele val = parent1;
		if (val.getUID().equals(allele.getUID()))
			val = parent2;
		return val;
	}

	@Override
	public Collection<String> getSpecialConditions()
	{
		ArrayList<String> conditions = new ArrayList<String>();
		
		if (this.isMoonRestricted && moonPhaseStart != null && moonPhaseEnd != null)
		{
			if (moonPhaseStart != moonPhaseEnd)
			{
				conditions.add(String.format(LocalizationManager.getLocalizedString("research.requiresPhase"),
						moonPhaseStart.getLocalizedName(), moonPhaseEnd.getLocalizedName()));
			}
			else
			{
				conditions.add(String.format(LocalizationManager.getLocalizedString("research.requiresPhaseSingle"),
						moonPhaseStart.getLocalizedName()));
			}
		}
		
		if (this.requiresBlock)
		{
			if (this.requiredBlockName != null)
			{
				conditions.add(String.format(LocalizationManager.getLocalizedString("research.requiresBlock"),
						LocalizationManager.getLocalizedString(this.requiredBlockName)));
			}
			else if (this.requiredBlockOreDictEntry != null)
			{
				ArrayList<ItemStack> ores = OreDictionary.getOres(this.requiredBlockOreDictEntry);
				if (ores.size() > 0)
				{
					conditions.add(String.format(LocalizationManager.getLocalizedString("research.requiresBlock"), ores.get(0).getDisplayName()));
				}
			}
			else
			{
				int meta = 0;
				if (this.requiredBlockMeta != OreDictionary.WILDCARD_VALUE)
				{
					meta = this.requiredBlockMeta;
				}
				conditions.add(String.format(LocalizationManager.getLocalizedString("research.requiresBlock"), 
						new ItemStack(this.requiredBlock, 1, this.requiredBlockMeta).getDisplayName()));
			}
		}
		
		if (this.requiredBiomeType != null)
		{
			String biomeName = this.requiredBiomeType.name().substring(0, 1) + this.requiredBiomeType.name().substring(1).toLowerCase();
			conditions.add(String.format(LocalizationManager.getLocalizedString("research.requiresBiome"), biomeName));
		}
		
		return conditions;
	}

	@Override
	public IBeeRoot getRoot()
	{
		return BeeManager.beeRoot;
	}
	
	public boolean arePartners(IAllele alleleA, IAllele alleleB)
	{
		return (this.parent1.getUID().equals(alleleA.getUID())) && this.parent2.getUID().equals(alleleB.getUID()) ||
				this.parent1.getUID().equals(alleleB.getUID()) && this.parent2.getUID().equals(alleleA.getUID());
	}
	
	public BeeMutation setSecret()
	{
		this.isSecret = true;
		
		return this;
	}

	public boolean isSecret()
	{
		return isSecret;
	}
	
	public BeeMutation setBlockRequired(Block block)
	{
		this.requiresBlock = true;
		this.requiredBlock = block;
		
		return this;
	}
	
	public BeeMutation setBlockAndMetaRequired(Block block, int meta)
	{
		this.requiresBlock = true;
		this.requiredBlock = block;
		this.requiredBlockMeta = meta;
		
		return this;
	}
	
	public BeeMutation setBlockRequired(String oreDictEntry)
	{
		this.requiresBlock = true;
		this.requiredBlockOreDictEntry = oreDictEntry;
		
		return this;
	}
	
	public BeeMutation setBlockRequiredNameOverride(String blockName)
	{
		this.requiredBlockName = blockName;
		
		return this;
	}
	
	public BeeMutation setMoonPhaseRestricted(MoonPhase begin, MoonPhase end)
	{
		this.isMoonRestricted = true;
		this.moonPhaseStart = begin;
		this.moonPhaseEnd = end;
		
		return this;
	}
	
	public BeeMutation setMoonPhaseBonus(MoonPhase begin, MoonPhase end, float mutationBonus)
	{
		this.moonPhaseMutationBonus = mutationBonus;
		this.moonPhaseStart = begin;
		this.moonPhaseEnd = end;
		
		return this;
	}
	
	public BeeMutation setBiomeRequired(BiomeDictionary.Type biomeType)
	{
		this.requiredBiomeType = biomeType;
		
		return this;
	}
}
