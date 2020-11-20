package net.invasionpvp.harmlesscat.commands;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import net.invasionpvp.harmlesscat.ReputationPointsMain;
import net.invasionpvp.harmlesscat.reputation.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.invasionpvp.harmlesscat.reputation.Utils.isInt;

public class AdminCommand implements CommandExecutor {

    //Command executable by Admin or Opped player or Console

    //          /arep give <player> <amt>, /arep give <faction> <amtperplayer> <mapnr> <ftopplace>

    public ReputationPointsMain rep = ReputationPointsMain.getRepMain();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (((Player) sender).isOp()) {
                executeCommand(sender, args);
            } else if (sender.hasPermission("rep.admin")) {
                executeCommand(sender, args);
            } else {
                Utils.sendNoPermission((Player)sender);
            }
        } else {
            executeCommand(sender, args);
        }
        return false;
    }
    public void executeCommand(CommandSender sender, String[] args) {
        if (args.length >= 1) {
            if (args[0].equals("give")) {
                if (args.length == 3) {
                    if (Bukkit.getPlayer(args[1]) != null) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (isInt(args[2])) {
                            rep.getJsonRep().addRepPoints(target,Integer.parseInt(args[2]));
                        } else {
                            Utils.sendError((Player)sender);
                        }
                    } else {
                        Utils.sendError((Player)sender);
                    }
                } else if (args.length == 5) {
                    Faction targetFaction = FactionColl.get().getByName(args[1]);
                    if (targetFaction != null && !targetFaction.getId().equals(Factions.ID_NONE)) {
                        if (Utils.isInt(args[2]) && Utils.isInt(args[3])&&Utils.isInt(args[4])) {
                            rep.getJsonRep().giveFactionPoints(targetFaction,Integer.parseInt(args[2]),Integer.parseInt(args[4]),Integer.parseInt(args[3]));
                        } else {
                            Utils.sendError((Player)sender);
                        }
                    } else {
                        Utils.sendError((Player)sender);
                    }
                } else {
                    Utils.sendError((Player)sender);
                }
            } else {
                Utils.sendError((Player)sender);
            }
        } else {
            Utils.sendError((Player)sender);
        }
    }
}
