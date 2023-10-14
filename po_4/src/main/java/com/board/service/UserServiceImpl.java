package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.EmployeeDTO;
import com.board.domain.UserDTO;
import com.board.mapper.UserMapper;
import com.board.paging.PaginationInfo;

@Service
public class UserServiceImpl implements UserService {
	 @Autowired
	  private UserMapper um;

	@Override
	public boolean insertUser(UserDTO params) {
		 int queryResult = 0;
		  queryResult = um.insertUser(params);
		  
		  return (queryResult == 1) ? true : false;
	}

	
	@Override
	public boolean loginUser(UserDTO params) {
		    UserDTO user =um.selectUser(params);
		    if (user != null && user.getNickname().equals(params.getNickname()) && user.getPw().equals(params.getPw())) {
		        return true; // 로그인 성공
		    }
		    return false; // 로그인 실패
		}
}