import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LogoutServlet", urlPatterns = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");   // 解决乱码问题
        HttpSession session = request.getSession();    //获取session对象
        session.invalidate();                          //注销该request的所有session
        PrintWriter printWriter=response.getWriter();  //显示内容
        printWriter.println("<h1>感谢您使用星空平台！</h1>");
        printWriter.print("3秒跳转......");
        //三秒自动跳到首页
        response.setHeader("Refresh", "3;URL=/login_war_exploded/index.jsp");
    }
}