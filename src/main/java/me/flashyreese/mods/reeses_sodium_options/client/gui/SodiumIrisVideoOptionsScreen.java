package me.flashyreese.mods.reeses_sodium_options.client.gui;

import me.flashyreese.mods.reeses_sodium_options.client.gui.frame.BasicFrame;
import me.jellysquid.mods.sodium.client.SodiumClientMod;
import me.jellysquid.mods.sodium.client.gui.widgets.FlatButtonWidget;
import me.jellysquid.mods.sodium.client.util.Dim2i;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resource.language.I18n;

import java.lang.reflect.InvocationTargetException;

public class SodiumIrisVideoOptionsScreen extends SodiumVideoOptionsScreen {
    public SodiumIrisVideoOptionsScreen(Screen prev) {
        super(prev);
    }

    @Override
    protected BasicFrame.Builder parentFrameBuilder() {
        BasicFrame.Builder basicFrame = super.parentFrameBuilder();

        Dim2i basicFrameDim = basicFrame.getDim();
        Dim2i tabFrameDim = new Dim2i(basicFrameDim.width() / 4 / 2, basicFrameDim.height() / 4 / 2, basicFrameDim.width() / 4 * 3, basicFrameDim.height() / 4 * 3);

        String text = I18n.translate("options.iris.shaderPackSelection");
        int size = this.client.textRenderer.getWidth(text);
        Dim2i shaderPackButtonDim;
        if (!SodiumClientMod.options().notifications.hideDonationButton) {
            shaderPackButtonDim = new Dim2i(tabFrameDim.getLimitX() - 134 - size, tabFrameDim.y() - 26, 10 + size, 20);
        } else {
            shaderPackButtonDim = new Dim2i(tabFrameDim.getLimitX() - size - 10, tabFrameDim.y() - 26, 10 + size, 20);
        }
        FlatButtonWidget shaderPackButton = new FlatButtonWidget(shaderPackButtonDim, text, () -> {

            /*this.client.openScreen(new net.coderbot.iris.gui.screen.ShaderPackScreen(this))*/
            // Let's hope Iris doesn't change the constructor nor the classpath :>
            try {
                Class<? extends Screen> screen = (Class<? extends Screen>) Class.forName("net.coderbot.iris.gui.screen.ShaderPackScreen");
                Screen instance = screen.getDeclaredConstructor(Screen.class).newInstance(this);
                this.client.openScreen(instance);
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        basicFrame.addChild(dim -> shaderPackButton);

        return basicFrame;
    }
}
