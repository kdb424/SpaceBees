package spacebees.bees;


import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import cpw.mods.fml.common.registry.GameRegistry;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.apiculture.EnumBeeType;
import forestry.api.genetics.IAllele;

/**
 * Simply a class to hold all the functions to manage species' default genomes.
 * @author MysteriousAges
 *
 */
public class BeeGenomeManager
{
	// Basic genome for All space bees.
	private static IAllele[] getTemplateModBase()
	{
		IAllele[] genome = new IAllele[EnumBeeChromosome.values().length];

//		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.MOON;
		genome[EnumBeeChromosome.SPEED.ordinal()] =  Allele.getBaseAllele("speedSlowest");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanShorter");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityNormal");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceNone");
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolFalse");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceNone");
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolFalse");
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolFalse");
		//TODO Add Moon Flower
		genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.getBaseAllele("flowersVanilla");
		genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringSlowest");
		genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryDefault");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectNone");

		return genome;
	}

	public static IAllele[] addRainResist(IAllele[] genome)
	{		
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolTrue");
		
		return genome;
	}
	
	
	public static IAllele[] getTemplateMoon()
	{
		IAllele[] genome = getTemplateMoonBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.MOON;
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanLong");
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedNorm");
		genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryLarger");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectMisanthrope");
		
		return genome;
	}
	private static IAllele[] getTemplateMoonBase()
	{
		IAllele[] genome = getTemplateModBase();

		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.MOON;
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceDown2");
		genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.getBaseAllele("flowersNether");
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectAggressive");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanShort");
		
		return genome;
	}
	
	
	/*--------------------- Other Stuff ---------------------------------- */
	
	public static ItemStack getBeeNBTForSpecies(BeeSpecies species, EnumBeeType beeType)
	{
		ItemStack taggedBee = null;
		switch (beeType)
		{
			case PRINCESS:
				//TODO Use FML GameRegistry
				//TODO See if change worked
				taggedBee = new ItemStack(GameRegistry.findItem("forestry", "beePrincessGE"));
				//taggedBee = ItemInterface.getItem("beePrincessGE");
				break;
			case QUEEN:
				taggedBee = new ItemStack(GameRegistry.findItem("forestry", "beeQueenGE"));
				//taggedBee = ItemInterface.getItem("beeQueenGE");
				break;
			case DRONE:
			default:
				taggedBee = new ItemStack(GameRegistry.findItem("forestry", "beeDroneGE"));
				//taggedBee = ItemInterface.getItem("beeDroneGE");
				break;
		}
		
		NBTTagCompound tags = new NBTTagCompound();
		
		addGeneToCompound(EnumBeeChromosome.SPECIES, species, tags);
		
		taggedBee.setTagCompound(tags);
		
		return taggedBee;
		//return null;
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
