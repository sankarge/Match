package com.dating.matcher.domain.model.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Match {

    private String displayName;

    private int age;

    private String jobTitle;

    private double height;

    private City city;

    private String mainPhoto;

    private int compatibilityScore;

    private int contactsExchanged;

    private boolean favourite;

    private String religion;

    private int dis;
}