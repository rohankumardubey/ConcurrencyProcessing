package com.app.dao;

import com.app.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    public int saveUser(User user) throws SQLException, ClassNotFoundException {
        int rows=0;
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(" insert into user values(?,?,?)");
        statement.setInt(1,user.getId());
        statement.setString(2,user.getName());
        statement.setString(3,user.getEmailAddress());
        rows=statement.executeUpdate();
        return rows;
    }
}
