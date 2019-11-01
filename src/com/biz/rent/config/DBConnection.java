package com.biz.rent.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBConnection {

	private static SqlSessionFactory sqlSessionFactory = null;
	static {
		String configFile = "com/biz/rent/config/mybatis-config.xml";
		InputStream is = null;
		
		try {
			is = Resources.getResourceAsStream(configFile);
			SqlSessionFactoryBuilder sessionBuilder
				= new SqlSessionFactoryBuilder();
			/*
			 * sqlSessionFactory를 null인가를 검사하는 이유
			 * multi thread 환경에서 singletone을 보장하기 위해서
			 */
			if(sqlSessionFactory == null) {
				sqlSessionFactory = sessionBuilder.build(is);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
}


/*
package com.biz.rent.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBConnection {
	
	/*
	 * SqlSession을 필요에 따라 
	 * 생성, 삭제등을 관리할 클래스
	 */
/*
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		
		String 
			configFile = "com/biz/rent/config/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(configFile);
			SqlSessionFactoryBuilder builder 
					= new SqlSessionFactoryBuilder();
			if(sqlSessionFactory == null) {
				sqlSessionFactory = builder.build(inputStream);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}


}
*/