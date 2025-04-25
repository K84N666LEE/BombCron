/* DiabloIV > BoardController.java */
package com.src.menu;

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

import com.src.dto.menu_vo;

//import jakarta.servlet.http.HttpServletResponse;
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
public class MenuController {

	@Autowired
	private MenuService menuService;

	/**
	 * 목록
	 * @param model
	 * @param menu
	 * @return
	 */
	@GetMapping(value="/menu_list")
	public String menu_list(
			ModelMap                model
			, HttpServletResponse response
			, menu_vo                 menu
			) throws IOException
	{
		log.info("● menu_list : menu_vo");

		try {
			List<menu_vo> menu_list = menuService.menu_list(menu);
			model.addAttribute( "menu_list", menu_list);
			response.getWriter().print( 1 ); // 1 (성공)
		} catch ( Exception e ) {
			log.debug("Exception error");
			response.getWriter().print( 0 ); // 0 (실패)
		}
		return "/menu/menu_list";
	}

	/**
	 * 글입력페이지
	 * @param model
	 * @param menu
	 * @return
	 */
	@GetMapping(value="/menu_form")
	public String menu_form(
			ModelMap                model
			, HttpServletResponse response
			, menu_vo                 menu
			) throws IOException
	{
		log.info("● menu_form : menu_vo");
		return "/menu/menu_form";
	}

	/**
	 * 글입력페이지
	 * @param model
	 * @param menu
	 * @return
	 */
	@GetMapping(value="/menu_view")
	public String menu_view(
			ModelMap                model
			, HttpServletResponse response
			, menu_vo                 menu
			) throws IOException
	{
		log.info("● menu_view : menu_vo");

		try {
			menu_vo menu_view = menuService.menu_view(menu);
			model.addAttribute( "menu_view", menu_view);
			response.getWriter().print( 1 ); // 1 (성공)
		} catch ( Exception e ) {
			log.debug("Exception error");
			response.getWriter().print( 0 ); // 0 (실패)
		}
		return "/menu/menu_view";
	}

	// 글입력프로세스
	@PostMapping("/menu_insert")
	public void menu_insert(
			ModelMap                  model
			, menu_vo                  menu
			, HttpServletResponse response
			) throws IOException
	{
		log.info("● menu_insert : menu_vo");

		try {
			menu.setGroupOrd(1);
			menu.setGroupLayer(0);
			int count = menuService.menu_insert(menu);
			response.getWriter().print( count ); // 1 (성공)
		} catch ( Exception e ) {
			log.debug("Exception error");
			response.getWriter().print( 0 ); // 0 (실패)
		}

	}

	/**
	 * 댓글입력페이지
	 * @param model
	 * @param menu
	 * @return
	 * @throws IOException
	 */
	@GetMapping(value="/menu_reply")
	public String menu_reply(
			ModelMap                model
			, HttpServletResponse response
			, menu_vo                 menu
			) throws IOException
	{
		log.info("● menu_reply : menu_vo");

		try {
			menu_vo menu_reply = menuService.menu_reply(menu);
			model.addAttribute( "menu_reply", menu_reply);
			response.getWriter().print( 1 ); // 1 (성공)
		} catch ( Exception e ) {
			log.debug("Exception error");
			response.getWriter().print( 0 ); // 0 (실패)
		}
		return "/menu/menu_reply";
	}

	// 댓글입력프로세스
	@PostMapping("/menu_reply_insert")
	public void menu_reply(
			ModelMap                  model
			, menu_vo                  menu
			, HttpServletResponse response
			) throws IOException
	{
		log.info("● menu_reply : menu_vo");

		try {
			// transaction
			//int count = 0;
			//count = menuService.menu_reply_update(menu); // 단일 멍티 작업
			//count = menuService.menu_reply_insert(menu); // 단일 멍티 작업

			menuService.menu_reply_transaction(menu); // 멍티 작업
			response.getWriter().print( 1 ); // 1 (성공)
		} catch ( Exception e ) {
			log.debug("Exception error");
			response.getWriter().print( 0 ); // 0 (실패)
		}

	}

}
