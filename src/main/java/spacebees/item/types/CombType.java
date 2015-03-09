package spacebees.item.types;

import spacebees.main.utils.LocalizationManager;

public enum CombType {
	MUNDANE("mundane", true), MOON("moon", true), MARS("mars", true),

	;

	private static int[][] colours = new int[][] {
			// TODO Learn what this does
			{ 0xCCCCCC, 0xE6E6E6 }, // Mundane
			{ 0xCCCCCC, 0xE6E6E6 }, // Mundane Once registered remove
			{ 0x993333, 0x1E160E }, // Moon
			{ 0x9872FF, 0x1E160E }, // Mars

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