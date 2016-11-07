package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class ManageDataDAO {
	SqlSession session = MybatisConfig.getSqlSessionFactory().openSession();
	
	public void storedData(String data){
		SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
	      Map<String, String> map = new HashMap<String, String>(); // String 객체에 category의 getter가 없기 때문에 map으로 감싸고 보내야한다
	      System.out.println("dao1");
	      String[] div = data.split(",");
	      System.out.println("dao2");
	      map.put("data", div[0]+","+div[1]);
	      map.put("menu", div[2]);
	      System.out.println("dao3");
	      System.out.println(div[2]);
	      sqlSession.insert("mapper.ManageData_mapper.relation", map);
	      System.out.println("dao4");
	      if(sqlSession != null)
	      {
	         sqlSession.close();
	      }
	      sqlSession.commit();
	}
	
	
	
	

}
