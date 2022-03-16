package fuzs.sneakycurses.client.handler;

import fuzs.sneakycurses.SneakyCurses;
import fuzs.sneakycurses.util.CurseMatcher;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ItemTooltipHandler {
    public void onItemTooltip(ItemStack stack, TooltipFlag context, List<Component> lines) {
        if (!CurseMatcher.isAffected(stack)) return;
        this.dyeHoverName(lines);
        this.hideCurses(lines, this.getCursesAsTooltip(stack));
    }

    private void dyeHoverName(List<Component> tooltip) {
        if (!SneakyCurses.CONFIG.client().colorName) return;
        TextComponent hoverName = null;
        for (Component value : tooltip) {
            if (value instanceof TextComponent component && component.getText().equals("")) {
                hoverName = component;
                break;
            }
        }
        if (hoverName != null) {
            hoverName.withStyle(ChatFormatting.RED);
        }
    }

    private void hideCurses(List<Component> tooltip, List<String> curses) {
        if (!SneakyCurses.CONFIG.client().hideCurses) return;
        // use this approach for compatibility with enchantment descriptions mod as this also matches their description key format
        Iterator<Component> iterator = tooltip.iterator();
        while (iterator.hasNext()) {
            Component component = iterator.next();
            if (component instanceof TranslatableComponent translatableComponent && translatableComponent.getKey().startsWith("enchantment")) {
                for (String curse : curses) {
                    if (translatableComponent.getKey().startsWith(curse)) {
                        iterator.remove();
                        break;
                    }
                }
            }
        }
    }

    private List<String> getCursesAsTooltip(ItemStack stack) {
        return EnchantmentHelper.getEnchantments(stack).keySet().stream()
                .filter(Objects::nonNull)
                .filter(Enchantment::isCurse)
                .map(Enchantment::getDescriptionId)
                .collect(Collectors.toList());
    }
}
