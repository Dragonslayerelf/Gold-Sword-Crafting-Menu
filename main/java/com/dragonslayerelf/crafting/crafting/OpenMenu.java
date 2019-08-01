package com.dragonslayerelf.crafting.crafting;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OpenMenu implements Listener {

    private ArrayList<InventoryMenu> invMens = new ArrayList<>();
    public ArrayList<InventoryMenu> getInvMens() {
        return invMens;
    }
    public OpenMenu() {
        ItemStack blankItem = new ItemStack(Material.GLASS_PANE, 1);
        ItemStack[] items = {new ItemStack(Material.BLUE_WOOL, 1), new ItemStack(Material.BLACK_WOOL, 1), new ItemStack(Material.RED_WOOL, 1)};
        String[] names = {"Give Diamond", "Give Coal", "Give Redstone"};
        ArrayList<List<String>> lores = new ArrayList<>();
        lores.add(Arrays.asList("Gives you a diamond!"));
        lores.add(Arrays.asList("Gives you coal!", "Without mining!"));
        lores.add(Arrays.asList("Gives you redstone!"));
        invMens.add(new InventoryMenu("Give Menu", 9, items, names, lores));
        ItemStack[] items2 = {new ItemStack(Material.GOLDEN_SWORD, 1), new ItemStack(Material.GOLDEN_SWORD, 1), new ItemStack(Material.GOLDEN_SWORD, 1), blankItem, new ItemStack(Material.FIRE_CHARGE, 1), blankItem, blankItem, blankItem, new ItemStack(Material.BLACK_WOOL, 1)};
        String[] names2 = {"Craft: Golden Sword Test 1 (Broken)", "Craft: Golden Sword Test 2 (Broken)", "Craft: Golden Sword Test 3 (Functioning)", " ", "Placeholder", "  ", "   ", "    ", "Output"};
        ArrayList<List<String>> lores2 = new ArrayList<>();
        lores2.add(Arrays.asList("Craft one golden sword using:", "2 Gold Ingot", "1 Stick", "This one won't craft unless you have 2 or 3 stacks of either 2 gold ingots and a stick or 2 separate stacks of gold bars and 1 stick respectively"));
        lores2.add(Arrays.asList("Craft one golden sword using:", "2 Gold Ingot", "1 Stick", "This one will craft, but will take all your sticks and all your gold."));
        lores2.add(Arrays.asList("Craft one golden sword using:", "2 Gold Ingot", "1 Stick", "This one works."));
        lores2.add(Arrays.asList(""));
        lores2.add(Arrays.asList("Placeholder"));
        lores2.add(Arrays.asList(""));
        lores2.add(Arrays.asList(""));
        lores2.add(Arrays.asList(""));
        lores2.add(Arrays.asList("Output"));
        invMens.add(new InventoryMenu("Crafting Gold Sword", 9, items2, names2, lores2));
    }
    public static void removeItems(Inventory inventory, Material type, int amount) {
        if (amount <= 0) return;
        int size = inventory.getSize();
        for (int slot = 0; slot < size; slot++) {
            ItemStack is = inventory.getItem(slot);
            if (is == null) continue;
            if (type == is.getType()) {
                int newAmount = is.getAmount() - amount;
                if (newAmount > 0) {
                    is.setAmount(newAmount);
                    break;
                } else {
                    inventory.clear(slot);
                    amount = -newAmount;
                    if (amount == 0) break;
                }
            }
        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        if(e.getWhoClicked() instanceof Player)
        {
            Player ply = (Player) e.getWhoClicked();
            if(e.getInventory().equals(invMens.get(0).getInv())) {
                e.setCancelled(true);
                if(e.getCurrentItem().equals(invMens.get(0).getItems().get(0))) {
                    ply.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
                }
                else if(e.getCurrentItem().equals(invMens.get(0).getItems().get(1)))
                {
                    ply.getInventory().addItem(new ItemStack(Material.COAL, 1));
                }
                else if (e.getCurrentItem().equals(invMens.get(0).getItems().get(2)))
                {
                    ply.getInventory().addItem(new ItemStack(Material.REDSTONE, 3));
                }
            }
            if(e.getInventory().equals(invMens.get(1).getInv())) {
                e.setCancelled(true);
                if (e.getCurrentItem().equals(invMens.get(1).getItems().get(0))) {
                    boolean crafting = false;
                    boolean type1 = false;
                    boolean type2 = false;
                    if ((ply.getInventory().contains(new ItemStack(Material.GOLD_INGOT, 2)) && ply.getInventory().contains(new ItemStack(Material.STICK, 1)))) {
                        crafting = true;
                        type1 = true;
                    } else if ((ply.getInventory().contains(new ItemStack(Material.GOLD_INGOT, 1)) && ply.getInventory().contains(new ItemStack(Material.GOLD_INGOT, 1)) && ply.getInventory().contains(new ItemStack(Material.STICK, 1)))) {
                        crafting = true;
                        type2 = true;
                    } else {
                        ply.sendMessage(ChatColor.RED + "You do not have the proper materials.");
                    }
                    if (crafting) {
                        ply.sendMessage(ChatColor.GOLD + "Crafting Gold Sword");
                        if (type1) {
                            ply.getInventory().remove(new ItemStack(Material.GOLD_INGOT, 2));
                        }
                        if (type2) {
                            ply.getInventory().remove(new ItemStack(Material.GOLD_INGOT, 1));
                            ply.getInventory().remove(new ItemStack(Material.GOLD_INGOT, 1));
                        }
                        ply.getInventory().remove(new ItemStack(Material.STICK, 1));
                        ply.getInventory().addItem(new ItemStack(Material.GOLDEN_SWORD, 1));
                    }
                }
            }
            if(e.getCurrentItem().equals(invMens.get(1).getItems().get(1)))
            {
                boolean crafting = false;
                boolean type1 = false;
                boolean type2 = false;
                if (ply.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 2) && ply.getInventory().containsAtLeast(new ItemStack(Material.STICK), 1)) {
                    crafting = true;
                    type1 = true;
                }
                else {
                    ply.sendMessage(ChatColor.RED + "You do not have the proper materials.");
                }
                if(crafting)
                {
                    ply.sendMessage(ChatColor.GOLD + "Crafting Gold Sword");
                    if(type1) { ply.getInventory().remove(Material.GOLD_INGOT); ply.getInventory().remove(Material.GOLD_INGOT); }
                    ply.getInventory().remove(Material.STICK);
                    ply.getInventory().addItem(new ItemStack(Material.GOLDEN_SWORD, 1));
                }
            }
            if(e.getCurrentItem().equals(invMens.get(1).getItems().get(2)))
            {
                boolean crafting = false;
                if (ply.getInventory().contains(Material.GOLD_INGOT, 2) && ply.getInventory().contains(Material.STICK, 1)) {
                    crafting = true;
                }
                else {
                    ply.sendMessage(ChatColor.RED + "You do not have the proper materials.");
                }
                if(crafting)
                {
                    Map<Integer, ItemStack> mp = ply.getInventory().addItem(new ItemStack(Material.GOLDEN_SWORD, 1));
                    if(mp.isEmpty()) {
                        ply.sendMessage(ChatColor.GOLD + "Crafting Gold Sword");
                        removeItems(ply.getInventory(), Material.GOLD_INGOT, 2);
                        removeItems(ply.getInventory(), Material.STICK, 1);
                    }
                    else {
                        ply.sendMessage(ChatColor.RED + "Inventory is full, can't add.");
                    }
                }
            }
        }
    }
}
