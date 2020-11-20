package net.invasionpvp.harmlesscat.file;

import net.invasionpvp.harmlesscat.ReputationPointsMain;
import org.bukkit.configuration.file.YamlConfiguration;

import static net.invasionpvp.harmlesscat.reputation.Utils.saveFileCon;

public class ConfigRep {

    public ReputationPointsMain rep = ReputationPointsMain.getRepMain();

    public void tryLoadDefaults() {
        YamlConfiguration configYaml = rep.getConfigYaml();
        if (!configYaml.getBoolean("default")) {
            configYaml.set("default",true);
            configYaml.set("title","&7PLAYER REP TITLE");
            configYaml.set("desc1", "desc1");
            configYaml.set("desc2", "desc2");
            configYaml.set("desc3", "desc3");
            configYaml.set("desc4", "desc4");
            configYaml.set("nohistory", "&7PLAYER HAS NO HISTORY");
            configYaml.set("noPermissionMsg", "&7NO PERMISSION");
            configYaml.set("invalidUsage", "&7INVALIDUSAGE");
            saveFileCon();
        }

    }

    public String title;
    public String desc1;
    public String desc2;
    public String desc3;
    public String desc4;
    public String nohistory;
    public String noPermissionMsg;
    public String invalidUsage;

    public void initValue() {
        title = rep.getConfigYaml().getString("title");
        desc1 = rep.getConfigYaml().getString("desc1");
        desc2 = rep.getConfigYaml().getString("desc2");
        desc3 = rep.getConfigYaml().getString("desc3");
        desc4 = rep.getConfigYaml().getString("desc4");
        nohistory = rep.getConfigYaml().getString("nohistory");
        noPermissionMsg = rep.getConfigYaml().getString("noPermissionMsg");
        invalidUsage = rep.getConfigYaml().getString("invalidUsage");
    }

    public String getNoPermissionMsg() {
        return noPermissionMsg;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc2() {
        return desc2;
    }

    public String getNohistory() {
        return nohistory;
    }

    public String getInvalidUsage() {
        return invalidUsage;
    }

    public String getDesc1() {
        return desc1;
    }
}
