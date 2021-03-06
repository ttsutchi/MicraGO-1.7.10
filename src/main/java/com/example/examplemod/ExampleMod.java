package com.example.examplemod;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";


    /* Items and Blocks */

    public static final CreativeTabs tabGo = new TabGo("tabGo");

    public static final Item itemCube = new ItemCube();

    public static final Block blockCubeOre = new BlockCubeOre();
    public static final Item itemCubeIngot = new ItemCubeIngot();

    public static final Item itemTest = new Item().setCreativeTab(ExampleMod.tabGo).setUnlocalizedName(ExampleMod.MODID + "_itemTest").setTextureName(ExampleMod.MODID + ":itemTest");

    public static AchievementPage testPage;
    public static Achievement gotchaSheep;





    @EventHandler
    public void init(FMLInitializationEvent event) {
        registerCube();
        registerMaterials();

        registerAchievements();

        GameRegistry.registerItem(itemTest, "itemTest");
    }

    public void registerCube() {
        GameRegistry.registerItem(itemCube, "itemCube");
        EntityRegistry.registerModEntity(EntityCube.class, "entityCube", EntityRegistry.findGlobalUniqueEntityId(), this, 10, 10, true);
        RenderingRegistry.registerEntityRenderingHandler(EntityCube.class, new RenderCube(itemCube));
        GameRegistry.addRecipe(
                new ItemStack(itemCube, 1),
                "AAA",
                "ABA",
                "AAA",
                'A', itemCubeIngot,
                'B', Items.iron_ingot);
    }

    public void registerMaterials() {
        GameRegistry.registerBlock(blockCubeOre, "blockCubeOre");
        GameRegistry.registerItem(itemCubeIngot, "itemCubeIngot");
        GameRegistry.registerWorldGenerator(new CubeOreGenerator(blockCubeOre, 200), 1);
    }


    public void registerAchievements() {
        AchievementPage.registerAchievementPage(MicraGoAchievementsManager.achievementPageMicraGo);


//        MinecraftForge.EVENT_BUS.register(new EntityDropsEventHandler());
        MinecraftForge.EVENT_BUS.register(new GetItemEventHandler());

        MinecraftForge.EVENT_BUS.register(new TestEventHandler());
    }
}
