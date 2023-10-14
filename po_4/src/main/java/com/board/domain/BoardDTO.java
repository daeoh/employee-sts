package com.board.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardDTO extends CommonDTO {
  private Long idx; // 번호 (PK)
  private String title; // 제목
  private String content; // 내용
  private String writer; // 작성자
  private int viewCnt; // 조회 수
  private String noticeYn; // 공지 여부
  private String secretYn; // 비밀 여부
  
  private MultipartFile file; // 업로드된 파일
  private String fileName; // 업로드된 파일명
  private long fileSize; // 업로드된 파일 크기

}