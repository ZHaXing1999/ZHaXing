import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;


@WebServlet(name = "IndexServlet", urlPatterns = "/IndexServlet")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // 解决乱码问题
        response.setContentType("text/html;charset=utf-8");
        // 创建或者获取保存用户信息的Session对象
        HttpSession session = request.getSession();
        Object user=session.getAttribute("user");
        if (user == null) {
            response.getWriter().print("<h1>您还没有登录，请<a href='/login_war_exploded/login.html'>登录</a></h1>");

        } else {
            //显示登录后的用户信息
            response.getWriter().print("<h1>您已登录，欢迎进入星空旅行平台，" + user + "！</h1>");
            response.getWriter().print("<a href='/login_war_exploded/LogoutServlet'>退出</a>");
            // 创建Cookie存放Session的标识号
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            //设置cookie存活时间:30分钟
            cookie.setMaxAge(60 * 30);
            //TODO 注意：在设置cookie路径时最好不要写死，可通过request.getContextpath()来获取当前项目的根目录来设置。
            cookie.setPath(request.getContextPath() + "/login_war_exploded");
            //向客户端发送一个cookie对象
            response.addCookie(cookie);
        }
    }
}