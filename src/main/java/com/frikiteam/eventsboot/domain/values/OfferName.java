package com.frikiteam.eventsboot.domain.values;

import com.frikiteam.eventsboot.command.application.notification.Notification;
import com.frikiteam.eventsboot.command.application.notification.Result;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Value
public class OfferName {
    @Column(name = "name_offer_name")
    private String name;
    @Column(name = "reference_offer_name")
    private String reference;
    private final static int MAX_LENGTH = 100;

    private OfferName(String name, String reference) {
        this.name = name;
        this.reference = reference;
    }

    protected OfferName() {
        this.name = "";
        this.reference = "";
    }

    public static Result<OfferName, Notification> create(String name, String reference) {
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
        return Result.success(new OfferName(name, reference));
    }
}
