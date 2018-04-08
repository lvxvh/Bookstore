package util;

import model.mysql.User;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by lxh on 2017/7/15.
 */
public class filter extends HttpServlet implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(true);

        String url = request.getRequestURI();
        User user = (User)session.getAttribute("user");

        if ((user == null || user.getRole().equals("user")) &&
                (url.contains("Manage") || url.contains("allBooksPro") ||
                   url.contains("allOrderPro") || url.contains("allCategories")
                  || url.contains("allUserPro"))) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
        if(user != null && (url.contains("login") || url.contains("register"))){
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig arg0) throws ServletException {
    }

}
