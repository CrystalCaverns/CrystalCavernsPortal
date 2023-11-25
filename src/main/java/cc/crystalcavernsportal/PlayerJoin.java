package cc.crystalcavernsportal;

import me.rockyhawk.commandpanels.api.Panel;
import me.rockyhawk.commandpanels.openpanelsmanager.PanelPosition;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Player p = e.getPlayer();
        YamlConfiguration worlds = YamlConfiguration.loadConfiguration(new File("/home/container/plugins/SlimeWorldManager/worlds.yml"));
        Location spawn = new Location(Bukkit.getWorld("world"),0.5,-63,0.5,0,0);
        p.sendTitle("\uDBEA\uDDE8", "", 0, 10, 10);
        if (worlds.contains("worlds." + p.getUniqueId())) {
            File file = new File("/home/container/plugins/CommandPanels/panels/manage_realm.yml");
            Panel panel = new Panel(file, "manage_realm");
            panel.open(p, PanelPosition.Top);
        } else {
            File file = new File("/home/container/plugins/CommandPanels/panels/create_realm.yml");
            Panel panel = new Panel(file, "create_realm");
            panel.open(p, PanelPosition.Top);
        }
        p.teleport(spawn);
    }
}