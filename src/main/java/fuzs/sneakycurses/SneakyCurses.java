package fuzs.sneakycurses;

import fuzs.puzzleslib.config.AbstractConfig;
import fuzs.puzzleslib.config.ConfigHolder;
import fuzs.puzzleslib.config.ConfigHolderImpl;
import fuzs.sneakycurses.config.ClientConfig;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SneakyCurses implements ModInitializer {
    public static final String MOD_ID = "sneakycurses";
    public static final String MOD_NAME = "Sneaky Curses";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    @SuppressWarnings("Convert2MethodRef")
    public static final ConfigHolder<ClientConfig, AbstractConfig> CONFIG = ConfigHolder.client(() -> new ClientConfig());

    public static void onConstructMod() {
        ((ConfigHolderImpl<?, ?>) CONFIG).addConfigs(MOD_ID);
    }

    @Override
    public void onInitialize() {
        onConstructMod();
    }
}
