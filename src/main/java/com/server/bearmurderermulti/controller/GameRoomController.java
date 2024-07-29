package com.server.bearmurderermulti.controller;

import com.server.bearmurderermulti.domain.dto.gameRoom.GameRoomSaveRequest;
import com.server.bearmurderermulti.domain.dto.gameRoom.GameRoomSaveResponse;
import com.server.bearmurderermulti.exception.Response;
import com.server.bearmurderermulti.service.GameRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/room")
@RequiredArgsConstructor
public class GameRoomController {

    private final GameRoomService gameRoomService;

    @PostMapping("/save")
    public Response<GameRoomSaveResponse> saveGameRoom(@RequestBody GameRoomSaveRequest request) {

        GameRoomSaveResponse response = gameRoomService.createGameRoom(request);

        return Response.success(response);
    }
}
