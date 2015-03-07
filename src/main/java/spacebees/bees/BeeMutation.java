package spacebees.bees;

import java.util.ArrayList;
import java.util.Collection;

import spacebees.main.Config;
import spacebees.main.utils.LocalizationManager;
import spacebees.main.utils.LogHelper;
import spacebees.main.utils.MoonPhase;
import spacebees.main.utils.compat.ExtraBeesHelper;
import spacebees.main.utils.compat.ForestryHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.oredict.OreDictionary;
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
		
		BeeSpecies[] spaceMundane = new BeeSpecies[] { BeeSpecies.MOON };
		String[] forestryMundane = new String[] { "Forest", "Meadows", "Modest", "Wintry", "Tropical", "Marshy" };
		String[] binnieMundane = new String[] { "marble", "rock", "water", "basalt" };
		
		for (BeeSpecies species : spaceMundane)
		{
			for (String str : forestryMundane)
			{
				new BeeMutation(species, Allele.getBaseSpecies(str), ForestryHelper.getTemplateForestryForSpecies("Common"), 15);
			}
			if (ExtraBeesHelper.isActive())
			{
				for (String str : binnieMundane)
				{
					LogHelper.info("Registering " + str);
					try
					{
						new BeeMutation(species, Allele.getExtraSpecies(str), ForestryHelper.getTemplateForestryForSpecies("Common"), 15);
					}
					catch (Exception e)
					{
						LogHelper.info("Unable to register! This mutation will not be available.");
					}
				}
			}
			new BeeMutation(species, Allele.getBaseSpecies("Common"), ForestryHelper.getTemplateForestryForSpecies("Cultivated"), 12);
//			new BeeMutation(species, Allele.getBaseSpecies("Cultivated"), BeeSpecies.ELDRITCH, 12);
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
		//this.nodeType = null;
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
					int[] dicId = OreDictionary.getOreIDs(new ItemStack(blockBelow, 1, blockMeta));
					if (dicId.length != 0)
					{
						if (!OreDictionary.getOreName(dicId[0]).equals(this.requiredBlockOreDictEntry))
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
					* housing.getMutationModifier((IBeeGenome)genome0,
					(IBeeGenome)genome1, chance)
					* BeeManager.beeRoot.getBeekeepingMode(housing.getWorld())
					.getMutationModifier((IBeeGenome)genome0,
							(IBeeGenome)genome1, chance));
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
