package spacebees.world.feature;

import micdoodle8.mods.galacticraft.api.event.wgen.GCCoreEventPopulate;
import micdoodle8.mods.galacticraft.planets.mars.dimension.WorldProviderMars;
import net.minecraft.world.gen.feature.WorldGenerator;
import spacebees.bees.HiveDescription;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class HiveGenMars
{
    private WorldGenerator hiveGenerator;

    @SubscribeEvent
    public void onPlanetDecorated(GCCoreEventPopulate.Post event)
    {
        if (this.hiveGenerator == null)
        {
			  this.hiveGenerator = new WorldGenHives(HiveDescription.MARS.getBlock());
        }

        if (event.worldObj.provider instanceof WorldProviderMars)
        {
            int hivesPerChunk = 2;
            int x;
            int y;
            int z;
            int metadata = 1; //Sets hive type 1 Mars
            int blockAbove;
            int blockBelow;
            
            //TODO Clean up generational code and test better
            for (int hiveCount = 0; hiveCount < hivesPerChunk; ++hiveCount)
            {
            	if(event.rand.nextInt(4) == 1){
	                x = event.chunkX + event.rand.nextInt(16) + 8;
	                y = event.rand.nextInt(100);
	                z = event.chunkZ + event.rand.nextInt(16) + 8;
	                // These variables seem backwards. This causes it to make sure it's hanging underground.
	                blockAbove = y++; 
	                blockBelow = y--;
	                if(event.worldObj.isAirBlock(x, blockBelow, z)){
	                	//On Ground Do not place
	                }
	                else
	                {
	                	if(event.worldObj.isAirBlock(x, blockAbove, z))
	                	{
	                		//On ground
	                		event.worldObj.setBlock(x, y, z, HiveDescription.MARS.getBlock(), metadata, 2);
	                		System.out.println("Hive at x " + x + " z " + z + " y " + y);
	                	}
	                }
            	}
            }
        }
    }
}