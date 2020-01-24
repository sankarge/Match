package com.dating.matcher;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.dating.matcher.domain.model.view.Match;
import com.dating.matcher.domain.model.view.MatchFilter;
import com.dating.matcher.service.MatchService;

@SpringBootTest
@ActiveProfiles("saveSampleMatches")
class MatcherApplicationIntegrationTest {

    private static final int TOTAL_RECORDS_COUNT = 20;

    @Autowired
    private MatchService matchService;

    @Test
    void shouldListAll_filterIsNull() {
        List<Match> matchList = matchService.filter(null);
        assertThat(matchList.size()).isEqualTo(TOTAL_RECORDS_COUNT);
    }

    @Test
    void shouldListAll_filterIsEmpty() {
        MatchFilter matchFilter = MatchFilter.builder().build();
        List<Match> matchList = matchService.filter(matchFilter);
        assertThat(matchList.size()).isEqualTo(TOTAL_RECORDS_COUNT);
    }

    @Test
    void verify_filterWithAgeFrom() {
        MatchFilter matchFilter =
                MatchFilter
                        .builder()
                        .ageFrom(40)
                        .build();
        List<Match> matchList = matchService.filter(matchFilter);
        matchList.forEach(match -> assertThat(match.getAge()).isGreaterThanOrEqualTo(40));
    }

    @Test
    void verify_filterWithAgeTo() {
        MatchFilter matchFilter =
                MatchFilter
                        .builder()
                        .ageTo(25)
                        .build();
        List<Match> matchList = matchService.filter(matchFilter);
        matchList.forEach(match -> assertThat(match.getAge()).isLessThanOrEqualTo(25));
    }

    @Test
    void verify_filterWithAgeBetween() {
        MatchFilter matchFilter =
                MatchFilter
                        .builder()
                        .ageFrom(30)
                        .ageTo(40)
                        .build();
        List<Match> matchList = matchService.filter(matchFilter);
        matchList.forEach(match -> assertThat(match.getAge()).isBetween(30, 40));
    }

    @Test
    void verify_filterWithHeightFrom() {
        MatchFilter matchFilter =
                MatchFilter
                        .builder()
                        .heightFrom(170)
                        .build();
        List<Match> matchList = matchService.filter(matchFilter);
        matchList.forEach(match -> assertThat(match.getHeight()).isGreaterThanOrEqualTo(170));
    }

    @Test
    void verify_filterWithHeightTo() {
        MatchFilter matchFilter =
                MatchFilter
                        .builder()
                        .heightTo(175)
                        .build();
        List<Match> matchList = matchService.filter(matchFilter);
        matchList.forEach(match -> assertThat(match.getHeight()).isLessThanOrEqualTo(175));
    }

    @Test
    void verify_filterWithHeightBetween() {
        MatchFilter matchFilter =
                MatchFilter
                        .builder()
                        .heightFrom(150)
                        .heightTo(165)
                        .build();
        List<Match> matchList = matchService.filter(matchFilter);
        matchList.forEach(match -> assertThat(match.getHeight()).isBetween(150d, 165d));
    }

    @Test
    void verify_filterWithCompatibilityScoreFrom() {
        MatchFilter matchFilter =
                MatchFilter
                        .builder()
                        .csFrom(95)
                        .build();
        List<Match> matchList = matchService.filter(matchFilter);
        matchList.forEach(match -> assertThat(match.getCompatibilityScore()).isGreaterThanOrEqualTo(95));
    }

    @Test
    void verify_filterWithCompatibilityScoreTo() {
        MatchFilter matchFilter =
                MatchFilter
                        .builder()
                        .csTo(80)
                        .build();
        List<Match> matchList = matchService.filter(matchFilter);
        matchList.forEach(match -> assertThat(match.getCompatibilityScore()).isLessThanOrEqualTo(80));
    }

    @Test
    void verify_filterWithCompatibilityScoreBetween() {
        MatchFilter matchFilter =
                MatchFilter
                        .builder()
                        .csFrom(85)
                        .csTo(95)
                        .build();
        List<Match> matchList = matchService.filter(matchFilter);
        matchList.forEach(match -> assertThat(match.getCompatibilityScore()).isBetween(85, 95));
    }

    @Test
    void verify_filterWithPhoto() {
        MatchFilter matchFilter =
                MatchFilter
                        .builder()
                        .hasPhoto(true)
                        .build();
        List<Match> matchList = matchService.filter(matchFilter);
        matchList.forEach(match -> assertThat(match.getMainPhoto()).isNotEmpty());
    }

    @Test
    void verify_filterWithoutPhoto() {
        MatchFilter matchFilter =
                MatchFilter
                        .builder()
                        .hasPhoto(false)
                        .build();
        List<Match> matchList = matchService.filter(matchFilter);
        matchList.forEach(match -> assertThat(match.getMainPhoto()).isNullOrEmpty());
    }

    @Test
    void verify_filterIsFavourite() {
        MatchFilter matchFilter =
                MatchFilter
                        .builder()
                        .isFavourite(true)
                        .build();
        List<Match> matchList = matchService.filter(matchFilter);
        matchList.forEach(match -> assertThat(match.isFavourite()).isTrue());
    }

    @Test
    void verify_filterIsNotFavourite() {
        MatchFilter matchFilter =
                MatchFilter
                        .builder()
                        .isFavourite(false)
                        .build();
        List<Match> matchList = matchService.filter(matchFilter);
        matchList.forEach(match -> assertThat(match.isFavourite()).isFalse());
    }

    @Test
    void verify_filterWithContacts() {
        MatchFilter matchFilter =
                MatchFilter
                        .builder()
                        .hasContact(true)
                        .build();
        List<Match> matchList = matchService.filter(matchFilter);
        matchList.forEach(match -> assertThat(match.getContactsExchanged()).isNotZero());
    }

    @Test
    void verify_filterWithoutContacts() {
        MatchFilter matchFilter =
                MatchFilter
                        .builder()
                        .hasContact(false)
                        .build();
        List<Match> matchList = matchService.filter(matchFilter);
        matchList.forEach(match -> assertThat(match.getContactsExchanged()).isEqualTo(0));
    }

    @Test
    void verify_filterWithDistance() {
        MatchFilter matchFilter =
                MatchFilter
                        .builder()
                        .distance(2)
                        .build();
        List<Match> matchList = matchService.filter(matchFilter);
        matchList.forEach(match -> assertThat(match.getDis()).isLessThanOrEqualTo(2));

        matchFilter =
                MatchFilter
                        .builder()
                        .distance(100)
                        .build();

        matchList = matchService.filter(matchFilter);
        matchList.forEach(match -> assertThat(match.getDis()).isLessThanOrEqualTo(100));
    }

    @Test
    void verify_filterWithAllCondition() {
        MatchFilter matchFilter =
                MatchFilter
                        .builder()
                        .ageFrom(40)
                        .ageTo(50)
                        .isFavourite(false)
                        .hasPhoto(true)
                        .hasContact(true)
                        .csFrom(80)
                        .csTo(88)
                        .heightFrom(165)
                        .heightTo(175)
                        .distance(2)
                        .build();

        List<Match> matchList = matchService.filter(matchFilter);
        matchList.forEach(this::verifyMatchWithAllCondition);
    }

    private void verifyMatchWithAllCondition(Match match) {
        assertThat(match.getAge()).isBetween(40, 50);
        assertThat(match.isFavourite()).isFalse();
        assertThat(match.getMainPhoto()).isNotEmpty();
        assertThat(match.getContactsExchanged()).isNotZero();
        assertThat(match.getCompatibilityScore()).isBetween(80, 88);
        assertThat(match.getHeight()).isBetween(165d, 175d);
        assertThat(match.getDis()).isLessThanOrEqualTo(2);
    }
}
