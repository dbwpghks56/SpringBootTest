package com.kosta.myapp.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageVO {

    private String roomId;
    private String writer;
    private String message;
}
