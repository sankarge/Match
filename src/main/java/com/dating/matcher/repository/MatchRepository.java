package com.dating.matcher.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.dating.matcher.domain.MatchDocument;

@Component
public interface MatchRepository extends CrudRepository<MatchDocument, String> {
}