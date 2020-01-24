package com.dating.matcher.repository;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.dating.matcher.domain.MatchDocument;
import com.dating.matcher.domain.QMatchDocument;
import com.querydsl.core.types.dsl.BooleanExpression;

@RepositoryRestResource(collectionResourceRel = "matches", path = "matches")
public interface MatchRepository extends PagingAndSortingRepository<MatchDocument, String>,
        QuerydslPredicateExecutor<MatchDocument>,
        QuerydslBinderCustomizer<QMatchDocument> {

    @Override
    default void customize(QuerydslBindings bindings, QMatchDocument model) {
        bindFavourite(bindings, model);
        bindPhoto(bindings, model);
        bindCompatibilityScore(bindings, model);
    }

    default void bindCompatibilityScore(final QuerydslBindings bindings, final QMatchDocument model) {
        bindings.bind(model.compatibilityScore).all((path, value) -> {
            Iterator<? extends Double> it = value.iterator();
            double from = it.next();
            if (value.size() >= 2) {
                double to = it.next();
                return Optional.of(path.between(from, to));
            } else {
                return Optional.of(path.eq(from));
            }
        });
    }

    default void bindFavourite(final QuerydslBindings bindings, final QMatchDocument model) {
        bindings.bind(model.favourite).first(BooleanExpression::eq);
    }

    default void bindPhoto(final QuerydslBindings bindings, final QMatchDocument model) {
        bindings.bind(model.mainPhoto).first((stringPath, s) -> stringPath.isNotNull());
    }

    @RestResource(path = "filter")
    default Iterable<MatchDocument> findMatches() {
        Iterable<MatchDocument> iterable = findAll(QMatchDocument.matchDocument.mainPhoto.isNotNull()
                .and(QMatchDocument.matchDocument.favourite.isTrue()));
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}