package spacebees.world.feature;

import spacebees.bees.HiveDescription;
import micdoodle8.mods.galacticraft.api.event.wgen.GCCoreEventPopulate;
import micdoodle8.mods.galacticraft.planets.mars.blocks.MarsBlocks;
import micdoodle8.mods.galacticraft.planets.mars.dimension.WorldProviderMars;
import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class HiveGenMars
{
    private WorldGenerator hiveGenerator;

    @SubscribeEvent
    public void onPlanetDecorated(GCCoreEventPopulate.Post event)
    {
        if (this.hiveGenerator == null)
        {
//            this.hiveGenerator = new WorldGenHives((Block) Block.blockRegistry.getObject("hive.mars"));//Hive Block goes in there
        	this.hiveGenerator = new WorldGenHives(HiveDescription.MARS.getBlock());//Hive Block goes in there
        }

        if (event.worldObj.provider instanceof WorldProviderMars)
        {
            int hivesPerChunk = 1;
            int x;
            int y;
            int z;

            for (int hiveCount = 0; hiveCount < hivesPerChunk; ++hiveCount)
            {
                x = event.chunkX + event.rand.nextInt(16) + 8;
                y = event.rand.nextInt(128);
                z = event.chunkZ + event.rand.nextInt(16) + 8;
                this.hiveGenerator.generate(event.worldObj, event.rand, x, y, z);
                System.out.println("Hive at x" + x + " z" + z + " y" + y);
            }
        }
    }
}