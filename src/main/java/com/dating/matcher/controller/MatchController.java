package com.dating.matcher.controller;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dating.matcher.domain.MatchDocument;
import com.dating.matcher.repository.MatchRepository;
import com.dating.matcher.service.MatchService;
import com.dating.matcher.view.json.FilterRequest;
import com.dating.matcher.view.json.MatchJSON;
import com.querydsl.core.types.Predicate;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MatchController {
    private final MatchRepository matchRepository;

    private final MatchService matchService;

    @GetMapping(value = "matches/filter1")
    @ResponseBody
    public ResponseEntity matches(@QuerydslPredicate(root = MatchDocument.class, bindings = MatchRepository.class) Predicate predicate,
            Pageable pageable,
            PagedResourcesAssembler<MatchDocument> assembler) {
        return ResponseEntity.ok(assembler.toModel(matchRepository.findAll(predicate, pageable)));
    }

    @GetMapping(value = "matches/filter")
    @ResponseBody
    public ResponseEntity filterMatches(FilterRequest filterRequest) {
        List<MatchJSON> filter = matchService.filter(filterRequest);
        return ResponseEntity.ok(filter);
    }
}
