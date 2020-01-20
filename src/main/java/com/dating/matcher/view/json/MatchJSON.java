package com.dating.matcher.view.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MatchJSON {

    @JsonProperty(value = "display_name")
    private String displayName;

    private int age;

    @JsonProperty(value = "job_title")
    private String jobTitle;

    @JsonProperty(value = "height_in_cm")
    private float height;

    private CityJSON city;

    @JsonProperty(value = "main_photo")
    private String mainPhoto;

    @JsonProperty(value = "compatibility_score")
    private float compatibilityScore;

    @JsonProperty(value = "contacts_exchanged")
    private int contactsExchanged;

    private boolean favourite;

    private String religion;
}