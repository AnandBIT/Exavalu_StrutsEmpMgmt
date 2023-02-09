/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.User;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class AuthService {

    public static AuthService authService = null;

    private AuthService() {
    }

    public static AuthService getInstance() {
        if (authService == null) {
            return new AuthService();
        } else {
            return authService;
        }
    }

    public boolean doLogin(User user) {
        boolean success = false;

        String sql = "Select * from users where email=? and password=?";

        try {
            Connection con = JDBCConnectionManager.getMySQLConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                success = true;
            }

            // NOTE - Getting error here (java.sql.SQLNonTransientConnectionException: No operations allowed after connection closed.)
//            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return success;
    }

    public boolean doSignUp(User user) {
        boolean result = false;

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String password = user.getPassword();
        String countryCode = user.getCountryCode();
        String stateCode = user.getStateCode();
        String districtCode = user.getDistrictCode();

        try {
            if (firstName != null && lastName != null && email != null && password != null && !"0".equals(countryCode) && !"0".equals(stateCode) && !"0".equals(districtCode)) {
                Connection con = JDBCConnectionManager.getMySQLConnection();
                String sql = "INSERT INTO users (firstName, lastName, email, password, countryCode, stateCode, districtCode) VALUES (?, ?, ?, ?,?,?,?);";
                PreparedStatement preparedStatement = con.prepareStatement(sql);

                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, password);
                preparedStatement.setString(5, countryCode);
                preparedStatement.setString(6, stateCode);
                preparedStatement.setString(7, districtCode);
                preparedStatement.executeUpdate();
                result = true;
//                con.close(); // NOTE - Getting error here (java.sql.SQLNonTransientConnectionException: No operations allowed after connection closed.)
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

}
