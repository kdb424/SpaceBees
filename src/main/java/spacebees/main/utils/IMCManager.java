package spacebees.main.utils;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class IMCManager
{
	public static void handle(IMCEvent event)
	{
		for (IMCMessage message : event.getMessages())
		{
			try
			{
				if (message.key.equals("addCrumblePair"))
				{
					handleCrumbleBlock(message);
				}
				else
				{
					FMLLog.warning("Space Bees recieved an IMC Message from a mod %s " +
									"but does not support messages keyed with %s.",
							message.getSender(), message.key);
				}
			}
			catch (Exception e)
			{
				FMLLog.warning("Space Bees recieved an invalid IMC Message from a mod %s! Please inform " +
								"the author of %s that they may not be correctly implementing message for key '%s'. Error details follow.",
						message.getSender(), message.key);
				FMLLog.info(e.getMessage());
			}
		}
	}

	private static void handleCrumbleBlock(IMCMessage message) throws Exception
	{
//		NBTTagCompound root = message.getNBTValue();
//		NBTBase source = root.getTag("source");
//		NBTBase target = root.getTag("target");
//
//		if (source != null && source instanceof NBTTagCompound)
//		{
//			ItemStack sourceItem = ItemStack.loadItemStackFromNBT((NBTTagCompound)source);
//			if (target instanceof NBTTagCompound)
//			{
//				ItemStack targetItem = ItemStack.loadItemStackFromNBT((NBTTagCompound)target);
//			}
//			else
//			{
//				throw new Exception("Required tag 'target' was either empty, or not a valid ItemStack.");
//			}
//		}
//		else
//		{
//			throw new Exception("Required tag 'source' was either empty, or not a valid ItemStack.");
//		}
		System.out.println("kdb424 broke it");
	}
}
