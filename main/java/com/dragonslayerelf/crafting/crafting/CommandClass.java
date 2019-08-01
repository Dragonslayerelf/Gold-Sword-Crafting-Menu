package com.dragonslayerelf.crafting.crafting;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandClass implements CommandExecutor {
    private ArrayList<InventoryMenu> invMens = new ArrayList<>();
    public CommandClass(ArrayList<InventoryMenu> n) {
        invMens = n;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player play = (Player) sender;
            if (command.getName().equals("giveMenu")) {
                play.openInventory(invMens.get(0).getInv());
            }
            if(command.getName().equals("craftTest"))
            {
                play.openInventory(invMens.get(1).getInv());
            }
        }
        return true;
    }
}
