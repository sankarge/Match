package com.dating.matcher.domain.model.view;

import static java.util.Optional.ofNullable;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchFilter {

    public static final MatchFilter NONE = MatchFilter.builder().build();

    private Boolean hasPhoto;
    private Boolean hasContact;
    private Boolean isFavourite;
    private Integer csFrom;
    private Integer csTo;
    private Integer ageFrom;
    private Integer ageTo;
    private Integer heightFrom;
    private Integer heightTo;
    private Integer distance;

    public Optional<Boolean> getHasPhoto() {
        return ofNullable(this.hasPhoto);
    }

    public Optional<Boolean> getHasContact() {
        return ofNullable(this.hasContact);
    }

    public Optional<Boolean> getIsFavourite() {
        return ofNullable(this.isFavourite);
    }

    public Optional<Integer> getCsFrom() {
        return ofNullable(this.csFrom);
    }

    public Optional<Integer> getCsTo() {
        return ofNullable(this.csTo);
    }

    public Optional<Integer> getAgeFrom() {
        return ofNullable(this.ageFrom);
    }

    public Optional<Integer> getAgeTo() {
        return ofNullable(this.ageTo);
    }

    public Optional<Integer> getHeightFrom() {
        return ofNullable(this.heightFrom);
    }

    public Optional<Integer> getHeightTo() {
        return ofNullable(this.heightTo);
    }

    public Optional<Integer> getDistance() {
        return ofNullable(this.distance);
    }
}
