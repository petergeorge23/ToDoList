package org.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcTaskDao implements org.example.dao.TaskDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTaskDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "Select * FROM tasks;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Task task = mapRowToTask(results);
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    public void addTask(String task) {
        String sql = "INSERT INTO tasks(task) VALUES(?);";
        jdbcTemplate.update(sql, task);
    }

    @Override
    public void deleteTask(int id) {
        String sql = "DELETE FROM tasks where id = ?;";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void markTaskAsCompleted(int id) {
        String sql = "UPDATE tasks SET completed = true WHERE id = ?;";
        jdbcTemplate.update(sql, id);

    }

    private Task mapRowToTask(SqlRowSet rowSet){
        Task task = new Task();
        task.setId(rowSet.getInt("id"));
        task.setTask(rowSet.getString("task"));
        task.setCompleted(rowSet.getBoolean("completed"));
        return task;
    }
}
