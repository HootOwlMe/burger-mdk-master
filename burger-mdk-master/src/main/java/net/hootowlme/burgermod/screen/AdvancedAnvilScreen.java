package net.hootowlme.burgermod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.hootowlme.burgermod.BurgerMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import net.minecraft.network.chat.Component;


public class AdvancedAnvilScreen extends AbstractContainerScreen<AdvancedAnvilMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(BurgerMod.MOD_ID, "textures/gui/advanced_anvil_gui.png");

    public AdvancedAnvilScreen(AdvancedAnvilMenu pMenu, Inventory pPlayerInventory, Component pTitle){
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - imageWidth) /2;
        int y = (height - imageHeight) /2;
        guiGraphics.blit(TEXTURE,x,y,0,0,imageWidth,imageHeight);

        renderProgressArrow(guiGraphics,x,y);

    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y){
        if (menu.isCrafting()){
            guiGraphics.blit(TEXTURE, x+85,y+30, 176, 0, menu.getScaledProgress(), 0);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float pPartialTick) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, pPartialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
