package spacebees.bees;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import spacebees.item.types.CombType;
import spacebees.item.types.DropType;
import spacebees.item.types.NuggetType;
import spacebees.item.types.PollenType;
import spacebees.item.types.ResourceType;
import spacebees.main.CommonProxy;
import spacebees.main.Config;
import spacebees.main.utils.LocalizationManager;
import spacebees.main.utils.LogHelper;
import spacebees.main.utils.compat.ForestryHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.apiculture.EnumBeeType;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.IBeeRoot;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.core.IIconProvider;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IClassification;
import forestry.api.genetics.IIndividual;
import forestry.api.genetics.IMutation;

public enum BeeSpecies implements IAlleleBeeSpecies, IIconProvider
{
//	Places to add a bee
//	BeeSpecies
//	BeeMutation
//	BeeClasification
//	BeeGenomeManager
//	CombType
//	If Hive Bee
//	HiveType
//	HiveDescription
	
	//TODO Fix colors
	MOON("Moon", "Luna",
			BeeClassification.MOON, 0xFFFFFF, 0xFFFFFF, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false, true),
	LUNATION("Lunation", "Lunationis",
			BeeClassification.LUNATION, 0xFFFFFF, 0xEFEFDF, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false, true),
	CORE("Core", "Core",
			BeeClassification.CORE, 0xFFFFFF, 0x992222, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false, true),
	MARS("Mars", "March",
			BeeClassification.MARS, 0xFF0000, 0xFF2222, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false, true),
	SATELLITE("Satellite", "Satellite",
			BeeClassification.SATELLITE, 0xFF0000, 0xFF2222, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false, true),
	TERRA("Terra", "Terra",
			BeeClassification.TERRA, 0xFF0000, 0xFF2222, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false, true),
	;
	
	public static void setupBeeSpecies()
	{
		// Species must be set inactive prior to registration.

		MOON.addProduct(Config.combs.getStackForType(CombType.MOON), 15)
				.setGenome(BeeGenomeManager.getTemplateMoon())
				.register();
		LUNATION.addProduct(Config.combs.getStackForType(CombType.MOON), 15)
			.setGenome(BeeGenomeManager.getTemplateLunation())
			.register();
		//TODO Add custom comb/drop
		CORE.addProduct(Config.combs.getStackForType(CombType.MOON), 15)
			.setGenome(BeeGenomeManager.getTemplateCore())
			.register();
		MARS.addProduct(Config.combs.getStackForType(CombType.MARS), 15)
		.setGenome(BeeGenomeManager.getTemplateMars())
		.register();
		SATELLITE.addProduct(Config.combs.getStackForType(CombType.MARS), 15)
		.setGenome(BeeGenomeManager.getTemplateSatellite())
		.register();
		TERRA.addProduct(Config.combs.getStackForType(CombType.MARS), 15)
		.setGenome(BeeGenomeManager.getTemplateTerra())
		.register();

	}

	
	private String binomial;
	private String authority;
	private int primaryColour;
	private int secondaryColour;
	private EnumTemperature temperature;
	private EnumHumidity humidity;
	private boolean hasEffect;
	private boolean isSecret;
	private boolean isCounted;
	private boolean isActive;
	private boolean isNocturnal;
	private IClassification branch;
	private HashMap<ItemStack, Integer> products;
	private HashMap<ItemStack, Integer> specialties;
	private IAllele genomeTemplate[];
	private String uid;
	private boolean dominant;
	private int complexity;
	
	@SideOnly(Side.CLIENT)
	private IIcon[][] icons;
	
	private final static int defaultBodyColour = 0xFF7C26;
	
	private BeeSpecies(String speciesName, String genusName, IClassification classification, int firstColour,
					   EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean hasGlowEffect, boolean isSpeciesDominant)
	{
		this(speciesName, genusName, classification, firstColour, defaultBodyColour,
				preferredTemp, preferredHumidity, hasGlowEffect, true, true, isSpeciesDominant);
	}

	private BeeSpecies(String speciesName, String genusName, IClassification classification, int firstColour, int secondColour,
					   EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean hasGlowEffect, boolean isSpeciesDominant)
	{
		this(speciesName, genusName, classification, firstColour, secondColour, preferredTemp, preferredHumidity, hasGlowEffect, true, true, isSpeciesDominant);
	}

	private BeeSpecies(String speciesName, String genusName, IClassification classification, int firstColour, int secondColour,
					   EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean isSecret, boolean hasGlowEffect, boolean isSpeciesDominant)
	{
		this(speciesName, genusName, classification, firstColour, secondColour,
				preferredTemp, preferredHumidity, hasGlowEffect, isSecret, true, isSpeciesDominant);
	}

	private BeeSpecies(String speciesName, String genusName, IClassification classification, int firstColour,
					   EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean isSecret, boolean hasGlowEffect, boolean isSpeciesDominant)
	{
		this(speciesName, genusName, classification, firstColour, defaultBodyColour,
				preferredTemp, preferredHumidity, hasGlowEffect, isSecret, true, isSpeciesDominant);
	}

	private BeeSpecies(String speciesName, String genusName, IClassification classification, int firstColour, int secondColour,
					   EnumTemperature preferredTemp, EnumHumidity preferredHumidity,
					   boolean hasGlowEffect, boolean isSpeciesSecret, boolean isSpeciesCounted, boolean isSpeciesDominant)
	{
		this.uid = "spacebees.species" + speciesName;
		this.dominant = isSpeciesDominant;
		AlleleManager.alleleRegistry.registerAllele(this);
		binomial = genusName;
		authority = "MysteriousAges";
		primaryColour = firstColour;
		secondaryColour = secondColour;
		temperature = preferredTemp;
		humidity = preferredHumidity;
		hasEffect = hasGlowEffect;
		isSecret = isSpeciesSecret;
		isCounted = isSpeciesCounted;
		products = new HashMap();
		specialties = new HashMap();
		this.branch = classification;
		this.branch.addMemberSpecies(this);
		this.isActive = true;
		this.isNocturnal = false;
	}

	public BeeSpecies setGenome(IAllele genome[])
	{
		genomeTemplate = genome;
		return this;
	}

	public IAllele[] getGenome()
	{
		return genomeTemplate;
	}

	public BeeSpecies addProduct(ItemStack produce, int percentChance)
	{
		products.put(produce, percentChance);
		return this;
	}

	public BeeSpecies addSpecialty(ItemStack produce, int percentChance)
	{
		specialties.put(produce, Integer.valueOf(percentChance));
		return this;
	}

	public ItemStack getBeeItem(EnumBeeType beeType)
	{
		return BeeManager.beeRoot.getMemberStack(BeeManager.beeRoot.getBee(null, BeeManager.beeRoot.templateAsGenome(genomeTemplate)), beeType.ordinal());
	}

	@Override
	public String getName()
	{
		return LocalizationManager.getLocalizedString(getUID());
	}

	@Override
	public String getDescription()
	{
		return LocalizationManager.getLocalizedString(getUID() + ".description");
	}

	@Override
	public String getUnlocalizedName()
	{
		return getUID();
	}

	@Override
	public EnumTemperature getTemperature()
	{
		return temperature;
	}

	@Override
	public EnumHumidity getHumidity()
	{
		return humidity;
	}

	@Override
	public boolean hasEffect()
	{
		return hasEffect;
	}

	public BeeSpecies setInactive()
	{
		this.isActive = false;
		return this;
	}

	public boolean isActive()
	{
		return this.isActive;
	}

	@Override
	public boolean isSecret()
	{
		return isSecret;
	}

	@Override
	public boolean isCounted()
	{
		return isCounted;
	}

	@Override
	public String getBinomial()
	{
		return binomial;
	}

	@Override
	public String getAuthority()
	{
		return authority;
	}

	@Override
	public IClassification getBranch()
	{
		return this.branch;
	}

	@Override
	public HashMap getProducts()
	{
		return products;
	}

	@Override
	public HashMap getSpecialty()
	{
		return specialties;
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
	public IBeeRoot getRoot()
	{
		return BeeManager.beeRoot;
	}

	@Override
	public boolean isNocturnal()
	{
		return this.isNocturnal;
	}

	@Override
	public boolean isJubilant(IBeeGenome genome, IBeeHousing housing)
	{
		return true;
	}

	private BeeSpecies register()
	{
		BeeManager.beeRoot.registerTemplate(this.getGenome());
		if (!this.isActive)
		{
			AlleleManager.alleleRegistry.blacklistAllele(this.getUID());
		}
		return this;
	}

	@Override
	public int getIconColour(int renderPass)
	{
		int value = 0xffffff;
		if (renderPass == 0)
		{
			value = this.primaryColour;
		}
		else if (renderPass == 1)
		{
			value = this.secondaryColour;
		}
		return value;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIconProvider getIconProvider()
	{
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(EnumBeeType type, int renderPass)
	{
		return icons[type.ordinal()][Math.min(renderPass, 2)];
	}

	@Override
	public int getComplexity()
	{
		return 1 + getMutationPathLength(this, new ArrayList<IAllele>());
	}
	
	private int getMutationPathLength(IAllele species, ArrayList<IAllele> excludeSpecies)
	{
		int own = 1;
		int highest = 0;
		excludeSpecies.add(species);
		
		for (IMutation mutation : getRoot().getPaths(species, EnumBeeChromosome.SPECIES.ordinal()))
		{
			if (!excludeSpecies.contains(mutation.getAllele0()))
			{
				int otherAdvance = getMutationPathLength(mutation.getAllele0(), excludeSpecies);
				if (otherAdvance > highest)
					highest = otherAdvance;
			}
			if (!excludeSpecies.contains(mutation.getAllele1()))
			{
				int otherAdvance = getMutationPathLength(mutation.getAllele1(), excludeSpecies);
				if (otherAdvance > highest)
					highest = otherAdvance;
			}
		}
		
		return own + (highest > 0 ? highest : 0);
	}

	@Override
	public float getResearchSuitability(ItemStack itemStack)
	{
		if (itemStack == null)
		{
			return 0f;
		}

		for (ItemStack product : this.products.keySet())
		{
			if (itemStack.isItemEqual(product))
			{
				return 1f;
			}
		}

		for (ItemStack specialty : this.specialties.keySet())
		{
			if (specialty.isItemEqual(itemStack))
			{
				return  1f;
			}
		}

		if (itemStack.getItem() == Config.fHoneyDrop)
		{
			return 0.5f;
		}
		else if (itemStack.getItem() == Config.fHoneydew)
		{
			return 0.7f;
		}
		else if (itemStack.getItem() == Config.fBeeComb || itemStack.getItem() == Config.combs)
		{
			return 0.4f;
		}
		else if (getRoot().isMember(itemStack))
		{
			return 1.0f;
		}
		else
		{
			for (Map.Entry<ItemStack, Float> catalyst : BeeManager.beeRoot.getResearchCatalysts().entrySet())
			{
				if (OreDictionary.itemMatches(itemStack, catalyst.getKey(), false))
				{
					return catalyst.getValue();
				}
			}
		}

		return 0f;
	}


	@Override
	public ItemStack[] getResearchBounty(World world, GameProfile researcher, IIndividual individual, int bountyLevel)
	{
		System.out.println("Bounty level: " + bountyLevel);
		ArrayList<ItemStack> bounty = new ArrayList<ItemStack>();
		
		if (world.rand.nextFloat() < ((10f / bountyLevel)))
		{
			Collection<? extends IMutation> resultantMutations = getRoot().getCombinations(this);
			if (resultantMutations.size() > 0)
			{
				IMutation[] candidates = resultantMutations.toArray(new IMutation[resultantMutations.size()]);
				bounty.add(AlleleManager.alleleRegistry.getMutationNoteStack(researcher, candidates[world.rand.nextInt(candidates.length)]));
			}
		}
		
		for (ItemStack product : this.products.keySet())
		{
			ItemStack copy = product.copy();
			copy.stackSize = 1 + world.rand.nextInt(bountyLevel / 2);
			bounty.add(copy);
		}
		
		for (ItemStack specialty : this.specialties.keySet())
		{
			ItemStack copy = specialty.copy();
			copy.stackSize = world.rand.nextInt(bountyLevel / 3);
			if (copy.stackSize > 0)
			{
				bounty.add(copy);
			}
		}
		
		return bounty.toArray(new ItemStack[bounty.size()]);
	}


	@Override
	public String getEntityTexture()
	{
		return "/gfx/forestry/entities/bees/honeyBee.png";
	}
	
	@Override
	public void registerIcons(IIconRegister itemMap)
	{
		this.icons = new IIcon[EnumBeeType.values().length][3];
		
		String root = this.getIconPath();
		
		IIcon body1 = itemMap.registerIcon(root + "body1");

		for (int i = 0; i < EnumBeeType.values().length; i++)
		{
			if (EnumBeeType.values()[i] == EnumBeeType.NONE)
				continue;
			
			icons[i][0] = itemMap.registerIcon(root + EnumBeeType.values()[i].toString().toLowerCase(Locale.ENGLISH) + ".outline");
			icons[i][1] = (EnumBeeType.values()[i] != EnumBeeType.LARVAE) ? body1 :
					itemMap.registerIcon(root + EnumBeeType.values()[i].toString().toLowerCase(Locale.ENGLISH) + ".body");
			icons[i][2] = itemMap.registerIcon(root + EnumBeeType.values()[i].toString().toLowerCase(Locale.ENGLISH) + ".body2");
		}
	}
	
	private String getIconPath()
	{
		String value;
		
//		switch (this)
//		{
//			case SKULKING:
//			case GHASTLY:
//			case SPIDERY:
//			case SMOULDERING:
//			case TC_BRAINY:
//			case TC_WISPY:
//			case TC_BATTY:
//			case AM_VORTEX:
//			case AM_WIGHT:
//			case TE_BLIZZY:
//			case TE_GELID:
//			case TE_DANTE:
//			case TE_PYRO:
//				value = CommonProxy.DOMAIN + ":bees/skulking/";
//				break;
//			
//			default:
				value = ForestryHelper.Name.toLowerCase() + ":bees/default/";
//				break;
//		}
		
		return value;
	}

	/// --------- Unused Functions ---------------------------------------------
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(short texUID)
	{
		return icons[0][0];
	}
}
