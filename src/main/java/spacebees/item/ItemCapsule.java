//TODO Update to forestry standards
/*package spacebees.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import spacebees.item.types.CapsuleType;
import spacebees.item.types.FluidType;
import spacebees.main.CommonProxy;
import spacebees.main.utils.TabSpaceBees;
import spacebees.main.utils.TextureManager;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ItemCapsule extends Item
{
	private CapsuleType capsuleType;
	
	public ItemCapsule(CapsuleType type, int maxStackSize)
	{
		super();
		this.capsuleType = type;
		this.setCreativeTab(TabSpaceBees.tabSpaceBees);
		this.setHasSubtypes(true);
		this.setMaxStackSize(maxStackSize);
		this.setUnlocalizedName("capsule." + type.toString().toLowerCase());
		
	}
	
	public CapsuleType getType()
	{
		return this.capsuleType;
	}

	@Override
	public String getItemStackDisplayName(ItemStack itemStack)
	{
		return String.format(this.capsuleType.getLocalizedName(), FluidType.values()[itemStack.getItemDamage()].getDisplayName());
	}

	public ItemStack getCapsuleForLiquid(FluidType l)
	{
		return new ItemStack(this, 1, l.ordinal());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List itemList)
	{
		for (FluidType l : FluidType.values())
		{
			if (l.available)
			{
				itemList.add(new ItemStack(this, 1, l.ordinal()));
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}
	
	private IIcon[] icons;
	
	private int counter = 0;
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		System.out.println(par1IconRegister);
		icons = new IIcon[2];
		String capsuleName = "capsule"+ this.capsuleType.getName().substring(0, 1).toUpperCase()+ this.capsuleType.getName().substring(1);
		icons[0] = TextureManager.getInstance().registerTex(par1IconRegister, capsuleName);
//		icons[1] = TextureManager.getInstance().registerTex(par1IconRegister, capsuleName);
		counter = 0;
		int subcounter = 0;
//		System.out.println(par1IconRegister.registerIcon(CommonProxy.DOMAIN + ":capsule" + this.capsuleType.getName().substring(0, 1).toUpperCase()+ this.capsuleType.getName().substring(1)));
//		icons[0] = par1IconRegister.registerIcon(CommonProxy.DOMAIN + ":capsule" + this.capsuleType.getName().substring(0, 1).toUpperCase()
//				+ this.capsuleType.getName().substring(1));
//		icons[1] = par1IconRegister.registerIcon(CommonProxy.DOMAIN + ":capsule" + this.capsuleType.getName().substring(0, 1).toUpperCase()
//				+ this.capsuleType.getName().substring(1));
//		this.icon = par1IconRegister.registerIcon(CommonProxy.DOMAIN + ":capsule" + this.capsuleType.getName().substring(0, 1).toUpperCase()
//				+ this.capsuleType.getName().substring(1));
		
		for (FluidType t : FluidType.values())
		{			
//			if (t != FluidType.EMPTY && this.icons[1] == null)
//			{
//				icons[1] = par1IconRegister.registerIcon(CommonProxy.DOMAIN + ":liquids/" + t.liquidID.toLowerCase());
//			}
			if(subcounter == counter && t != FluidType.EMPTY){
				this.icons[1] = par1IconRegister.registerIcon(CommonProxy.DOMAIN + ":liquids/" + t.liquidID.toLowerCase());
				System.out.println(this.icons[1]);
			}
			else{
				++counter;
				++subcounter;
			}
		}
//		System.out.println(par1IconRegister);
//		System.out.println("Registered " + counter + " icons");
//		System.out.println("Registered " + subcounter + " icons");
	}

//	@Override
//	@SideOnly(Side.CLIENT)
//	public IIcon getIconFromDamageForRenderPass(int metadata, int pass)
//	{
//		System.out.println("1");
//		IIcon i = this.itemIcon;
//		if (metadata != 0 && pass == 0)
//		{
//			System.out.println(i);
//			i = FluidType.values()[Math.max(0, Math.min(metadata, FluidType.values().length - 1))].liquidIcon;
//			System.out.println(i);
//		}
//		return i;
//	}
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamageForRenderPass(int i, int j) {
		if (j > 0 ) {
			return icons[1];
		} else {
			return icons[0];
		}
	}

	@Override
	public int getRenderPasses(int metadata)
	{
//		return (metadata == 0) ? 1 : 2;
		return 2;
	}
}
*/