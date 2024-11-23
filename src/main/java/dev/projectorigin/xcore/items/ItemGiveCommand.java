package dev.projectorigin.xcore.items;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.TabCompleter;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ItemGiveCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (args[0].equalsIgnoreCase("nocturnite")) {
            ItemStack thisitem = new ItemStack(Material.PAPER); {
                ItemMeta meta = thisitem.getItemMeta();
                meta.setCustomModelData(4000);
                meta.setItemName("§fNocturnite");
                thisitem.setItemMeta(meta);
                player.getInventory().addItem(thisitem);
                return true;
            }
        }
        if (args[0].equalsIgnoreCase("nocturnite_axe")) {
            ItemStack thisitem = new ItemStack(Material.NETHERITE_AXE); {
                ItemMeta meta = thisitem.getItemMeta();
                meta.setCustomModelData(1);
                meta.setItemName("§fNocturnite Axe");
                AttributeModifier damageModifier = new AttributeModifier(
                        UUID.randomUUID(), // Unique ID
                        "generic.attackDamage", // Attribute to modify
                        13.0, // Amount of damage increase (adjust this value as needed)
                        AttributeModifier.Operation.ADD_NUMBER, // Add the value to base damage
                        EquipmentSlot.HAND // Apply only when in main hand
                );
                meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damageModifier);
                thisitem.setItemMeta(meta);
                player.getInventory().addItem(thisitem);
                return true;
            }
        }
        if (args[0].equalsIgnoreCase("nocturnite_hoe")) {
            ItemStack thisitem = new ItemStack(Material.NETHERITE_HOE); {
                ItemMeta meta = thisitem.getItemMeta();
                meta.setCustomModelData(1);
                meta.setItemName("§fNocturnite Hoe");
                thisitem.setItemMeta(meta);
                player.getInventory().addItem(thisitem);
                return true;
            }
        }

        return false;
    }
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> suggestions = new ArrayList<>();

        if (args.length == 1) {
            // Provide a list of items for tab completion
            List<String> items = Arrays.asList(
                    "nocturnite","nocturnite_axe","nocturnite_hoe"

            );
            for (String item : items) {
                if (item.toLowerCase().startsWith(args[0].toLowerCase())) {
                    suggestions.add(item);
                }
            }
        }

        return suggestions;
    }
}
