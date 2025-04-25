package com.src.cmm;


import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/* -----------------------------------------------------------------
 * 제목명 : Bomb Scheduler
 * 파일명 : CmmConfigure
 * 생성일 : 2023. 1. 22.
 * 작성자 : Charlotte
 * 설명  : ENC @Bean 설정
 -----------------------------------------------------------------
 * 변경이력
 -----------------------------------------------------------------
 * 작성일          | 작성자   | 변경이력
 -----------------------------------------------------------------
   2023. 1. 22.   Charlotte  최초작성
 -----------------------------------------------------------------
 */
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@Configuration
@EnableEncryptableProperties
@PropertySource("classpath:/secure.properties")
public class CmmConfigure implements WebMvcConfigurer {

	/**
	 * ENC Configure
	 * @param env
	 * @return
	 * StringEncryptor
	 *
	 */
	@Bean("jasyptStringEncryptor")
	StringEncryptor stringEncryptor(Environment env) {

		// properties 파일 호출
		String algorithm = env.getProperty("secure.algorithm");
		String key       = env.getProperty("secure.key");

		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(key); // secure key
		config.setAlgorithm(algorithm); // secure method
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		return encryptor;
	}

}