package com.server.bearmurderermulti.domain.dto.chat;

import com.server.bearmurderermulti.domain.entity.Chat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatSaveResponse {

    private String sender;
    private String chatContent;

    public static ChatSaveResponse of(Chat chat) {
        return new ChatSaveResponse(chat.getSender(), chat.getChatContent());
    }
}
