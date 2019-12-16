/*
 * Copyright (c) 2017, 2018, 2019 Adrian Siekierka
 *
 * This file is part of Unlimited Chisel Works - TFC1.
 *
 * Unlimited Chisel Works - TFC1 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Unlimited Chisel Works - TFC1 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Unlimited Chisel Works - TFC1.  If not, see <http://www.gnu.org/licenses/>.
 */

package pl.asie.ucw;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.objects.blocks.stone.BlockRockVariant;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.lang.reflect.Field;

@Mod(
		modid = "unlimitedchiselworks_tfc1",
		version = "${version}"
)
public class UnlimitedChiselWorksTFC1 {
	private static Rock defaultRockObject;

	static void patchRockField(BlockRockVariant from, BlockRockVariant to) {
		try {
			Field rockField = BlockRockVariant.class.getDeclaredField("rock");
			rockField.setAccessible(true);
			rockField.set(to, from.getRock());
			to.setHardness(from.getRock().getRockCategory().getHardness()).setResistance(from.getRock().getRockCategory().getResistance());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	static Rock getDefaultRockObject() {
		if (defaultRockObject == null) {
			for (Rock rock : TFCRegistries.ROCKS) {
				defaultRockObject = rock;
				break;
			}
		}

		return defaultRockObject;
	}
}
