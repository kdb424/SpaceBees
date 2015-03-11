package spacebees.world.feature;

import micdoodle8.mods.galacticraft.api.event.wgen.GCCoreEventPopulate;
import micdoodle8.mods.galacticraft.planets.asteroids.dimension.WorldProviderAsteroids;
import net.minecraft.world.gen.feature.WorldGenerator;
import spacebees.bees.HiveDescription;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class HiveGenAsteroids
{
    private WorldGenerator hiveGenerator;

    @SubscribeEvent
    public void onPlanetDecorated(GCCoreEventPopulate.Post event)
    {
    	System.out.println("Starting Asteroid Hive Gen");
        if (this.hiveGenerator == null)
        {
			  this.hiveGenerator = new WorldGenHives(HiveDescription.ASTEROID.getBlock());
        }

        if (event.worldObj.provider instanceof WorldProviderAsteroids)
        {
            int hivesPerChunk = 12;
            int x;
            int y = 0;
            int z;
            int metadata = 2; //Sets hive type 2 ASTEROID
            int blockAbove;
            int blockBelow;

            for (int hiveCount = 0; hiveCount < hivesPerChunk; ++hiveCount)
            {
            	System.out.println("Doing stuff");
	            x = event.chunkX + event.rand.nextInt(16) + 8;
	            z = event.chunkZ + event.rand.nextInt(16) + 8;
	            // These variables seem backwards. This causes it to make sure it's on the ground.
	            blockAbove = y--; 
	            blockBelow = y++;
	            if(event.worldObj.isAirBlock(x, blockBelow, z)){
	            	//Floating Do not place
	            	System.out.println("Never gonna happen");
	            }
	            else
	            {
	            	if(event.worldObj.isAirBlock(x, blockAbove, z))
	            	{
	            		//On ground
	            		event.worldObj.setBlock(x, y, z, HiveDescription.ASTEROID.getBlock(), metadata, 2);
	            		System.out.println("Hive at x " + x + " z " + z + " y " + y);
	            	}
	            	else
	            		System.out.println("Failed hive at x " + x + " z " + z + " y " + y);
	            }
                	
            }
        }
    }
}