package com.server.bearmurderermulti.domain.dto.chat;

import com.server.bearmurderermulti.domain.dto.scenario.TokensDTO;
import com.server.bearmurderermulti.domain.entity.Chat;
import com.server.bearmurderermulti.domain.entity.GameSet;
import com.server.bearmurderermulti.domain.enum_class.ChatRoleType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NpcChatResponse {

    private ChatContent answer;
    private TokensDTO tokens;

    public Chat toEntity(int chatDay, LocalDateTime time, ChatRoleType senderType, ChatRoleType receiverType, GameSet gameSet) {
        return ChatContent.toEntity(answer, chatDay, time, senderType, receiverType, gameSet);
    }
}
