package xyz.cowsaysmoo;

import java.io.*;
import java.net.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandHelp{
     
    FileConfiguration config = CryptoCraft.plugin.getConfig();
    CommandHelp(CommandSender sender, Command command, String label, String[] args)  throws MalformedURLException, IOException {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("Help");
        }
    }
}
    
