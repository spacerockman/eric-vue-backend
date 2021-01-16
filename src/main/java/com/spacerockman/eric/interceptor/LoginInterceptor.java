package com.spacerockman.eric.interceptor;

import com.spacerockman.eric.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//配置拦截器
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception{
        HttpSession session = httpServletRequest.getSession();
        String contextPath = session.getServletContext().getContextPath();
        String[] requireAuthPath = new String[]{
                "index",
        };

        String uri = httpServletRequest.getRequestURI();

        uri = StringUtils.remove(uri, contextPath+"/");
        String page = uri;

        if(beginWith(page, requireAuthPath)){
            User user = (User) session.getAttribute("user");
            if(user==null){
                httpServletResponse.sendRedirect("login");
                return false;
            }
        }

        return true;
    }

    private boolean beginWith(String page, String[] requireAuthPages){
        boolean result = false;
        for (String requireAuthPage : requireAuthPages){
            if (StringUtils.startsWith(page, requireAuthPage)){
                result = true;
                break;
            }
        }
        return result;
    }
}
