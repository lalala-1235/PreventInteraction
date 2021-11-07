package listener;

import npc.NPC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import packet.PacketReader;

public class OnJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(NPC.getNPCs() == null || NPC.getNPCs().isEmpty()) return;

        NPC.addJoinPacket(e.getPlayer());

        PacketReader reader = new PacketReader();
        reader.inject(e.getPlayer());
    }

}
