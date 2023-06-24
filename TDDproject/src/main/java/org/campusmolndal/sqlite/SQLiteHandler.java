package org.campusmolndal.sqlite;

import org.campusmolndal.todo.Todo;
import org.campusmolndal.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteHandler {
    private static final String DATABASE_NAME = "todo.db";
    public SQLiteHandler() {
        createTables();
    }
    public void createTables() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getconnection();
            ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name TEXT)");
            ps.executeUpdate();
            ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS todos (id INTEGER PRIMARY KEY, done BOOLEAN, todo TEXT, user_id INT, FOREIGN KEY(user_id) REFERENCES users(id))");
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in SQLiteHandler.createTables: " + e.getMessage());
        } finally {
            try {
                if(ps != null) ps.close();
                if(conn != null) closeConnection(conn);
            } catch (SQLException e) {
                System.err.println("Error in SQLiteHandler.createTables: " + e.getMessage());
            }
        }
    }
    public User insert(User user) {
        if(user == null || user.getName() == null) return null;
        try (Connection conn = getconnection()) {
            user = insertUser(user, conn);
        } catch (SQLException e) {
            System.err.println("Error in SQLiteHandler.insert: " + e.getMessage());
            return null;
        }
        return user;
    }
    public Todo insert(Todo todo) {
        if(todo == null || todo.getUser() == null || todo.getText() == null)
            return null;
        try (Connection conn = getconnection()) {
            todo = insertTodo(todo, conn);
        } catch (SQLException e) {
            System.err.println("Error in SQLiteHandler.insert: " + e.getMessage());
            return null;
        }
        return todo;
    }
    private Todo insertTodo(Todo todo, Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO todos (todo, user_id, done) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, todo.getText());
        ps.setInt(2, Integer.parseInt(todo.getUser()));
        ps.setBoolean(3, todo.isDone());
        ps.executeUpdate();

        try (ResultSet keys = ps.getGeneratedKeys()) {
            if (keys.next()) {
                todo.setId(String.valueOf(keys.getInt(1)));
            }
        } catch (SQLException e) {
            System.err.println("Error in SQLiteHandler.insert: " + e.getMessage());
            return null;
        }
        return todo;
    }
    private User insertUser(User user, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO users (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, user.getName());
        ps.executeUpdate();

        try (ResultSet keys = ps.getGeneratedKeys()) {
            if (keys.next()) {
                user.setId(String.valueOf(keys.getInt(1)));
            }
        } catch (SQLException e) {
            System.err.println("Error in SQLiteHandler.insert: " + e.getMessage());
            return null;
        }
        return user;
    }

    private Connection getconnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
    }
    private void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    public User getUser(String id) {
        if(id == null) return null;
        try (Connection conn = getconnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            ps.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(String.valueOf(rs.getInt("id")));
                    user.setName(rs.getString("name"));
                    return user;
                }
            } catch (SQLException e) {
                System.err.println("Error in SQLiteHandler.getUser: " + e.getMessage());
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error in SQLiteHandler.getUser: " + e.getMessage());
            return null;
        }
        return null;
    }

    public User update(User user) {
        if(user == null || user.getId() == null || user.getName() == null) return null;
        try (Connection conn = getconnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE users SET name = ? WHERE id = ?")) {
            ps.setString(1, user.getName());
            ps.setInt(2, Integer.parseInt(user.getId()));
            ps.executeUpdate();
            return user;
        } catch (SQLException e) {
            System.err.println("Error in SQLiteHandler.update: " + e.getMessage());
            return null;
        }
    }
    public Todo update(Todo todo) {
        if(todo == null || todo.getText() == null || todo.getId() == null || todo.getUser() == null) return null;
        try (Connection conn = getconnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE todos SET todo = ?, done = ?, user_id = ? WHERE id = ?")) {
            ps.setString(1, todo.getText());
            ps.setBoolean(2, todo.isDone());
            ps.setInt(3, Integer.parseInt(todo.getUser()));
            ps.setInt(4, Integer.parseInt(todo.getId()));
            ps.executeUpdate();
            return todo;
        } catch (SQLException e) {
            System.err.println("Error in SQLiteHandler.update: " + e.getMessage());
            return null;
        }
    }

    public User delete(User user) {
        if(user == null || user.getId() == null) return null;
        try (Connection conn = getconnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
            ps.setInt(1, Integer.parseInt(user.getId()));
            ps.executeUpdate();
            return user;
        } catch (Exception e) {
            System.err.println("Error in SQLiteHandler.delete: " + e.getMessage());
            return null;
        }
    }
    public Todo delete(Todo todo) {
        if(todo == null || todo.getId() == null) return null;
        try (Connection conn = getconnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM todos WHERE id = ?")) {
            ps.setInt(1, Integer.parseInt(todo.getId()));
            ps.executeUpdate();
            return todo;
        } catch (Exception e) {
            System.err.println("Error in SQLiteHandler.delete: " + e.getMessage());
            return null;
        }
    }

    public List<User> listUsers() {
        List<User> users = new ArrayList<>();
        try (Connection conn = getconnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM users")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(String.valueOf(rs.getInt("id")));
                    user.setName(rs.getString("name"));
                    users.add(user);
                }
            } catch (SQLException e) {
                System.err.println("Error in SQLiteHandler.listUsers: " + e.getMessage());
                return users;
            }
        } catch (SQLException e) {
            System.err.println("Error in SQLiteHandler.listUsers: " + e.getMessage());
            return users;
        }
        return users;
    }

    public Todo getTodo(String id) {
        if(id == null) return null;
        try (Connection conn = getconnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM todos WHERE id = ?")) {
            ps.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Todo todo = new Todo();
                    todo.setId(String.valueOf(rs.getInt("id")));
                    todo.setText(rs.getString("todo"));
                    todo.setDone(rs.getBoolean("done"));
                    todo.setUser(String.valueOf(rs.getInt("user_id")));
                    return todo;
                }
            } catch (SQLException e) {
                System.err.println("Error in SQLiteHandler.getTodo: " + e.getMessage());
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error in SQLiteHandler.getTodo: " + e.getMessage());
            return null;
        }
        return null;
    }

    public List<Todo> listTodos() {
        List<Todo> todos = new ArrayList<>();
        try (Connection conn = getconnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM todos")) {
            try (ResultSet rs = ps.executeQuery()) {
                getTodos(todos, rs);
            } catch (SQLException e) {
                System.err.println("Error in SQLiteHandler.listTodos: " + e.getMessage());
                return todos;
            }
        } catch (SQLException e) {
            System.err.println("Error in SQLiteHandler.listTodos: " + e.getMessage());
            return todos;
        }
        return todos;
    }

    public List<Todo> getTodosByUserId(String id) {
        List<Todo> todos = new ArrayList<>();
        if(id == null) return todos;
        try (Connection conn = getconnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM todos WHERE user_id = ?")) {
            ps.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = ps.executeQuery()) {
                getTodos(todos, rs);
            } catch (SQLException e) {
                System.err.println("Error in SQLiteHandler.getTodosByUserId: " + e.getMessage());
                return todos;
            }
        } catch (SQLException e) {
            System.err.println("Error in SQLiteHandler.getTodosByUserId: " + e.getMessage());
            return todos;
        }
        return todos;
    }

    private void getTodos(List<Todo> todos, ResultSet rs) throws SQLException {
        while (rs.next()) {
            Todo todo = new Todo();
            todo.setId(String.valueOf(rs.getInt("id")));
            todo.setText(rs.getString("todo"));
            todo.setDone(rs.getBoolean("done"));
            todo.setUser(String.valueOf(rs.getInt("user_id")));
            todos.add(todo);
        }
    }
}
