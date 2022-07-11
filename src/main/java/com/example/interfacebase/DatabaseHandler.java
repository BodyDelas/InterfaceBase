package com.example.interfacebase;

import java.sql.*;
import java.util.ArrayList;


public class DatabaseHandler extends Configs {
    static Connection dbConnection;
    static Statement statement;

    public DatabaseHandler() {
        try{
            getDbConnection();
            statement = dbConnection.createStatement();
            System.out.println("success");
        } catch (Exception e) {}
    }

    public void getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(
                connectionString,
                dbName,
                dbPass
        );
        System.out.println("Connect");
    }

    public static void signUpUser(String firstName, String lastName, String userName, String password, String group) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO `" + Const.USER_TABLE + "` (`" +
                Const.USER_FIRSTNAME + "`, `" + Const.USER_LASTNAME + "`, `" +
                Const.USER_USERNAME + "`, `" + Const.USER_PASSWORD + "`, `" +
                Const.USER_GROUP + "`) " + "VALUES (\""+firstName+"\", \""+lastName+"\", \""+userName+"\",\""+password+"\","+group+")";
        System.out.println(insert);


        statement.executeUpdate(insert);

    }

    public static ResultSet getUser(User user){
        ResultSet resSet = null;
        System.out.println(user.getUserName()+", "+user.getPassword());
        String select = "SELECT * FROM `" + Const.USER_TABLE + "` WHERE `" +
                Const.USER_USERNAME + "`=\""+user.getUserName()+"\" AND `" + Const.USER_PASSWORD + "`=\""+user.getPassword()+"\"";
        System.out.println(select);

        try {
            resSet = statement.executeQuery(select);
        } catch (Exception e) {
            System.out.println("not success");
        }
        return resSet;
    }


    public static void updateUserData(String oldUserName, String oldPassword, String userName, String password) {
        try {

            String query = String.format(
                    "UPDATE `users` SET `username`=\"%s\", `password`=\"%s\" " +
                            "WHERE `username`=\"%s\" AND `password`=\"%s\"",
                    userName,
                    password,
                    oldUserName,
                    oldPassword
            );
            System.out.println(query);
            statement.executeUpdate(query);
            System.out.println("success");


        } catch (Exception e) {}
    }

    public static ArrayList<User> getAllUsers() {
        ArrayList<User> arr = new ArrayList<>();
        String query = "SELECT * FROM `users`";
        try {
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                arr.add(new User(
                        res.getInt("idusers"),
                        res.getString("firstname"),
                        res.getString("lastname"),
                        res.getString("username"),
                        res.getString("password"),
                        res.getInt("group"),
                        res.getInt("type")
                ));
            }
        } catch (Exception e) {}
        return arr;
    }

    public static ArrayList<String> getAllGroups() {
        ArrayList<String> arr = new ArrayList<>();
        String query = "SELECT * FROM `group`";
        try {
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                arr.add(res.getString("num"));
            }
        } catch (Exception e) {}
        return arr;
    }

    public static User getUser(String userName) {
        try {
            ResultSet res = statement.executeQuery(String.format(
                    "SELECT * FROM `users`" +
                            "WHERE `username` = '%s'",
                    userName
            ));
            res.next();
            return new User(
                    res.getInt("idusers"),
                    res.getString("firstname"),
                    res.getString("lastname"),
                    userName,
                    res.getString("password"),
                    res.getInt("group"),
                    res.getInt("type")
            );
        } catch (Exception e) {}
        return null;
    }


    public static void addQuote(String quote, String subject, String teacher, String date, int userId) {
        try {

            if (date.equals("")) {
                statement.executeUpdate(String.format(
                        "INSERT INTO `quote` (`quote`, `subject`, `teacher`, `user_id`) " +
                           "VALUES ('%s', '%s', '%s', %s)",
                        quote, subject, teacher, userId
                ));
            } else {
                statement.executeUpdate(String.format(
                        "INSERT INTO `quote` (`quote`, `subject`, `teacher`, `date`, `user_id`) " +
                                "VALUES ('%s', '%s', '%s', Date('%s'), %s)",
                        quote, subject, teacher, date, userId
                ));
            }

        } catch (Exception e) {
            System.out.println("Error in addQuote()");
        }
    }

    public static ArrayList<Quote> getEditableQuotes() {
        ArrayList<Quote> arr = new ArrayList<>();

        try {
            String query = "";
            if (Current.user.getType() == 1) {
                query = String.format(
                        "SELECT * FROM `quote` " +
                        "WHERE `user_id` = '%s'",
                        Current.user.getId()
                );
            }
            else if (Current.user.getType() == 2) {
                query = String.format(
                        "SELECT * FROM `quote` " +
                        "WHERE (SELECT `group` FROM `users` WHERE `idusers`=`user_id` LIMIT 1) = %s",
                        Current.user.getGroup()
                );
            }
            else if (Current.user.getType() == 3) {
                query = "SELECT * FROM `quote`";
            }
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                arr.add(new Quote(
                        result.getInt("id"),
                        result.getString("subject"),
                        result.getString("teacher"),
                        result.getString("quote"),
                        result.getString("date"),
                        result.getInt("user_id")
                ));
            }
        } catch (Exception e) {}

        return arr;
    }


    public static ArrayList<Quote> getUneditableQuotes() {
        ArrayList<Quote> arr = new ArrayList<>();

        try {
            String query = "";
            if (Current.user.getType() == 1) {
                query = String.format(
                        "SELECT * FROM `quote` " +
                                "WHERE `user_id` != '%s'",
                        Current.user.getId()
                );
            }
            else if (Current.user.getType() == 2) {
                query = String.format(
                        "SELECT * FROM `quote` " +
                                "WHERE (SELECT `group` FROM `users` WHERE `idusers`=`user_id` LIMIT 1) != %s",
                        Current.user.getGroup()
                );
            }
            else if (Current.user.getType() == 3) {
                query = "";
            }
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                arr.add(new Quote(
                        result.getInt("id"),
                        result.getString("subject"),
                        result.getString("teacher"),
                        result.getString("quote"),
                        result.getString("date"),
                        result.getInt("user_id")
                ));
            }
        } catch (Exception e) {}

        return arr;
    }


    public static void deleteQuote(int id) {
        String query = String.format(
                "DELETE FROM `quote` " +
                "WHERE `id`=%s",
                id
        );
        try {
            statement.executeUpdate(query);
        } catch (Exception e) {}
    }

    public static ArrayList<Quote> getAllQuotes() {
        ArrayList<Quote> arr = new ArrayList<>();
        String query = String.format("SELECT * FROM `quote`");
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                arr.add(new Quote(
                        result.getInt("id"),
                        result.getString("subject"),
                        result.getString("teacher"),
                        result.getString("quote"),
                        result.getString("date"),
                        result.getInt("user_id")
                ));
            }
        } catch (Exception e) {}
        return arr;
    }

    public static void updateQuote(int id, String quote, String subject, String teacher, String date) {
        String query = "";
        try {
            System.out.println(1);
            System.out.println(date);

            if (date==null) {
                System.out.println(1);
                query = String.format(
                        "UPDATE `quote` " +
                        "SET `quote`='%s', `subject`='%s', `teacher`='%s' " +
                        "WHERE `id` = %s",
                        quote, subject, teacher, id
                );
                System.out.println(2);
            } else {
                System.out.println(74);
                query = String.format(
                        "UPDATE `quote` " +
                                "SET `quote`='%s', `subject`='%s', `teacher`='%s', `date`=Date('%s') " +
                                "WHERE `id` = %s",
                        quote, subject, teacher, date, id
                );
            }
            System.out.println(query);
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error in updateQuote()");
            System.out.println("query="+query);
        }
    }

    public static Quote getQuote(int id) {
        try {
            ResultSet result = statement.executeQuery(String.format(
                    "SELECT * FROM `quote` WHERE `id`=%s",
                    id
            ));
            result.next();
            return new Quote(
                    result.getInt("id"),
                    result.getString("subject"),
                    result.getString("teacher"),
                    result.getString("quote"),
                    result.getString("date"),
                    result.getInt("user_id")
            );

        } catch (Exception e) {}

        return null;
    }

    public static int countQuotes() {
        try {
            System.out.println(1);
            String query = "SELECT COUNT(*) AS 'count' FROM `quote` " +
                    "WHERE `user_id`="+Current.user.getId();
            System.out.println(query);
            ResultSet res = statement.executeQuery(query);
            res.next();
            return res.getInt("count");
        } catch (Exception e) {
            System.out.println("Error in countQuotes()");
        }
        return 0;
    }

}
