package com.src.dto;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("menu_vo")
public class menu_vo {

	private Integer code        ; // 글 번호
	private Integer originNo    ; // 원글 번호
	private Integer groupOrd    ; // 원글(답글포함)에 대한 순서
	private Integer groupLayer  ; // 답글 계층
	private String title       ; // 글 제목
	private String content     ; // 글 내용
	private String writer      ; // 작성자
	private String regDatetime ; // 등록시간

	private int    max_ord     ; // 정렬최대값

}
