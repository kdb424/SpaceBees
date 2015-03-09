package spacebees.item.types;

import spacebees.main.utils.LocalizationManager;

public enum WaxType {
	SPACE("space", true, 0xCCFFFF);

	private WaxType(String n, boolean sp, int c) {
		this.name = n;
		this.sparkly = sp;
		this.colour = c;
	}

	private String name;
	public boolean sparkly;
	public int colour;

	public String getName() {
		return LocalizationManager.getLocalizedString("wax." + this.name);
	}
}