package cc.crystalcavernsportal;

import de.themoep.minedown.MineDown;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class CrystalCavernsPortal extends JavaPlugin implements Listener {
    public List<UUID> toSend = new ArrayList<>();
    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("profile")).setExecutor(new cc.crystalcavernsportal.ProfileCommand());
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getLogger().info("Crystal Caverns Portal plugin loaded successfully!");
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (toSend.contains(player.getUniqueId())) {
                    String message = "&#bbbbbb&%vault_eco_balance% &white&\uDBEB\uDEEA      &#9944ff&%gamepoints_balance% &white&\uDBCB\uDD10";
                    message = PlaceholderAPI.setPlaceholders(player, message);
                    TextComponent formatted_message = new TextComponent(MineDown.parse(message));
                    formatted_message.setFont("currency_display");
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, formatted_message);
                }
            }
        }, 30L, 30L);
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        toSend.add(player.getUniqueId());
    }
}

