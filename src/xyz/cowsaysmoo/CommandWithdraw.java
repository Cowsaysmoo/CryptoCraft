package xyz.cowsaysmoo;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class CommandWithdraw implements CommandExecutor{
     
    FileConfiguration config = CryptoCraft.plugin.getConfig();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player) {
            if (args.length > 3) {
                sender.sendMessage("Too many arguments!");
                return false;
            } 
            if (args.length < 3) {
                sender.sendMessage("Not enough arguments!");
                return false;
            }
        WalletInfo info = new WalletInfo();
        info.sendWithdraw(sender, command, label, args);
        } else {
            sender.sendMessage("You must be a player!");
            return false;
        }
        return true;
    }
}
    
   
 
