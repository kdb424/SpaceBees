package spacebees.item.types;

import spacebees.main.utils.LocalizationManager;

public enum PropolisType {
//	SPACE("space", 0x2222FF), ;
	MOON("moon", 0x2222FF),
	MARS("mars", 0xFF2222), ;

	private PropolisType(String pName, int overlayColour) {
		this.name = pName;
		this.colour = overlayColour;
	}

	private String name;
	public int colour;

	public String getName() {
		return LocalizationManager.getLocalizedString("propolis." + this.name);
	}
}
