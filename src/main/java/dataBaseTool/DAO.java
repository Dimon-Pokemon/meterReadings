package dataBaseTool;
import model.MeteringDevice;
import model.ReadingLog;
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


    public ArrayList<ReadingLog> getReadingLog(Street street, MeteringDevice meteringDevice){
        ArrayList<ReadingLog> readingLogs = new ArrayList<>();
        String sql = """
                    select "date", serial_number, title, reading, name_street, capacity from reading r\s
                    join metering_device md on md.serial_number = r.metering_device_fk\s
                    join street s on s.id = md.street_fk\s
                    join type_metering_device tmd on tmd.id = md.type_metering_device_fk
                    where s.id = %d
                    """;
        ResultSet readings;
        try{
            if (meteringDevice == null){
                sql = sql.concat("\norder by date desc");
                readings = statement.executeQuery(sql.formatted(street.getId()));
            }
            else{
                sql = sql.concat(" and serial_number = %d\norder by date desc");
                readings = statement.executeQuery(sql.formatted(street.getId(), meteringDevice.getSerialNumber()));
            }
            while (readings.next()){
                readingLogs.add(new ReadingLog(
                        readings.getDate("date"),
                        readings.getString("serial_number"),
                        readings.getString("title"),
                        readings.getDouble("reading"),
                        readings.getString("name_street"),
                        readings.getInt("capacity")
                ));
            }
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return readingLogs;
    }

    public ArrayList<ReadingLog> getReadingLog(Street street){
        return getReadingLog(street, null);
    }

    public void addNewMetering(MeteringDevice meteringDevice, Double newReading){
        try{
            statement.executeUpdate("""
                    insert into reading (metering_device_fk, reading)
                    values (%d, %s)
                    """.formatted(meteringDevice.getId(), newReading.toString().replace('.', ',')));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<MeteringDevice> getMeteringDevices(Street street){
        ArrayList<MeteringDevice> meteringDevices = new ArrayList<>();
        try{
            ResultSet resultSQL = statement.executeQuery("""
                    select md.id, name_street, title, serial_number, capacity, accuracy from metering_device md
                    join street s on s.id = md.street_fk
                    join type_metering_device tmp on tmp.id = md.type_metering_device_fk
                    where s.id = %d;
                    """.formatted(street.getId()));
            while(resultSQL.next()){
                meteringDevices.add(new MeteringDevice(
                        resultSQL.getInt("id"),
                        resultSQL.getString("name_street"),
                        resultSQL.getString("title"),
                        resultSQL.getLong("serial_number"),
                        resultSQL.getInt("capacity"),
                        resultSQL.getInt("accuracy")
                ));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return meteringDevices;
    }

    public ArrayList<String> getRegionOrCity(String that){
        ArrayList<String> result = new ArrayList<>();
        try{
            statement.execute("""
                    select * from %s
                    """.formatted(that));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  result;
    }

    public ArrayList<Street> getStreets(){
        ArrayList<Street> streets = new ArrayList<>();
        try {

            ResultSet resultQuery = statement.executeQuery("select * from street");
            while(resultQuery.next()){
                streets.add(new Street(
                        resultQuery.getLong("id"),
                        resultQuery.getString("region_fk"),
                        resultQuery.getString("city_fk"),
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


    public void addNewMeteringDevice(Long serialNumber, Long streetFk, Long typeMeteringDeviceFk){
        try{
            statement.executeUpdate("""
                        insert into metering_device
                        values (%d, '%s', '%s')
                        """.formatted(serialNumber, streetFk, typeMeteringDeviceFk));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addNewTypeMeteringDevice(String title, String facilityFk, Integer capacity, Integer accuracy){
        try {
            statement.executeUpdate("""
                    insert into type_metering_device
                    (title, facility_fk, capacity)
                    values
                    ('%s', '%s', %d, %d)
                    """.formatted(title, facilityFk, capacity, accuracy));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateTypeMeteringDevice(String title, String facilityFk, Integer capacity, Integer accuracy){
        try{
            statement.executeUpdate("""
                    update type_metering_device
                    set title = '%s', facility_fk = '%s', capacity = %d, accuracy = %d
                    """.formatted(title, facilityFk, capacity, accuracy));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteTypeMeteringDevice(Long id){
        try{
            statement.executeUpdate("""
                    delete from type_metering_device tmd
                    where tmd.id = %d
                    """.formatted(id));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<TypeMeteringDevice> getTypesMeteringDevice(){
        ArrayList<TypeMeteringDevice> typeMeteringDevices = new ArrayList<>();
        try{
            ResultSet resultSQL = statement.executeQuery("""
                    select * from type_metering_device
                    """);
            while(resultSQL.next()){
                typeMeteringDevices.add(new TypeMeteringDevice(
                        resultSQL.getLong("id"),
                        resultSQL.getString("title"),
                        resultSQL.getString("facility_fk"),
                        resultSQL.getInt("capacity"),
                        resultSQL.getInt("accuracy")
                ));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return typeMeteringDevices;
    }

}
