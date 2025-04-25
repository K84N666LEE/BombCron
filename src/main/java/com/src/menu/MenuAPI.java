/* DiabloIV > BoardController.java */
package com.src.menu;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.src.dto.menu_vo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RestController
@RequestMapping("/api/menu")
@CrossOrigin
public class MenuAPI {

	@Autowired
	private MenuService menuService;

  @Operation(summary = "메뉴목록", description = "메뉴 목록 전체 값을 json 으로.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Successful operation"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping
  public List<menu_vo> menu_list(
			ModelMap                model
			, HttpServletResponse response
			, menu_vo                 menu
			) throws IOException
	{
      return menuService.menu_list(menu);
  }


  @PostMapping
  public menu_vo createmenu_vo(@RequestBody menu_vo menu) {
      return menuService.menu_save(menu);
  }
  
/*
  @GetMapping("/{id}")
  public List<menu_vo> menu_view(@PathVariable Long id) {
      return productRepository.findById(id)
              .map(ResponseEntity::ok)
              .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public menu_vo createmenu_vo(@RequestBody menu_vo product) {
      return productRepository.save(product);
  }

  @PutMapping("/{id}")
  public ResponseEntity<menu_vo> updatemenu_vo(@PathVariable Long id, @RequestBody menu_vo updatedmenu_vo) {
      return productRepository.findById(id)
              .map(existingmenu_vo -> {
                  existingmenu_vo.setName(updatedmenu_vo.getName());
                  existingmenu_vo.setPrice(updatedmenu_vo.getPrice());
                  productRepository.save(existingmenu_vo);
                  return ResponseEntity.ok(existingmenu_vo);
              }).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletemenu_vo(@PathVariable Long id) {
      return productRepository.findById(id)
              .map(product -> {
                  productRepository.delete(product);
                  return ResponseEntity.noContent().build();
              }).orElse(ResponseEntity.notFound().build());
  }
*/
}
