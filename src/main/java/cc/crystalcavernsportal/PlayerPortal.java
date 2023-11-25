package cc.crystalcavernsportal;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class PlayerPortal implements Listener {
    @EventHandler
    public void onPlayerUsePortal(PlayerPortalEvent e) {
        e.getPlayer().sendMessage("§f\uDBF7\uDC35 §cWhoa, sorry friend, but you can't get to the Nether from your Private Realm! Though, you can get there from the Exploration Realm!");
    }
}
