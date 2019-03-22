package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {
    public static User get(Long id) throws ClassNotFoundException, SQLException {
        //DB 가 뭐야? mysql
        //어딨어? 알려주께..
        //드라이버 로드
        //커넥션 맺고
        //SQL 쿼리 만들고
        //쿼리 실행하고
        //실행된 쿼리를 오브젝트에 매핑하고
        //자원 해지

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.0.54/jeju?serverTimezone=UTC"
                , "jeju", "jejupw");

        PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id=?");
        preparedStatement.setLong(1,id);

        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        resultSet.close();
        preparedStatement.close();
        connection.close();
        //리턴


        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.0.54/jeju?serverTimezone=UTC"
                , "jeju", "jejupw");

        PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo(name, password) values (?, ?)");
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("select last_insert_id()");
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        Long id = resultSet.getLong(1);

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return id;
    }
}
