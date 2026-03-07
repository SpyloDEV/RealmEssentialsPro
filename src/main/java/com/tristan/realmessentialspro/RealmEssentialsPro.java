package com.tristan.realmessentialspro;

import com.tristan.realmessentialspro.commands.*;
import com.tristan.realmessentialspro.config.MessageManager;
import com.tristan.realmessentialspro.data.DataStore;
import com.tristan.realmessentialspro.gui.GuiListener;
import com.tristan.realmessentialspro.listeners.PlayerConnectionListener;
import com.tristan.realmessentialspro.listeners.TeleportWarmupListener;
import com.tristan.realmessentialspro.managers.*;
import org.bukkit.plugin.java.JavaPlugin;

public class RealmEssentialsPro extends JavaPlugin {

    private static RealmEssentialsPro instance;

    private MessageManager messageManager;
    private DataStore dataStore;
    private TeleportManager teleportManager;
    private SpawnManager spawnManager;
    private HomeManager homeManager;
    private WarpManager warpManager;
    private RequestManager requestManager;
    private GuiManager guiManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        saveResource("messages.yml", false);

        this.messageManager = new MessageManager(this);
        this.dataStore = new DataStore(this);
        this.teleportManager = new TeleportManager(this);
        this.spawnManager = new SpawnManager(this, dataStore);
        this.homeManager = new HomeManager(this, dataStore);
        this.warpManager = new WarpManager(this, dataStore);
        this.requestManager = new RequestManager(this);
        this.guiManager = new GuiManager(this, homeManager, warpManager);

        registerCommands();
        registerListeners();

        getLogger().info("RealmEssentialsPro enabled.");
    }

    @Override
    public void onDisable() {
        dataStore.saveAll();
    }

    public void reloadPlugin() {
        reloadConfig();
        messageManager.reload();
        dataStore.reloadAll();
    }

    private void registerCommands() {
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        getCommand("sethome").setExecutor(new SetHomeCommand(this));
        getCommand("home").setExecutor(new HomeCommand(this));
        getCommand("delhome").setExecutor(new DelHomeCommand(this));
        getCommand("homes").setExecutor(new HomesCommand(this));
        getCommand("tpa").setExecutor(new TpaCommand(this, false));
        getCommand("tpahere").setExecutor(new TpaCommand(this, true));
        getCommand("tpaccept").setExecutor(new TpaResponseCommand(this, true));
        getCommand("tpdeny").setExecutor(new TpaResponseCommand(this, false));
        getCommand("setwarp").setExecutor(new SetWarpCommand(this));
        getCommand("warp").setExecutor(new WarpCommand(this));
        getCommand("delwarp").setExecutor(new DelWarpCommand(this));
        getCommand("warps").setExecutor(new WarpsCommand(this));
        getCommand("heal").setExecutor(new HealCommand(this));
        getCommand("feed").setExecutor(new FeedCommand(this));
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("repair").setExecutor(new RepairCommand(this));
        getCommand("rep").setExecutor(new RepCommand(this));
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerConnectionListener(this), this);
        getServer().getPluginManager().registerEvents(new GuiListener(this), this);
        getServer().getPluginManager().registerEvents(new TeleportWarmupListener(this), this);
    }

    public static RealmEssentialsPro getInstance() {
        return instance;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }

    public DataStore getDataStore() {
        return dataStore;
    }

    public TeleportManager getTeleportManager() {
        return teleportManager;
    }

    public SpawnManager getSpawnManager() {
        return spawnManager;
    }

    public HomeManager getHomeManager() {
        return homeManager;
    }

    public WarpManager getWarpManager() {
        return warpManager;
    }

    public RequestManager getRequestManager() {
        return requestManager;
    }

    public GuiManager getGuiManager() {
        return guiManager;
    }
}
