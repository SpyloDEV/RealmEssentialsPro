package com.tristan.realmessentialspro.models;

import java.util.UUID;

public class TeleportRequest {
    private final UUID sender;
    private final UUID target;
    private final boolean here;
    private final long createdAt;

    public TeleportRequest(UUID sender, UUID target, boolean here) {
        this.sender = sender;
        this.target = target;
        this.here = here;
        this.createdAt = System.currentTimeMillis();
    }

    public UUID getSender() { return sender; }
    public UUID getTarget() { return target; }
    public boolean isHere() { return here; }
    public long getCreatedAt() { return createdAt; }
}
