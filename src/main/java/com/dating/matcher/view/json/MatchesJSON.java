package com.dating.matcher.util;

import java.util.List;

import com.dating.matcher.domain.Match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matches {
    List<Match> matches;
}