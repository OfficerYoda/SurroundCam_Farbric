package de.officeryoda.surroundcam.client.cam;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class CamManager implements ClientPlayConnectionEvents.Join {

    boolean inCam = false;

    public void test() {
        MinecraftClient.getInstance().player.sendMessage(Text.literal("Cam is " + inCam));
    }

    public void toggleCam() {
        inCam = !inCam;
        test();
    }

    public void setCam(boolean value) {
        inCam = value;
    }

    @Override
    public void onPlayReady(ClientPlayNetworkHandler handler, PacketSender sender, MinecraftClient client) {
        inCam = false;
    }
}
