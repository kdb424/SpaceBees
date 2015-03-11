package spacebees.item;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import spacebees.item.types.ResourceType;
import spacebees.main.CommonProxy;
import spacebees.main.utils.TabSpaceBees;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemMiscResources extends Item
{
	private IIcon[] icons = new IIcon[ResourceType.values().length];
	
	public ItemMiscResources()
	{
		super();
		this.setCreativeTab(TabSpaceBees.tabSpaceBees);
		this.setHasSubtypes(true);
		this.setUnlocalizedName("miscResources");
		GameRegistry.registerItem(this, "miscResources");
	}
	
	public ItemStack getStackForType(ResourceType type)
	{
		return new ItemStack(this, 1, type.ordinal());
	}
	
	public ItemStack getStackForType(ResourceType type, int count)
	{
		return new ItemStack(this, count, type.ordinal());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tabs, List list)
	{
		for (ResourceType type : ResourceType.values())
		{
			if (type.showInList)
			{
				list.add(this.getStackForType(type));
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		for (int i = 0; i < ResourceType.values().length; i++)
		{
			this.icons[i] = par1IconRegister.registerIcon(CommonProxy.DOMAIN + ":" + ResourceType.values()[i].getName());
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		//TODO Fix this workaround
		// Causes errors because meta < 1. Only visible error with NEI installed
		if(meta < 1)
			return icons[meta];
		else
			return icons[0];
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{
		return ResourceType.values()[stack.getItemDamage()].getLocalizedName();
	}

}
