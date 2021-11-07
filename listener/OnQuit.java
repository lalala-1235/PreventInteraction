package listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import packet.PacketReader;

public class OnQuit implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        PacketReader reader = new PacketReader();
        reader.uninject(e.getPlayer());
    }
}
