package com.yadavmahesh.restapi.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    USER("USER");
    @Getter
    private final String value;
}
