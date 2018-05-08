package xyz.cowsaysmoo;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class CommandTip implements CommandExecutor{

    FileConfiguration config = CryptoCraft.plugin.getConfig();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        
        WalletInfo info = new WalletInfo();
        info.sendTip(sender, command, label, args);   
        return true;
    }
}

