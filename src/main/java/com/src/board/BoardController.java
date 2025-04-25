/* DiabloIV > BoardController.java */
package com.src.board;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.src.dto.BoardVO;
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
@Slf4j
@Controller
@RequestMapping
@CrossOrigin
public class BoardController {

	@Autowired
	private BoardService boardService;

	/**
	 * 목록
	 * @param model
	 * @param bbs
	 * @return
	 */
	@GetMapping(value="/board_list")
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
	@GetMapping(value="/board_form")
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
	@GetMapping(value="/board_view")
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
	@PostMapping("/board_insert")
	public void board_insert(
			ModelMap                  model
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
	@GetMapping(value="/board_reply")
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
	@PostMapping("/board_reply_insert")
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
	@GetMapping(value="/board_status")
	public String board_status(
			ModelMap                model
			, BoardVO                 bbs
			) throws IOException
	{
		log.info("● board_status : BoardVO");
		return "/board/board_status";
	}


}
