package gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class GUI {
    private final ArrayList<ItemStack> itemlist = new ArrayList<>();
    private final Inventory inv;

    public GUI(String name, int size) {
        this.inv = Bukkit.createInventory(null, size, name);
    }

    public void setItem(Material material, int index, int amount, String title, String... lore) {
        this.inv.setItem(index, createItem(material, amount, title, lore));
    }

    private ItemStack createItem(Material material, int amount, String title, String... lore) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(title);
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    public Inventory getGUI() {
        return inv;
    }

}
