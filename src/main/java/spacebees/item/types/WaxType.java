package spacebees.item.types;

import spacebees.main.utils.LocalizationManager;

public enum WaxType
{
	MAGIC("magic", true, 0xd242df),
	SOUL("soul", false, 0x967C63),
	AMNESIC("amnesic", true, 0x856DFF)
	;
	
	private WaxType(String n, boolean sp, int c)
	{
		this.name = n;
		this.sparkly = sp;
		this.colour = c;
	}
	
	private String name;
	public boolean sparkly;
	public int colour;
	
	public String getName()
	{
		return LocalizationManager.getLocalizedString("wax." + this.name);
	}
}