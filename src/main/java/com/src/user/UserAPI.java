/* DiabloIV > BoardController.java */
package com.src.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.src.dto.UserDTO;

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


@Tag(name = "User API", description = "사용자 API를 관리합니다.")
@Slf4j
@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserAPI {

	@Autowired
	private UserService userService;

	@PostMapping("/ins")
	@Operation(summary = "사용자 정보 입력", description = "사용자 이름과 이메일을 입력받아 처리합니다.")
	public int user_insert(
			UserDTO userDto,
		@Parameter(description = "사용자 이름", required = true) @RequestParam String name,
		@Parameter(description = "사용자 이메일", required = true) @RequestParam String email) {

		log.debug("사용자 이름: %s, 이메일: %s", name, email);
		
		userDto.setName(name);
		userDto.setEmail(email);
		int cnt = userService.user_insert(userDto);
		return cnt;

	}

}
