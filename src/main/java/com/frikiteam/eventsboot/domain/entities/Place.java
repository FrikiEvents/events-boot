package com.frikiteam.eventsboot.domain.entities;

import com.frikiteam.eventsboot.domain.values.*;
import lombok.Data;
import org.axonframework.modelling.command.AggregateIdentifier;

import javax.persistence.*;

@Entity(name = "Place")
@Table(name = "places")
@Data
public class Place {
  @AggregateIdentifier
  @EmbeddedId
  @AttributeOverrides({
      @AttributeOverride(name = "value", column = @Column(name = "id", columnDefinition = "BINARY(16)"))
  })
  private PlaceId id;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "name", column = @Column(name = "name_district", length = 100, nullable = false)),
      @AttributeOverride(name = "reference", column = @Column(name = "reference_district", length = 100, nullable = false))
  })
  private District district;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "name", column = @Column(name = "name_city", length = 100, nullable = false)),
      @AttributeOverride(name = "reference", column = @Column(name = "reference_city", length = 100, nullable = false))
  })
  private City city;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "name", column = @Column(name = "name_country", length = 100, nullable = false)),
      @AttributeOverride(name = "reference", column = @Column(name = "reference_country", length = 100, nullable = false))
  })
  private Country country;

  @Embedded
  private AuditTrail auditTrail;

  private String name;

  public Place() {

  }

  public Place(PlaceId placeid, District district, Country country, City city, AuditTrail auditTrail, String placeName) {
    setId(placeid);
    setDistrict(district);
    setAuditTrail(auditTrail);
    setName(placeName);
    setAuditTrail(auditTrail);
    setCountry(country);
    setCity(city);
  }



}
