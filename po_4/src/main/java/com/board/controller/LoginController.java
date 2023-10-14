//package com.board.controller;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import com.board.domain.UserDTO;
//import com.board.service.UserService;
//
//@Controller
//public class LoginController {
//
//    @Autowired
//    private UserService userService;
//    
//    @GetMapping(value = "/project/login") 
//    public String showLoginForm(Model model) {
//        model.addAttribute("user", new UserDTO());
//  	  return "project/공통/로그인";
//    }
//
//
//    @PostMapping("/project/login")
//    public String login(@ModelAttribute("user") UserDTO user, Model model, HttpSession session) {
//        // 입력된 닉네임과 비밀번호로 로그인 시도
//        boolean isAuthenticated = userService.authenticateUser(user.getNickname(), user.getPw());
//
//        if (isAuthenticated) {
//            // 로그인 성공 시 세션에 사용자 정보 저장
//            session.setAttribute("loggedInUser", user);
//            
//            // 로그인 성공 시 홈 페이지 또는 다른 페이지로 리다이렉션
//            return "redirect:/project/main"; // 로그인 후 이동할 페이지 설정
//        } else {
//            // 로그인 실패 시 에러 메시지를 모델에 추가
//            model.addAttribute("errorMessage", "로그인에 실패했습니다. 다시 시도해주세요.");
//            return "redirect:/project/login"; // 로그인 실패 시 다시 로그인 페이지로 리다이렉션
//        }
//    }
//    
//}
