package xyz.cowsaysmoo;

import java.io.*;
import java.util.Scanner;
import org.bukkit.entity.Player;
import static xyz.cowsaysmoo.CryptoCraft.plugin;

public class ConfigPlayerlist{

    ConfigPlayerlist(Player playerJoined) throws IOException{
        String data = (playerJoined.getName() + " " + playerJoined.getUniqueId() + "\n");
        System.out.println(data);
        int search = 0;
        Scanner scanner = new Scanner(plugin.getPlayersFile());
            while (scanner.hasNextLine()) {
            String lineFromFile = scanner.nextLine();
                if(lineFromFile.contains(playerJoined.getName() + " " + playerJoined.getUniqueId())) { 
                    System.out.println("UUID found for " + playerJoined.getName());
                    search = 1;
                    break;
                }
            }
        if (search != 1){
            BufferedWriter writer = new BufferedWriter(new FileWriter(plugin.getPlayersFile(), true));
            writer.write(data);
            System.out.println("Wrote UUID to file for " + playerJoined.getName());
            writer.close(); 
        }
    }
}
    

