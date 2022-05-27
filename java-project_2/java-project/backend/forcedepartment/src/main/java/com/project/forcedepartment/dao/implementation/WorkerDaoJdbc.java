package com.project.forcedepartment.dao.implementation;

import com.project.forcedepartment.dao.WorkerDao;
import com.project.forcedepartment.model.Worker;
import com.project.forcedepartment.model.util.UserTypes;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class WorkerDaoJdbc implements WorkerDao {

    private DataSource dataSource;
    private String workerText = String.valueOf(UserTypes.WORKER);

    public WorkerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static List<String> arrayAggConverter(String arrayAggString) {
        StringBuilder arrayAgg = new StringBuilder(arrayAggString);
        arrayAgg.deleteCharAt(arrayAgg.length() - 1);
        arrayAgg.deleteCharAt(0);
        String planeArray = arrayAgg.toString();
        List<String> listOfProfession = Arrays.asList(planeArray.split(","));
        List<String> newListOfProfession = new ArrayList<>();
        for (String profession : listOfProfession) {
            profession = profession.replace("\"", "");
            newListOfProfession.add(profession);
        }
        return newListOfProfession;
    }

    private List<Worker> getWorkers(PreparedStatement st) throws SQLException {
        ResultSet rs = st.executeQuery();
        List<Worker> result = new ArrayList<>();
        while (rs.next()) {
            List<String> listOfProfession = arrayAggConverter(rs.getString(12));
            Worker worker = new Worker(rs.getInt(1),rs.getString(2), rs.getString(3),
                    rs.getDate(4), rs.getDate(5), rs.getString(6), rs.getString(7),
                    rs.getString(8), rs.getString(9), listOfProfession, rs.getDouble(11));
            result.add(worker);
        }
        return result;
    }

    @Override
    public List<Worker> getAllByRating() {
        try (Connection conn = dataSource.getConnection()) {
        String sql = "SELECT\n" +
                "   website_user.id,\n" +
                "   website_user.first_name,\n" +
                "   website_user.last_name,\n" +
                "   website_user.registration_date,\n" +
                "   website_user.birth_date,\n" +
                "   website_user.is_admin,\n" +
                "   website_user.group_name,\n" +
                "   website_user.email,\n" +
                "   worker.description,\n" +
                "    worker.phone_number,\n" +
                "    worker.rate,\n" +
                "    ARRAY_AGG(profession.profession_name)\n" +
                "FROM worker\n" +
                "   FULL JOIN website_user ON worker.user_id = website_user.id\n" +
                "   FULL JOIN worker_experience ON website_user.id = worker_experience.worker_id\n" +
                "   FULL JOIN profession ON profession.id = worker_experience.profession_id\n" +
                "WHERE website_user.group_name = ?\n" +
                "GROUP BY website_user.id,website_user.first_name, website_user.last_name, website_user.registration_date, website_user.birth_date, website_user.is_admin, website_user.group_name, website_user.email, worker.description, worker.phone_number, worker.rate\n" +
                "ORDER BY worker.rate DESC;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, workerText);
            ResultSet rs = st.executeQuery();
            List<Worker> result = new ArrayList<>();
            while (rs.next()) {
                List<String> listOfProfession = arrayAggConverter(rs.getString(12));
                Worker worker = new Worker(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getDate(4),
                        rs.getDate(5) ,rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), listOfProfession, rs.getDouble(11));
                result.add(worker);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all suppliers", e);
        }
    }

    @Override
    public List<Worker> getAllByProfession(String profession) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT\n" +
                    "   website_user.id,\n" +
                    "   website_user.first_name,\n" +
                    "   website_user.last_name,\n" +
                    "   website_user.registration_date,\n" +
                    "   website_user.birth_date,\n" +
                    "   website_user.is_admin,\n" +
                    "   website_user.group_name,\n" +
                    "   website_user.email,\n" +
                    "   worker.description,\n" +
                    "    worker.phone_number,\n" +
                    "    worker.rate,\n" +
                    "    ARRAY_AGG(profession.profession_name)\n" +
                    "FROM worker\n" +
                    "   FULL JOIN website_user ON worker.user_id = website_user.id\n" +
                    "   FULL JOIN worker_experience ON website_user.id = worker_experience.worker_id\n" +
                    "   FULL JOIN profession ON profession.id = worker_experience.profession_id\n" +
                    "WHERE website_user.group_name = ? AND profession.profession_name = ?\n" +
                    "GROUP BY website_user.id,website_user.first_name, website_user.last_name, website_user.registration_date, website_user.birth_date, website_user.is_admin, website_user.group_name, website_user.email, worker.description, worker.phone_number, worker.rate;\n";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, workerText);
            st.setString(2, profession);
            return getWorkers(st);
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all suppliers", e);
        }
    }

    @Override
    public List<Worker> getAllByWorkObject(String workObject) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT\n" +
                    "   website_user.id,\n" +
                    "   website_user.first_name,\n" +
                    "   website_user.last_name,\n" +
                    "   website_user.registration_date,\n" +
                    "   website_user.birth_date,\n" +
                    "   website_user.is_admin,\n" +
                    "   website_user.group_name,\n" +
                    "   website_user.email,\n" +
                    "   worker.description,\n" +
                    "    worker.phone_number,\n" +
                    "    worker.rate,\n" +
                    "    ARRAY_AGG(profession.profession_name)\n" +
                    "FROM worker\n" +
                    "   FULL JOIN website_user ON worker.user_id = website_user.id\n" +
                    "   FULL JOIN worker_experience ON website_user.id = worker_experience.worker_id\n" +
                    "   FULL JOIN profession ON profession.id = worker_experience.profession_id\n" +
                    "   FULL JOIN work_requirement ON profession.id = work_requirement.profession_id\n" +
                    "   FULL JOIN work_object ON work_object.id = work_requirement.work_object_id\n" +
                    "WHERE website_user.group_name = ? AND work_object.work_object = ?\n" +
                    "GROUP BY website_user.id,website_user.first_name, website_user.last_name, website_user.registration_date, website_user.birth_date, website_user.is_admin, website_user.group_name, website_user.email, worker.description, worker.phone_number, worker.rate;\n";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, workerText);
            st.setString(2, workObject);
            return getWorkers(st);
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all suppliers", e);
        }
    }

    @Override
    public List<Worker> getAllByName(String name) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT\n" +
                    "   website_user.id,\n" +
                    "   website_user.first_name,\n" +
                    "   website_user.last_name,\n" +
                    "   website_user.registration_date,\n" +
                    "   website_user.birth_date,\n" +
                    "   website_user.is_admin,\n" +
                    "   website_user.group_name,\n" +
                    "   website_user.email,\n" +
                    "   worker.description,\n" +
                    "    worker.phone_number,\n" +
                    "    worker.rate,\n" +
                    "    ARRAY_AGG(profession.profession_name)\n" +
                    "FROM worker\n" +
                    "   FULL JOIN website_user ON worker.user_id = website_user.id\n" +
                    "   FULL JOIN worker_experience ON website_user.id = worker_experience.worker_id\n" +
                    "   FULL JOIN profession ON profession.id = worker_experience.profession_id\n" +
                    "WHERE website_user.group_name = ? AND  LOWER(website_user.first_name) LIKE LOWER(?) OR LOWER(website_user.last_name) LIKE LOWER(?)\n" +
                    "GROUP BY website_user.id,website_user.first_name, website_user.last_name, website_user.registration_date, website_user.birth_date, website_user.is_admin, website_user.group_name, website_user.email, worker.description, worker.phone_number, worker.rate;\n";
            PreparedStatement st = conn.prepareStatement(sql);
            String namePart = "%" + name + "%";
            st.setString(1, workerText);
            st.setString(2, namePart);
            st.setString(3, namePart);
            return getWorkers(st);
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all suppliers", e);
        }
    }

    @Override
    public List<Worker> getAllByFilter(String name, String workObject, String profession, int rating) {
        // is-available, year experience
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT\n" +
                    "   website_user.id,\n" +
                    "   website_user.first_name,\n" +
                    "   website_user.last_name,\n" +
                    "   website_user.registration_date,\n" +
                    "   website_user.birth_date,\n" +
                    "   website_user.is_admin,\n" +
                    "   website_user.group_name,\n" +
                    "   website_user.email,\n" +
                    "   worker.description,\n" +
                    "    worker.phone_number,\n" +
                    "    worker.rate,\n" +
                    "    ARRAY_AGG(DISTINCT profession.profession_name)\n" +
                    "FROM worker\n" +
                    "   LEFT JOIN website_user ON worker.user_id = website_user.id\n" +
                    "   LEFT JOIN worker_experience ON website_user.id = worker_experience.worker_id\n" +
                    "   LEFT JOIN profession ON profession.id = worker_experience.profession_id\n" +
                    "   LEFT JOIN work_requirement ON profession.id = work_requirement.profession_id\n" +
                    "   LEFT JOIN work_object ON work_object.id = work_requirement.work_object_id\n" +
                    "\n" +
                    "    WHERE\n" +
                    "        (LOWER(website_user.first_name) LIKE LOWER(?) OR LOWER(website_user.last_name) LIKE LOWER(?))\n" +
                    "        AND work_object.work_object LIKE ?\n" +
                    "        AND profession.profession_name LIKE ?\n" +
                    "        AND worker.rate >= ?\n" +
                    "GROUP BY website_user.id,website_user.first_name, website_user.last_name, website_user.registration_date, website_user.birth_date, website_user.is_admin, website_user.group_name, website_user.email, worker.description, worker.phone_number, worker.rate;\n";
            PreparedStatement st = conn.prepareStatement(sql);

            String namePart = "";
            if (Objects.equals(name, "no")) {
                namePart = "%%";
            } else {
                namePart = "%" + name + "%";
            }
            st.setString(1, namePart);
            st.setString(2, namePart);

            String workObjectPart = "";
            if (Objects.equals(workObject, "no")) {
                workObjectPart = "%%";
            } else {
                workObjectPart = "%" + workObject + "%";
            }
            st.setString(3, workObjectPart);

            String professionPart = "";
            if (Objects.equals(profession, "no")) {
                professionPart = "%%";
            } else {
                professionPart = "%" + profession + "%";
            }
            st.setString(4, professionPart);


            st.setInt(5, rating);

            return getWorkers(st);
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all suppliers", e);
        }

    }
}
