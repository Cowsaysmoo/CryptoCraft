/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.cowsaysmoo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import static xyz.cowsaysmoo.CryptoCraft.plugin;

/**
 *
 * @author Jared
 */
public class WalletInfo {
    FileConfiguration config = CryptoCraft.plugin.getConfig();
    public String getAddress(CommandSender sender, Command command, String label, String[] args) {
        String address = null;
        if (sender instanceof Player) {
            try {
                Player player = (Player) sender;
                String StrURL = null;
                switch (args[0]){
                    case "doge":
                        StrURL = ("https://block.io/api/v2/get_balance/?api_key=" + config.getString("doge-api-key") + "&labels=" + player.getUniqueId());
                        break;
                    case "btc":
                        StrURL = ("https://block.io/api/v2/get_balance/?api_key=" + config.getString("btc-api-key") + "&labels=" + player.getUniqueId());
                        break;
                    case "ltc":
                        StrURL = ("https://block.io/api/v2/get_balance/?api_key=" + config.getString("ltc-api-key") + "&labels=" + player.getUniqueId());
                        break;
                    case "dogetest":
                        StrURL = ("https://block.io/api/v2/get_balance/?api_key=" + config.getString("dogetest-api-key") + "&labels=" + player.getUniqueId());
                        break;
                    case "btctest":
                        StrURL = ("https://block.io/api/v2/get_balance/?api_key=" + config.getString("btctest-api-key") + "&labels=" + player.getUniqueId());
                        break;
                    case "ltctest":
                        StrURL = ("https://block.io/api/v2/get_balance/?api_key=" + config.getString("ltctest-api-key") + "&labels=" + player.getUniqueId());
                        break;          
                }
                URL url = new URL(StrURL);
                HttpURLConnection conn = null;
                try {
                    conn = (HttpURLConnection) url.openConnection();
                } catch (IOException ex) {
                    Logger.getLogger(CommandDeposit.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    if(conn.getResponseCode() < 400){
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));
                        
                        String inputLine;
                        int count = 1;
                        
                        
                        while ((inputLine = in.readLine()) != null) {
                            
                            if(count == 11){
                                inputLine = inputLine.replaceAll("\\s","");
                                inputLine = inputLine.replaceAll(",","");
                                inputLine = inputLine.replaceAll("\"","");
                                inputLine = inputLine.replaceAll("address:","");
                                address = inputLine;
                                count = count + 1;
                            }
                            else{
                                count = count + 1;
                            }
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
                                inputLine = inputLine.trim();
                                player.sendMessage(inputLine);
                                count = count + 1;
                            }
                            else{
                                count = count + 1;
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(CommandDeposit.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (MalformedURLException ex) {        
                Logger.getLogger(CommandDeposit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return address;
    }
    
    public String getAvailableBalance(CommandSender sender, Command command, String label, String[] args){
       String balance = null;
       if (sender instanceof Player) {
            try {
                Player player = (Player) sender;
                String StrURL = null;
                switch (args[0]){
                    case "doge":
                        StrURL = ("https://block.io/api/v2/get_balance/?api_key=" + config.getString("doge-api-key") + "&labels=" + player.getUniqueId());
                        break;
                    case "btc":
                        StrURL = ("https://block.io/api/v2/get_balance/?api_key=" + config.getString("btc-api-key") + "&labels=" + player.getUniqueId());
                        break;
                    case "ltc":
                        StrURL = ("https://block.io/api/v2/get_balance/?api_key=" + config.getString("ltc-api-key") + "&labels=" + player.getUniqueId());
                        break;
                    case "dogetest":
                        StrURL = ("https://block.io/api/v2/get_balance/?api_key=" + config.getString("dogetest-api-key") + "&labels=" + player.getUniqueId());
                        break;
                    case "btctest":
                        StrURL = ("https://block.io/api/v2/get_balance/?api_key=" + config.getString("btctest-api-key") + "&labels=" + player.getUniqueId());
                        break;
                    case "ltctest":
                        StrURL = ("https://block.io/api/v2/get_balance/?api_key=" + config.getString("ltctest-api-key") + "&labels=" + player.getUniqueId());
                        break; 
                }
                URL url = new URL(StrURL);
                HttpURLConnection conn = null;
                try {
                    conn = (HttpURLConnection) url.openConnection();
                } catch (IOException ex) {
                    Logger.getLogger(CommandBalance.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    if(conn.getResponseCode() < 400){
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));
                        
                        String inputLine;
                        int count = 1;
                        while ((inputLine = in.readLine()) != null) {
                            
                            if(count == 12){
                                inputLine = inputLine.replaceAll("\\s","");
                                inputLine = inputLine.replaceAll(",","");
                                inputLine = inputLine.replaceAll("\"","");
                                inputLine = inputLine.replaceAll(":","");
                                inputLine = inputLine.replaceAll("available_balance","");
                                balance = inputLine;
                                
                                count = count + 1;
                            }
                            else{
                                count = count + 1;
                            }
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
                                inputLine = inputLine.trim();
                                player.sendMessage(inputLine);
                                count = count + 1;
                            }
                            else{
                                count = count + 1;
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(CommandBalance.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (MalformedURLException ex) {
                Logger.getLogger(CommandBalance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return balance;
    }

    public String getPendingBalance(CommandSender sender, Command command, String label, String[] args){
        String balance = null;
        if (sender instanceof Player) {
            try {
                Player player = (Player) sender;
                String StrURL = null;
                switch (args[0]){
                    case "doge":
                        StrURL = ("https://block.io/api/v2/get_balance/?api_key=" + config.getString("doge-api-key") + "&labels=" + player.getUniqueId());
                        break;
                    case "btc":
                        StrURL = ("https://block.io/api/v2/get_balance/?api_key=" + config.getString("btc-api-key") + "&labels=" + player.getUniqueId());
                        break;
                    case "ltc":
                        StrURL = ("https://block.io/api/v2/get_balance/?api_key=" + config.getString("ltc-api-key") + "&labels=" + player.getUniqueId());
                        break;
                    case "dogetest":
                        StrURL = ("https://block.io/api/v2/get_balance/?api_key=" + config.getString("dogetest-api-key") + "&labels=" + player.getUniqueId());
                        break;
                    case "btctest":
                        StrURL = ("https://block.io/api/v2/get_balance/?api_key=" + config.getString("btctest-api-key") + "&labels=" + player.getUniqueId());
                        break;
                    case "ltctest":
                        StrURL = ("https://block.io/api/v2/get_balance/?api_key=" + config.getString("ltctest-api-key") + "&labels=" + player.getUniqueId());
                        break; 
                }
                URL url = new URL(StrURL);
                HttpURLConnection conn = null;
                try {
                    conn = (HttpURLConnection) url.openConnection();
                } catch (IOException ex) {
                    Logger.getLogger(CommandBalance.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    if(conn.getResponseCode() < 400){
                        BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                        
                        String inputLine;
                        int count = 1;
                        while ((inputLine = in.readLine()) != null) {
                            
                            if(count == 13){
                                inputLine = inputLine.replaceAll("\\s","");
                                inputLine = inputLine.replaceAll(",","");
                                inputLine = inputLine.replaceAll("\"","");
                                inputLine = inputLine.replaceAll(":","");
                                inputLine = inputLine.replaceAll("pending_received_balance","");
                                balance = inputLine;
                                
                                count = count + 1;
                            }
                            else{
                                count = count + 1;
                            }
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
                                inputLine = inputLine.trim();
                                player.sendMessage(inputLine);
                                count = count + 1;
                            }
                            else{
                                count = count + 1;
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(CommandBalance.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (MalformedURLException ex) {
                Logger.getLogger(CommandBalance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return balance;
    }
    
    public void sendTip(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player) {
            try {
                Player player = (Player) sender;
                Player target = player.getServer().getPlayer(args[1]);
                String UUID = null;
                Scanner scanner = null;
                try {
                    scanner = new Scanner(plugin.getPlayersFile());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CommandTip.class.getName()).log(Level.SEVERE, null, ex);
                }
                while (scanner.hasNextLine()) {
                    String lineFromFile = scanner.nextLine();
                    if(lineFromFile.contains(args[1])) {
                        
                        lineFromFile = lineFromFile.replaceAll(args[1],"");
                        lineFromFile = lineFromFile.replaceAll("\\s","");
                        UUID = lineFromFile;
                        break;
                    }
                }
                if(UUID == null){
                    player.sendMessage("Player does not exist! [Usernames are case sensitive]");
                    return;
                }
                String StrURL = null;
                switch (args[0]){
                    case "doge":
                        StrURL = ("https://block.io/api/v2/withdraw_from_labels/?api_key=" + config.getString("doge-api-key") + "&amounts=" + args[2] + "&from_labels=" + player.getUniqueId() + "&to_labels=" + UUID + "&pin=" + config.getString("secret-pin"));
                        break;
                    case "btc":
                        StrURL = ("https://block.io/api/v2/withdraw_from_labels/?api_key=" + config.getString("btc-api-key") + "&amounts=" + args[2] + "&from_labels=" + player.getUniqueId() + "&to_labels=" + UUID + "&pin=" + config.getString("secret-pin"));
                        break;
                    case "ltc":
                        StrURL = ("https://block.io/api/v2/withdraw_from_labels/?api_key=" + config.getString("ltc-api-key") + "&amounts=" + args[2] + "&from_labels=" + player.getUniqueId() + "&to_labels=" + UUID + "&pin=" + config.getString("secret-pin"));
                        break;
                    case "dogetest": 
                        StrURL = ("https://block.io/api/v2/withdraw_from_labels/?api_key=" + config.getString("dogetest-api-key") + "&amounts=" + args[2] + "&from_labels=" + player.getUniqueId() + "&to_labels=" + UUID + "&pin=" + config.getString("secret-pin"));
                        break;
                    case "btctest":
                        StrURL = ("https://block.io/api/v2/withdraw_from_labels/?api_key=" + config.getString("btctest-api-key") + "&amounts=" + args[2] + "&from_labels=" + player.getUniqueId() + "&to_labels=" + UUID + "&pin=" + config.getString("secret-pin"));
                        break;
                    case "ltctest":
                        StrURL = ("https://block.io/api/v2/withdraw_from_labels/?api_key=" + config.getString("ltctest-api-key") + "&amounts=" + args[2] + "&from_labels=" + player.getUniqueId() + "&to_labels=" + UUID + "&pin=" + config.getString("secret-pin"));
                        break;
                }
                URL url = null;
                try {
                    url = new URL(StrURL);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(CommandTip.class.getName()).log(Level.SEVERE, null, ex);
                }
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if(conn.getResponseCode() < 400){
                    BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                    
                        String inputLine;
                        int count = 1;
                        while ((inputLine = in.readLine()) != null) {
                        
                            if(count == 1 || count == 10 || count == 11){
                                count = count + 1;
                            }
                            else{
                                inputLine = inputLine.replaceAll("\\s","");
                                inputLine = inputLine.replaceAll(",","");
                                inputLine = inputLine.replaceAll("\\}","");
                                inputLine = inputLine.replaceAll("\\{","");
                                inputLine = inputLine.replaceAll("\"","");
                                inputLine = inputLine.replaceAll(":"," : ");
                                inputLine = inputLine.replaceAll("data :","");
                                inputLine = inputLine.replaceAll("_"," ");
                            
                                player.sendMessage(inputLine);
                                count = count + 1;
                            }
                        } 
                    }
                    else{
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
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
                                inputLine = inputLine.trim();
                                player.sendMessage(inputLine);
                                
                                if("n".equals(args[3])){
                                    target.sendMessage(player.getName() + " has sent you " + args[2] + " " + args[0] + "!");
                                }
                                else if(args[3] == null){
                                    target.sendMessage("Someone has sent you " + args[2] + " " + args[0] + "!");
                                }
                                else{
                                    target.sendMessage("Someone has sent you " + args[2] + " " + args[0] + "!");
                                }
                                count = count + 1;
                            }
                            else{
                                count = count + 1;
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(CommandTip.class.getName()).log(Level.SEVERE, null, ex);    
                }
            }
        return;
    }
    
    public void sendWithdraw(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player) {
            try {
                Player player = (Player) sender;
                String StrURL = null;
                switch (args[0]){
                    case "doge":
                        StrURL = ("https://block.io/api/v2/withdraw_from_labels/?api_key=" + config.getString("doge-api-key") + "&amounts=" + args[2] + "&from_labels=" + player.getUniqueId() + "&to_address=" + args[1] + "&pin=" + config.getString("secret-pin"));
                        break;
                    case "btc":
                        StrURL = ("https://block.io/api/v2/withdraw_from_labels/?api_key=" + config.getString("btc-api-key") + "&amounts=" + args[2] + "&from_labels=" + player.getUniqueId() + "&to_address=" + args[1] + "&pin=" + config.getString("secret-pin"));
                        break;
                    case "ltc":
                        StrURL = ("https://block.io/api/v2/withdraw_from_labels/?api_key=" + config.getString("ltc-api-key") + "&amounts=" + args[2] + "&from_labels=" + player.getUniqueId() + "&to_address=" + args[1] + "&pin=" + config.getString("secret-pin"));
                        break;
                    case "dogetest":
                        StrURL = ("https://block.io/api/v2/withdraw_from_labels/?api_key=" + config.getString("dogetest-api-key") + "&amounts=" + args[2] + "&from_labels=" + player.getUniqueId() + "&to_address=" + args[1] + "&pin=" + config.getString("secret-pin"));
                        break;
                    case "btctest":
                        StrURL = ("https://block.io/api/v2/withdraw_from_labels/?api_key=" + config.getString("btctest-api-key") + "&amounts=" + args[2] + "&from_labels=" + player.getUniqueId() + "&to_address=" + args[1] + "&pin=" + config.getString("secret-pin"));
                        break;
                    case "ltctest":
                        StrURL = ("https://block.io/api/v2/withdraw_from_labels/?api_key=" + config.getString("ltctest-api-key") + "&amounts=" + args[2] + "&from_labels=" + player.getUniqueId() + "&to_address=" + args[1] + "&pin=" + config.getString("secret-pin"));
                        break; 
                }
                URL url = new URL(StrURL);
                
                HttpURLConnection conn = null;
                try {
                    conn = (HttpURLConnection) url.openConnection();
                } catch (IOException ex) {
                    Logger.getLogger(CommandWithdraw.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    if(conn.getResponseCode() < 400){
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));
                        
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            inputLine = inputLine.replaceAll("\\s","");
                            inputLine = inputLine.replaceAll(",","");
                            inputLine = inputLine.replaceAll("\"","");
                            inputLine = inputLine.replaceAll("\\}","");
                            inputLine = inputLine.replaceAll("\\{","");
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
                                inputLine = inputLine.trim();
                                player.sendMessage(inputLine);
                                count = count + 1;
                            }
                            else{
                                count = count + 1;
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(CommandWithdraw.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (MalformedURLException ex) {        
                Logger.getLogger(CommandWithdraw.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
    public void sendDonation(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player) {
            try {
                Player player = (Player) sender;
                String StrURL = null;
                switch (args[0]){
                    case "doge":
                        StrURL = ("https://block.io/api/v2/withdraw_from_labels/?api_key=" + config.getString("doge-api-key") + "&amounts=" + args[2] + "&from_labels=" + player.getUniqueId() + "&to_address=" + config.getString("donate-address") + "&pin=" + config.getString("secret-pin"));
                        break;
                    case "btc":
                        StrURL = ("https://block.io/api/v2/withdraw_from_labels/?api_key=" + config.getString("btc-api-key") + "&amounts=" + args[2] + "&from_labels=" + player.getUniqueId() + "&to_address=" + config.getString("donate-address") + "&pin=" + config.getString("secret-pin"));
                        break;
                    case "ltc":
                        StrURL = ("https://block.io/api/v2/withdraw_from_labels/?api_key=" + config.getString("ltc-api-key") + "&amounts=" + args[2] + "&from_labels=" + player.getUniqueId() + "&to_address=" + config.getString("donate-address") + "&pin=" + config.getString("secret-pin"));
                        break;
                    case "dogetest":
                        StrURL = ("https://block.io/api/v2/withdraw_from_labels/?api_key=" + config.getString("dogetest-api-key") + "&amounts=" + args[2] + "&from_labels=" + player.getUniqueId() + "&to_address=" + config.getString("donate-address") + "&pin=" + config.getString("secret-pin"));
                        break;
                    case "btctest":
                        StrURL = ("https://block.io/api/v2/withdraw_from_labels/?api_key=" + config.getString("btctest-api-key") + "&amounts=" + args[2] + "&from_labels=" + player.getUniqueId() + "&to_address=" + config.getString("donate-address") + "&pin=" + config.getString("secret-pin"));
                        break;
                    case "ltctest":
                        StrURL = ("https://block.io/api/v2/withdraw_from_labels/?api_key=" + config.getString("ltctest-api-key") + "&amounts=" + args[2] + "&from_labels=" + player.getUniqueId() + "&to_address=" + config.getString("donate-address") + "&pin=" + config.getString("secret-pin"));
                        break; 
                }
                URL url = new URL(StrURL);
                
                HttpURLConnection conn = null;
                try {
                    conn = (HttpURLConnection) url.openConnection();
                } catch (IOException ex) {
                    Logger.getLogger(CommandDonate.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    if(conn.getResponseCode() < 400){
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));
                        
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            
                            inputLine = inputLine.replaceAll("\\s","");
                            inputLine = inputLine.replaceAll(",","");
                            inputLine = inputLine.replaceAll("\"","");
                            inputLine = inputLine.replaceAll("\\}","");
                            inputLine = inputLine.replaceAll("\\{","");
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
                                inputLine = inputLine.trim();
                                player.sendMessage(inputLine);
                                count = count + 1;
                            }
                            else{
                                count = count + 1;
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(CommandDonate.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (MalformedURLException ex) {
                Logger.getLogger(CommandDonate.class.getName()).log(Level.SEVERE, null, ex);
            }  
        } 
    }
}



   

