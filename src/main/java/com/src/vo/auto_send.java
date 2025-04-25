package com.src.vo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("auto_send")
public class auto_send implements Serializable {

	private static final long serialVersionUID = 1L;

	private int    ast_num      ; // [_] 일련번호
	private int    ast_siteNo   ; // [_] 사이트번호
	private String ast_part     ; // [_] 예비
	private String ast_fd_00    ; // [_] 취소여부
	private String ast_fd_01    ; // [_] 실행완료여부
	private String ast_fd_02    ; // [_] 실행시작일시
	private String ast_fd_03    ; // [_] 반복실행매월일
	private String ast_fd_04    ; // [_] 반복실행매주요일
	private String ast_fd_05    ; // [_] 반복실행시분초
	private String ast_fd_06    ; // [_] 제목(설명)
	private String ast_fd_07    ; // [_] 실행프로그램
	private String ast_fd_08    ; // [P] 넘겨줄 파리미터 값
	private String ast_fd_09    ; // [_] 생성위치
	private String ast_fd_10    ; // [_] 예비
	private String ast_fd_11    ; // [_] 예비
	private String ast_fd_12    ; // [_] 예비
	private String ast_create_u ; // [_] 생성자
	private String ast_create_d ; // [_] 생성일

	private String date_now     ; // [_] 현재일시

}
