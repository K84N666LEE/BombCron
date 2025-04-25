/* DiabloIV > BoardMapper.java */
package com.src.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.src.dto.BoardVO;

/* -----------------------------------------------------------------
 * 제목명 : DiabloIV
 * 파일명 : BoardMapper.java
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
@Mapper
@SuppressWarnings("rawtypes")
public interface BoardMapper {

	public int           board_total         ( BoardVO bbs    );
	public List<BoardVO> board_list          ( BoardVO bbs    );
	public int           board_insert        ( BoardVO bbs    );
	public BoardVO       board_view          ( BoardVO bbs    );
	public int           board_reply_update  ( BoardVO bbs    );

	public BoardVO       board_reply         ( BoardVO bbs    );
	public int           board_max_originNo  ( BoardVO bbs    );
	public int           board_reply_insert  ( BoardVO bbs    );

	public List<Map>     board_list_ajax_map ( Map     params );
	public List<Map>     board_list_ajax_vo  ( BoardVO bbs    );

}
