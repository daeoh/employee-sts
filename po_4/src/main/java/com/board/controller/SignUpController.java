//package com.board.controller;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.board.constant.Method;
//import com.board.domain.UserDTO;
//import com.board.service.UserService;
//import com.board.util.UiUtils;
//
//@Controller
//public class SignUpController extends UiUtils {
//
//	    @Autowired
//	    private UserService userService;
//
//	    @GetMapping("/project/signup")
//	    public String showSignUpForm(Model model) {
//	        model.addAttribute("user", new UserDTO());
//	        return "signup";
//	    }
//
//	    @PostMapping("/project/signup")
//	    public String signUp(@ModelAttribute("params") UserDTO params, Model model)
//	    	 {
//	    	 Map<String, Object> Params = getUserSignUpParams(params);
//	    	 String redirectPath = "/project/signup"; // 기본 리다이렉션 경로
//	    	    try {
//	    	        boolean isRegistered = userService.insertUser(params);
//	    	        if (isRegistered) {
//	    	            // 회원가입 성공 시 성공 메시지를 플래시 어트리뷰트에 추가
//	    	           showMessageWithRedirect("회원가입이 완료되었습니다. 로그인해주세요.", "/project/login", Method.GET, Params, model);
//	    	            redirectPath = "/project/login"; // 회원가입 성공 시 로그인 페이지로 리다이렉션
//	    	        } else {
//	    	            // 회원가입 실패 시 에러 메시지를 플래시 어트리뷰트에 추가
//	    	           showMessageWithRedirect("회원가입에 실패했습니다. 다시 시도해주세요.", "/project/signup", Method.GET, Params, model);
//	    	        }
//	    	    } catch (Exception e) {
//	    	        // 예외 발생 시 에러 메시지를 플래시 어트리뷰트에 추가
//	    	        showMessageWithRedirect("회원가입 중 오류가 발생했습니다. 다시 시도해주세요.", "/project/signup", Method.GET, Params, model);
//	    	    }
//	    	    return "redirect:" + redirectPath;
//	    }
//
//	    public Map<String, Object> getUserSignUpParams(UserDTO userDTO) {
//	        Map<String, Object> params = new LinkedHashMap<>();
//	        params.put("employee_no", userDTO.getEmployee_no());
//	        params.put("nickname", userDTO.getNickname());
//	        params.put("pw", userDTO.getPw());
//	        return params;
//	    }
//	    @PostMapping("/project/signup")
//	    public String signUp(@ModelAttribute("user") UserDTO user, RedirectAttributes redirectAttributes,Model model) {
//	        // 회원 정보를 데이터베이a스에 저장
//	        boolean isRegistered = userService.insertUser(user);
//
//	        if (isRegistered) {
//	        	 // 회원가입 성공 시 성공 메시지를 모델에 추가
//	            model.addAttribute("successMessage", "회원가입이 완료되었습니다. 로그인해주세요.");
//	            return "redirect:/project/login"; // 회원가입 성공 시 로그인 페이지로 리다이렉션
//	        } else {
//	            // 회원가입 실패 시 에러 메시지를 모델에 추가
//	            model.addAttribute("errorMessage", "회원가입에 실패했습니다. 다시 시도해주세요.");
//	            return "redirect:/project/signup"; // 회원가입 실패 시 다시 회원가입 페이지로 리다이렉션
//	        }
//	}
//
//	}
//	    
//
//	 
//
