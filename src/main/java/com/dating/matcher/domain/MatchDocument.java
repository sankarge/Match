package com.dating.matcher.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "matches")
@Data
public class MatchDocument {
    @Id
    private String id;

    private String displayName;

    private int age;

    private String jobTitle;

    private float height;

    private CityDocument city;

    private String mainPhoto;

    private float compatibilityScore;

    private int contactsExchanged;

    private boolean favourite;

    private String religion;
}