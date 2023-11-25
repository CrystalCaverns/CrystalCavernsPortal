package cc.crystalcavernsportal;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CrystalCavernsPortal extends JavaPlugin {
    @Override
    public void onEnable() {
        plugin = this;
        Objects.requireNonNull(getCommand("realm")).setExecutor(new RealmCommand());
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new SpawnCommand());
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new PlayerPortal(), this);
        getLogger().info("Crystal Caverns Portal plugin loaded successfully!");
    }
    public static Plugin getPlugin() {
        return plugin;
    }
    public static JavaPlugin plugin;
}

