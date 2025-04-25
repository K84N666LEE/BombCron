/* DiabloIV > BoardMapper.java */
package com.src.menu;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.src.dto.menu_vo;

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
public interface MenuMapper {

	public int           menu_total         ( menu_vo menu );
	public List<menu_vo> menu_list          ( menu_vo menu );
	public int           menu_insert        ( menu_vo menu );
	public menu_vo       menu_view          ( menu_vo menu );
	public int           menu_reply_update  ( menu_vo menu );

	public menu_vo       menu_reply         ( menu_vo menu );
	public int           menu_max_originNo  ( menu_vo menu );
	public int           menu_reply_insert  ( menu_vo menu );
	public menu_vo       menu_save          ( menu_vo menu );

}
