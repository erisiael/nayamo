package dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import vo.OKS;
import vo.SKA_LIST;

public class OKS_DAO 
{
	SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
	
	public OKS selectOne(OKS oks)
	{
		OKS o = sqlSession.selectOne("mapper.OKS_mapper.selectOne", oks);
		sqlSession.close();
		return o;
	}
	
	//백승훈
	public List<SKA_LIST> selectAllBoardList() 
	{
		List<SKA_LIST> list = sqlSession.selectList("mapper.OKS_mapper.selectAllBoardList");
		return list;
	}

	public int insertOKS(OKS oks) 
	{
		int res = sqlSession.insert("mapper.OKS_mapper.insertOKS", oks);
		sqlSession.commit();
		return res;
	}

	public OKS selectOne(String email)  // 이메일 중복 체크 함수 
	{
		OKS o = sqlSession.selectOne("mapper.OKS_mapper.chkDuplication", email);
		return o;
	}
	//끝
	
	
	/*김동현 작업 시작*/
	public int OKS_update(OKS noks){
		int result = 0;
		System.out.println("dao : " + noks.getEmail());
		result = sqlSession.update("mapper.OKS_mapper.oks_update", noks);
		sqlSession.commit();
		return result;
	}
	
	public OKS OKS_find(String email){
		OKS result = null;
		result = sqlSession.selectOne("mapper.OKS_mapper.oks_find", email);
		return result;
	}
	/*김동현 작업 끝*/
}
