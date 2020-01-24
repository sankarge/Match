package com.dating.matcher.view.json;

import java.util.Optional;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
@Getter
@Setter
public class FilterRequest {
    Optional<Boolean> hasPhoto = Optional.empty();
    Optional<Boolean> hasContact = Optional.empty();
    Optional<Boolean> isFavourite = Optional.empty();
    Optional<Integer> csFrom = Optional.empty();
    Optional<Integer> csTo = Optional.empty();
    Optional<Integer> ageFrom = Optional.empty();
    Optional<Integer> ageTo = Optional.empty();
    Optional<Integer> heightFrom = Optional.empty();
    Optional<Integer> heightTo = Optional.empty();
    Optional<Integer> distance = Optional.empty();
}
