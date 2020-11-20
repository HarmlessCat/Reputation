package net.invasionpvp.harmlesscat.commands;

import net.invasionpvp.harmlesscat.ReputationPointsMain;
import net.invasionpvp.harmlesscat.reputation.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.invasionpvp.harmlesscat.reputation.Utils.col;

public class PlayerCommand implements CommandExecutor {

    public ReputationPointsMain rep = ReputationPointsMain.getRepMain();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player pSender = (Player)sender;
            if (args.length == 1) {
                if  (Bukkit.getPlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);
                    Utils.sendBase(pSender);
                    for (String string : rep.getJsonRep().classFromPlayer(target).historySetLines) {
                        pSender.sendMessage(col(string));
                    }
                }
            } else {
                Utils.sendError((Player)sender);
            }
         }
        return false;
    }
    //Executable by normal player
}
