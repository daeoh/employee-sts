package com.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.constant.Method;
import com.board.domain.UserDTO;
import com.board.service.UserService;
import com.board.util.UiUtils;

@Controller
public class UserController extends UiUtils {

    @Autowired
    private UserService userService;

    @GetMapping("/project/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "signup";
    }

    @PostMapping("/project/signup")
    public String signUp(@ModelAttribute("user")UserDTO user,  Model model) {
        // 회원 정보를 데이터베이스에 저장
    	System.out.println(user.getEmployee_no());
    	System.out.println(user.getNickname());
    	System.out.println(user.getPw());
        boolean isRegistered = userService.insertUser(user);

        if (isRegistered) {
        	// 회원가입 성공 시 성공 메시지를 모델에 추가
            model.addAttribute("successMessage", "회원가입이 완료되었습니다. 로그인해주세요.");
            return "redirect:/project/login"; // 회원가입 성공 시 로그인 페이지로 리다이렉션
        } else {
        	// 회원가입 실패 시 에러 메시지를 모델에 추가
            model.addAttribute("errorMessage", "회원가입에 실패했습니다. 다시 시도해주세요.");
            return "redirect:/project/signup"; // 회원가입 실패 시 다시 회원가입 페이지로 리다이렉션
            
        }
    }
    @GetMapping(value = "/project/login")
    public String showLoginForm(Model model,HttpSession session) {
        model.addAttribute("user", new UserDTO());
        return "project/공통/로그인"; // 로그인 폼을 표시하는 Thymeleaf 템플릿의 이름
    }

    @PostMapping("/project/login")
    public String login(@ModelAttribute("user") UserDTO user, Model model, HttpSession session) {
        // 입력된 닉네임과 비밀번호로 로그인 시도
        boolean loginUser = userService.loginUser(user);
        if (loginUser) {
            // 로그인 성공 시 세션에 사용자 정보 저장
            session.setAttribute("loggedInUser", user);
            System.out.println("로그인 성공: " + user.getNickname());
            System.out.println("로그인 성공: " + user.getPw());
            return "redirect:/main"; 
        } else {
            System.out.println("로그인 실패: " + user.getNickname());
            System.out.println("로그인 실패: " + user.getPw());
            model.addAttribute("errorMessage", "로그인에 실패했습니다. 다시 시도해주세요.");
            return "project/공통/로그인";
    }
}
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션을 무효화시킵니다.
    	session.removeAttribute("loggedInUser");

        // 로그아웃 후 홈 페이지로 리다이렉트합니다.
        return "redirect:/project/main";
    }
    
}