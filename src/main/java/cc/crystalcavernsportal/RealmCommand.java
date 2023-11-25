package cc.crystalcavernsportal;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedDataManager;
import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Objects;

import static cc.crystalcavernsportal.CrystalCavernsPortal.*;

public class RealmCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        Player p = Bukkit.getPlayer(args[1]);
        String uuid = Objects.requireNonNull(p).getUniqueId().toString();
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        if (args[0].equals("load")) {
            YamlConfiguration worlds = YamlConfiguration.loadConfiguration(new File("/home/container/plugins/SlimeWorldManager/worlds.yml"));
            CachedDataManager cachedDataManager = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p).getCachedData();
            int borderSize = Integer.parseInt(Objects.requireNonNull(cachedDataManager.getMetaData().getMetaValue("private_realms_size")));
            if (worlds.contains("worlds." + uuid)) {
                //LOAD WORLD
                Bukkit.dispatchCommand(console, "aswm load " + uuid);
                p.sendMessage("§f\uDBE8\uDC08 §7§oLoaded your Private Realm. Teleporting...");
                Bukkit.getScheduler().runTaskLater(getPlugin(), () -> {
                    Bukkit.dispatchCommand(console, "aswm goto " + uuid + " " + p.getName());
                    p.sendMessage("§f\uDBDD\uDD29 §aTeleported successfully!");
                    p.sendTitle("\uDBEA\uDDE8", "", 0, 10, 10);
                }, 40L);
                Bukkit.getScheduler().runTaskLater(getPlugin(), () -> {
                    WorldBorder worldBorder = Objects.requireNonNull(Bukkit.getWorld(uuid)).getWorldBorder();
                    if (worldBorder.getSize() >= 1000) {
                        worldBorder.setCenter(0.5, 0.5);
                        worldBorder.setSize(borderSize);
                    }
                }, 60L);
            } else if (args.length == 3) {
                //CREATE NEW WORLD WITH GIVEN TEMPLATE
                Bukkit.dispatchCommand(console, "aswm load " + args[2]);
                p.sendMessage("§f\uDBE8\uDC08 §7§oPreparing your new Private Realm...");
                Bukkit.getScheduler().runTaskLater(getPlugin(), () -> {
                    Bukkit.dispatchCommand(console, "aswm clone-world " + args[2] + " " + uuid);
                    p.sendMessage("§f\uDBE8\uDC08 §7§oYour Private Realm has been created. Teleporting...");
                }, 40L);
                Bukkit.getScheduler().runTaskLater(getPlugin(), () -> {
                    Bukkit.dispatchCommand(console, "aswm goto " + uuid + " " + p.getName());
                    p.sendMessage("§f\uDBDD\uDD29 §aTeleported successfully!");
                    p.sendTitle("\uDBEA\uDDE8", "", 0, 10, 10);
                }, 80L);
                Bukkit.getScheduler().runTaskLater(getPlugin(), () -> {
                    WorldBorder worldBorder = Objects.requireNonNull(Bukkit.getWorld(uuid)).getWorldBorder();
                    if (worldBorder.getSize() >= 1000) {
                        worldBorder.setCenter(0.5, 0.5);
                        worldBorder.setSize(borderSize);
                    }
                }, 100L);
            }
            return false;
        }
        if (args[0].equals("delete")) {
            //DELETE WORLD
            Bukkit.dispatchCommand(console, "aswm unload " + uuid);
            p.sendMessage("§f\uDBE8\uDC08 §7§oUnloaded your Private Realm. Deleting...");
            Bukkit.getScheduler().runTaskLater(getPlugin(), () -> Bukkit.dispatchCommand(console, "aswm delete " + uuid), 40L);
            Bukkit.getScheduler().runTaskLater(getPlugin(), () -> {
                Bukkit.dispatchCommand(console, "aswm delete " + uuid);
                p.sendMessage("§f\uDBDD\uDD29 §aDeleted successfully!");
            }, 60L);
            return false;
        }
        return false;
    }
}
