package dao.Custom;

import dao.CrudDao;
import entity.Customer;
import entity.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomDao extends CrudDao<Room,String> {
    public ArrayList<Room> searchRooms(String searchText) throws SQLException;
    public ResultSet autoGenerateId() throws SQLException;
    public ResultSet loadRoomId() throws SQLException;
    public boolean updateRoomCheckDetails(String id) throws SQLException;
}
