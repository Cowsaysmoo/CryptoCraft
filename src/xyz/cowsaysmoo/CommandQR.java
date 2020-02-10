package xyz.cowsaysmoo;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


public class CommandQR implements CommandExecutor{
    
    
    FileConfiguration config = CryptoCraft.plugin.getConfig();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(sender instanceof Player){
            
            Player player = (Player) sender;
            World world = (World) player.getWorld();   
            
            try {
                
                BitMatrix matrix = generateQRCode("Hello!!!!", 35,35);
                
                int SizeX = matrix.getHeight();
                int SizeY = matrix.getWidth();
                //int SizeX = 3;
                //int SizeY = 3;
                boolean clear = true;
                
                boolean[][] QRArray = bitMatToArray(matrix);
                boolean temp;
                for (int i = 0; i < QRArray.length; i++) {
                    for (int j = 0; j < QRArray[i].length / 2; j++) {
                        temp = QRArray[i][j];
                        QRArray[i][j] = QRArray[i][QRArray.length - 1 - j];
                        QRArray[i][QRArray.length - 1 -j] = temp;
                    }
                }
                boolean[][] rotQRArray = new boolean[SizeX][SizeY];
                for (int i = 0; i < SizeX; i++) {
                    for (int j = 0; j < SizeY; j++) {
                     rotQRArray[j][ (SizeX-1)- i] = QRArray[i][j]; 
                    }
                }
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
                            if(rotQRArray[i][j] == true){ //matrix.get(i, j)
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
                            sender.sendMessage("TEST!");
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
                }catch (WriterException | IOException e) {
                    player.sendMessage("QR ERROR");
            }
        }else {
            sender.sendMessage("You must be a player!");
            return false;
        }
        return true;
    }
    public static boolean[][] bitMatToArray(BitMatrix matrix){
        int SizeX = matrix.getHeight();
        int SizeY = matrix.getWidth();
        boolean array[][]  = new boolean[SizeX][SizeY];
        for(int i = 0; i < SizeX; i++){
            for(int j = 0; j < SizeY; j++){
                array[i][j] = matrix.get(i, j);
            }
        }
        return array;
    }
    public static BitMatrix generateQRCode(String text, int width, int height)
        throws WriterException, IOException {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height);
        return matrix;
    }    
}
