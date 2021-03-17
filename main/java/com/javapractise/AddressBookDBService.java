package com.javapractise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {

    private PreparedStatement addressBookDataStatement;
    private static AddressBookDBService addressBookDBService;

    public static AddressBookDBService getInstance() {
        if (addressBookDBService == null)
            addressBookDBService = new AddressBookDBService();
        return addressBookDBService;
    }

    private AddressBookDBService() {
    }

    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/addressbook_service?useSSL=false";
        String userName = "root";
        String password = "grandmaster";
        Connection connection;
        System.out.println("Connecting to database:" + jdbcURL);
        connection = DriverManager.getConnection(jdbcURL, userName, password);
        System.out.println("Connection is successful!!" + connection);

        return connection;
    }

    public List<AddressBookData> readData() {
        String sql = "SELECT * FROM addressbook";
        List<AddressBookData> addressBookData = new ArrayList<>();
        try (Connection connection = this.getConnection();) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            addressBookData = this.getAddressBookData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressBookData;
    }

    private List<AddressBookData> getAddressBookData(ResultSet resultSet) {
        List<AddressBookData> addressBookData = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                int zip = resultSet.getInt("zip");
                int phoneNumber = resultSet.getInt("phoneNumber");
                String email = resultSet.getString("email");
                addressBookData.add(new AddressBookData(firstName,lastName,address,city,state,zip,phoneNumber,email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressBookData;
    }

    public List<AddressBookData> getAddressBookData(String name) {
        List<AddressBookData> addressBookDataList = null;
        if (this.addressBookDataStatement == null)
            this.prepareStatementForAddressBookData();
        try {
            addressBookDataStatement.setString(1, name);
            ResultSet resultSet = addressBookDataStatement.executeQuery();
            addressBookDataList = this.getAddressBookData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressBookDataList;
    }

    private void prepareStatementForAddressBookData() {
        try {
            Connection connection = this.getConnection();
            String sql = "SELECT * FROM addressbook WHERE firstName = ?";
            addressBookDataStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int updateAddressBookData(String name, String city) {
        String sql = String.format("update addressbook set city ='%s' where firstName = '%s';", city, name);
        try (Connection connection = this.getConnection();) {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
