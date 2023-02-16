/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Role;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author kumar
 */
public class RoleService {

    public static RoleService roleService = null;
    private static final Logger logger = Logger.getLogger(RoleService.class);

    public static RoleService getInstance() {
        if (roleService == null) {
            return new RoleService();
        } else {
            return roleService;
        }
    }

    public static ArrayList getAllRoles() {
        ArrayList roleList = new ArrayList();
        try {

            Connection con = JDBCConnectionManager.getMySQLConnection();

            String sql = "Select * from roles";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Role role = new Role();

                role.setRoleId(rs.getInt("roleId"));
                role.setRoleName(rs.getString("roleName"));

                roleList.add(role);

            }

        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

        return roleList;

    }
}
