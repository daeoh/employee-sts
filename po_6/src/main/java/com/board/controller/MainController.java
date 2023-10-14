package com.board.controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.board.domain.UserDTO;

@Controller
public class MainController {
//	프로젝트 테스트용 페이지 ------------------------------------------------------------  
  static boolean value = true	; //로그인된 아이디 권한 (임시)

  @GetMapping(value = "/main") 
  public String main(Model model, HttpSession session) {
      // 세션에서 로그인 정보를 가져옴
      UserDTO loggedInUser = (UserDTO) session.getAttribute("loggedInUser");

      // 만약 로그인 정보가 없다면 로그인 페이지로 리다이렉션
      if (loggedInUser == null) {
          return "redirect:/login";
      }else {
    	  model.addAttribute("admin", value);
    	  model.addAttribute("loggedInUser", loggedInUser);
    	  return "project/공통/메인";
      }
  }
  @GetMapping(value = "/project/myInfo") 
  public String myInfo(){
	  return "project/공통/내정보";
  }
  @GetMapping(value = "/project/join") 
  public String join(){
	  return "project/공통/회원가입";
  }
  
  @GetMapping(value = "/project/board") 
  public String board(){
	  return "project/공통/게시판";
  }
  @GetMapping(value = "/project/write") 
  public String write(){
	  return "project/공통/게시글작성";
  }
//  @GetMapping(value = "/project/ES") 
//  public String 근태관리(){
//	  return "project/관리자/근태관리";
//  }
  @GetMapping(value = "/project/work") 
  public String 출퇴근관리(){
	  return "project/사용자/근무현황조회";
  }
  
  
}