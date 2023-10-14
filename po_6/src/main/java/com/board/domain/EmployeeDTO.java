package com.board.domain;

import lombok.Data;

@Data
public class EmployeeDTO extends CommonDTO {
	private   int  idx;
	private String depName;
	private String jikup;
	
	private int  no;
	private String name;
	private String juminNum;
	private String telNum;
	private String email;
	private Long dep_no;
} 