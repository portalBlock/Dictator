package net.portalblock.dictator;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/**
 * Created by portalBlock on 2/18/14.
 */
public class Dictator extends JavaPlugin implements Listener{

    String prefix;
    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(this, this);
        saveResource("config.yml", false);
        prefix = ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix"));
    }

    @EventHandler
    public void onCommandPreProccess(PlayerCommandPreprocessEvent e){
        String command = e.getMessage().replaceFirst("/", "").split(" ")[0];
        if(command.equalsIgnoreCase("reload")&&getConfig().getBoolean("blockReload")){
            List<String> pluginMsg = getConfig().getStringList("reload");
            if(pluginMsg.size() > 0){
                for(String msg : pluginMsg){
                    e.getPlayer().sendMessage(prefix+ChatColor.translateAlternateColorCodes('&', msg));
                }
            }
            e.setCancelled(true);
            return;
        }
        List<String> messages = getConfig().getStringList(command);
        if(e.getPlayer().isOp()||e.getPlayer().hasPermission("dictator.bypass."+command)||e.getPlayer().hasPermission("dictator.bypass")){
            return;
        }
        if(messages != null){
            if(messages.size() > 0){
                e.setCancelled(true);
                for(String str : messages){
                    e.getPlayer().sendMessage(prefix+ChatColor.translateAlternateColorCodes('&', str));
                }
            }
        }
    }
}
