package com.board.service;

import java.util.List;

import com.board.domain.EmployeeDTO;
import com.board.domain.UserDTO;

public interface UserService {
	public boolean insertUser(UserDTO params);
	public boolean loginUser(UserDTO params);


}
