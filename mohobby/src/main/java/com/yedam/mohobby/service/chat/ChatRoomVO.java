package com.yedam.mohobby.service.chat;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatRoomVO {

    private String roomId;
    private String roomName;


    public static ChatRoomVO create(String name) {
    	ChatRoomVO room = new ChatRoomVO();
        room.roomId = UUID.randomUUID().toString();
        room.roomName = name;
        return room;
    }
}