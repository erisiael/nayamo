package dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfig 
{
	private static SqlSessionFactory sqlSessionFactory;
	
	static
	{
		setsqlSessionFactory();
	}
	
	private static void setsqlSessionFactory()
	{
		String res = "properties/mybatis-config.xml";
		try 
		{
			InputStream inputStream = Resources.getResourceAsStream(res);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory()
	{
		return sqlSessionFactory;
	}
}
