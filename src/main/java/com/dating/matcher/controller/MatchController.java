package com.dating.matcher.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dating.matcher.service.MatchService;
import com.dating.matcher.view.json.FilterRequest;
import com.dating.matcher.view.json.MatchJSON;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping(value = "matches/filter")
    @ResponseBody
    public ResponseEntity filterMatches(FilterRequest filterRequest) {
        List<MatchJSON> filter = matchService.filter(filterRequest);
        return ResponseEntity.ok(filter);
    }
}
