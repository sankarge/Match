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

import com.dating.matcher.domain.MatchDocument;
import com.dating.matcher.view.json.FilterRequest;
import com.dating.matcher.view.json.MatchJSON;

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

    public List<MatchJSON> filter(FilterRequest request) {
        NearQuery query = constructQuery(request);
        GeoResults<MatchJSON> results = mongoTemplate
                .query(MatchDocument.class)
                .as(MatchJSON.class)
                .near(query)
                .all();

        return results
                .getContent()
                .stream()
                .map(GeoResult::getContent)
                .collect(Collectors.toList());
    }

    private NearQuery constructQuery(FilterRequest request) {
        return QueryBuilder
                .construct()
                .withAge(request.getAgeFrom(), request.getAgeTo())
                .withCompatibilityScore(request.getCsFrom(), request.getCsTo())
                .withContacts(request.getHasContact())
                .withFavourite(request.getIsFavourite())
                .withHeight(request.getHeightFrom(), request.getHeightTo())
                .withPhoto(request.getHasPhoto())
                .withInDistance(request.getDistance());
    }
}
