package net.invasionpvp.harmlesscat.reputation;

public class ReputationClass {

    public String playerName = null;
    public String playerUUID = null;

    // %reputation_total%
    public int reputationPointsTotal = 0;

    // %reputation_lines_nr%
    public String [] historySetLines = null;

    // %reputation_faction_nr%
    public String[] historyFactions = null;
    // %reputation_pointsgained_nr%
    public Integer[] historyPointsGiven = null;
    // %reputation_ftopplace_nr%
    public Integer[] historyFTopPlace = null;


    public Integer[] getHistoryFTopPlace() {
        return historyFTopPlace;
    }

    public Integer getReputationPointsTotal() {
        return reputationPointsTotal;
    }

    public Integer[] getHistoryPointsGiven() {
        return historyPointsGiven;
    }

    public String[] getHistoryFactions() {
        return historyFactions;
    }

    public String[] getHistorySetLines() {
        return historySetLines;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerUUID() {
        return playerUUID;
    }
}
