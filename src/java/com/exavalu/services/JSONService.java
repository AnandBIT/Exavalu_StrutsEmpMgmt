/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.JSONData;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author kumar
 */
public class JSONService {

    private static final Logger logger = Logger.getLogger(JSONService.class);

    public static boolean sendDataToDB(List<JSONData> jsonData) {
        boolean result = false;

        try {
            Connection con = JDBCConnectionManager.getMySQLConnection();
            String sql = "INSERT INTO posts (id, title, body) VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            int totalRows = 0;

            for (JSONData post : jsonData) {
                preparedStatement.setInt(1, post.getId());
                preparedStatement.setString(2, post.getTitle());
                preparedStatement.setString(3, post.getBody());

                int row = preparedStatement.executeUpdate();
                if (row == 1) {
                    totalRows++;
                }
            }
            if (totalRows > 0) {
                System.out.println("Total rows inserted = " + totalRows);
                result = true;
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return result;
    }
}
