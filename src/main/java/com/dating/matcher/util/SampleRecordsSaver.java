package com.dating.matcher.util;

import static com.dating.matcher.util.ViewModelMapper.VIEW_MODEL_MAPPER;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dating.matcher.repository.MatchRepository;
import com.dating.matcher.view.json.MatchJSON;
import com.dating.matcher.view.json.MatchesJSON;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

//@Component
@RequiredArgsConstructor
public class SampleRecordsSaver implements CommandLineRunner {

    private final MatchRepository matchRepository;

    @Override
    public void run(final String... args) throws IOException {
        persistSampleMatchRecords();
    }

    private void persistSampleMatchRecords() throws IOException {
        readMatchListFromJSONFile()
                .stream()
                .map(VIEW_MODEL_MAPPER::map)
                .forEach(matchRepository::save);
    }

    private List<MatchJSON> readMatchListFromJSONFile() throws IOException {
        InputStream json = this.getClass().getResourceAsStream("/matches.json");
        ObjectMapper mapper = new ObjectMapper();
        MatchesJSON matchesJSON = mapper.readValue(json, MatchesJSON.class);
        return matchesJSON.getMatches();
    }
}
