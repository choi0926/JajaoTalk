package com.choi.jajaotalk.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {

    CREATE("CREATE"),ENTER("ENTER"),MESSAGE("MESSAGE"),LEAVE("LEAVE");

    private String value;
}
