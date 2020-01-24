package com.dating.matcher.domain.model.file;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Matches {
    private List<Match> matches;
}