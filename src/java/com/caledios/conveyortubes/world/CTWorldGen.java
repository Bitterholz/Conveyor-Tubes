package com.caledios.conveyortubes.world;

import java.util.Random;

import com.caledios.conveyortubes.block.BlockRegistry;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class CTWorldGen implements IWorldGenerator{

	private WorldGenerator gen_oreRutile; //Generates Rutile Ore, used in Overworld
	private WorldGenerator gen_oreVanadinite; //Generates Vanadinite Ore, used in Overworld
	
	public CTWorldGen(){
		this.gen_oreRutile = new WorldGenMinable(BlockRegistry.oreRutile.getDefaultState(), 6);
		this.gen_oreVanadinite = new WorldGenSmallMinable(BlockRegistry.oreVanadinite.getDefaultState());
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.getDimensionId()){
		case 0: //Overworld
			this.runGenerator(gen_oreRutile, world, random, chunkX, chunkZ, 6, 12, 36);
			this.runGenerator(gen_oreVanadinite, world, random, chunkX, chunkZ, 6, 0, 22);
			break;
		case -1: //Nether
			
			break;
		case 1: //End
			
			break;
		}
	}
	
	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
	    if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
	        throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

	    int heightDiff = maxHeight - minHeight + 1;
	    for (int i = 0; i < chancesToSpawn; i ++) {
	        int x = chunk_X * 16 + rand.nextInt(16);
	        int y = minHeight + rand.nextInt(heightDiff);
	        int z = chunk_Z * 16 + rand.nextInt(16);
	        generator.generate(world, rand, new BlockPos(x, y, z));
	    }
	}

}
