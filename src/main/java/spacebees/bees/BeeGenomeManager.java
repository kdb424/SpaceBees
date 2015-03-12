package spacebees.bees;


import spacebees.main.utils.ItemInterface;
import spacebees.main.utils.compat.ExtraBeesHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.apiculture.EnumBeeType;
import forestry.api.genetics.IAllele;

/**
 * Simply a class to hold all the functions to manage species' default genomes.
 *
 * @author MysteriousAges
 */
public class BeeGenomeManager
{
	// Basic genome for All spacebees.
	private static IAllele[] getTemplateModBase()
	{
		IAllele[] genome = new IAllele[EnumBeeChromosome.values().length];

		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.MOON;
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedSlowest");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanShorter");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityLow");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceNone");
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolFalse");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceNone");
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolFalse");
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolFalse");
		//TODO Different Flowers
		genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.getBaseAllele("flowersVanilla");
		genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringSlowest");
		genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryDefault");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectNone");

		return genome;
	}
	
	//TODO Re-visit configuration when things are more finalized
	public static IAllele[] getTemplateMoon()
	{
		IAllele[] genome = getTemplateModBase();
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.MOON;
		return genome;
	}
	
	public static IAllele[] getTemplateLunation()
	{
		IAllele[] genome = getTemplateMoon();
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.LUNATION;
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceUp1");
		return genome;
	}
	public static IAllele[] getTemplateCore()
	{
		IAllele[] genome = getTemplateLunation();
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.CORE;
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceUp2");
		return genome;
	}
	public static IAllele[] getTemplateMars()
	{
		IAllele[] genome = getTemplateModBase();
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.MARS;		
		return genome;
	}
	public static IAllele[] getTemplateSatellite()
	{
		IAllele[] genome = getTemplateMars();
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.SATELLITE;
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceUp1");
		return genome;
	}
	public static IAllele[] getTemplateTerra()
	{
		IAllele[] genome = getTemplateSatellite();		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TERRA;
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceUp2");		
		return genome;
	}

	public static IAllele[] getTemplateAsteroid()
	{
		IAllele[] genome = getTemplateModBase();		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.ASTEROID;		
		return genome;
	}
	public static IAllele[] getTemplateTerrene()
	{
		IAllele[] genome = getTemplateAsteroid();
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TERRENE;
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceUp1");		
		return genome;
	}
	public static IAllele[] getTemplatePlanetoid()
	{
		IAllele[] genome = getTemplateTerrene();		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.PLANETOID;
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceUp2");		
		return genome;
	}
	
	
	
	public static IAllele[] addRainResist(IAllele[] genome)
	{
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolTrue");
		
		return genome;
	}
	

	/*--------------------- Other Stuff ---------------------------------- */
	
	public static ItemStack getBeeNBTForSpecies(BeeSpecies species, EnumBeeType beeType)
	{
		ItemStack taggedBee;
		switch (beeType)
		{
			case PRINCESS:
				taggedBee = ItemInterface.getItemStack("beePrincessGE");
				break;
			case QUEEN:
				taggedBee = ItemInterface.getItemStack("beeQueenGE");
				break;
			case DRONE:
			default:
				taggedBee = ItemInterface.getItemStack("beeDroneGE");
				break;
		}
		
		NBTTagCompound tags = new NBTTagCompound();
		
		addGeneToCompound(EnumBeeChromosome.SPECIES, species, tags);
		
		taggedBee.setTagCompound(tags);
		
		return taggedBee;
	}
	
	private static void addGeneToCompound(EnumBeeChromosome gene, IAllele allele, NBTTagCompound compound)
	{
		NBTTagCompound geneRoot = new NBTTagCompound();
		compound.setTag("Genome", geneRoot);
		NBTTagList chromosomes = new NBTTagList();
		geneRoot.setTag("Chromosomes", chromosomes);
		
		NBTTagCompound selectedGene = new NBTTagCompound();
		chromosomes.appendTag(selectedGene);
		
		selectedGene.setByte("Slot", (byte)gene.ordinal());
		selectedGene.setString("UID0", allele.getUID());
		selectedGene.setString("UID1", allele.getUID());
	}
}
