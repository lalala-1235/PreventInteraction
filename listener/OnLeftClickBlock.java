package listener;

import main.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnLeftClickBlock implements Listener {
    @EventHandler
    public void onLeftClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if(e.getAction()!= Action.LEFT_CLICK_BLOCK || p.getInventory().getItemInMainHand().getType()!= Material.WOODEN_HOE) return;

        e.setCancelled(true);
        if(e.getClickedBlock()!=null) {
            Location loc = e.getClickedBlock().getLocation();
            Main.selected.put(2, loc);
            p.sendMessage("location 2 set at: " + loc.getX() + ", " + loc.getY() + ", " + loc.getZ());
        }
    }
}
