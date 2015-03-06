package spacebees.bees;

import spacebees.main.utils.LocalizationManager;
import spacebees.main.utils.compat.ThermalExpansionHelper;
import net.minecraft.potion.Potion;
import forestry.api.apiculture.IAlleleBeeEffect;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IAlleleEffect;
import forestry.api.genetics.IAlleleFlowers;
import forestry.api.genetics.IAlleleRegistry;

public class Allele implements IAllele
{
	public static AlleleFloat speedBlinding;
	
	public static IAlleleBeeEffect forestryBaseEffect;
	
	public static IAlleleEffect effectCleansing;
	public static IAlleleEffect effectDigSpeed;
	public static IAlleleEffect effectMoveSpeed;
	public static IAlleleEffect effectSlowSpeed;
	public static IAlleleEffect effectWithering;
	public static IAlleleEffect effectInvisibility;
	
	public static void setupAdditionalAlleles()
	{
		forestryBaseEffect = (IAlleleBeeEffect)getBaseAllele("effectNone");
		
		Allele.speedBlinding = new AlleleFloat("speedBlinding", 2f, false);
		
		

		Allele.effectCleansing = new AlleleEffectCure("Curative", false);
		Allele.effectDigSpeed = new AlleleEffectPotion("DigSpeed", Potion.digSpeed, 15, false);
		Allele.effectMoveSpeed = new AlleleEffectPotion("MoveSpeed", Potion.moveSpeed, 10, false);
		Allele.effectSlowSpeed = new AlleleEffectPotion("SlowSpeed", Potion.moveSlowdown, 3, false).setMalicious();
		Allele.effectWithering = new AlleleEffectPotion("Withering", Potion.wither, 10, false).setMalicious();
		
		
		Allele.effectInvisibility = new AlleleEffectPotion("Invisibility", Potion.invisibility, 10, false);

		
	}
	
	public static void registerDeprecatedAlleleReplacements()
	{
		IAlleleRegistry registry = AlleleManager.alleleRegistry;

		registry.registerDeprecatedAlleleReplacement("thaumicbees.fertilityHighDominant",	Allele.getBaseAllele("fertilityHigh"));
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speedBlinding",			speedBlinding);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.effectNodeAttract",		Allele.getBaseAllele("effectBeatific"));
		registry.registerDeprecatedAlleleReplacement("thaumicbees.effectNodePurify",		Allele.getBaseAllele("effectBeatific"));
		registry.registerDeprecatedAlleleReplacement("thaumicbees.effectNodeFlux",			Allele.getBaseAllele("effectBeatific"));
		registry.registerDeprecatedAlleleReplacement("thaumicbees.effectNodeCharge",		Allele.getBaseAllele("effectBeatific"));
		registry.registerDeprecatedAlleleReplacement("magicbees.effectNodeAttract", 		Allele.getBaseAllele("effectBeatific"));
		registry.registerDeprecatedAlleleReplacement("magicbees.effectNodePurify",		Allele.getBaseAllele("effectBeatific"));
		registry.registerDeprecatedAlleleReplacement("magicbees.effectNodeFlux",			Allele.getBaseAllele("effectBeatific"));
		registry.registerDeprecatedAlleleReplacement("magicbees.effectNodeCharge",		Allele.getBaseAllele("effectBeatific"));
		registry.registerDeprecatedAlleleReplacement("thaumicbees.effectCurative",			effectCleansing);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.effecteffectDigSpeed",	effectDigSpeed);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.effecteffectMoveSpeed",	effectMoveSpeed);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.effecteffectSlowSpeed",	effectSlowSpeed);
		//registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesEsoteric",			BeeSpecies.ESOTERIC);
		
	}

	public static IAlleleBeeSpecies getBaseSpecies(String name)
	{
		return (IAlleleBeeSpecies) AlleleManager.alleleRegistry.getAllele((new StringBuilder()).append("forestry.species").append(name).toString());
	}
	
	public static IAlleleBeeSpecies getExtraSpecies(String name)
	{
		return (IAlleleBeeSpecies) AlleleManager.alleleRegistry.getAllele((new StringBuilder()).append("extrabees.species.").append(name).toString());
	}
	
	public static IAllele getBaseAllele(String name)
	{
		return AlleleManager.alleleRegistry.getAllele("forestry." + name);
	}
	
	public static IAllele getAllele(String name)
	{
		return AlleleManager.alleleRegistry.getAllele(name);
	}
	
	private String uid;
	private boolean dominant;

	public Allele(String id, boolean isDominant)
	{
		this.uid = "spacebees." + id;
		this.dominant = isDominant;
		AlleleManager.alleleRegistry.registerAllele(this);
	}

	@Override
	public String getUID()
	{
		return this.uid;
	}

	@Override
	public boolean isDominant()
	{
		return this.dominant;
	}

	@Override
	public String getName()
	{
		return LocalizationManager.getLocalizedString(getUID());
	}

	@Override
	public String getUnlocalizedName() {
		return LocalizationManager.getLocalizedString(getUID());
	}

}
