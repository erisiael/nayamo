package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import vo.LETTER;
import vo.OKS;
import vo.SKA_LIST;

public class OKS_DAO 
{
	//SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
	
	public OKS selectOne(OKS oks)
	{
		SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
		OKS o = sqlSession.selectOne("mapper.OKS_mapper.selectOne", oks);
		if(sqlSession != null) sqlSession.close();
		return o;
	}
	
	//백승훈
	public List<SKA_LIST> selectAllBoardList() 
	{
		SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
		List<SKA_LIST> list = sqlSession.selectList("mapper.OKS_mapper.selectAllBoardList");
		if(sqlSession != null) sqlSession.close();
		return list;
	}

	public int insertOKS(OKS oks) 
	{
		SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
		int res = sqlSession.insert("mapper.OKS_mapper.insertOKS", oks);
		sqlSession.commit();
		if(sqlSession != null) sqlSession.close();
		return res;
	}

	public OKS selectOne(String email)  // 이메일 중복 체크 함수 
	{
		SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
		OKS o = sqlSession.selectOne("mapper.OKS_mapper.chkDuplication", email);
		if(sqlSession != null) sqlSession.close();
		return o;
	}
	//끝
	
	
	/*김동현 작업 시작*/
	public int OKS_update(OKS noks){
		SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
		int result = 0;
		System.out.println("dao : " + noks.getEmail());
		result = sqlSession.update("mapper.OKS_mapper.oks_update", noks);
		sqlSession.commit();
		if(sqlSession != null) sqlSession.close();
		return result;
	}
	
	public OKS OKS_find(String email){
		SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
		OKS result = null;
		result = sqlSession.selectOne("mapper.OKS_mapper.oks_find", email);
		if(sqlSession != null) sqlSession.close();
		return result;
	}
	
	public List<LETTER> login_letter(String nick){
		SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
		List<LETTER> result = null;
		result = sqlSession.selectList("mapper.OKS_mapper.login_letter", nick);
		if(sqlSession != null) sqlSession.close();
		return result;
	}
	
	public int writeLetter(LETTER letter){
		SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
		int result = 0;
		result = sqlSession.insert("mapper.OKS_mapper.writeLetter", letter);
		sqlSession.commit();
		if(sqlSession != null) sqlSession.close();
		return result;
	}
	
	public LETTER readLetterForm(int no){
		SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
		LETTER result = null;
		result = sqlSession.selectOne("mapper.OKS_mapper.readLetterForm", no);
		if(sqlSession != null) sqlSession.close();
		return result;
	}
	
	public void updateLetter(int no){
		SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
		sqlSession.update("mapper.OKS_mapper.updateLetter", no);
		sqlSession.commit();
		if(sqlSession != null) sqlSession.close();
	}
	/*김동현 작업 끝*/

	public int getTotal(String searchField, String searchText)
	{
		SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
		int totalRecordCount = 0;
		Map<String, String> map = new HashMap<String, String>();

		map.put("searchField", searchField);
		map.put("searchText", searchText);

		try {
			totalRecordCount = sqlSession.selectOne("mapper.OKS_mapper.getTotal", map);
			// sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
		return totalRecordCount;
	}
	
	public int getTotal2(String board_List_Form, OKS oks) 
	{
		SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
		int totalRecordCount = 0;
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("board_List_Form", board_List_Form);
		map.put("O_n", Integer.toString(oks.getOKS_no())); // 내가 직접 쓴 경매를 가지고 오기 위해, 로그인 한 oks 객체를 parameter로 받아 OKS_no를 가져온다
		
		try {
			totalRecordCount = sqlSession.selectOne("mapper.OKS_mapper.getTotal2", map);
			// sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
		return totalRecordCount;
	}
	
	public int getTotal3(String board_List_Form, OKS oks) 
	{
		SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
		int totalRecordCount = 0;
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("board_List_Form", board_List_Form);
		map.put("O_n", Integer.toString(oks.getOKS_no())); // 내가 직접 쓴 경매를 가지고 오기 위해, 로그인 한 oks 객체를 parameter로 받아 OKS_no를 가져온다
		
		try {
			totalRecordCount = sqlSession.selectOne("mapper.OKS_mapper.getTotal3", map);
			// sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
		return totalRecordCount;
	}
	
	 public List<SKA_LIST> listBoard(String searchField, String searchText, int startRecord, int countPerPage)
	 {
		  SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
	      List<SKA_LIST> boardlist = null;
	      
	      //쿼리로 전달할 parameter들
	      Map<String, String> map = new HashMap<String, String>();
	      map.put("searchField", searchField);
	      map.put("searchText", searchText);
	      
	      //결과 레코드 중 읽을 위치와 개수
	      RowBounds bound = new RowBounds(startRecord, countPerPage);
	      
	      boardlist = sqlSession.selectList("mapper.OKS_mapper.listBoard", map, bound);
	      //sqlSession.commit();
	      if(sqlSession != null)
	      {
	    	  sqlSession.close();
	      }
	        
	      return boardlist;
	}
	 
	public List<SKA_LIST> listBoard2(String board_List_Form, OKS oks, int startRecord, int countPerPage) 
	{
		SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
	    List<SKA_LIST> boardlist = null;
	    
	    //쿼리로 전달할 parameter들
	    Map<String, String> map = new HashMap<String, String>();
	    map.put("board_List_Form", board_List_Form);
		map.put("O_n", Integer.toString(oks.getOKS_no()));
		
		//결과 레코드 중 읽을 위치와 개수
	    RowBounds bound = new RowBounds(startRecord, countPerPage);
	    
	    boardlist = sqlSession.selectList("mapper.OKS_mapper.listBoard2", map, bound);
	    if(sqlSession != null)
	    {
	      sqlSession.close();
	    }
	    return boardlist;
	}
	
	public List<SKA_LIST> listBoard3(String board_List_Form, OKS oks, int startRecord, int countPerPage) 
	{
		SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
	    List<SKA_LIST> boardlist = null;
	    
	    //쿼리로 전달할 parameter들
	    Map<String, String> map = new HashMap<String, String>();
	    map.put("board_List_Form", board_List_Form);
		map.put("O_n", Integer.toString(oks.getOKS_no()));
		
		//결과 레코드 중 읽을 위치와 개수
	    RowBounds bound = new RowBounds(startRecord, countPerPage);
	    
	    boardlist = sqlSession.selectList("mapper.OKS_mapper.listBoard3", map, bound);
	    if(sqlSession != null)
	    {
	      sqlSession.close();
	    }
	    return boardlist;
	}

	public List<SKA_LIST> Board_list(String board_List_Form, int oks_no)
	{
		SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
	    List<SKA_LIST> boardlist = null;
	    Map<String, Integer> map = new HashMap<String, Integer>();
	   
	    boardlist = sqlSession.selectList("mapper.OKS_mapper.Board_list", map);
	    if(sqlSession != null)
	    {
	    	  sqlSession.close();
	    }
	    return boardlist;
	}
	
	public List<String> searchDB(String category) 
	{
		SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
		List<String> list = null;
		
		list = sqlSession.selectList("mapper.OKS_mapper.searchDB", category);
		if(sqlSession != null)
		{
			sqlSession.close();
		}
		return list;
	}


	
}
