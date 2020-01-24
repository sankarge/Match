package com.dating.matcher.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dating.matcher.service.MatchService;
import com.dating.matcher.domain.model.view.MatchFilter;
import com.dating.matcher.domain.model.view.Match;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping(value = "matches/filter")
    @ResponseBody
    public ResponseEntity filterMatches(MatchFilter matchFilter) {
        List<Match> filter = matchService.filter(matchFilter);
        return ResponseEntity.ok(filter);
    }
}
