package com.choi.jajaotalk.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {

    CATEGORY_FREEDOM("CATEGORY_FREEDOM","자유"),
    CATEGORY_HOBBY("CATEGORY_HOBBY","취미"),
    CATEGORY_LOVE("CATEGORY_LOVE","연애"),
    CATEGORY_GAME("CATEGORY_GAME","게임"),
    CATEGORY_WORRY("CATEGORY_WORRY","고민");

    private String key;
    private String value;

}
