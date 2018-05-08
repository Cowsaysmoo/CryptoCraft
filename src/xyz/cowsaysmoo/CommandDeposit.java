package xyz.cowsaysmoo;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandDeposit implements CommandExecutor{
     
    
    FileConfiguration config = CryptoCraft.plugin.getConfig();
    String address;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        Player player = (Player) sender;
        WalletInfo info = new WalletInfo();
        String address = info.getAddress(sender, command, label, args);
        player.sendMessage("Send coins to this address: ");
        player.sendMessage(address);
        
        return true;
    }
}
    
    

