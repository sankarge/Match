package com.dating.matcher.domain;

import org.springframework.data.geo.Point;

import lombok.Data;

@Data
public class CityDocument {
    private String name;
    private Point point;
}
