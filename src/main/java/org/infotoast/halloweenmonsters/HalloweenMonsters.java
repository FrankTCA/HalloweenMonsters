package org.infotoast.halloweenmonsters;

import me.casperge.realisticseasons.api.SeasonsAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class HalloweenMonsters extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getLogger().info("HalloweenMonsters is enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent evt) {
        if (getConfig().getStringList("Prevent-Spawn").contains(evt.getEntity().getType().toString().toLowerCase())) {
            SeasonsAPI seasons = SeasonsAPI.getInstance();
            List<String> activeEvents = seasons.getActiveEvents(evt.getLocation().getWorld());
            if (activeEvents.contains("&6Halloween")) {
                if (getConfig().getStringList("Halloween-Exceptions").contains(evt.getEntity().getType().toString().toLowerCase())) {
                    return;
                }
            }
            evt.setCancelled(true);
        }
    }
}
