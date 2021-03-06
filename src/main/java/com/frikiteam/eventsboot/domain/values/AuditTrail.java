package com.frikiteam.eventsboot.domain.values;

import lombok.Value;
import com.frikiteam.eventsboot.command.application.notification.Notification;
import com.frikiteam.eventsboot.command.application.notification.Result;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Embeddable
@Value
public class AuditTrail {
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "created_by"))
    })
    private PlaceId createdBy;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "updated_by"))
    })
    private PlaceId updatedBy;

    private AuditTrail(LocalDateTime createdAt, LocalDateTime updatedAt, PlaceId createdBy, PlaceId updatedBy) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    protected AuditTrail() {
        this.createdAt = null;
        this.updatedAt = null;
        this.createdBy = null;
        this.updatedBy = null;
    }

    public static Result<AuditTrail, Notification> create(UUID createdBy) {
        return Result.success(new AuditTrail(LocalDateTime.now(ZoneOffset.UTC), null, PlaceId.of(createdBy), null));
    }
}
