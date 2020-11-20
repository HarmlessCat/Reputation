package net.invasionpvp.harmlesscat.reputation;

import net.invasionpvp.harmlesscat.ReputationPointsMain;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

    public static String col(String message) {
        return ChatColor.translateAlternateColorCodes('&',message);
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public static void saveFileCon() {
        try {
            ReputationPointsMain.getRepMain().getConfigYaml().save(ReputationPointsMain.getRepMain().getConfigFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] increaseArrayString(String[] array, String addedValue) {
        List<String> targetList =
                new ArrayList<>(Arrays.asList(array));
        targetList.add(addedValue);
        return targetList.toArray(new String[0]);
    }

    public static Integer[] increaseArrayInteger(Integer[] array, Integer addedValue) {

        List<Integer> targetList =
                new ArrayList<>(Arrays.asList(array));
        targetList.add(addedValue);
        return targetList.toArray(new Integer[0]);
    }

    public static void sendError(Player player) {
        player.sendMessage(col(ReputationPointsMain.getRepMain().getConfigRep().getInvalidUsage()));
    }

    public static void sendNoPermission(Player player) {
        player.sendMessage(col(ReputationPointsMain.getRepMain().getConfigRep().getNoPermissionMsg()));
    }

    public static void sendBase(Player player) {
        player.sendMessage(col(ReputationPointsMain.getRepMain().getConfigRep().getTitle()));
        player.sendMessage("");
        player.sendMessage(col(ReputationPointsMain.getRepMain().getConfigRep().getDesc1()));
        player.sendMessage(col(ReputationPointsMain.getRepMain().getConfigRep().getDesc2()));
    }
}
