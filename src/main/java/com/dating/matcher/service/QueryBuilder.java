package com.dating.matcher.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
class QueryBuilder {

    private static final Point LONDON_CENTRAL = new Point(51.5071576, -0.1275761);

    private List<Criteria> criteriaList = new ArrayList<>();

    private QueryBuilder() {
    }

    static QueryBuilder construct() {
        return new QueryBuilder();
    }

    QueryBuilder withAge(Optional<Integer> ageFrom, Optional<Integer> ageTo) {
        if (ageFrom.isPresent() || ageTo.isPresent()) {
            Criteria criteria = Criteria.where("age");
            ageFrom.ifPresent(criteria::gte);
            ageTo.ifPresent(criteria::lte);
            criteriaList.add(criteria);
        }
        return this;
    }

    QueryBuilder withCompatibilityScore(Optional<Integer> csFrom, Optional<Integer> csTo) {
        if (csFrom.isPresent() || csTo.isPresent()) {
            Criteria criteria = Criteria.where("compatibilityScore");
            csFrom.map(i -> (float) i / 100).ifPresent(criteria::gte);
            csTo.map(i -> (float) i / 100).ifPresent(criteria::lte);
            criteriaList.add(criteria);
        }
        return this;
    }

    QueryBuilder withContacts(Optional<Boolean> hasContact) {
        hasContact.map(this::contactsCriteria)
                .ifPresent(criteria -> criteriaList.add(criteria));
        return this;
    }

    QueryBuilder withFavourite(Optional<Boolean> isFavourite) {
        isFavourite.ifPresent(bool -> criteriaList.add(Criteria.where("favourite").is(bool)));
        return this;
    }

    QueryBuilder withHeight(Optional<Integer> heightFrom, Optional<Integer> heightTo) {
        if (heightFrom.isPresent() || heightTo.isPresent()) {
            Criteria criteria = Criteria.where("height");
            heightFrom.ifPresent(criteria::gte);
            heightTo.ifPresent(criteria::lte);
            criteriaList.add(criteria);
        }
        return this;
    }

    QueryBuilder withPhoto(Optional<Boolean> hasPhoto) {
        hasPhoto.map(this::photoCriteria)
                .ifPresent(criteria -> criteriaList.add(criteria));
        return this;
    }

    NearQuery withInDistance(Optional<Integer> withinDistance) {
        NearQuery nearQuery = getNearQuery(withinDistance);
        if (criteriaList.size() == 0) {
            return nearQuery;
        }

        return nearQuery.query(getQueryWithAllCriteria());
    }

    private NearQuery getNearQuery(final Optional<Integer> withinDistance) {
        return withinDistance
                .map(QueryBuilder::nearQuery)
                .orElse(nearQuery(500));
    }

    private static NearQuery nearQuery(Integer distance) {
        return NearQuery.near(LONDON_CENTRAL).maxDistance(new Distance(distance, Metrics.KILOMETERS));
    }

    private Query getQueryWithAllCriteria() {
        return new Query(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
    }

    private Criteria contactsCriteria(boolean hasContacts) {
        Criteria contactsExchanged = Criteria.where("contactsExchanged");
        return hasContacts ? contactsExchanged.gt(0) : contactsExchanged.lte(0);
    }

    private Criteria photoCriteria(boolean hasPhoto) {
        return Criteria.where("mainPhoto").exists(hasPhoto);
    }
}
