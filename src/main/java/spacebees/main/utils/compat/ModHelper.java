package spacebees.main.utils.compat;

public class ModHelper
{
	public static void preInit()
	{
		ForestryHelper.preInit();
		ExtraBeesHelper.preInit();
	}

	public static void init()
	{
		ForestryHelper.init();
		ExtraBeesHelper.init();
	}

	public static void postInit()
	{
		ForestryHelper.postInit();
		ExtraBeesHelper.postInit();
	}
}
