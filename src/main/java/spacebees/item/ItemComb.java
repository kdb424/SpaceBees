package spacebees.item;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import spacebees.item.types.CombType;
import spacebees.main.Config;
import spacebees.main.utils.compat.ForestryHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import forestry.api.core.Tabs;

public class ItemComb extends Item
{
	public ItemComb()
	{
		super();
		this.setCreativeTab(Tabs.tabApiculture);
		this.setHasSubtypes(true);
		this.setUnlocalizedName("comb");
		GameRegistry.registerItem(this, "comb");
	}
	
	public ItemStack getStackForType(CombType type)
	{
		return new ItemStack(this, 1, type.ordinal());
	}
	
	public ItemStack getStackForType(CombType type, int count)
	{
		return new ItemStack(this, count, type.ordinal());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tabs, List list)
	{
		for (CombType type : CombType.values())
		{
			if (type.showInList || Config.forestryDebugEnabled)
			{
				list.add(this.getStackForType(type));
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	@Override
	public int getRenderPasses(int meta)
	{
		return 2;
	}
	
	@SideOnly(Side.CLIENT)
	private IIcon secondIcon;
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(ForestryHelper.Name.toLowerCase() + ":beeCombs.0");
		this.secondIcon = par1IconRegister.registerIcon(ForestryHelper.Name.toLowerCase() + ":beeCombs.1");
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass)
	{
		return (pass == 0) ? itemIcon : secondIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass)
	{
		int meta = Math.max(0, Math.min(CombType.values().length - 1, stack.getItemDamage()));
		int colour = CombType.values()[meta].getColours()[0];
		
		if (pass >= 1)
		{
			colour = CombType.values()[meta].getColours()[1];
		}
		
		return colour;
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{
		return CombType.values()[stack.getItemDamage()].getName();
	}

}
