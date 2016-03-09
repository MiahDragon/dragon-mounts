/*
 ** 2012 August 27
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package info.ata4.minecraft.dragon.client;

import info.ata4.minecraft.dragon.DragonMounts;
import info.ata4.minecraft.dragon.client.gui.GuiDragonDebug;
import info.ata4.minecraft.dragon.client.handler.DragonControl;
import info.ata4.minecraft.dragon.client.render.DragonRenderer;
import info.ata4.minecraft.dragon.server.CommonProxy;
import info.ata4.minecraft.dragon.server.entity.EntityTameableDragon;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class ClientProxy extends CommonProxy {
    
    @Override
    public void onPreInit(FMLPreInitializationEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityTameableDragon.class, new IRenderFactory() {
            @Override
            public Render createRenderFor(RenderManager manager) {
                return new DragonRenderer(manager);
            }
        });
    }

    @Override
    public void onInit(FMLInitializationEvent evt) {
        super.onInit(evt);
    }

    @Override
    public void onPostInit(FMLPostInitializationEvent event) {
        if (DragonMounts.instance.getConfig().isDebug()) {
            MinecraftForge.EVENT_BUS.register(new GuiDragonDebug());
        }

        MinecraftForge.EVENT_BUS.register(new DragonControl(getNetwork()));
    }
}
