package dev.projectorigin.xcore.items;

import net.kyori.adventure.text.Component;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.TabCompleter;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ItemGiveCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (args[0].equalsIgnoreCase("nocturnite")) {
            ItemStack nocturnite = new ItemStack(Material.PAPER); {
                ItemMeta meta = nocturnite.getItemMeta();
                meta.setCustomModelData(4000);
                meta.itemName(Component.text("Nocturnite"));
                nocturnite.setItemMeta(meta);
                player.getInventory().addItem(nocturnite);
                return true;
            }
        } else if (args[0].equalsIgnoreCase("nocturnite_axe")) {
            ItemStack nocturniteAxe = new ItemStack(Material.NETHERITE_AXE);
            {
                ItemMeta meta = nocturniteAxe.getItemMeta();
                meta.setCustomModelData(1);
                meta.itemName(Component.text("Nocturnite Axe"));
                AttributeModifier damageModifier = new AttributeModifier(
                        UUID.randomUUID(), // Unique ID
                        "generic.attackDamage", // Attribute to modify
                        13.0, // Amount of damage increase (adjust this value as needed)
                        AttributeModifier.Operation.ADD_NUMBER, // Add the value to base damage
                        EquipmentSlot.HAND // Apply only when in main hand
                );
                meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damageModifier);
                nocturniteAxe.setItemMeta(meta);
                player.getInventory().addItem(nocturniteAxe);
                return true;
            }
        } else if (args[0].equalsIgnoreCase("nocturnite_hoe")) {
            ItemStack nocturniteHoe = new ItemStack(Material.NETHERITE_HOE); {
                ItemMeta meta = nocturniteHoe.getItemMeta();
                meta.setCustomModelData(1);
                meta.itemName(Component.text("Nocturnite Hoe"));
                nocturniteHoe.setItemMeta(meta);
                player.getInventory().addItem(nocturniteHoe);
                return true;
            }
        }

        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return Arrays.asList("nocturnite", "nocturnite_axe", "nocturnite_hoe");
    }

}
