/* DiabloIV > BoardController.java */
package com.src.board;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.src.dto.BoardVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 ******************************************
 * @업무명 : DiabloIV 게시판
 * @파일명 : BoardController.java
 * @생성일 : 2023. 1. 21.
 * @작성자 : Charlotte
 * @비고란 :
 ******************************************
 *   작성일     작성자      내용
 ******************************************
 * 2023.1.21 Charlotte  최초입력
 ******************************************
 */

@Tag(name = "게시판 API", description = "게시판 API를 관리합니다.")
@Slf4j
@RestController
@RequestMapping("/api/bbs")
@CrossOrigin
public class BoardAPI {

	@Autowired
	private BoardService boardService;

  @GetMapping("/users/{id}")
  @Operation(summary = "사용자 조회", description = "ID를 기준으로 사용자 정보를 조회합니다.")
  public String getUserById(
        @Parameter(description = "사용자 ID") @PathVariable Long id
  ) {
      return "User ID: " + id;
  }

  @PostMapping("/users/ins")
  @Operation(summary = "사용자 입력", description = "ID를 기준으로 사용자 정보를 조회합니다.")
  public String insertUserById(
        @Parameter(description = "사용자 ID") Long id
        , @Parameter(description = "사용자 ID") Long aaa
        , @Parameter(description = "사용자 ID") Long bbb
        , @Parameter(description = "사용자 ID") Long ccc
        , @Parameter(description = "사용자 ID") Long ddd
  ) {
  	String rtn = "---->> " + "User ID: " + id + "User ID: " + aaa + "User ID: " + bbb + "User ID: " + ccc + "User ID: " + ddd;
      return "rtn ID : " + rtn;
  }

	/**
	 * 목록
	 * @param model
	 * @param bbs
	 * @return
	 */
	@GetMapping(value="/list")
	public String board_list(
			ModelMap                model
			, HttpServletResponse response
			, BoardVO                 bbs
			) throws IOException
	{
		log.info("● board_list : BoardVO");

		try {
			List<BoardVO> bbslist = boardService.board_list(bbs);
			model.addAttribute( "bbslist", bbslist);
		} catch ( Exception e ) {
			log.debug("Exception error");
		}
		return "/board/board_list";
	}

	/**
	 * 글입력페이지
	 * @param model
	 * @param bbs
	 * @return
	 */
	@GetMapping(value="/form")
	public String board_form(
			ModelMap                model
			, HttpServletResponse response
			, BoardVO                 bbs
			) throws IOException
	{
		log.info("● board_form : BoardVO");
		return "/board/board_form";
	}

	/**
	 * 글입력페이지
	 * @param model
	 * @param bbs
	 * @return
	 */
	@GetMapping(value="/view")
	public String board_view(
			ModelMap                model
			, HttpServletResponse response
			, BoardVO                 bbs
			) throws IOException
	{
		log.info("● board_view : BoardVO");

		try {
			BoardVO bbs_view = boardService.board_view(bbs);
			model.addAttribute( "bbs_view", bbs_view);
		} catch ( Exception e ) {
			log.debug("Exception error");
		}
		return "/board/board_view";
	}

	// 글입력프로세스
	@PostMapping("/insert")
	public void board_insert(
			ModelMap                  model
			, @Parameter(description = "사용자 ID") @PathVariable Long bbs_num

			, BoardVO                  bbs
			, HttpServletResponse response
			) throws IOException
	{
		log.info("● board_insert : BoardVO");

		try {
			bbs.setGroupOrd(1);
			bbs.setGroupLayer(0);
			int count = boardService.board_insert(bbs);
			response.getWriter().print( count ); // 1 (성공)
		} catch ( Exception e ) {
			log.debug("Exception error");
			response.getWriter().print( 0 ); // 0 (실패)
		}

	}

	/**
	 * 댓글입력페이지
	 * @param model
	 * @param bbs
	 * @return
	 * @throws IOException
	 */
	@GetMapping(value="/reply")
	public String board_reply(
			ModelMap                model
			, HttpServletResponse response
			, BoardVO                 bbs
			) throws IOException
	{
		log.info("● board_reply : BoardVO");

		try {
			BoardVO board_reply = boardService.board_reply(bbs);
			model.addAttribute( "board_reply", board_reply);
		} catch ( Exception e ) {
			log.debug("Exception error");
		}
		return "/board/board_reply";
	}

	// 댓글입력프로세스
	@PostMapping("/reply_insert")
	public void board_reply(
			ModelMap                  model
			, BoardVO                  bbs
			, HttpServletResponse response
			) throws IOException
	{
		log.info("● board_reply : BoardVO");

		try {
			// transaction
			//int count = 0;
			//count = boardService.board_reply_update(bbs); // 단일 멍티 작업
			//count = boardService.board_reply_insert(bbs); // 단일 멍티 작업

			boardService.board_reply_transaction(bbs); // 멍티 작업
			response.getWriter().print( 1 ); // 1 (성공)
		} catch ( Exception e ) {
			log.debug("Exception error");
			response.getWriter().print( 0 ); // 0 (실패)
		}

	}

	/**
	 * 
	 * @차트 & W2UI
	 * @param model
	 * @param bbs
	 * @return
	 * String
	 *
	 */
	@GetMapping(value="/status")
	public String board_status(
			ModelMap                model
			, BoardVO                 bbs
			) throws IOException
	{
		log.info("● board_status : BoardVO");
		return "/board/board_status";
	}


}
