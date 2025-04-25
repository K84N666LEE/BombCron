package com.src.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Data
@Getter
@Setter
@Alias("UserDTO")
public class UserDTO {

	@Schema(description = "일련번호", example = "1") private int    id        ;
	@Schema(description = "이름", example = "홍길동") private String name       ;
	@Schema(description = "이메일", example = "test@gmail.com") private String email     ;

	@ArraySchema(
	    schema = @Schema(description = "사용자목록"),
	    minItems = 1,
	    uniqueItems = true
	)
	private List<UserDTO> userDTO;
	
}
