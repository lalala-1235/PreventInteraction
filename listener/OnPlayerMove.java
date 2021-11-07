package listener;

import main.Main;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
//import org.bukkit.util.Vector;
import utils.Utils;

public class OnPlayerMove implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if(Main.preventLocation.isEmpty()) return;
        if(Main.storedLocation==null) {
            Main.storedLocation = e.getPlayer().getLocation();
            return;
        }

        Location pLoc = e.getPlayer().getLocation();

        Main.preventLocation.forEach(loc -> {
            Location loc1 = loc[0];
            Location loc2 = loc[1];

            if(Utils.checkBetween((int)loc1.getX(), (int)loc2.getX(), (int)pLoc.getX())
            && Utils.checkBetween((int)loc1.getY(), (int)loc2.getY(), (int)pLoc.getY())
            && Utils.checkBetween((int)loc1.getZ(), (int)loc2.getZ(), (int)pLoc.getZ())) {
//                e.getPlayer().sendMessage("당신은 이곳에 들어올 수 없습니다!");
//                Vector eventVector = Main.storedLocation.subtract(pLoc).toVector();
//                eventVector.normalize();
//
//                pLoc.add(eventVector);
//
//                e.getPlayer().teleport(pLoc);

                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1, 10));
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1, 10));
            }
        });

        Main.storedLocation = pLoc;
    }
}