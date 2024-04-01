
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);

    }
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(("text/html;charset=utf-8"));
        //读取表单提交中的用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //当没有输入用户名或密码时，点击登录会刷新。
        if (password.isEmpty()) {
            request.setAttribute("msg", "密码不能为空");
            request.getRequestDispatcher("/login.html").forward(request, response);
            return;
        }
        if (username.isEmpty()) {
            request.setAttribute("msg", "用户名不能为空");
            request.getRequestDispatcher("/login.html").forward(request, response);
            return;
        }
        //从数据库读取用户信息
        SourceDao sourceDao=new SourceDao();
        User user=sourceDao.Query(username);
        //通过验证做的操作
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            //将用户信息存入session
            request.getSession().setAttribute("user",username);
            //调用IndexServlet类中的doGet方法
            IndexServlet indexServlet= new IndexServlet();
            indexServlet.doGet(request,response);
        }
        else {
            PrintWriter writer=response.getWriter();
            writer.print("<h1>用户名或密码错误，登录失败！</h1>");
            writer.print("<h1>请<a href='/login_war_exploded/login.html'>返回</a></h1>");
        }

    }
}