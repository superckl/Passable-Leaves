package rtg.world.gen.terrain.vanilla;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaExtremeHillsPlus extends TerrainBase
{
	public TerrainVanillaExtremeHillsPlus()
	{
	}
	
	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float ocean, float border, float river)
	{
		float h = simplex.noise2(x / 300f, y / 300f) * 135f * river;
		h *= h / 32f;
		h = h > 150f ? 150f : h;

		if(h > 10f)
		{
			float d = (h - 10f) / 2f > 8f ? 8f : (h - 10f) / 2f;
			h += simplex.noise2(x / 35f, y / 35f) * d;
			h += simplex.noise2(x / 60f, y / 60f) * d * 0.5f;

			if(h > 35f)
			{
				float d2 = (h - 35f) / 1.5f > 30f ? 30f : (h - 35f) / 1.5f;
				h += cell.noise(x / 25D, y / 25D, 1D) * d2;
			}
		}
		
		h += simplex.noise2(x / 28f, y / 28f) * 4;
		h += simplex.noise2(x / 18f, y / 18f) * 2;
		h += simplex.noise2(x / 8f, y / 8f) * 2;
		
		return h + 67f;
	}
}
