package net.invasionpvp.harmlesscat;

import net.invasionpvp.harmlesscat.commands.AdminCommand;
import net.invasionpvp.harmlesscat.commands.PlayerCommand;
import net.invasionpvp.harmlesscat.file.ConfigRep;
import net.invasionpvp.harmlesscat.file.JsonRep;
import net.invasionpvp.harmlesscat.reputation.PlaceHolders;
import net.invasionpvp.harmlesscat.reputation.playerinit.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

import static net.invasionpvp.harmlesscat.reputation.Utils.col;

public final class ReputationPointsMain extends JavaPlugin {

    //Main instance
    private static ReputationPointsMain repMain;
    public static ReputationPointsMain getRepMain() { return repMain; }

    //Files
    public File dataFolder;
    public File getPluginDataFolder() { return dataFolder; }

    public File factionDataFolder;
    public File getFactionDataFolder() { return factionDataFolder; }

    public File configFile;
    public YamlConfiguration configYaml;
    public File getConfigFile() { return configFile; }
    public YamlConfiguration getConfigYaml() { return configYaml; }

    //Instance
    private JsonRep jsonRep;
    private PlaceHolders placeHolders;
    private ConfigRep configRep;
    private PlayerListener playerListener;

    public PlayerListener getPlayerListener() { return playerListener; }
    public ConfigRep getConfigRep() { return configRep; }
    public PlaceHolders getPlaceHolders() { return placeHolders; }
    public JsonRep getJsonRep() { return jsonRep; }

    @Override
    public void onEnable() {
        System.out.println(col("ReputationPoints ENABLED!"));
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        repMain = this;
        dataFolder = getDataFolder();
        initiateDataFolder();
        initiateClasses();
        initiateConfig();
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        getCommand("rep").setExecutor(new PlayerCommand());
        getCommand("arep").setExecutor(new AdminCommand());
        configRep.initValue();
        new PlaceHolders().register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void initiateClasses() {
        jsonRep = new JsonRep();
        placeHolders = new PlaceHolders();
        configRep = new ConfigRep();
        playerListener = new PlayerListener();
    }

    public void initiateConfig() {
        configFile = new File(getPluginDataFolder(),"config.yml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        configYaml = YamlConfiguration.loadConfiguration(configFile);
      configRep.tryLoadDefaults();
    }

    public void initiateDataFolder() {
        factionDataFolder = new File(getPluginDataFolder(),"playerdata");
        factionDataFolder.mkdir();
    }
}
