package com.dating.matcher.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Document(collection = "matches")
public class Match {
    @Id
    private String id;

    String displayName;

    int age;

    String jobTitle;

    float height;

    City city;

    String mainPhoto;

    float compatibilityScore;

    int contactsExchanged;

    boolean favourite;

    String religion;
}