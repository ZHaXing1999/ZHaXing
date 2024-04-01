
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SourceDao {
    //从数据库读取用户信息
    public User Query(String name) {
        User user=new User();
        try {
            Connection connection =MySQL.getConn();
            String sql = "select username,password from user where username='"+name+"'";
            PreparedStatement pstmt =  connection.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
