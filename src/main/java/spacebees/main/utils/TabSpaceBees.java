package spacebees.main.utils;

import spacebees.item.types.ResourceType;
import spacebees.main.Config;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabSpaceBees extends CreativeTabs {
	public static TabSpaceBees tabSpaceBees = new TabSpaceBees();

	public TabSpaceBees() {
		super(getNextID(), "spaceBees");
	}

	public Item getTabIconItem() {
		 return
		 Config.miscResources.getStackForType(ResourceType.SPACE_BEES).getItem();

	}
}