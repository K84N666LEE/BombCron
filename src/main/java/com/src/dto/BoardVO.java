package com.src.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Alias("BoardVO")
public class BoardVO {

	@Schema(description = "글 번호", example = "45")
	private int    code        ; // 글 번호
	@Schema(description = "원글 번호", example = "27")
	private int    originNo    ; // 원글 번호
	@Schema(description = "원원글(답글포함)에 대한 순서", example = "1")
	private int    groupOrd    ; // 원글(답글포함)에 대한 순서
	@Schema(description = "답글 계층", example = "3")
	private int    groupLayer  ; // 답글 계층
	@Schema(description = "글 제목", example = "글의 제목이 표시됨")
	private String title       ; // 글 제목
	@Schema(description = "글 내용", example = "글의 내용이 textarea 형식으로 출력 됨.")
	private String content     ; // 글 내용
	@Schema(description = "작성자", example = "입력한 사람의 이름")
	private String writer      ; // 작성자
	@Schema(description = "등록시간", example = "글 등록시 년월일 시분초가 입력됨")
	private String regDatetime ; // 등록시간

	@Schema(description = "정렬최대값", example = "27")
	private int    max_ord     ; // 정렬최대값

	@ArraySchema(
	    schema = @Schema(description = "게시글목록"),
	    minItems = 1,
	    uniqueItems = true
	)
	private List<BoardVO> boardVO;
	
}
