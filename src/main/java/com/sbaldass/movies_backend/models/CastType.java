package com.sbaldass.movies_backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CastType {
    @JsonProperty("director") DIRECTOR,
    @JsonProperty("producer") PRODUCER,
    @JsonProperty("actor") ACTOR;
}
