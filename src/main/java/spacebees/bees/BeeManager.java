package spacebees.bees;

import forestry.api.apiculture.IBeeRoot;
import forestry.api.genetics.AlleleManager;
import spacebees.block.types.HiveType;
import spacebees.item.types.DropType;
import spacebees.main.Config;

public class BeeManager
{
	public static IBeeRoot beeRoot;
	
	public static void ititializeBees()
	{
		beeRoot = (IBeeRoot)AlleleManager.alleleRegistry.getSpeciesRoot("rootBees");
		
		Allele.setupAdditionalAlleles();
		BeeSpecies.setupBeeSpecies();
		Allele.registerDeprecatedAlleleReplacements();
		BeeMutation.setupMutations();
		
		HiveType.initHiveData();
		
//		beeRoot.setResearchSuitability(Config.drops.getStackForType(DropType.INTELLECT), 0.5f);
	}
}
