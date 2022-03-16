package fuzs.sneakycurses.client;

import fuzs.sneakycurses.client.handler.ItemTooltipHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;

public class SneakyCursesClient implements ClientModInitializer {
    public static void onConstructMod() {
        registerHandlers();
    }

    private static void registerHandlers() {
        ItemTooltipHandler itemTooltipHandler = new ItemTooltipHandler();
        ItemTooltipCallback.EVENT.register(itemTooltipHandler::onItemTooltip);
    }

    @Override
    public void onInitializeClient() {
        onConstructMod();
    }
}
