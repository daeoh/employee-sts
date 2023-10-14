package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.GuestBookDTO;

@Mapper
public interface GuestBookMapper {
	
	  public int insertGuestBook(GuestBookDTO params);

	  public int updateGuestBook(GuestBookDTO params);

	  public int deleteGuestBook(Long no);

	  public List<GuestBookDTO> selectGuestBookList(GuestBookDTO params);

	  public int selectGuestBookTotalCount(GuestBookDTO params);
	  
	  public GuestBookDTO selectGuestBookDetail(Long no);
	  
}
