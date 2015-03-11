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
			// TODO Color combs
			{ 0xCCCCCC, 0xE6E6E6 }, // Blank. USed for debug
			{ 0xCCCCCC, 0xE6E6E6 }, // Moon
			{ 0x993333, 0x1E160E }, // Mars
			{ 0x0000CC, 0x1E160E }, // Asteroid

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