package cc.crystalcavernsportal;

import me.rockyhawk.commandpanels.api.Panel;
import me.rockyhawk.commandpanels.classresources.placeholders.PanelPlaceholders;
import me.rockyhawk.commandpanels.openpanelsmanager.PanelPosition;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class PlayerDeath implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        World world = p.getWorld();
        Location spawn = new Location(Bukkit.getWorld(p.getUniqueId()),0.5,3,0.5,0,0);
        p.teleport(spawn);
        p.sendTitle("\uDBEA\uDDE8", "", 0, 10, 10);
        if (world.getName().equals("world")) {
            File file = new File("/home/container/plugins/CommandPanels/panels/private_realms.yml");
            Panel panel = new Panel(file, "private_realms");
            PanelPlaceholders placeholders = panel.placeholders;
            YamlConfiguration worlds = YamlConfiguration.loadConfiguration(new File("/home/container/plugins/SlimeWorldManager/worlds.yml"));
            placeholders.addPlaceholder("has-realm", String.valueOf(worlds.contains("worlds." + p.getUniqueId())));
            panel.open(p, PanelPosition.Top);
        }
    }
}