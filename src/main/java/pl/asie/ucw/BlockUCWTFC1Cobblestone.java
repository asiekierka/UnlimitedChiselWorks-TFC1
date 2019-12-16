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

import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.objects.blocks.stone.BlockRockVariant;
import net.dries007.tfc.objects.blocks.stone.BlockRockVariantFallable;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import pl.asie.ucw.util.BlockStateUtil;

public class BlockUCWTFC1Cobblestone extends BlockRockVariantFallable implements IUCWBlock {
	private UCWBlockRule rule = UCWObjectBroker.get().getRule();
	private IBlockState base = UCWObjectBroker.get().getBase();

	public BlockUCWTFC1Cobblestone() {
		super(Rock.Type.COBBLE, UnlimitedChiselWorksTFC1.getDefaultRockObject());
		UnlimitedChiselWorksTFC1.patchRockField((BlockRockVariant) this.base.getBlock(), this);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return BlockStateUtil.applyProperties(this, this.rule.throughBlock.getStateFromMeta(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return this.rule.throughBlock.getMetaFromState(BlockStateUtil.applyProperties(this.rule.throughBlock, state));
	}

	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
		Item.getItemFromBlock(this).getSubItems(tab, items);
	}

	protected BlockStateContainer createBlockState() {
		this.rule = UCWObjectBroker.get().getRule();
		return UCWObjectBroker.get().createBlockState(this);
	}

	@Override
	public IBlockState getBaseState() {
		return base;
	}

	@Override
	public IBlockState getThroughState(IBlockState state) {
		return BlockStateUtil.applyProperties(this.rule.throughBlock, state);
	}
}