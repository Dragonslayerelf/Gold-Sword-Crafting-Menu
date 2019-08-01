package com.dragonslayerelf.crafting.crafting;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Crafting extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        OpenMenu n = new OpenMenu();
        CommandClass f = new CommandClass(n.getInvMens());
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(n, this);
        this.getCommand("giveMenu").setExecutor(f);
        this.getCommand("craftTest").setExecutor(f);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
