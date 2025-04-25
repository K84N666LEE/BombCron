package com.src.auto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.src.vo.auto_send;
import com.src.vo.auto_send_log;

@Mapper
public interface AutoMapper {
	int auto_total(auto_send paramauto_send);

	List<auto_send> auto_check(auto_send paramauto_send);

	void auto_update(auto_send paramauto_send);

	void auto_log_insert(auto_send_log paramauto_send_log);
}
