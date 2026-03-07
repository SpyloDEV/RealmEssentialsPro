package com.tristan.realmessentialspro.managers;

import com.tristan.realmessentialspro.RealmEssentialsPro;
import com.tristan.realmessentialspro.models.TeleportRequest;

import java.util.*;

public class RequestManager {
    private final RealmEssentialsPro plugin;
    private final Map<UUID, List<TeleportRequest>> requests = new HashMap<>();

    public RequestManager(RealmEssentialsPro plugin) {
        this.plugin = plugin;
    }

    public void addRequest(TeleportRequest request) {
        requests.computeIfAbsent(request.getTarget(), k -> new ArrayList<>()).removeIf(r -> r.getSender().equals(request.getSender()));
        requests.computeIfAbsent(request.getTarget(), k -> new ArrayList<>()).add(request);
    }

    public TeleportRequest getRequest(UUID target, UUID sender) {
        cleanup(target);
        return requests.getOrDefault(target, List.of())
                .stream()
                .filter(r -> r.getSender().equals(sender))
                .findFirst()
                .orElse(null);
    }

    public void removeRequest(UUID target, UUID sender) {
        requests.computeIfPresent(target, (k, list) -> {
            list.removeIf(r -> r.getSender().equals(sender));
            return list;
        });
    }

    public void cleanup(UUID target) {
        int expire = plugin.getConfig().getInt("settings.tpa-expire-seconds", 60);
        long now = System.currentTimeMillis();
        requests.computeIfPresent(target, (k, list) -> {
            list.removeIf(r -> now - r.getCreatedAt() > expire * 1000L);
            return list;
        });
    }
}
