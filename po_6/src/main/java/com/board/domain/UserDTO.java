package com.board.domain;


import lombok.Data;

@Data
public class UserDTO extends CommonDTO {
	private int employee_no;
	private String nickname;
	private String pw;
	private String adminpw;
	private String successmessage;
	private String errormessage;
}
