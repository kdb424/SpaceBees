package spacebees.item;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import spacebees.main.CommonProxy;
import spacebees.main.Config;
import spacebees.main.utils.MoonPhase;
import spacebees.main.utils.TabSpaceBees;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemMoonDial extends Item
{
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public ItemMoonDial()
	{
		super();
		this.setCreativeTab(TabSpaceBees.tabSpaceBees);
		this.setUnlocalizedName("moonDial");
		GameRegistry.registerItem(this, "moonDial");
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.icons = new IIcon[MoonPhase.values().length];
		for (int i = 0; i < MoonPhase.values().length; ++i)
		{
			this.icons[i] = par1IconRegister.registerIcon(CommonProxy.DOMAIN + ":moonDial." + i);
		}
		this.itemIcon = this.icons[4];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		return this.icons[MoonPhase.getMoonPhaseFromTime(player.worldObj.getWorldTime()).ordinal()];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List infoList, boolean par4)
	{
		if (Config.moonDialShowsPhaseInText &&
				entityPlayer.getCurrentEquippedItem() != null &&
				entityPlayer.getCurrentEquippedItem().getItem() == this)
		{
			infoList.add("\u00A77" + MoonPhase.getMoonPhaseFromTime(entityPlayer.worldObj.getWorldTime()).getLocalizedName());
		}
	}
}
