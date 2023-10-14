package com.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.board.domain.WorkDTO;
import com.board.service.WorkService;

@Controller
public class WorkController{
	
  @Autowired
  private WorkService workService;
  
  @GetMapping("/project/ES")
  public String openWorkList(@ModelAttribute("params") WorkDTO params, Model model, HttpSession session) {
//    UserDTO loggedInUser = (UserDTO)session.getAttribute("loggedInUser");
//    if(loggedInUser.getNickname()=="4번직원") {value=true;} else {value=false;}

	MainController.value=true;// 권한이 없다면 직원번호를 전달하여야 함...
    List<WorkDTO> workList = workService.getWorkList(params);
    model.addAttribute("workList", workList);
    System.out.println(params.toString());
    return "project/관리자/근태관리";
  }
  
  @PostMapping("/ES/add")//추가 클릭시
  public String registerWork(@ModelAttribute("params") final WorkDTO params, Model model) {
    try {
      workService.registerWork(params);
    } catch (DataAccessException e) {
    } catch (Exception e) {
    }
    return "redirect:/ES";
  }
  
}
