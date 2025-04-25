/* DiabloIV > BoardService.java */
package com.src.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.src.dto.UserDTO;

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
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public int user_insert(UserDTO userDto) {
		return userMapper.user_insert(userDto);
	}

}
