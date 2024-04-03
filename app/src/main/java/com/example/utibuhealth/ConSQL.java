package com.example.utibuhealth;

import android.annotation.SuppressLint;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.StrictMode;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ConSQL {
    Connection con;

    @SuppressLint("NewApi")
    public Connection conclass() {
        String ip = "192.168.0.101", port = "1433", db = "UtibuHealthDB", username = "DESKTOP-41JEUSP", password = "";
        StrictMode.ThreadPolicy a = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(a);
        String connectURL = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + db + ";user=" + username + ";"+"password=" + password + ";";
            con = DriverManager.getConnection(connectURL);
        } catch (Exception e) {
            Log.e("Error :", e.getMessage());
        }
        return con;
    }

    // Method to insert user data into UserInfo table
    public boolean insertUserData(String fullName, String emailAddress, String password) {
        boolean success = false;
        try {
            // Establish connection
            con = conclass();
            // SQL query to insert user data
            String query = "INSERT INTO UserInfo (FullName, EmailAddress, Password) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, fullName);
            pstmt.setString(2, emailAddress);
            pstmt.setString(3, password);
            // Execute query
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
            // Close resources
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    // Method to verify user data into UserInfo table

    public boolean authenticateUser(String email, String password) {
        boolean isAuthenticated = false;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = conclass();
            if (con != null) {
                String query = "SELECT * FROM UserInfo WHERE EmailAddress = ? AND Password = ?";
                ps = con.prepareStatement(query);
                ps.setString(1, email);
                ps.setString(2, password);
                rs = ps.executeQuery();

                if (rs.next()) {
                    isAuthenticated = true;
                }
            }
        } catch (SQLException e) {
            Log.e("SQL Error", e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                Log.e("SQL Error", e.getMessage());
            }
        }

        return isAuthenticated;
    }

    // Method to update user password in UserInfo table
    public boolean updatePassword(String emailAddress, String newPassword) {
        boolean success = false;
        try {
            // Establish connection
            con = conclass();
            // SQL query to update user password
            String query = "UPDATE UserInfo SET Password = ? WHERE EmailAddress = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, emailAddress);
            // Execute query
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
            // Close resources
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    // Method to retrieve user information by email from UserInfo table
    public UserInfo getUserInfoByEmail(String email) {
        UserInfo userInfo = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = conclass();
            if (con != null) {
                String query = "SELECT FullName, EmailAddress FROM UserInfo WHERE EmailAddress = ?";
                ps = con.prepareStatement(query);
                ps.setString(1, email);
                rs = ps.executeQuery();

                if (rs.next()) {
                    String fullName = rs.getString("FullName");
                    String emailAddress = rs.getString("EmailAddress");
                    userInfo = new UserInfo(fullName, emailAddress);
                }
            }
        } catch (SQLException e) {
            Log.e("SQL Error", e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                Log.e("SQL Error", e.getMessage());
            }
        }

        return userInfo;
    }

    //Insert Order Data into the Order table
    public boolean insertOrderData(String itemName, String emailAddress, int quantityOrdered, double totalPrice, String status) {
        boolean success = false;
        try {
            // Establish connection
            con = conclass();
            // SQL query to insert order data
            String query = "INSERT INTO orders (ItemID, CustomerEmail, QuantityOrdered, TotalPrice, Status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            // Get ItemID based on itemName from the stock table
            int itemID = getItemID(itemName);
            pstmt.setInt(1, itemID);
            pstmt.setString(2, emailAddress);
            pstmt.setInt(3, quantityOrdered);
            pstmt.setDouble(4, totalPrice);
            pstmt.setString(5, status);
            // Execute query
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
            // Close resources
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    // Method to get ItemID based on itemName from the stock table
    private int getItemID(String itemName) {
        int itemID = -1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = conclass();
            if (con != null) {
                String query = "SELECT ItemID FROM stock WHERE ItemName = ?";
                ps = con.prepareStatement(query);
                ps.setString(1, itemName);
                rs = ps.executeQuery();
                if (rs.next()) {
                    itemID = rs.getInt("ItemID");
                }
            }
        } catch (SQLException e) {
            Log.e("SQL Error", e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                Log.e("SQL Error", e.getMessage());
            }
        }
        return itemID;
    }

    int getAvailableQuantityFromDatabase(String itemName) {
        int availableQuantity = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = conclass();
            if (con != null) {
                String query = "SELECT QuantityInStock FROM stock WHERE ItemName = ?";
                ps = con.prepareStatement(query);
                ps.setString(1, itemName);
                rs = ps.executeQuery();

                if (rs.next()) {
                    availableQuantity = rs.getInt("QuantityInStock");
                }
            }
        } catch (SQLException e) {
            Log.e("SQL Error", e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                Log.e("SQL Error", e.getMessage());
            }
        }

        return availableQuantity;
    }

    // Method to fetch orders from the database based on email address
    public List<Order> getOrdersByEmail(String emailAddress) {
        List<Order> orders = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Establish connection
            con = conclass();
            // SQL query to fetch orders based on email address
            String query = "SELECT OrderID, ItemID, CustomerEmail, QuantityOrdered, TotalPrice, Status FROM orders WHERE CustomerEmail = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, emailAddress);
            // Execute query
            rs = pstmt.executeQuery();
            // Process the result set
            while (rs.next()) {
                // Retrieve order details from the result set
                int orderID = rs.getInt("OrderID");
                int itemID = rs.getInt("ItemID");
                String customerEmail = rs.getString("CustomerEmail");
                int quantityOrdered = rs.getInt("QuantityOrdered");
                double totalPrice = rs.getDouble("TotalPrice");
                String status = rs.getString("Status");
                // Create an Order object and add it to the list
                Order order = new Order(orderID, itemID, customerEmail, quantityOrdered, totalPrice, status);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close resources
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return orders;
    }

    // Method to get the email address of the logged-in user from the database
    public String getLoggedInUserEmail() {
        String loggedInUserEmail = null;
        // Replace this query with the appropriate SQL query to fetch the logged-in user's email
        String email = null;
        String password = null;
        String loggedInUserMail = authenticateUserAndGetEmail(email, password);
        String query = "SELECT OrderID, ItemID, CustomerEmail, QuantityOrdered, TotalPrice, Status FROM orders WHERE CustomerEmail = ?";


        try {
            con = conclass();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(query);
                // Set parameters if needed
                // Execute query
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    loggedInUserEmail = rs.getString("EmailAddress");
                }

                // Close resources
                rs.close();
                ps.close();
                con.close();
            }
        } catch (SQLException e) {
            Log.e("SQL Error", e.getMessage());
        }

        return loggedInUserMail;
    }

    // Method to authenticate user and retrieve email address
    public String authenticateUserAndGetEmail(String email, String password) {
        String emailAddress = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = conclass();
            if (con != null) {
                String query = "SELECT EmailAddress FROM UserInfo WHERE EmailAddress = ? AND Password = ?";
                ps = con.prepareStatement(query);
                ps.setString(1, email);
                ps.setString(2, password);
                rs = ps.executeQuery();

                if (rs.next()) {
                    // User is authenticated, retrieve the email address
                    emailAddress = rs.getString("EmailAddress");
                }
            }
        } catch (SQLException e) {
            Log.e("SQL Error", e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                Log.e("SQL Error", e.getMessage());
            }
        }

        return emailAddress;
    }

}




