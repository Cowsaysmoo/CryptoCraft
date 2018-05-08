package xyz.cowsaysmoo;

import java.io.IOException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;


/**
 *
 * @author Jared
 */

class MyListener implements Listener {
    
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) throws IOException{
        
        Player playerJoined = event.getPlayer();
        ConfigPlayerlist list = new ConfigPlayerlist(playerJoined);
    }
}
    

