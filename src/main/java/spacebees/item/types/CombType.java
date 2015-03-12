package spacebees.item.types;

import spacebees.main.utils.LocalizationManager;

public enum CombType {
		//Name,   Show in creative
	BLANK("blank", false), 
	MOON("moon", true),
	MARS("mars", true),
	ASTEROID("asteroid", true),

	;

	private static int[][] colours = new int[][] {
			{ 0xCCCCCC, 0xE6E6E6 }, // Blank. Used for debug
			{ 0xCCCCCC, 0xE6E6E6 }, // Moon
			{ 0xFF0000, 0xFF2222 }, // Mars
			{ 0x222222, 0x222299 }, // Asteroid

	};

	private CombType(String pName, boolean show) {
		this.name = pName;

		this.showInList = show;
	}

	public void setHidden() {
		this.showInList = false;
	}

	private String name;
	public boolean showInList;

	public String getName() {
		return LocalizationManager.getLocalizedString("comb." + this.name);
	}

	public int[] getColours() {
		return colours[this.ordinal()];
	}
}