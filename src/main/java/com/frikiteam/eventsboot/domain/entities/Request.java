package com.frikiteam.eventsboot.domain.entities;

import com.frikiteam.eventsboot.domain.values.RequestId;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity(name = "Request")
@Table(name = "requests")
@Data
public class Request {
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "id", columnDefinition = "BINARY(16)"))
    })
    private RequestId id;

    @Column(name = "request_description")
    private String RequestDescription;

    @Column(name = "employee_id")
    private Integer EmployeeId;

    @Column(name = "passenger_id")
    private Integer PassengerId;

    @Column(name = "created_at")
    private LocalDateTime CreatedAt;

    @Column(name = "updated_at")
    private LocalDateTime UpdatedAt;

    public Request(RequestId id, String requestDescription, Integer employeeId, Integer passengerId,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        RequestDescription = requestDescription;
        EmployeeId = employeeId;
        PassengerId = passengerId;
        CreatedAt = createdAt;
        UpdatedAt = updatedAt;
    }

    public Request() {

    }
}
