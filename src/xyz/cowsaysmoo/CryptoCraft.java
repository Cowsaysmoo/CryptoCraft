package xyz.cowsaysmoo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class CryptoCraft extends JavaPlugin{
    
    public static CryptoCraft plugin;
    private File playersFile;
    private FileConfiguration players;
    FileConfiguration config = getConfig();  
    
    @Override
    public void onEnable(){
        plugin = this;
        try {
            createPlayers();
        } catch (FileNotFoundException | InvalidConfigurationException ex) {
            
        }
        getServer().getPluginManager().registerEvents(new MyListener(), this);
        config.addDefault("doge-api-key", "xxxx-xxxx-xxxx-xxxx");
        config.addDefault("btc-api-key", "xxxx-xxxx-xxxx-xxxx");
        config.addDefault("ltc-api-key", "xxxx-xxxx-xxxx-xxxx");
        config.addDefault("dogetest-api-key", "xxxx-xxxx-xxxx-xxxx");
        config.addDefault("btctest-api-key", "xxxx-xxxx-xxxx-xxxx");
        config.addDefault("ltctest-api-key", "xxxx-xxxx-xxxx-xxxx");
        config.addDefault("secret-pin", "xxxxxxxx");
        config.addDefault("donate-address", "DK2ae1tjmCMud78TTiGMwkkHqFQdCzhMV7");
        config.options().copyDefaults(true);
        saveConfig();
        
        this.getCommand("tip").setExecutor(new CommandTip());
        this.getCommand("withdraw").setExecutor(new CommandWithdraw());
        this.getCommand("donate").setExecutor(new CommandDonate());
        this.getCommand("balance").setExecutor(new CommandBalance());
        this.getCommand("deposit").setExecutor(new CommandDeposit());
        this.getCommand("wallet").setExecutor(new CommandWallet());
        this.getCommand("shift").setExecutor(new CommandShift());
        this.getCommand("QR").setExecutor(new CommandQR());
    }
    
    @Override
    public void onDisable() {
    }
    
    public FileConfiguration getPlayers() {
        return this.players;
    }
    public File getPlayersFile() {
        return this.playersFile;
    }
    
    private void createPlayers() throws FileNotFoundException, InvalidConfigurationException {
        playersFile = new File(getDataFolder(), "players.yml");

        if (!playersFile.exists()) {  
            playersFile.getParentFile().mkdirs();
            saveResource("players.yml", false); 
        }
        players = new YamlConfiguration();
        try {
            players.load(playersFile);
        } catch (IOException e) {
        }
    }
}
