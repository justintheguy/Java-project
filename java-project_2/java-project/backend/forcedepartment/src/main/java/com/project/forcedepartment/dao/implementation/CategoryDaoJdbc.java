package com.project.forcedepartment.dao.implementation;

import com.project.forcedepartment.dao.CategoryDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoJdbc implements CategoryDao {

    private DataSource dataSource;

    public CategoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<String> getAllProfession() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT\n" +
                    "    profession.id,\n" +
                    "    profession.profession_name\n" +
                    "FROM profession;";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<String> result = new ArrayList<>();
            while (rs.next()) {
                result.add(rs.getString(2));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all suppliers", e);
        }
    }

    @Override
    public List<String> getAllWorkObject() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT\n" +
                    "   work_object.id,\n" +
                    "    work_object.work_object\n" +
                    "FROM work_object;";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<String> result = new ArrayList<>();
            while (rs.next()) {
                result.add(rs.getString(2));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all suppliers", e);
        }
    }
}
