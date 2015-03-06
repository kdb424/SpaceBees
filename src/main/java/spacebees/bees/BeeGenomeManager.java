package spacebees.bees;


import cpw.mods.fml.common.registry.GameRegistry;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.apiculture.EnumBeeType;
import forestry.api.genetics.IAllele;
import spacebees.main.utils.compat.ExtraBeesHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * Simply a class to hold all the functions to manage species' default genomes.
 * @author MysteriousAges
 *
 */
public class BeeGenomeManager
{
	// Basic genome for All thaumic bees.
	private static IAllele[] getTemplateModBase()
	{
		IAllele[] genome = new IAllele[EnumBeeChromosome.values().length];

//		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.MYSTICAL;
		genome[EnumBeeChromosome.SPEED.ordinal()] =  Allele.getBaseAllele("speedSlowest");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanShorter");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityNormal");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceNone");
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolFalse");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceNone");
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolFalse");
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolFalse");
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
	
	
	
	/*--------------------- Other Stuff ---------------------------------- */
	
	public static ItemStack getBeeNBTForSpecies(BeeSpecies species, EnumBeeType beeType)
	{
		ItemStack taggedBee;
		switch (beeType)
		{
			case PRINCESS:
				//TODO Use FML GameRegistry
				//taggedBee = ItemInterface.getItem("beePrincessGE");
				break;
			case QUEEN:
				//taggedBee = ItemInterface.getItem("beeQueenGE");
				break;
			case DRONE:
			default:
				//taggedBee = ItemInterface.getItem("beeDroneGE");
				break;
		}
		
		NBTTagCompound tags = new NBTTagCompound();
		
		addGeneToCompound(EnumBeeChromosome.SPECIES, species, tags);
		
		//taggedBee.setTagCompound(tags);
		
		//return taggedBee;
		return null;
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
