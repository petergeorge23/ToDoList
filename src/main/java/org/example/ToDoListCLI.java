package org.example;

import org.apache.commons.dbcp2.BasicDataSource;

import org.example.dao.TaskDao;
import org.example.model.JdbcTaskDao;
import org.example.model.Task;

import javax.sql.DataSource;
import java.util.List;
import java.util.Scanner;

public class ToDoListCLI {

    private TaskDao taskDao;
    private String input;
    private int choice;

//    public ToDoListCLI(DataSource dataSource) {
//        taskDao = new JdbcTaskDao(dataSource);
//    }

    public static void main(String[] args) {

        ToDoListCLI app = new ToDoListCLI();
        app.run();
    }

    private void run() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/todo_list");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        // Create an instance of the SampleDAO
        taskDao = new JdbcTaskDao(dataSource);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. View tasks");
            System.out.println("2. Add task");
            System.out.println("3. Delete task");
            System.out.println("4. Mark task as completed");
            System.out.println("5. Exit");
            System.out.println("  ");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    List<Task> tasks = taskDao.getTasks();
                    for (Task task : tasks) {
                        System.out.println(task);

                    }
                    break;
                case 2:
                    System.out.print("Enter task description: ");
                    scanner.nextLine();
                    String description = scanner.nextLine();
                    taskDao.addTask(description);
                    System.out.println("Task added successfully.");
                    System.out.println("");
                    break;
                case 3:
                    System.out.print("Enter task ID to delete or enter 999 to exit: ");
                    int id = scanner.nextInt();
                    if(id == 999){
                        break;
                    }
                    taskDao.deleteTask(id);
                    System.out.println("Task deleted successfully.");
                    System.out.println(" ");
                    break;
                case 4:
                    System.out.print("Enter task ID: ");
                    int taskId = scanner.nextInt();
                    taskDao.markTaskAsCompleted(taskId);
                    System.out.println("Task marked as completed.");
                    System.out.println("");
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }


}
