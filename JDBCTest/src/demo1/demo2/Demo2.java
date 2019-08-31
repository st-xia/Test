package demo1.demo2;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import javax.sql.rowset.serial.SerialBlob;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

public class Demo2 {
    @Test
    public void fun1()throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/p1?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=utf8&autoReconnect=true&rewriteBatchedStatements=TRUE";
        String nam="root";
        String pwd="96050725xia";

        Connection connection=DriverManager.getConnection(url,nam,pwd);
        String sql="alter table temp add(data mediumtext)";
        String sql1="insert into temp values(?,?,?)";
        String sql2="select * from temp";
        PreparedStatement pstmt=connection.prepareStatement(sql1);
        pstmt.setInt(1,3);
        pstmt.setString(2,"北京");
        byte[] bytes=IOUtils.toByteArray("D:/北京欢迎你.mp3");
        Blob blob=new SerialBlob(bytes);
        pstmt.setBlob(3,blob);
        pstmt.execute();
    }

    @Test
    public void fun2() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/p1?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=utf8&autoReconnect=true&rewriteBatchedStatements=TRUE";
        String nam="root";
        String pwd="96050725xia";

        Connection connection=DriverManager.getConnection(url,nam,pwd);
        String sql="select * from temp";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        ResultSet set=pstmt.executeQuery();
        if (set.next()) {
            Blob blob=set.getBlob("data");
            InputStream in= blob.getBinaryStream();
            OutputStream out=new FileOutputStream("D:/bjhyn.mp3");
            IOUtils.copy(in,out);
            System.out.println(set.getInt(1)+set.getString(2)+set.getByte(3));
        }
    }
}
