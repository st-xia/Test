package demo1;

import org.junit.Test;

import java.sql.*;

public class Demo1 {
    @Test
    public void fun1() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/p1?serverTimezone=UTC&useSSL=false";
        String nam="root";
        String pwd="96050725xia";

        Connection connection=DriverManager.getConnection(url,nam,pwd);
        System.out.println(connection);
        Statement s=connection.createStatement();
        String strTemp="create table temp(num int,nam varchar(10))";
        String strTemp1="insert into temp values(1,'张三')";
        String strTemp2="select * from temp";
        boolean b=s.execute(strTemp2);
        ResultSet set=s.getResultSet();

        PreparedStatement pstmt=connection.prepareStatement("insert into  temp values (?,?)//");
        pstmt.execute();
        pstmt.getResultSet();
    }
}
