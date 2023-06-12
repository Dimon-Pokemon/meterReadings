package DAO;
import model.Street;
import model.TypeMeteringDevice;

import java.sql.*;
import java.util.ArrayList;

public class DAO {

    private Connection driverManager;
    private Statement statement;

    public DAO(String url, String user, String password){
        try {
            this.driverManager = DriverManager.getConnection(url, user, password);
            this.statement = driverManager.createStatement();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public DAO(){
        this("jdbc:postgresql://localhost:5432/meteringDeviceAndStreet", "postgres", "Admin2022");
    }

    DAO(Connection driverManager){
        this.driverManager = driverManager;
    }

    public ArrayList<Street> getStreets(){
        ArrayList<Street> streets = new ArrayList<>();
        try {

            ResultSet resultQuery = statement.executeQuery("select * from street");
            while(resultQuery.next()){
                streets.add(new Street(
                        resultQuery.getLong("id"),
                        resultQuery.getString("region"),
                        resultQuery.getString("city"),
                        resultQuery.getString("name_street")
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return streets;
    }

    public void deleteStreet(Long id){
        try{
            statement.executeUpdate("""
                    delete
                    from street
                    where id = %s
                    """.formatted(id));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteStreet(String street){
        String[] streetItem = street.split(", ");
        try{
            statement.executeUpdate("""
                    delete
                    from street
                    where region = %s and city = %s and street = %s
                    """);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addNewStreet(String region, String city, String street){
        try{
            statement.executeUpdate("""
                    insert into street (region, city, name_street)
                    values ('%s', '%s', '%s')
                    """.formatted(region, city, street));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateStreet(Long id, String region, String city, String street){
        try{
            statement.executeUpdate("""
                    update street
                    set region = '%s', city = '%s', name_street = '%s'
                    where id = %d
                    """.formatted(region, city, street, id));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> getFacilities(){
        ArrayList<String> facilities = new ArrayList<>();
        try{
            ResultSet resultSet = statement.executeQuery("select * from facility");
            while(resultSet.next()){
                facilities.add(resultSet.getString("name"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return facilities;
    }



}
