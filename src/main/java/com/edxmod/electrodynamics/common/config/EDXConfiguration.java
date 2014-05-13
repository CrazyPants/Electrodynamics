package com.edxmod.electrodynamics.common.config;

import java.io.File;

/**
 * @author Royalixor.
 */
public class EDXConfiguration {

    public static File mainConfigFile;

    public static void init(String configPath) {
        mainConfigFile = new File(configPath + "main.cfg");

        EDXConfigurationMain.init(mainConfigFile);
    }
}
