package com.dating.matcher.view.json;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MatchesJSON {
    private List<MatchJSON> matches;
}