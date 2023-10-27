package com.code.dbcourse1;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

@SpringBootApplication
@MapperScan("com.code.dbcourse1.mapper")
public class DbCourse1Application {

	// 验证码
	@Bean
	public DefaultKaptcha getDefaultKaptcha(){
		com.google.code.kaptcha.impl.DefaultKaptcha defaultKaptcha = new com.google.code.kaptcha.impl.DefaultKaptcha();
		Properties properties = new Properties();
		properties.put("kaptcha.border", "no");
		properties.put("kaptcha.textproducer.font.color", "black");
		properties.put("kaptcha.image.width", "150");
		properties.put("kaptcha.image.height", "40");
		properties.put("kaptcha.textproducer.font.size", "30");
		properties.put("kaptcha.session.key", "verifyCode");
		properties.put("kaptcha.textproducer.char.space", "5");
		Config config = new Config(properties);
		defaultKaptcha.setConfig(config);

		return defaultKaptcha;
	}

	/**
	 * 输出地址名
	 */
	public static void printHome() {
		Properties properties = new Properties();
		try {
			properties.load(DbCourse1Application.class.getResourceAsStream("/application.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String port = properties.getProperty("server.port");
		String contextPath = properties.getProperty("server.servlet.context-path");
		String adr = null;
		try {
			adr = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.printf("http://%s:%s%s\n", "localhost", port, contextPath);
		System.out.printf("http://%s:%s%s\n", "127.0.0.1", port, contextPath);
		System.out.printf("http://%s:%s%s\n", adr, port, contextPath);
	}

	public static void main(String[] args) {
		SpringApplication.run(DbCourse1Application.class, args);
		printHome();
	}

}
