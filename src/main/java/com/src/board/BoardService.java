/* DiabloIV > BoardService.java */
package com.src.board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.src.dto.BoardVO;

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
@Transactional
@SuppressWarnings("rawtypes")
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;

	public int board_total(BoardVO bbs) {
		return boardMapper.board_total(bbs);
	}

	public List<BoardVO> board_list(BoardVO bbs) {
		return boardMapper.board_list(bbs);
	}

	public List<Map> board_list_ajax_map(Map params) {
		return boardMapper.board_list_ajax_map(params);
	}

	public List<Map> board_list_ajax_vo(BoardVO bbs) {
		return boardMapper.board_list_ajax_vo(bbs);
	}

	public int board_insert(BoardVO bbs) {
		return boardMapper.board_insert(bbs);
	}

	public BoardVO board_view(BoardVO bbs) {
		return boardMapper.board_view(bbs);
	}

	public BoardVO board_reply(BoardVO bbs) {
		return boardMapper.board_reply(bbs);
	}

	public int board_reply_insert(BoardVO bbs) {
		return boardMapper.board_reply_insert(bbs);
	}

	public int board_reply_update(BoardVO bbs) {
		return boardMapper.board_reply_update(bbs);
	}

	public int board_max_originNo(BoardVO bbs) {
		return boardMapper.board_max_originNo(bbs);
	}

	public void board_reply_transaction(BoardVO bbs) {
		boardMapper.board_reply_update(bbs);
		boardMapper.board_reply_insert(bbs);
	}

}
