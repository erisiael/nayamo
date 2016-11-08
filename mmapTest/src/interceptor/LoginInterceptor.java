package interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		if (arg0.getInvocationContext().getSession().get("OKS") == null) {
			arg0.getInvocationContext().getValueStack().setParameter("errorMessage", "로그인이 필요합니다.");
			return Action.LOGIN;
		}
		return arg0.invoke();
	}

}
