package net.invasionpvp.harmlesscat.reputation.playerinit;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.invasionpvp.harmlesscat.ReputationPointsMain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.UUID;

public class PlayerListener implements Listener {

    public ReputationPointsMain rep = ReputationPointsMain.getRepMain();

    public HashMap<UUID, File> playerJsonFile = new HashMap<>();
    public HashMap<UUID, File> getPlayerJsonFile() { return playerJsonFile; }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        rep.getPlayerListener().getPlayerJsonFile().put(event.getPlayer().getUniqueId(), rep.getJsonRep().getPlayerJson(event.getPlayer()));
        if (!rep.getPlayerListener().getPlayerJsonFile().get(event.getPlayer().getUniqueId()).exists()) {
            try {
                rep.getPlayerListener().getPlayerJsonFile().get(event.getPlayer().getUniqueId()).createNewFile();
                rep.getJsonRep().writeDefaults(event.getPlayer(),rep.getPlayerListener().getPlayerJsonFile().get(event.getPlayer().getUniqueId()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        rep.getPlayerListener().getPlayerJsonFile().remove((event.getPlayer().getUniqueId()));
    }
}
