package action;

import com.opensymphony.xwork2.ActionSupport;

import properties.Search_Keyword;

public class AjaxKeywordAction extends ActionSupport {

	private String keyword;
	private String[] result;
	
	public String searchKeyword() {
		
		Search_Keyword sk = new Search_Keyword();
		result = sk.Search("IT", keyword);
		
		return SUCCESS;
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String[] getResult() {
		return result;
	}

	public void setResult(String[] result) {
		this.result = result;
	}

	public static void main(String[] args) {
		AjaxKeywordAction aka = new AjaxKeywordAction();
		aka.searchKeyword();
	}
	
}
