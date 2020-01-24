package com.dating.matcher.domain.model.file;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Match {

    @JsonProperty(value = "display_name")
    private String displayName;

    private int age;

    @JsonProperty(value = "job_title")
    private String jobTitle;

    @JsonProperty(value = "height_in_cm")
    private double height;

    private City city;

    @JsonProperty(value = "main_photo")
    private String mainPhoto;

    @JsonProperty(value = "compatibility_score")
    private int compatibilityScore;

    @JsonProperty(value = "contacts_exchanged")
    private int contactsExchanged;

    private boolean favourite;

    private String religion;

    @SuppressWarnings("unused")
    public void setCompatibilityScore(Float compatibilityScore) {
        this.compatibilityScore = (int) (compatibilityScore * 100);
    }
}