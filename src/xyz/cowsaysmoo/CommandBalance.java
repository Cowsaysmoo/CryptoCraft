package xyz.cowsaysmoo;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandBalance implements CommandExecutor{
     
    FileConfiguration config = CryptoCraft.plugin.getConfig();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        
        Player player = (Player) sender;
        WalletInfo info = new WalletInfo();
        String address = info.getAddress(sender, command, label, args);
        String aBalance = info.getAvailableBalance(sender, command, label, args);
        String pBalance = info.getPendingBalance(sender, command, label, args);
        player.sendMessage("Address: ");
        player.sendMessage(address);
        player.sendMessage("Available balance: " + aBalance);
        player.sendMessage("Pending recieved balance: " + pBalance);
        
        return true;
    }
}
    
    
   
        
        


