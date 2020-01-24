package com.dating.matcher;

import static com.dating.matcher.mapper.ModelMapper.MODEL_MAPPER;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.dating.matcher.service.MatchRepository;
import com.dating.matcher.domain.model.file.Match;
import com.dating.matcher.domain.model.file.Matches;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@Profile("sampleRecords")
@RequiredArgsConstructor
public class SampleRecordsSaver implements CommandLineRunner {

    private final MatchRepository matchRepository;

    @Override
    public void run(final String... args) throws IOException {
        persistSampleMatchRecords();
    }

    private void persistSampleMatchRecords() throws IOException {
        readMatchesFromFile()
                .stream()
                .map(MODEL_MAPPER::fileToDomain)
                .forEach(matchRepository::save);
    }

    private List<Match> readMatchesFromFile() throws IOException {
        return new ObjectMapper()
                .readValue(getResourceAsStream(), Matches.class)
                .getMatches();
    }

    private InputStream getResourceAsStream() {
        return this.getClass().getResourceAsStream("/matches.json");
    }
}
