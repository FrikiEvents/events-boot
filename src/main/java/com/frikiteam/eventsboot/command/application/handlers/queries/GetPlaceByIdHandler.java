package com.frikiteam.eventsboot.command.application.handlers.queries;

import com.frikiteam.eventsboot.command.application.dtos.PlaceView;
import com.frikiteam.eventsboot.command.application.queries.GetPlaceByIdQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class GetPlaceByIdHandler {
    @PersistenceContext
    private EntityManager entityManager;

    @QueryHandler
    public PlaceView handle(GetPlaceByIdQuery query) {
        String sql = String.join(
                " ",
                "SELECT ",
                "BIN_TO_UUID(id) AS placeId,",
                "name_city AS nameCity,",
                "reference_city AS referenceCity,",
                "name_country AS nameCountry,",
                "reference_country AS referenceCountry,",
                "name_district AS nameDistrict,",
                "reference_district AS referenceDistrict,",
                "name AS name ",
                "FROM places ",
                "WHERE id = UUID_TO_BIN(:placeId)"
        );

        return (PlaceView) this.entityManager.createNativeQuery(sql)
                .setParameter("placeId", query.getPlaceId())
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(PlaceView.class))
                .getSingleResult();
    }
}
