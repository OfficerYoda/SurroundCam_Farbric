package de.officeryoda.surroundcam.client;

import de.officeryoda.surroundcam.client.cam.CamManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class SurroundcamClient implements ClientModInitializer {

    private static KeyBinding keyBinding;
    private CamManager camManager;

    @Override
    public void onInitializeClient() {
        camManager = new CamManager();

        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.surroundcam.spook", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_F6, // The keycode of the key
                "category.surroundcam" // The translation key of the keybinding's category.
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while(keyBinding.wasPressed()) {
                onKeyPress(client);
            }
        });

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            camManager.onPlayReady(handler, sender, client);
        });
    }

    private void onKeyPress(MinecraftClient client) {
        camManager.toggleCam();
    }
}
