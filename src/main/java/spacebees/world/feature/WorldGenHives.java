package spacebees.world.feature;


import net.minecraft.block.Block;
import spacebees.block.BlockHive;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenHives extends WorldGenerator
{
	private Block hiveBlock;

    public WorldGenHives(Block hive)
    {
        this.hiveBlock = hive;
    }

    @Override
    public boolean generate(World par1World, Random par2Random, int x, int y, int z)
    {
        int i1 = x + par2Random.nextInt(8) - par2Random.nextInt(8);
        int j1 = y + par2Random.nextInt(4) - par2Random.nextInt(4);
        int k1 = z + par2Random.nextInt(8) - par2Random.nextInt(8);

        if (par1World.isAirBlock(i1, j1, k1) && (!par1World.provider.hasNoSky || j1 < 127) && this.hiveBlock.canBlockStay(par1World, i1, j1, k1))
        {
            par1World.setBlock(i1, j1, k1, this.hiveBlock, par2Random.nextInt(3), 2);
        }

        return true;
    }
}
