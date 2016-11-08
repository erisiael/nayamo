package action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.STR_DAO;
import properties.FileService;
import vo.OKS;
import vo.STR;

public class AjaxSTRListAction extends ActionSupport implements SessionAware{
	
	private List<STR> str_list;
	private Map<String, Object> session;


	public String showSTRs() {
		STR_DAO dao = new STR_DAO();
		str_list = dao.selectMyListSTR((OKS) session.get("OKS"));
		for (STR str : str_list) {
			FileService fs = new FileService(str.getEnter_code(), ".svg");
			String svgdata = fs.loadFile();
			if (svgdata != null) {
				str.setSvgdata(svgdata);
			}
		}
		return SUCCESS;
	}
	
	public List<STR> getStr_list() {
		return str_list;
	}
	
	public void setStr_list(List<STR> str_list) {
		this.str_list = str_list;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
