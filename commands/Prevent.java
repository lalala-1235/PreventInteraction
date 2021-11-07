package commands;

import main.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class Prevent implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(Main.selected.containsKey(1) && Main.selected.containsKey(2)) {
            Location[] array = {Main.selected.get(1), Main.selected.get(2)};
            Main.preventLocation.add(array);

            return true;
        }

        return false;
    }
}
