package com.board.service;

import java.util.List;
import com.board.paging.Criteria;

import com.board.domain.GuestBookDTO;

public interface GuestBookService {
	
	public List<GuestBookDTO> getList(GuestBookDTO params); // 방명록 목록 조회
	public boolean registerguestbook(GuestBookDTO params);  // 방명록 정보 등록
	public boolean deleteguestbook(Long no);  // 방명록 정보 삭제
	public GuestBookDTO getGuestBookDetail(Long no);
	
}
