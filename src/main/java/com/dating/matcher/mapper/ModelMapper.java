package com.dating.matcher.mapper;

import static org.mapstruct.factory.Mappers.getMapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.geo.Point;

import com.dating.matcher.domain.model.persistence.MatchDocument;
import com.dating.matcher.domain.model.file.City;
import com.dating.matcher.domain.model.file.Match;

@Mapper
public interface ModelMapper {

    ModelMapper MODEL_MAPPER = getMapper(ModelMapper.class);

    @Mapping(source = "city", target = "city.point", qualifiedByName = "setPoint")
    @Mapping(source = "city.name", target = "city.name")
    MatchDocument fileToDomain(Match match);

    @Named("setPoint")
    default Point setPoint(City city) {
        return new Point(city.getLat(), city.getLon());
    }

}