package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.STR;
import vo.OKS;

public class STR_DAO {

	public boolean insertSTR(STR str) {
		boolean result = false;
		
		SqlSession session = MybatisConfig.getSqlSessionFactory().openSession();
		int row = session.insert("mapper.STR_mapper.insert", str);
		session.commit();
		
		result = (row > 0);

		return result;
	}
	
	public boolean updateSTR(STR str) {
		boolean result = false;
		
		SqlSession session = MybatisConfig.getSqlSessionFactory().openSession();
		int row = session.update("mapper.STR_mapper.update", str);
		session.commit();
		
		result = row > 0;
		
		return result;
	}
	
	public STR selectOneSTR(STR str) {
		STR result = null;
		
		SqlSession session = MybatisConfig.getSqlSessionFactory().openSession();
		result = session.selectOne("mapper.STR_mapper.selectOne", str);
		
		return result;
	}
	
	public List<STR> selectListSTR() {
		List<STR> result = null;
		
		SqlSession session = MybatisConfig.getSqlSessionFactory().openSession();
		result = session.selectList("mapper.STR_mapper.selectAll");
		
		return result;
	}
	
	public List<STR> selectMyListSTR(OKS oks) {
		List<STR> result = null;
		SqlSession session = MybatisConfig.getSqlSessionFactory().openSession();
		result = session.selectList("mapper.STR_mapper.selectMyList", oks);
		
		return result;
	}
	public List<String> searchDB(String category) 
	   {
	      SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
	      List<String> list = null;
	      Map<String, String> map = new HashMap<String, String>();
	      
	      list = sqlSession.selectList("mapper.OKS_mapper.searchDB", map);
	      if(sqlSession != null)
	      {
	         sqlSession.close();
	      }
	      return list;
	   }
	
}
