package com.dating.matcher.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.dating.matcher.domain.model.persistence.MatchDocument;

@Component
public interface MatchRepository extends CrudRepository<MatchDocument, String> {
}