package main;

import commands.Prevent;
import commands.Create;
import commands.Test;
import listener.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import packet.PacketReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class Main extends JavaPlugin {
    public static HashMap<Integer, Location> selected = new HashMap<>();
    public static ArrayList<Location[]> preventLocation = new ArrayList<>();
    public static Location storedLocation;
    public static HashMap<UUID, PermissionAttachment> permission = new HashMap<>();

    @Override
    public void onEnable() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new OnLeftClickBlock(), this);
        pm.registerEvents(new OnRightClickBlock(), this);
        pm.registerEvents(new OnPlayerMove(), this);
        pm.registerEvents(new OnJoin(), this);
        pm.registerEvents(new OnQuit(), this);
        pm.registerEvents(new ClickNPC(), this);

        Objects.requireNonNull(getCommand("prevent")).setExecutor(new Prevent());
        Objects.requireNonNull(getCommand("create")).setExecutor(new Create());
        Objects.requireNonNull(getCommand("test")).setExecutor(new Test());

        if(!Bukkit.getOnlinePlayers().isEmpty())
            for (Player player : Bukkit.getOnlinePlayers()) {
                PacketReader reader = new PacketReader();
                reader.inject(player);
            }

        System.out.println("PreventInteraction enabled.");
    }

    @Override
    public void onDisable() {
        if(!Bukkit.getOnlinePlayers().isEmpty())
            for (Player player : Bukkit.getOnlinePlayers()) {
                PacketReader reader = new PacketReader();
                reader.uninject(player);
            }
        System.out.println("PreventInteraction disabled.");
    }
}
