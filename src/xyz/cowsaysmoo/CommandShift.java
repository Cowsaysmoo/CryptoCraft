/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.cowsaysmoo;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Jared
 */
class CommandShift implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
            ShapeShift shift = new ShapeShift();
            WalletInfo info = new WalletInfo();
            String amount = args[0];
            String coinSent = args[1];
            String coinWithdrawn = args[2];
            
            args[0] = coinSent;
            String addressReturn = info.getAddress(sender, command, label, args);
            args[0] = coinWithdrawn;
            String addressWithdraw = info.getAddress(sender, command, label, args);
            
            
            shift.doTransaction(amount, coinSent + "_" + coinWithdrawn, addressWithdraw, addressReturn);
            
            
            return true;
        
       
    }
}
    

