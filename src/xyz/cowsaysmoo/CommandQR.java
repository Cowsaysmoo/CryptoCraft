package xyz.cowsaysmoo;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class CommandQR implements CommandExecutor{
     
    FileConfiguration config = CryptoCraft.plugin.getConfig();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(sender instanceof Player){
            
            Player player = (Player) sender;
            World world = (World) player.getWorld();   
            
            int SizeX = 3;
            int SizeY = 3;
            boolean clear = true;
            //boolean[][] QRArray = new int[SizeX][SizeY];
            boolean[][] QRArray = {{true,false,true},{false,true,true},{false,true,false}};
            boolean[][] clearArray = new boolean[SizeX][SizeY];
            int BlockX;
            int BlockY;
            int PlayerX = player.getLocation().getBlockX();    
            int PlayerY = player.getLocation().getBlockY();    //Up & Down
            int PlayerZ = player.getLocation().getBlockZ();
            int BlockZ = PlayerZ + 3;
            //Block block = world.getBlockAt(BlockX,BlockY,BlockZ);

            for(int i = 0; i < SizeX; i++){
                for(int j = 0; j < SizeY; j++){
                    BlockX = PlayerX + j;    
                    BlockY = PlayerY + i;    //Up & Down
                    Block block = world.getBlockAt(BlockX,BlockY,BlockZ);
                    if(block.isEmpty()){
                        if(QRArray[i][j] == true){
                            block.setType(Material.BEDROCK);
                            //block.setType(Material.WOOL);
                            //BlockState state = block.getState();
                            //Wool woolData = (Wool)state.getData();
                            //woolData.setColor(DyeColor.BLACK);
                        }
                        else{
                            block.setType(Material.SNOW_BLOCK);
                           // BlockState state = block.getState();
                            //Wool woolData = (Wool)state.getData();
                            //woolData.setColor(DyeColor.WHITE);
                        }
                        
                    }
                    else{
                        clearArray[i][j] = true;
                        clear = false;
                    }
                }
            }
            if(clear == false){
                for(int i = 0; i < SizeX; i++){
                    for(int j = 0; j < SizeY; j++){
                        BlockX = PlayerX + j;    
                        BlockY = PlayerY + i;    //Up & Down
                        Block block = world.getBlockAt(BlockX,BlockY,BlockZ);
                        if(clearArray[i][j] != true){
                            block.setType(Material.AIR);
                        }
                    }
                }
                player.sendMessage("Could not place QR, there are blocks in the way!");
            }          
        }
        return true;
    }
}