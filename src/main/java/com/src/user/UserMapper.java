/* DiabloIV > BoardMapper.java */
package com.src.user;

import org.apache.ibatis.annotations.Mapper;

import com.src.dto.UserDTO;

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
public interface UserMapper {

	public int user_insert(UserDTO userDto);

}
