package com.src.auto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.src.vo.auto_send;
import com.src.vo.auto_send_log;

@Service
public class AutoServiceImpl implements AutoService {
	@Autowired
	private AutoMapper autoMapper;

	public void auto_update(auto_send auto) {
		this.autoMapper.auto_update(auto);
	}

	public List<auto_send> auto_check(auto_send auto) {
		return this.autoMapper.auto_check(auto);
	}

	public int auto_total(auto_send auto) {
		return this.autoMapper.auto_total(auto);
	}

	public void auto_log_insert(auto_send_log logs) {
		this.autoMapper.auto_log_insert(logs);
	}
}
