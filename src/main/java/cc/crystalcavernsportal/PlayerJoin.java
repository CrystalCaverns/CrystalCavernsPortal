package cc.crystalcavernsportal;

import me.rockyhawk.commandpanels.api.Panel;
import me.rockyhawk.commandpanels.openpanelsmanager.PanelPosition;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

import static cc.crystalcavernsportal.CrystalCavernsPortal.toSend;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Location spawn = new Location(Bukkit.getWorld("world"),0,1,0,0,0);
        File file = new File("/home/container/plugins/CommandPanels/panels/private_realms.yml");
        Panel panel = new Panel(file, "private_realms");
        p.teleport(spawn);
        toSend.add(p.getUniqueId());
        panel.open(p, PanelPosition.Top);
    }
}