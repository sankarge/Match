package com.dating.matcher.util;

import static org.mapstruct.factory.Mappers.getMapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.geo.Point;

import com.dating.matcher.domain.MatchDocument;
import com.dating.matcher.view.json.CityJSON;
import com.dating.matcher.view.json.MatchJSON;

@Mapper
public interface ViewModelMapper {

    ViewModelMapper VIEW_MODEL_MAPPER = getMapper(ViewModelMapper.class);

    @Mapping(source = "city", target = "city.point", qualifiedByName = "setPoint")
    @Mapping(source = "city.name", target = "city.name")
    MatchDocument map(MatchJSON matchJSON);

    @Named("setPoint")
    default Point setPoint(CityJSON cityJSON) {
        return new Point(cityJSON.getLat(), cityJSON.getLon());
    }

}