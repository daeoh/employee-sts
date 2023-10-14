package com.board.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.board.constant.Method;
import com.board.domain.BoardDTO;
import com.board.paging.Criteria;
import com.board.service.BoardService;
import com.board.util.UiUtils;

@Controller
public class BoardController extends UiUtils {

  @Autowired
  private BoardService boardService;
  
  @GetMapping(value = "/board/write.do")
  public String openBoardWrite(@ModelAttribute("params") BoardDTO params, 
		  @RequestParam(value = "idx", required = false) Long idx, Model model) {
    if (idx == null) {
      model.addAttribute("board", new BoardDTO());
    } else {
      BoardDTO board = boardService.getBoardDetail(idx);
      if (board == null || "Y".equals(board.getDeleteYn())) {
        return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/board/list.do", Method.GET, null, model);
      }
      model.addAttribute("board", board);
    }

    return "board/write";
  }
  
  @PostMapping(value = "/board/register.do")
  public String registerBoard(@ModelAttribute("params") final BoardDTO params, Model model,
	  @RequestParam("file") MultipartFile file) {
    Map<String, Object> pagingParams = getPagingParams(params);
    try {
    	
      // 파일 업로드 처리를 위한 로직 추가
        if (!file.isEmpty()) {
        	
        	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        	String uploadDir = "C:\\sts-workspace\\board10\\src\\main\\resources\\fileupload";
            try {
                Path filePath = Path.of(uploadDir + fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                params.setFileName(fileName); // BoardDTO에 파일 이름 저장
                params.setFileSize(file.getSize()); // BoardDTO에 파일 크기 저장
            } catch (IOException e) {
                // 파일 저장 중에 예외 발생 시 처리
                return showMessageWithRedirect("파일 업로드에 실패하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
            }
        }

      boolean isRegistered = boardService.registerBoard(params);
      if (isRegistered == false) {           
        return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
      }
    } catch (DataAccessException e) {
      return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
    } catch (Exception e) {
      return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
    }

    return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/board/list.do", Method.GET, pagingParams, model);
  }

//  @GetMapping(value = "/board/list.do")
//  public String openBoardList(@ModelAttribute("criteria") Criteria criteria, Model model) {
//    List<BoardDTO> boardList = boardService.getBoardList(criteria);
//    model.addAttribute("boardList", boardList);
//
//    return "board/list";
//  }
  
  @GetMapping(value = "/board/list.do")
  public String openBoardList(@ModelAttribute("params") BoardDTO params, Model model) {
    List<BoardDTO> boardList = boardService.getBoardList(params);
    model.addAttribute("boardList", boardList);

    return "board/list";
  }

  @GetMapping(value = "/board/view.do")
  public String openBoardDetail(@ModelAttribute("params") BoardDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
    if (idx == null) {
      return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list.do", Method.GET, null, model);
    }

    BoardDTO board = boardService.getBoardDetail(idx);
    if (board == null || "Y".equals(board.getDeleteYn())) {
      return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/board/list.do", Method.GET, null, model);
    }
    model.addAttribute("board", board);

    return "board/view";
  }
  
  @PostMapping(value = "/board/delete.do")
  public String deleteBoard(@ModelAttribute("params") BoardDTO params, 
		  @RequestParam(value = "idx", required = false)  Long idx, Model model) {
    if (idx == null) {
      return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list.do", Method.GET, null, model);
    }

    Map<String, Object> pagingParams = getPagingParams(params);
    try {
      boolean isDeleted = boardService.deleteBoard(idx);
      if (isDeleted == false) {
        return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
      }
    } catch (DataAccessException e) {
      return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list.do", Method.GET, pagingParams, model);

    } catch (Exception e) {
      return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
    }

    return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/board/list.do", Method.GET, pagingParams, model);
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