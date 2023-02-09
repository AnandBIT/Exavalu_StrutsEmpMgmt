/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Country;
import com.exavalu.models.District;
import com.exavalu.models.State;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kumar
 */
public class GeoMapService {

    public static GeoMapService geomapService = null;

    public static GeoMapService getInstance() {
        if (geomapService == null) {
            return new GeoMapService();
        } else {
            return geomapService;
        }
    }

    public static ArrayList getAllCountries() {
        ArrayList countryList = new ArrayList();

        try {
            Connection con = JDBCConnectionManager.getMySQLConnection();
            String sql = "Select * from countries;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Country country = new Country();
                country.setCountryCode(rs.getString("countryCode"));
                country.setCountryName(rs.getString("countryName"));
                countryList.add(country);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("Number of countries -> " + countryList.size());
        return countryList;
    }

    public static ArrayList getStatesByCountryCode(String countryCode) {
        ArrayList stateList = new ArrayList();

        try {
            Connection con = JDBCConnectionManager.getMySQLConnection();
            String sql = "Select * from states where countryCode=?;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, countryCode);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                State state = new State();
                state.setStateCode(rs.getString("stateCode"));
                state.setStateName(rs.getString("stateName"));
                state.setCountryCode(rs.getString("countryCode"));

                stateList.add(state);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("Number of states -> " + stateList.size());
        return stateList;
    }

    public static ArrayList getDistrictsByStateCode(String stateCode) {
        ArrayList districtList = new ArrayList();

        try {
            Connection con = JDBCConnectionManager.getMySQLConnection();
            String sql = "Select * from districts where stateCode=?;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, stateCode);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                District district = new District();
                district.setDistrictCode(rs.getString("districtCode"));
                district.setDistrictName(rs.getString("districtName"));
                district.setStateCode(rs.getString("stateCode"));

                districtList.add(district);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("Number of districts -> " + districtList.size());
        return districtList;
    }
}
