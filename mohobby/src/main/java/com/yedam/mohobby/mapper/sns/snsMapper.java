package com.yedam.mohobby.mapper.sns;

import java.util.List;

import com.yedam.mohobby.service.sns.SnsPostVO;

public interface snsMapper {
	//게시물 전체 조회
	public List<SnsPostVO> snsPostList();
	
	//강사 유저 게시물 조회
	public List<SnsPostVO> snsCPostList();
	
	//게시물 단건 조회
	public SnsPostVO getSnsPost(SnsPostVO snsPostVo);
	
	//게시물 등록
	public int insertPost(SnsPostVO snsPostVo);
	
	//게시물 수정
	public int updatePost(SnsPostVO snsPostVo);
	
	//게시물 삭제
	public int deletePost(int postId);
}
