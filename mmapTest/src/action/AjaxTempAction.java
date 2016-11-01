package action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.OKS_DAO;
import vo.OKS;

public class AjaxTempAction extends ActionSupport implements SessionAware
{
	private Map<String, Object> session;
	private boolean dupChk = false; // 이메일 중복 함수를 위한 변수
	private OKS oks;
	
	public String emailChk() throws Exception // 이메일 중복 확인을 하는 함수
	{
		OKS_DAO dao = new OKS_DAO();
		OKS tmp = dao.selectOne(oks.getEmail());
		if(tmp != null)
		{
			session.put("email", tmp.getEmail());
			dupChk = false;
		}
		else
		{
			dupChk = true;
		}
		return SUCCESS;
	}

	//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public boolean isDupChk() {
		return dupChk;
	}

	public void setDupChk(boolean dupChk) {
		this.dupChk = dupChk;
	}

	public OKS getOks() {
		return oks;
	}

	public void setOks(OKS oks) {
		this.oks = oks;
	}
}
