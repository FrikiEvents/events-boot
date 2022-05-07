package com.frikiteam.eventsboot.command.application.handlers.queries;

import com.frikiteam.eventsboot.command.application.dtos.EventInformationView;
import org.axonframework.queryhandling.QueryHandler;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;
import com.frikiteam.eventsboot.command.application.queries.GetEventInformationByIdQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Component
public class GetEventInformationByIdHandler {
    @PersistenceContext
    private EntityManager entityManager;

    @QueryHandler
    public EventInformationView handle(GetEventInformationByIdQuery query) {
        String sql = String.join(
                " ",
                "SELECT",
                "BIN_TO_UUID(id) AS eventInformationId,",
                "event_description AS eventDescription,",
                "event_image AS eventImage,",
                "event_link AS eventLink,",
                "date_format(start_date, '%Y-%m-%d %H:%i') AS startDate,",
                "date_format(end_date, '%Y-%m-%d %H:%i') AS endDate ",
                "FROM events_information",
                "WHERE id = UUID_TO_BIN(:eventInformationId)"
        );

        return (EventInformationView) this.entityManager.createNativeQuery(sql)
                .setParameter("eventInformationId", query.getEventInformationId())
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(EventInformationView.class))
                .getSingleResult();
    }
}
