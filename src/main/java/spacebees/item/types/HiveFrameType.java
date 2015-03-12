package spacebees.item.types;

import spacebees.main.utils.LocalizationManager;

public enum HiveFrameType
{
	//Don't forget to add to config
	//Short Call
	MOON("Cheese", 	//Name
			50,		//Damage
			1.2f,	//Territory
			1.2f,	//Mutation
			2f,		//Lifespan
			1.5f,		//Production
			1f),	//Genetic Decay
	MARS("Sludge",//Name
			120,	//Damage
			1f,		//Territory
			.8f,	//Mutation
			1f,		//Lifespan
			2f,		//Production
			1f),	//Genetic Decay
	ASTEROID("Ceres",//Name
			60,		//Damage
			2f,		//Territory
			3f,		//Mutation
			.5f,	//Lifespan
			.5f,	//Production
			1.5f),	//Genetic Decay
	BLACKHOLE("BlackHole",//Name
			60,		//Damage
			0.1f,//Territory
			2f,		//Mutation
			2.5f,	//Lifespan
			0f,		//Production
			0.01f),	//Genetic Decay
	//Detailed Call
	//Seems like the bool values aren't used.
//	MOON("Test", 	//Name
//			100,	//Damage
//			1f,		//Territory
//			1f,		//Mutation
//			1f,		//Lifespan
//			1f,		//Production
//			1f,		//Flowering
//			0.7f,	//Genetic Decay
//			false,	//Sealed
//			true,	//Self Lit
//			true,	//Sun Simulation
//			true	//Hellish
//			),	

	;
	
	private HiveFrameType(String name, int damage,
						  float territory, float mutation, float lifespan, float production, float geneticDecay)
	{
		this(name, damage, territory, mutation, lifespan, production, 1f, geneticDecay, false, false, false, false);
	}
	
	private HiveFrameType(String name, int damage,
						  float territory, float mutation, float lifespan, float production, float flowering, float geneticDecay,
						  boolean sealed, boolean lit, boolean sunlit, boolean hellish)
	{
		this.frameName = name;
		this.maxDamage = damage;
		
		this.territoryMod = territory;
		this.mutationMod = mutation;
		this.lifespanMod = lifespan;
		this.productionMod = production;
		this.floweringMod = flowering;
		this.geneticDecayMod = geneticDecay;
		this.isSealed = sealed;
		this.isLit = lit;
		this.isSunlit = sunlit;
		this.isHellish = hellish;
	}
	
	private String frameName;
	public int maxDamage;
	
	public float territoryMod;
	public float mutationMod;
	public float lifespanMod;
	public float productionMod;
	public float floweringMod;
	public float geneticDecayMod;
	public boolean isSealed;
	public boolean isLit;
	public boolean isSunlit;
	public boolean isHellish;
	
	public String getName()
	{
		return this.frameName;
	}
	
	public String getLocalizedName()
	{
		return LocalizationManager.getLocalizedString("frame." + this.frameName);
	}
}
