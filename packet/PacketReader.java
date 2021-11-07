package packet;

import event.RightClickNPC;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import main.Main;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.EntityPlayer;
import npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PacketReader {
    Channel channel;
    public static Map<UUID, Channel> channels = new HashMap<>();

    public void inject(Player player) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        channel = craftPlayer.getHandle().b.a.k;
        channels.put(player.getUniqueId(), channel);

        if(channel.pipeline().get("PacketInjector") != null) return;

        //리스너 추가
        channel.pipeline().addAfter("decoder", "PacketInjector", new MessageToMessageDecoder<Packet<?>>() {
            @Override
            protected void decode(ChannelHandlerContext channelHandlerContext, Packet<?> packet, List<Object> list) {
                list.add(packet);
                readPacket(player, packet);
            }
        });
    }

    public void uninject(Player player) {
        try {
            channel = channels.get(player.getUniqueId());
        } catch(NullPointerException e) {
            System.out.println("[WARN] Player's channel was not registered, so the channel is not updated. Add NPCs.");
        }

        if(channel.pipeline().get("PacketInjector") != null) {
            channel.pipeline().remove("PacketInjector");
        }
    }

    public void readPacket(Player player, Packet<?> packet) {
//        System.out.println("PACKET >> " + packet);

        if(packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInUseEntity")) {
            int id = (int) getValue(packet, "a"); //얘는 우리가 뭘 클릭하는지 id를 알려줄것
                for (EntityPlayer npc : NPC.getNPCs()) {
                    if(npc.getId() == id) { //id가 npc id와 같으면
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), () -> Bukkit.getPluginManager().callEvent(new RightClickNPC(player, npc)));
                    }
                }

        } //PacketPlayInUseEntity: 엔티티에 대고 클릭했을 때 패킷(무슨 엔티티건간에 상관x)
    }

    //instance에서 name이라는 이름의 필드 값을 가져옴
    private Object getValue(Object instance, String name) {
        Object result = null;

        try {
            Field field = instance.getClass().getDeclaredField(name);
            field.setAccessible(true);

            result = field.get(instance);

            field.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
