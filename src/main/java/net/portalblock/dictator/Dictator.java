package net.portalblock.dictator;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
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
        prefix = ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix"));
    }

    @EventHandler
    public void onTabCom(PlayerChatTabCompleteEvent e){
        if(!e.getPlayer().isOp()){
            for(String str : e.getTabCompletions()){
                e.getTabCompletions().remove(str);
            }
            e.getTabCompletions().add("This feature is disabled!");
        }
    }

    @EventHandler
    public void onCommandPreProccess(PlayerCommandPreprocessEvent e){
        String command = e.getMessage().replaceFirst("/", "").split(" ")[0];
         if(command.equalsIgnoreCase("reload")){
            List<String> pluginMsg = getConfig().getStringList("reload");
            if(pluginMsg.size() > 0){
                for(String msg : pluginMsg){
                    e.getPlayer().sendMessage(prefix+ChatColor.translateAlternateColorCodes('&', msg));
                }
            }
            e.setCancelled(true);
        }
        if(!e.getPlayer().isOp()&&!e.getPlayer().hasPermission("dictator.bypass")){
            if(command.equalsIgnoreCase("pl")||command.equalsIgnoreCase("plugins")||command.equalsIgnoreCase("plugin")){
                List<String> pluginMsg = getConfig().getStringList("plugins");
                if(pluginMsg.size() > 0){
                    for(String msg : pluginMsg){
                        e.getPlayer().sendMessage(prefix+ChatColor.translateAlternateColorCodes('&', msg));
                    }
                }
                e.setCancelled(true);
            }else if(command.equalsIgnoreCase("version")||command.equalsIgnoreCase("ver")){
                List<String> pluginMsg = getConfig().getStringList("version");
                if(pluginMsg.size() > 0){
                    for(String msg : pluginMsg){
                        e.getPlayer().sendMessage(prefix+ChatColor.translateAlternateColorCodes('&', msg));
                    }
                }
                e.setCancelled(true);
            }else if(command.equalsIgnoreCase("op")){
                List<String> pluginMsg = getConfig().getStringList("op");
                if(pluginMsg.size() > 0){
                    for(String msg : pluginMsg){
                        e.getPlayer().sendMessage(prefix+ChatColor.translateAlternateColorCodes('&', msg));
                    }
                }
                e.setCancelled(true);
            }else if(command.equalsIgnoreCase("deop")){
                List<String> pluginMsg = getConfig().getStringList("deop");
                if(pluginMsg.size() > 0){
                    for(String msg : pluginMsg){
                        e.getPlayer().sendMessage(prefix+ChatColor.translateAlternateColorCodes('&', msg));
                    }
                }
                e.setCancelled(true);
            }
        }
    }
}
