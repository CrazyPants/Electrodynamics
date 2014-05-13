package com.edxmod.electrodynamics.common.events.misc;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.apache.logging.log4j.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Royalixor.
 */
public class CapeHandler {

    private final String listLocation = "https://dl.dropboxusercontent.com/u/218544836/capes.txt";
    private final int timeout = 1000;

    private static final Graphics CAPE_IMAGE = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB).getGraphics();
    private HashMap<String, String> capes = new HashMap<String, String>();
    private ArrayList<AbstractClientPlayer> capePlayers = new ArrayList<AbstractClientPlayer>();

    public static CapeHandler instance;

    public CapeHandler() {
        buildCapeURLDatabase();
        instance = null;
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void preRenderSpecials(RenderPlayerEvent.Specials.Pre event) {
        if (Loader.isModLoaded("shadersmod")) {
            return;
        }

        if (event.entityPlayer instanceof AbstractClientPlayer) {
            AbstractClientPlayer clientPlayer = (AbstractClientPlayer) event.entityPlayer;

            if (!capePlayers.contains(clientPlayer)) {
                String caperURL = capes.get(event.entityPlayer.getDisplayName());

                if (caperURL == null) {
                    return;
                }

                capePlayers.add(clientPlayer);

                ReflectionHelper.setPrivateValue(ThreadDownloadImageData.class, clientPlayer.getTextureCape(), false, new String[]{"tex tureUploaded", "textureUploaded"});

                new Thread(new CapeThread(clientPlayer, caperURL)).start();
                event.renderCape = true;
            }
        }
    }

    public void buildCapeURLDatabase() {
        URL url;

        try {
            url = new URL(listLocation);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String string;
            int lineTracker = 1;

            while ((string = bufferedReader.readLine()) != null) {
                if (!string.startsWith("--") && !string.isEmpty()) {
                    if (string.contains(":")) {
                        String nickName = string.substring(0, string.indexOf(":"));
                        String capeLink = string.substring(string.indexOf(":") + 1);
                        new Thread(new CapePreload(capeLink)).start();
                        capes.put(nickName, capeLink);
                    } else {
                        FMLLog.log(Level.WARN, "[EDX] Syntax error on line " + lineTracker + ": " + string + "of the cape list. Inform the development team!");
                    }
                }
                lineTracker++;
            }
            bufferedReader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class CapeThread implements Runnable {

        AbstractClientPlayer clientPlayer;
        String capeURL;

        public CapeThread(AbstractClientPlayer player, String cape) {
            clientPlayer = player;
            capeURL = cape;
        }

        @Override
        public void run() {
            try {
                Image cape = new ImageIcon(new URL(capeURL)).getImage();
                BufferedImage bufferedImage = new BufferedImage(cape.getWidth(null), cape.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                bufferedImage.getGraphics().drawImage(cape, 0, 0, null);
                ReflectionHelper.setPrivateValue(ThreadDownloadImageData.class, clientPlayer.getTextureCape(), bufferedImage, new String[]{"bufferedImage", "bufferedImage"});

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    private class CapePreload implements Runnable {

        String capeURL;

        public CapePreload(String link) {
            capeURL = link;
        }

        @Override
        public void run() {
            try {
                CAPE_IMAGE.drawImage(new ImageIcon(new URL(capeURL)).getImage(), 0, 0, null);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    public void refreshCapes() {
        capes.clear();
        capePlayers.clear();
        buildCapeURLDatabase();
    }
}
