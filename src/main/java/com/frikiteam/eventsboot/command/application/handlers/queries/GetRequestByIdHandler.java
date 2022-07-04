package com.frikiteam.eventsboot.command.application.handlers.queries;

import com.frikiteam.eventsboot.command.application.dtos.EventInformationView;
import com.frikiteam.eventsboot.command.application.dtos.RequestView;
import com.frikiteam.eventsboot.command.application.queries.GetEventInformationByIdQuery;
import com.frikiteam.eventsboot.command.application.queries.GetRequestByIdQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class GetRequestByIdHandler {
    @PersistenceContext
    private EntityManager entityManager;

    @QueryHandler
    public RequestView handle(GetRequestByIdQuery query) {
        String sql = String.join(
                " ",
                "SELECT",
                "BIN_TO_UUID(id) AS requestId,",
                "request_description AS requestDescription,",
                "passenger_id AS passengerId,",
                "employee_id AS employeeId,",
                "date_format(created_at, '%Y-%m-%d %H:%i') AS createdAt,",
                "date_format(updated_at, '%Y-%m-%d %H:%i') AS updatedAt ",
                "FROM requests",
                "WHERE id = UUID_TO_BIN(:requestId)"
        );

        return (RequestView) this.entityManager.createNativeQuery(sql)
                .setParameter("requestId", query.getRequestId())
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(RequestView.class))
                .getSingleResult();
    }
}
