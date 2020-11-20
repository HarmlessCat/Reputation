package net.invasionpvp.harmlesscat.file;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.xlib.gson.Gson;
import net.invasionpvp.harmlesscat.ReputationPointsMain;
import net.invasionpvp.harmlesscat.reputation.ReputationClass;
import net.invasionpvp.harmlesscat.reputation.Utils;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;


public class JsonRep {

    public Gson gson = Factions.get().getGson();

    public ReputationPointsMain rep = ReputationPointsMain.getRepMain();
    public File dataFolder = rep.getFactionDataFolder();

    public File getPlayerJson(Player player) {
        return new File(dataFolder,player.getUniqueId().toString()+".json");
    }

    public void writeDefaults(Player player, File playerJson) {
        try {
            PrintWriter pw = new PrintWriter(playerJson, "UTF-8");
            ReputationClass reputationClass = new ReputationClass();
            reputationClass.playerUUID = player.getUniqueId().toString();
            reputationClass.playerName = player.getName();
            reputationClass.reputationPointsTotal = 0;
            reputationClass.historySetLines = new String[]{};
            reputationClass.historyFactions = new String[]{};
            reputationClass.historyPointsGiven = new Integer[]{};
            reputationClass.historyFTopPlace = new Integer[]{};
            String jsonString = gson.toJson(reputationClass);
            pw.print(jsonString);
            pw.flush();
            pw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public ReputationClass classFromPlayer(Player player) {
        try {
            String test = new String(Files.readAllBytes(getPlayerJson(player).toPath()));
            return gson.fromJson(test, ReputationClass.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void addRepPoints(Player player, int amt) {
        ReputationClass reputationClass = classFromPlayer(player);
        if (getPlayerJson(player).exists()) {
            try {
                PrintWriter pw = new PrintWriter(getPlayerJson(player), "UTF-8");
                int prevAmt = reputationClass.getReputationPointsTotal();
                reputationClass.reputationPointsTotal = prevAmt+amt;
                String jsonString = gson.toJson(reputationClass);
                if (jsonString != null) {
                    pw.print(jsonString);
                    pw.flush();
                    pw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void addRepHistory(Faction faction, Player player, int amt, int place, int map)  {
        if (getPlayerJson(player).exists()) {
            ReputationClass playerRepInfo = classFromPlayer(player);
            String lore = ("&d&lRank &a#"+place+". &l"+faction.getName()+"&r&f on Map #"+map+" &8&l(&d&l"+amt+"&8&l)");
            playerRepInfo.reputationPointsTotal = playerRepInfo.getReputationPointsTotal()+amt;
            playerRepInfo.historySetLines = Utils.increaseArrayString(playerRepInfo.getHistorySetLines(),lore);
            playerRepInfo.historyPointsGiven = Utils.increaseArrayInteger(playerRepInfo.getHistoryPointsGiven(), amt);
            playerRepInfo.historyFTopPlace = Utils.increaseArrayInteger(playerRepInfo.getHistoryFTopPlace(),place);
            playerRepInfo.historyFactions = Utils.increaseArrayString(playerRepInfo.getHistoryFactions(),faction.getName());
            try {
                PrintWriter pw = new PrintWriter(getPlayerJson(player), "UTF-8");
                String jsonString = gson.toJson(playerRepInfo);
                pw.print(jsonString);
                pw.flush();
                pw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void giveFactionPoints(Faction targetFaction, int amt, int place, int map) {
        for (MPlayer mPlayer : targetFaction.getMPlayers()) {
            addRepHistory(targetFaction,mPlayer.getPlayer(), amt, place, map);
        }
    }

    public Integer getTotalPoints(Player player) {
        if (getPlayerJson(player).exists()) {
            ReputationClass reputationClass = classFromPlayer(player);
            return reputationClass.reputationPointsTotal;
        }
        return null;
    }
}
