/* DiabloIV > BoardService.java */
package com.src.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.src.dto.menu_vo;

/* ----------------------------------------------------------------
 * 제목명 : DiabloIV
 * 파일명 : BoardService.java
 * 생성일 : 2023. 1. 21.
 * 작성자 : Charlotte
 * 설명 :
 -----------------------------------------------------------------
 * 변경이력
 -----------------------------------------------------------------
 * 작성일          | 작성자   | 변경이력
 -----------------------------------------------------------------
   2023. 1. 21.   Charlotte  최초작성
 -----------------------------------------------------------------
 */
@Service
public class MenuService {

	@Autowired
	private MenuMapper menuMapper;

	public int menu_total(menu_vo menu) {
		return menuMapper.menu_total(menu);
	}

	public List<menu_vo> menu_list(menu_vo menu) {
		return menuMapper.menu_list(menu);
	}

	public int menu_insert(menu_vo menu) {
		return menuMapper.menu_insert(menu);
	}

	public menu_vo menu_view(menu_vo menu) {
		return menuMapper.menu_view(menu);
	}

	public menu_vo menu_reply(menu_vo menu) {
		return menuMapper.menu_reply(menu);
	}

	public int menu_reply_insert(menu_vo menu) {
		return menuMapper.menu_reply_insert(menu);
	}

	public int menu_reply_update(menu_vo menu) {
		return menuMapper.menu_reply_update(menu);
	}

	public int menu_max_originNo(menu_vo menu) {
		return menuMapper.menu_max_originNo(menu);
	}

	@Transactional
	public void menu_reply_transaction(menu_vo menu) {
		//menuMapper.menu_reply_update(menu);
		menuMapper.menu_reply_insert(menu);
	}

  public menu_vo menu_save(menu_vo menu) {
    menuMapper.menu_save(menu);
    return menu;
}


}
