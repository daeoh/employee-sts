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

import com.board.domain.EmployeeDTO;
import com.board.domain.UserDTO;
import com.board.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/HRM") 
	public String hrm(@ModelAttribute("employee") EmployeeDTO params, Model model,HttpSession session){
		UserDTO loggedInUser = (UserDTO)session.getAttribute("loggedInUser");
		  List<EmployeeDTO> employeeList = employeeService.getEmployeeList(params);
		  model.addAttribute("employeeList", employeeList);
		  return "project/관리자/인사관리";
	}
	
	@GetMapping("/employee/add")
	public String employee(@ModelAttribute("employee") final EmployeeDTO employee, Model model) {
		return "project/관리자/직원추가";
	}
	
	@PostMapping("/employee/add") 
	public String employeeAdd(@ModelAttribute("employee") final EmployeeDTO employee, Model model){
		  boolean isRegistered=employeeService.insertEmployee(employee);
	 if (isRegistered) {
         return "redirect:/HRM"; // 회원가입 성공 시 로그인 페이지로 리다이렉션
     } else {
     	// 회원가입 실패 시 에러 메시지를 모델에 추가
         return "employee/add"; // 회원가입 실패 시 다시 회원가입 페이지로 리다이렉션
     }
     }
}
