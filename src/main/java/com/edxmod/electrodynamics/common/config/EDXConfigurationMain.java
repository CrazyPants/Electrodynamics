package com.edxmod.electrodynamics.common.config;


import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

import java.io.File;

/**
 * @author Royalixor.
 */
public class EDXConfigurationMain {

    public static Configuration config;

    public static boolean optifineErrors;
    public static int spawnSearchRadius;

    public static void init(File configFile) {
        config = new Configuration(configFile);

        try {
            config.load();

            optifineErrors = config.get("Miscellaneous Settings", "Display Optifine Errors", true).getBoolean(true);
            spawnSearchRadius = config.get("Miscellaneous Settings", "What radius should EDX search to find spawn", 1024, "This values must be above 256").getInt();
        } catch (Exception e) {
            FMLLog.log(Level.ERROR, e, "[EDX] There was a problem loading the configuration!");
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}
