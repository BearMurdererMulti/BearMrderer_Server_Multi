package com.server.bearmurderermulti.domain.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AIChatRequest {

    private Long gameNo;
    private String secretKey;
    private String sender;
    private Map<String, String> receiver = new HashMap<>();
    private String chatContent;
    private int chatDay;
    private String previousStory;
    private List<Map<String, Object>> previousChatContents; // 이전 대화 내용들

}
