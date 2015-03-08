package spacebees.world.feature;

import micdoodle8.mods.galacticraft.api.event.wgen.GCCoreEventPopulate;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderMoon;
import net.minecraft.world.gen.feature.WorldGenerator;
import spacebees.bees.HiveDescription;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class HiveGenMoon
{
    private WorldGenerator hiveGenerator;

    @SubscribeEvent
    public void onPlanetDecorated(GCCoreEventPopulate.Post event)
    {
        if (this.hiveGenerator == null)
        {
			  this.hiveGenerator = new WorldGenHives(HiveDescription.MOON.getBlock());//
        }

        if (event.worldObj.provider instanceof WorldProviderMoon)
        {
            int hivesPerChunk = 2;
            int x;
            int y;
            y = 0;
            int z;
            int metadata = 0; //Sets hive type 0 Moon
            int blockAbove;
            int blockBelow;

            for (int hiveCount = 0; hiveCount < hivesPerChunk; ++hiveCount)
            {
            	if(event.rand.nextInt(3) == 1){
	                x = event.chunkX + event.rand.nextInt(16) + 8;
	                while(y <= 60)
	                	y = event.rand.nextInt(128);
	                z = event.chunkZ + event.rand.nextInt(16) + 8;
	                // These variables seem backwards. This causes it to make sure it's on the ground.
	                blockAbove = y--; 
	                blockBelow = y++;
	                if(event.worldObj.isAirBlock(x, blockBelow, z)){
	                	//Floating Do not place
	                }
	                else
	                {
	                	if(event.worldObj.isAirBlock(x, blockAbove, z))
	                	{
	                		//On ground
	                		event.worldObj.setBlock(x, y, z, HiveDescription.MOON.getBlock(), metadata, 2);
	                		System.out.println("Hive at x " + x + " z " + z + " y " + y);
	                	}
	                }
            	}
                	
            }
        }
    }
}