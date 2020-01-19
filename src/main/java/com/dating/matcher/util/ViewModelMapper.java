package com.dating.matcher.util;

import static org.mapstruct.factory.Mappers.getMapper;

import org.mapstruct.Mapper;

import com.dating.matcher.domain.MatchDocument;
import com.dating.matcher.view.json.MatchJSON;

@Mapper
public interface ViewModelMapper {

    ViewModelMapper VIEW_MODEL_MAPPER = getMapper(ViewModelMapper.class);

    MatchDocument map(MatchJSON matchJSON);
}