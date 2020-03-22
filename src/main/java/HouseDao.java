import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class HouseDao implements Dao<House> {
    List<House> houses;
    ResourceDB res;
    public HouseDao(){
        houses = getList();
        res = new ResourceDB();
    }
    public List<House> getList() {
        try (Connection connection = DriverManager.getConnection(res.getUrl(), res.getLogin(), res.getPassword())){
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM house_info");
            while (rs.next()){
                if (findById(rs.getInt(1)) == null)
                    houses.add(new House(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getFloat(4),
                        rs.getInt(5), rs.getLong(6)));
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e.getMessage(),e);
        }
        return houses;
    }

    public void insertData(House house) {
        if (houses.stream().noneMatch(i -> i.getDistrict().equals(house.getDistrict()) &&
                i.getAddress().equals(house.getAddress())))
        {
            houses.add(house);
        }
        else {
            System.out.println("This house is already exists");
            return;
        }
        try(Connection con = getConnection(res.getUrl(), res.getLogin(), res.getPassword())){
            PreparedStatement ps = con.prepareStatement("INSERT INTO " +
                    "house_info(district,address,area,room_number,price) " +
                    "VALUES(?,?,?,?,?)");
            try{
                ps.setString(1, house.getDistrict());
                ps.setString(2, house.getAddress());
                ps.setFloat(3, house.getArea());
                ps.setInt(4, house.getRoomNumber());
                ps.setLong(5, house.getPrice());
                ps.executeUpdate();

                house.setHouseId(findIdByAddress(new String[]{house.getDistrict(), house.getAddress()}));
            } finally {
                ps.close();
            }
        }
        catch (SQLException ex){
            throw new RuntimeException(ex.getMessage(), ex);
        }
        System.out.println("Successfully!");
    }

    public String getAllData(){
        StringBuilder stringBuilder = new StringBuilder();
        houses.forEach(i -> stringBuilder.append(i.toString()+"\r\n"));
        return stringBuilder.toString();
    }
    public void updateData(House house, String[] args) {
        if(houses.stream().anyMatch(i -> i.getHouseId() == house.getHouseId())){
            houses.get(houses.indexOf(house)).setDistrict(args[0]);
            houses.get(houses.indexOf(house)).setAddress(args[1]);
            houses.get(houses.indexOf(house)).setArea(Float.parseFloat(args[2]));
            houses.get(houses.indexOf(house)).setRoomNumber(Integer.parseInt(args[3]));
            houses.get(houses.indexOf(house)).setPrice(Long.parseLong(args[4]));
        }
        else {
            System.out.println("This house doesnt exist");
            return;
        }
        try(Connection con = getConnection(res.getUrl(), res.getLogin(), res.getPassword())){
            PreparedStatement ps = con.prepareStatement("UPDATE house_info SET district = ?," +
                    "address = ?," +
                    "area = ?," +
                    "room_number = ?," +
                    "price = ? where house_id = ?");
            try{
                ps.setString(1, house.getDistrict());
                ps.setString(2, house.getAddress());
                ps.setFloat(3, house.getArea());
                ps.setInt(4, house.getRoomNumber());
                ps.setLong(5, house.getPrice());
                ps.setInt(6, house.getHouseId());
                ps.executeUpdate();
            } finally {
                ps.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
       }
        System.out.println("Successfully!");
    }

    public void deleteData(int id) {
        if (houses.stream().anyMatch(i -> i.getHouseId() == id))
            houses.remove(findById(id));
        else {
            System.out.println("This house doesnt exist");
            return;
        }
        try(Connection con = getConnection(res.getUrl(), res.getLogin(), res.getPassword())){
            PreparedStatement ps = con.prepareStatement("DELETE FROM house_info WHERE house_id = ?");
            try{
                ps.setInt(1, id);
                ps.executeUpdate();
            } finally {
                ps.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
        System.out.println("Successfully!");
    }


    public House findById(int id) {
        for (House house : houses)
            if (house.getHouseId() == id) return house;
        return null;
    }
    public int findIdByAddress(String[] args){
        int result = -1;
        String query = String.format("SELECT house_id FROM house_info WHERE " +
                "district = '%s' AND address = '%s'", args[0], args[1]);

        try (Connection con = getConnection(res.getUrl(), res.getLogin(), res.getPassword())) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            result = rs.getInt(1);
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
        return result;
    }
}
