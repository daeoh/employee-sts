package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.BoardDTO;
import com.board.domain.GuestBookDTO;
import com.board.mapper.GuestBookMapper;
import com.board.paging.PaginationInfo;

@Service
public class GuestBookServiceImpl implements GuestBookService {
	@Autowired
	private GuestBookMapper guestbookmapper;

	@Override
	public List<GuestBookDTO> getList(GuestBookDTO params) {
	    List<GuestBookDTO> guestbookList = Collections.emptyList();

	    int guestbookTotalCount =guestbookmapper.selectGuestBookTotalCount(params);

	    PaginationInfo paginationInfo = new PaginationInfo(params);
	    paginationInfo.setTotalRecordCount(guestbookTotalCount);

	    params.setPaginationInfo(paginationInfo);

	    if (guestbookTotalCount > 0) {
	      guestbookList = guestbookmapper.selectGuestBookList(params);
	    }

	    return guestbookList;
	  }

	@Override
	public boolean registerguestbook(GuestBookDTO params) {
	    int queryResult = 0;

	    if (params.getNo() == null) {
	        queryResult = guestbookmapper.insertGuestBook(params);
	    } else {
	        queryResult = guestbookmapper.updateGuestBook(params);
	    }

	    // GuestBookDTO guestbook = null;
	    // System.out.println(guestbook.getTitle());

	    return (queryResult == 1) ? true : false;
	  }


	  @Override
	  public boolean deleteguestbook(Long no) {
	    int queryResult = 0;

	    GuestBookDTO guestbook = guestbookmapper.selectGuestBookDetail(no);

	    if (guestbook != null) {
	      queryResult = guestbookmapper.deleteGuestBook(no);
	    }

	    return (queryResult == 1) ? true : false;
	  }


	@Override
	  public GuestBookDTO getGuestBookDetail(Long no) {
	    return guestbookmapper.selectGuestBookDetail(no);
	  }
}
