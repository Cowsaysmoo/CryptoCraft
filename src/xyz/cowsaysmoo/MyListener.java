package xyz.cowsaysmoo;

import java.io.IOException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

class MyListener implements Listener {
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException{
        Player playerJoined = event.getPlayer();
        WalletInfo info = new WalletInfo();
        
        info.createWalletOnJoin(playerJoined, "doge");
        info.createWalletOnJoin(playerJoined, "btc");
        info.createWalletOnJoin(playerJoined, "ltc");
    }
    
    public void onPlayerLogin(PlayerLoginEvent event) throws IOException{
        
        Player playerJoined = event.getPlayer();
        ConfigPlayerlist list = new ConfigPlayerlist();
        list.ConfigPlayerlist(playerJoined);
    }
}
    

