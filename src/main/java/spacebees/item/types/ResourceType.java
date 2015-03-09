package spacebees.item.types;

import spacebees.main.utils.LocalizationManager;

public enum ResourceType {
	SPACE_BEES("spaceBees", false)

	//Sets creative mode tab icon
//	RESEARCH_STARTNODE("fragment", false),
//	RESEARCH_BEEINFUSION("beeInfusion",	false)
	;


	private ResourceType(String n, boolean show) {
		this.name = n;
		this.showInList = show;
	}

	private String name;
	public boolean showInList;

	public void setHidden() {
		this.showInList = false;
	}

	public String getName() {
		return this.name;
	}

	public String getLocalizedName() {
		return LocalizationManager.getLocalizedString("resource." + this.name);
	}
 }
