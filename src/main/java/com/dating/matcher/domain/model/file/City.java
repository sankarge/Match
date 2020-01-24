package com.dating.matcher.domain.model.file;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class City {
    private String name;
    private double lat;
    private double lon;
}
