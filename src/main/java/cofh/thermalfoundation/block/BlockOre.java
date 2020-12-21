package cofh.thermalfoundation.block;

import cofh.api.core.IInitializer;
import cofh.lib.util.helpers.ItemHelper;
import cofh.lib.util.helpers.StringHelper;
import cofh.thermalfoundation.ThermalFoundation;
import cofh.thermalfoundation.item.TFItems;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockOre extends Block implements IInitializer {

	public BlockOre() {

		super(Material.rock);
		setHardness(3.0F);
		setResistance(5.0F);
		setStepSound(soundTypeStone);
		setCreativeTab(ThermalFoundation.tabCommon);
		setBlockName("thermalfoundation.ore");

		setHarvestLevel("pickaxe", 2);
		setHarvestLevel("pickaxe", 1, 0);
		setHarvestLevel("pickaxe", 1, 1);
		setHarvestLevel("pickaxe", 3, 6);
		setHarvestLevel("pickaxe", 1, 7);
		setHarvestLevel("pickaxe", 3, 8);
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {

		for (int i = 0; i < NAMES.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {

		int metadata = world.getBlockMetadata(x, y, z);
		if (metadata >= LIGHT.length || metadata < 0) {
			return 0;
		}
		return LIGHT[metadata];
	}

	@Override
	public int damageDropped(int i) {

		return i;
	}

	@Override
	public IIcon getIcon(int side, int metadata) {

		return TEXTURES[metadata];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir) {

		for (int i = 0; i < NAMES.length; i++) {
			TEXTURES[i] = ir.registerIcon("thermalfoundation:ore/Ore_" + StringHelper.titleCase(NAMES[i]));
		}
	}

	/* IInitializer */
	@Override
	public boolean preInit() {

		GameRegistry.registerBlock(this, ItemBlockOre.class, "Ore");

		oreCopper = new ItemStack(this, 1, 0);
		oreTin = new ItemStack(this, 1, 1);
		oreSilver = new ItemStack(this, 1, 2);
		oreLead = new ItemStack(this, 1, 3);
		oreNickel = new ItemStack(this, 1, 4);
		orePlatinum = new ItemStack(this, 1, 5);
		oreMithril = new ItemStack(this, 1, 6);
		oreAluminum = new ItemStack(this, 1, 7);
		oreIridium = new ItemStack(this, 1, 8);

		ItemHelper.registerWithHandlers("oreCopper", oreCopper);
		ItemHelper.registerWithHandlers("oreTin", oreTin);
		ItemHelper.registerWithHandlers("oreSilver", oreSilver);
		ItemHelper.registerWithHandlers("oreLead", oreLead);
		ItemHelper.registerWithHandlers("oreNickel", oreNickel);
		ItemHelper.registerWithHandlers("orePlatinum", orePlatinum);
		ItemHelper.registerWithHandlers("oreMithril", oreMithril);
		ItemHelper.registerWithHandlers("oreAluminum", oreAluminum);
		ItemHelper.registerWithHandlers("oreIridium", oreIridium);

		return true;
	}

	@Override
	public boolean initialize() {

		return true;
	}

	@Override
	public boolean postInit() {

		ItemHelper.addSmelting(TFItems.ingotCopper, oreCopper, 0.6F);
		ItemHelper.addSmelting(TFItems.ingotTin, oreTin, 0.7F);
		ItemHelper.addSmelting(TFItems.ingotSilver, oreSilver, 0.9F);
		ItemHelper.addSmelting(TFItems.ingotLead, oreLead, 0.8F);
		ItemHelper.addSmelting(TFItems.ingotNickel, oreNickel, 1.0F);
		ItemHelper.addSmelting(TFItems.ingotPlatinum, orePlatinum, 1.0F);
		ItemHelper.addSmelting(TFItems.ingotMithril, oreMithril, 1.5F);
		ItemHelper.addSmelting(TFItems.ingotAluminum, oreAluminum, 0.6F);
		ItemHelper.addSmelting(TFItems.ingotIridium, oreIridium, 1.2F);

		return true;
	}

	public static final String[] NAMES = { "copper", "tin", "silver", "lead", "nickel", "platinum", "mithril", "aluminum", "iridium" };
	public static final IIcon[] TEXTURES = new IIcon[NAMES.length];
	public static final int[] LIGHT = { 0, 0, 4, 0, 0, 4, 8, 0, 4 };
	public static final int[] RARITY = { 0, 0, 0, 0, 0, 1, 2, 0, 1 };

	public static ItemStack oreCopper;
	public static ItemStack oreTin;
	public static ItemStack oreSilver;
	public static ItemStack oreLead;
	public static ItemStack oreNickel;
	public static ItemStack orePlatinum;
	public static ItemStack oreMithril;
	public static ItemStack oreAluminum;
	public static ItemStack oreIridium;

}
