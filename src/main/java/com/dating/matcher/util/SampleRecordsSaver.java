package com.dating.matcher.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dating.matcher.domain.Match;
import com.dating.matcher.repository.MatchRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SampleRecordsLoader implements CommandLineRunner {

    private final MatchRepository matchRepository;

    @Override
    public void run(final String... args) throws IOException {
        List<Match> matches = readMatchListFromJSONFile();
        matchRepository.saveAll(matches);
    }

    private List<Match> readMatchListFromJSONFile() throws IOException {
        InputStream json = this.getClass().getResourceAsStream("/matches.json");
        ObjectMapper mapper = new ObjectMapper();
        Matches matches = mapper.readValue(json, Matches.class);
        return matches.matches;
    }
}
