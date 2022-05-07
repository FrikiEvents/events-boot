package com.frikiteam.eventsboot.domain.values;

import lombok.Value;
import com.frikiteam.eventsboot.application.notification.Notification;
import com.frikiteam.eventsboot.application.notification.Result;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Value
public class District {
    @Column(name = "name")
    private String name;
    @Column(name = "reference")
    private String reference;
    private final static int MAX_LENGTH = 75;

    private District(String name, String reference) {
        this.name = name;
        this.reference = reference;
    }

    protected District() {
        this.name = "";
        this.reference = "";
    }

    public static Result<District, Notification> create(String name, String reference) {
        Notification notification = new Notification();
        name = name == null ? "" : name.trim();
        reference = reference == null ? "" : reference.trim();
        if (name.isEmpty()) {
            notification.addError("name is required", null);
            return Result.failure(notification);
        }
        if (reference.isEmpty()) {
            notification.addError("reference is required", null);
            return Result.failure(notification);
        }
        if (name.length() > MAX_LENGTH) {
            notification.addError("The maximum length of a name is " + MAX_LENGTH + " characters including spaces", null);
        }
        if (reference.length() > MAX_LENGTH) {
            notification.addError("The maximum length of a reference is " + MAX_LENGTH + " characters including spaces", null);
        }
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        return Result.success(new District(name, reference));
    }
}
