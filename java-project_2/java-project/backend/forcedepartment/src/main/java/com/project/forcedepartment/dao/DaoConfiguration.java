package com.project.forcedepartment.dao;

import com.project.forcedepartment.dao.implementation.CategoryDaoJdbc;
import com.project.forcedepartment.dao.implementation.UserDaoJdbc;
import com.project.forcedepartment.dao.implementation.WorkerDaoJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DaoConfiguration {

    private DataSource dataSource;

    @Autowired
    public DaoConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public WorkerDao getWorkerDao() {
        return new WorkerDaoJdbc(dataSource);
    }

    @Bean
    public UserDao getUserDao() {
        return new UserDaoJdbc(dataSource);
    }

    @Bean
    public CategoryDao getCategoryDao() {
        return new CategoryDaoJdbc(dataSource);
    }


}
