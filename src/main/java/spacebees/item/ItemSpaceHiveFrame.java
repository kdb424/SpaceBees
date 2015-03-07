package spacebees.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import spacebees.item.types.HiveFrameType;
import spacebees.main.CommonProxy;
import spacebees.main.utils.TabSpaceBees;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import forestry.api.apiculture.IBee;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.IHiveFrame;

public class ItemSpaceHiveFrame extends Item implements IHiveFrame
{
	private HiveFrameType type;

	public ItemSpaceHiveFrame(HiveFrameType frameType)
	{
		super();
		this.type = frameType;
		this.setMaxDamage(this.type.maxDamage);
		this.setMaxStackSize(1);
		this.setCreativeTab(TabSpaceBees.tabSpaceBees);
		this.setUnlocalizedName("frame" + frameType.getName());
		GameRegistry.registerItem(this, "frame" + frameType.getName());
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(CommonProxy.DOMAIN + ":frame" + type.getName());
	}
	
	// --------- IHiveFrame functions -----------------------------------------
	
	@Override
	public ItemStack frameUsed(IBeeHousing housing, ItemStack frame, IBee queen, int wear)
	{
		return this.doWear(housing.getWorld(), housing.getXCoord(), housing.getYCoord(), housing.getZCoord(), frame, wear);
	}
	
	private ItemStack doWear(World w, int x, int y, int z, ItemStack frame, int wear)
	{
		int damage = wear;
		
		frame.setItemDamage(frame.getItemDamage() + damage);
		
		if (frame.getItemDamage() >= frame.getMaxDamage())
		{
			// Break the frame.
			frame = null;
		}
		
		return frame;
	}
	
	@Override
	public float getTerritoryModifier(IBeeGenome genome, float currentModifier)
	{
		return this.type.territoryMod;
	}

	@Override
	public float getMutationModifier(IBeeGenome genome, IBeeGenome mate, float currentModifier)
	{
		return this.type.mutationMod;
	}

	@Override
	public float getLifespanModifier(IBeeGenome genome, IBeeGenome mate, float currentModifier)
	{
		return this.type.lifespanMod;
	}

	@Override
	public float getProductionModifier(IBeeGenome genome, float currentModifier)
	{
		return this.type.productionMod;
	}

	@Override
	public float getFloweringModifier(IBeeGenome genome, float currentModifier)
	{
		return this.type.floweringMod;
	}

	@Override
	public boolean isSealed()
	{
		return this.type.isSealed;
	}

	@Override
	public boolean isSelfLighted()
	{
		return this.type.isLit;
	}

	@Override
	public boolean isSunlightSimulated()
	{
		return this.type.isSunlit;
	}

	@Override
	public boolean isHellish()
	{
		return this.type.isHellish;
	}

	@Override
	public float getGeneticDecay(IBeeGenome genome, float currentModifier)
	{
		return this.type.geneticDecayMod;
	}

	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
	{
		return false;
	}
	

}
