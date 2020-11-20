package net.invasionpvp.harmlesscat.reputation;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.invasionpvp.harmlesscat.ReputationPointsMain;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


import static net.invasionpvp.harmlesscat.reputation.Utils.col;

public class PlaceHolders extends PlaceholderExpansion {


    // %reputation_total%
    // %reputation_faction%
    // %reputation_pointsgained%
    // %reputation_ftopplace%

    // %reputation_line1-8%

    // %reputation_givenpoints1-8%

    // %reputation_faction1-8%

    // %reputation_ftop1-8%
    @Override
    public @NotNull String getIdentifier() {
        return "reputation";
    }

    @Override
    public @NotNull String getAuthor() {
        return "HarmlessCat#5791";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.2";
    }

    public String onPlaceholderRequest(Player player, @NotNull String identifier){

        if(player == null){
            return "";
        }

        switch (identifier) {
            case "title":
                return col(ReputationPointsMain.getRepMain().getConfigRep().getTitle());
            case "null":
                return col("  ");
            case "desc1":
                return col(ReputationPointsMain.getRepMain().getConfigRep().getDesc1());
            case "desc2":
                return col(ReputationPointsMain.getRepMain().getConfigRep().getDesc2());
            case "total":
                if (col(ReputationPointsMain.getRepMain().getJsonRep().classFromPlayer(player).getReputationPointsTotal().toString()) != null) {
                    return col(ReputationPointsMain.getRepMain().getJsonRep().classFromPlayer(player).getReputationPointsTotal().toString());
                } else {
                    return "null";
                }
            case "line1":
                return col(ReputationPointsMain.getRepMain().getJsonRep().classFromPlayer(player).getHistorySetLines()[0]);
            case "line2":
                return col(ReputationPointsMain.getRepMain().getJsonRep().classFromPlayer(player).getHistorySetLines()[1]);
            case "line3":
                return col(ReputationPointsMain.getRepMain().getJsonRep().classFromPlayer(player).getHistorySetLines()[2]);
            case "line4":
                return col(ReputationPointsMain.getRepMain().getJsonRep().classFromPlayer(player).getHistorySetLines()[3]);
            case "line5":
                return col(ReputationPointsMain.getRepMain().getJsonRep().classFromPlayer(player).getHistorySetLines()[4]);
            case "line6":
                return col(ReputationPointsMain.getRepMain().getJsonRep().classFromPlayer(player).getHistorySetLines()[5]);
            case "line7":
                return col(ReputationPointsMain.getRepMain().getJsonRep().classFromPlayer(player).getHistorySetLines()[6]);
            case "line8":
                return col(ReputationPointsMain.getRepMain().getJsonRep().classFromPlayer(player).getHistorySetLines()[7]);
            default:
                return "";
        }


    }
}
