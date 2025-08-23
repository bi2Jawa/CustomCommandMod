package com.github.bi2jawa.customcommands;

import com.github.bi2jawa.customcommands.Commands.CustomCommandBase;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Config {
    public static Configuration config;

    public static boolean readBool(File file, CustomCommandBase command, String key) {
        config = new Configuration(file);
        config.load();
        return config.get(command.getCommandName(), key, true).getBoolean();
    }

    public static void writeConfig(CustomCommandBase command, Boolean bool, String key) {
        config.get(command.getCommandName(), key, true).setValue(bool);
        if (config.hasChanged()) {
            config.save();
        }
    }

    public static void writeConfig(CustomCommandBase command, String str, String key) {
        config.get(command.getCommandName(), key, "").setValue(str);
        if (config.hasChanged()) {
            config.save();
        }
    }
}
