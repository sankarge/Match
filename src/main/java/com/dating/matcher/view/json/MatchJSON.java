package com.dating.matcher.view;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.dating.matcher.domain.City;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "matches")
public class Match {
    @Id
    private String id;
    @JsonProperty(value = "display_name")
    String displayName;
    int age;
    @JsonProperty(value = "job_title")
    String jobTitle;
    @JsonProperty(value = "height_in_cm")
    float height;
    City city;
    @JsonProperty(value = "main_photo")
    String mainPhoto;
    @JsonProperty(value = "compatibility_score")
    float compatibilityScore;
    @JsonProperty(value = "contacts_exchanged")
    int contactsExchanged;
    boolean favourite;
    String religion;
}