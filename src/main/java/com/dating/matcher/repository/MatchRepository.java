package com.dating.matcher.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dating.matcher.domain.MatchDocument;

@RepositoryRestResource(collectionResourceRel = "matches", path = "matches")
public interface MatchRepository extends PagingAndSortingRepository<MatchDocument, String> {

}