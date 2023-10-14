package com.board.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.constant.Method;
import com.board.domain.GuestBookDTO;
import com.board.paging.Criteria;
import com.board.service.GuestBookService;
import com.board.util.UiUtils;

@Controller
public class GuestBookController extends UiUtils {

  @Autowired
  private GuestBookService guestbookService;

  @GetMapping(value = "/guestbook/write.do")
  public String openGuestBookWrite(@ModelAttribute("params") GuestBookDTO params,
      @RequestParam(value = "no", required = false) Long no, Model model) {
    if (no== null) {
      model.addAttribute("guestbook", new GuestBookDTO());
    } else {
      GuestBookDTO guestbook = guestbookService.getGuestBookDetail(no);
      if (guestbook == null || "Y".equals(guestbook.getDeleteYn())) {
        return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/guestbook/list.do", Method.GET, null, model);
      }
      model.addAttribute("guestbook", guestbook);
    }

    return "guestbook/write";
  }


  @PostMapping(value = "/guestbook/register.do")
  public String registerGuestBook(@ModelAttribute("params") GuestBookDTO params, Model model) {
    Map<String, Object> pagingParams = getPagingParams(params);
    try {
      boolean isRegistered = guestbookService.registerguestbook(params);
      if (isRegistered == false) {
        return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/guestbook/list.do", Method.GET, pagingParams, model);
      }
    } catch (DataAccessException e) {
      return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/guestbook/list.do", Method.GET, pagingParams, model);

    } catch (Exception e) {
      return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/guestbook/list.do", Method.GET, pagingParams, model);
    }

    return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/guestbook/list.do", Method.GET, pagingParams, model);
  }

//  @GetMapping(value = "/guestbook/list.do")
//  public String openGuestBookList(@ModelAttribute("criteria") Criteria criteria, Model model) {
//    List<GuestBookDTO> guestbookList = guestbookService.getGuestBookList(criteria);
//    model.addAttribute("guestbookList", guestbookList);
//
//    return "guestbook/list";
//  }

  @GetMapping(value = "/guestbook/list.do")
  public String openGuestBookList(@ModelAttribute("params") GuestBookDTO params, Model model) {
    List<GuestBookDTO> guestbookList = guestbookService.getList(params);
    model.addAttribute("guestbookList", guestbookList);

    return "guestbook/list";
  }

  @GetMapping(value = "/guestbook/view.do")
  public String openGuestBookDetail(@ModelAttribute("params") GuestBookDTO params,
      @RequestParam(value = "no", required = false) Long no, Model model) {
    if (no == null) {
      return showMessageWithRedirect("올바르지 않은 접근입니다.", "/guestbook/list.do", Method.GET, null, model);
    }

    GuestBookDTO guestbook = guestbookService.getGuestBookDetail(no);
    if (guestbook == null) {
      return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/guestbook/list.do", Method.GET, null, model);
    }
    model.addAttribute("guestbook", guestbook);

    return "guestbook/view";
  }

  @PostMapping(value = "/guestbook/delete.do")
  public String deleteGuestBook(@ModelAttribute("params") GuestBookDTO params,
      @RequestParam(value = "no", required = false) Long no, Model model) {
    if (no == null) {
      return showMessageWithRedirect("올바르지 않은 접근입니다.", "/guestbook/list.do", Method.GET, null, model);
    }

    Map<String, Object> pagingParams = getPagingParams(params);
    try {
      boolean isDeleted = guestbookService.deleteguestbook(no);
      if (isDeleted == false) {
        return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/guestbook/list.do", Method.GET, pagingParams, model);
      }
    } catch (DataAccessException e) {
      return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/guestbook/list.do", Method.GET, pagingParams, model);

    } catch (Exception e) {
      return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/guestbook/list.do", Method.GET, pagingParams, model);
    }

    return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/guestbook/list.do", Method.GET, pagingParams, model);
  }

  public Map<String, Object> getPagingParams(Criteria criteria) {

    Map<String, Object> params = new LinkedHashMap<>();
    params.put("currentPageNo", criteria.getCurrentPageNo());
    params.put("recordsPerPage", criteria.getRecordsPerPage());
    params.put("pageSize", criteria.getPageSize());
    params.put("searchType", criteria.getSearchType());
    params.put("searchKeyword", criteria.getSearchKeyword());

    return params;
  }

}