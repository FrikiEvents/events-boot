package com.frikiteam.eventsboot.domain.values;

import lombok.Value;
import com.frikiteam.eventsboot.command.application.notification.Notification;
import com.frikiteam.eventsboot.command.application.notification.Result;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Value
public class Country {
    @Column(name = "name_country")
    private String name;
    @Column(name = "reference_country")
    private String reference;
    private final static int MAX_LENGTH = 100;

    private Country(String name, String reference) {
        this.name = name;
        this.reference = reference;
    }

    protected Country() {
        this.name = "";
        this.reference = "";
    }

    public static Result<Country, Notification> create(String name, String reference) {
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
        return Result.success(new Country(name, reference));
    }
}
