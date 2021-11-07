package commands;

import npc.NPC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Create implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if((!(commandSender instanceof Player))) return false;

        Player p = (Player) commandSender;
        if(strings.length == 0) {
            NPC.createNPC(p, p.getName());
            p.sendMessage("NPC CREATED");
            return true;
        }

        NPC.createNPC(p, strings[0]);
        p.sendMessage("NPC CREATED");
        return true;
    }
}
