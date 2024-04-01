
import java.sql.*;
public class MySQL {
    private static String url = "jdbc:mysql://localhost:3306/test3";  //url中的test3为数据库库名
    private static String user = "root";  //数据库登陆账号
    private static String password = "980517";  //数据库登陆密码
    private static Connection con = null;

    //获取连接
    public static Connection getConn() {
        try {
            // 1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2.获取连接
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

//测试数据库连接
    public static void main(String[] args) {
        System.out.println(getConn());
    }
}
