package com.yedam.mohobby.service.communal;

import lombok.Data;

@Data
//찜
public class JjimVO {
    private String memberId; //회원아이디
    private int targetType; //타겟유형
    private int targetId; //타켓아이디
}
