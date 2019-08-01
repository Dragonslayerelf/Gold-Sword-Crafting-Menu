package com.dragonslayerelf.crafting.crafting;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class InventoryMenu implements Listener {
    private String invName = "";
    private int size = 0;
    private ArrayList<ItemStack> items = new ArrayList<>();
    private ArrayList<String> optionNames = new ArrayList<>();
    private ArrayList<List<String>> optionLores = new ArrayList<>();
    private Inventory inv;

    public InventoryMenu(String inName, int inSize, ItemStack[] inItems, String[] inNames, ArrayList<List<String>> inLores)
    {
        size = inSize;
        invName = inName;
        for(int i = 0; i < inItems.length; i++)
            items.add(inItems[i]);
        for(int i = 0; i < inNames.length; i++)
            optionNames.add(inNames[i]);
        for(int i = 0; i < inLores.size(); i++)
            optionLores.add(inLores.get(i));
        inv = Bukkit.getServer().createInventory(null, size, invName);
        for(int i = 0; i < items.size(); i++) {
            ItemMeta n = items.get(i).getItemMeta();
            n.setDisplayName(optionNames.get(i));
            n.setLore(optionLores.get(i));
            items.get(i).setItemMeta(n);
            inv.setItem(i, items.get(i));
        }

    }

    public String getInvName() {
        return invName;
    }
    public int getInvSize(){
        return size;
    }
    public ArrayList<ItemStack> getItems() {
        return items;
    }
    public ArrayList<String> getOptNames(){
        return optionNames;
    }
    public ArrayList<List<String>> getOptLores (){
        return optionLores;
    }
    public Inventory getInv(){
        return inv;
    }
}
