package listener;

import event.RightClickNPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ClickNPC implements Listener {
    @EventHandler
    public void onClickNPC(RightClickNPC e) {
        Player p = e.getPlayer();
        p.sendMessage("Wa!");
    }
}
