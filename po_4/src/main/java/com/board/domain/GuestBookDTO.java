package com.board.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GuestBookDTO extends CommonDTO  {
	private Long no;
	private String name;
	private String password;
	private String content;
	private LocalDateTime regDate;
}
