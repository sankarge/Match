package com.dating.matcher.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.stereotype.Service;

import com.dating.matcher.domain.model.persistence.MatchDocument;
import com.dating.matcher.domain.model.view.Match;
import com.dating.matcher.domain.model.view.MatchFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MatchService {

    private final MongoTemplate mongoTemplate;

    @PostConstruct
    public void createIndex() {
        mongoTemplate
                .indexOps(MatchDocument.class)
                .ensureIndex(new GeospatialIndex("city.point"));
    }

    public List<Match> filter(MatchFilter filter) {
        NearQuery query = constructQuery(filter);

        return executeQuery(query)
                .getContent()
                .stream()
                .map(GeoResult::getContent)
                .collect(Collectors.toList());
    }

    private GeoResults<Match> executeQuery(NearQuery query) {
        return mongoTemplate
                .query(MatchDocument.class)
                .as(Match.class)
                .near(query)
                .all();
    }

    private NearQuery constructQuery(MatchFilter filter) {
        return QueryBuilder
                .construct()
                .withAge(filter.getAgeFrom(), filter.getAgeTo())
                .withCompatibilityScore(filter.getCsFrom(), filter.getCsTo())
                .withContacts(filter.getHasContact())
                .withFavourite(filter.getIsFavourite())
                .withHeight(filter.getHeightFrom(), filter.getHeightTo())
                .withPhoto(filter.getHasPhoto())
                .withInDistance(filter.getDistance());
    }
}
