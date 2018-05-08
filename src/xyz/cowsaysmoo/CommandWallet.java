package xyz.cowsaysmoo;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandWallet implements CommandExecutor{
     
    FileConfiguration config = CryptoCraft.plugin.getConfig();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player) {
            try {
                Player player = (Player) sender;
                String StrURL = null;
                switch (args[0]){
                    case "doge":
                        StrURL = ("https://block.io/api/v2/get_new_address/?api_key=" + config.getString("doge-api-key") + "&label=" + player.getUniqueId());
                        break;
                    case "btc":
                        StrURL = ("https://block.io/api/v2/get_new_address/?api_key=" + config.getString("btc-api-key") + "&label=" + player.getUniqueId());
                        break;
                    case "ltc":
                        StrURL = ("https://block.io/api/v2/get_new_address/?api_key=" + config.getString("ltc-api-key") + "&label=" + player.getUniqueId());
                        break;
                    case "dogetest":
                        StrURL = ("https://block.io/api/v2/get_new_address/?api_key=" + config.getString("dogetest-api-key") + "&label=" + player.getUniqueId());
                        break;
                    case "btctest":
                        StrURL = ("https://block.io/api/v2/get_new_address/?api_key=" + config.getString("btctest-api-key") + "&label=" + player.getUniqueId());
                        break;
                    case "ltctest":
                        StrURL = ("https://block.io/api/v2/get_new_address/?api_key=" + config.getString("ltctest-api-key") + "&label=" + player.getUniqueId());
                        break;     
                }
                URL url = new URL(StrURL);
                HttpURLConnection conn = null;
                try {
                    conn = (HttpURLConnection) url.openConnection();
                } catch (IOException ex) {
                    Logger.getLogger(CommandWallet.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    if(conn.getResponseCode() < 400){
                        
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            inputLine = inputLine.replaceAll("\\}","");
                            inputLine = inputLine.replaceAll("\\{","");
                            inputLine = inputLine.replaceAll("\\s","");
                            inputLine = inputLine.replaceAll(",","");
                            inputLine = inputLine.replaceAll("\"","");
                            inputLine = inputLine.replaceAll(":"," : ");
                            inputLine = inputLine.replaceAll("_"," ");
                            player.sendMessage(inputLine);
                        }
                    }
                    else{
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(conn.getErrorStream()));
                        
                        String inputLine;
                        int count = 1;
                        while ((inputLine = in.readLine()) != null) {
                            if(count == 2){
                                player.sendMessage("Failed!");
                                count = count + 1;
                            }
                            else if(count == 4){
                                
                                inputLine = inputLine.replaceAll(",","");
                                inputLine = inputLine.replaceAll("\"","");
                                inputLine = inputLine.replaceAll("\\}","");
                                inputLine = inputLine.replaceAll("\\{","");
                                inputLine = inputLine.replaceAll("error_message :","");
                                player.sendMessage(inputLine);
                                count = count + 1;
                            }
                            else{
                                count = count + 1;
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(CommandWallet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (MalformedURLException ex) {     
                Logger.getLogger(CommandWallet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
}


    
  
