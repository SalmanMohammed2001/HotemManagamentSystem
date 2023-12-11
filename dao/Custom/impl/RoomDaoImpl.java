package dao.Custom.impl;

import dao.CrudUtil;
import dao.Custom.RoomDao;
import db.DBConnection;
import entity.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RoomDaoImpl implements RoomDao {
    @Override
    public boolean save(Room r1) throws SQLException {
        return CrudUtil.execute("INSERT INTO  Room VALUES(?,?,?,?,?)",
                r1.getId(),r1.getRoomType(),r1.getAvailable(),r1.getCleanStatus(),r1.getPrice());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM Room WHERE id=?",id);
    }

    @Override
    public boolean update(Room r1) throws SQLException {
        return CrudUtil.execute("UPDATE Room SET  bedType=?, availability=?,cleanStatus=?,price=? WHERE id=?",
                r1.getRoomType(),r1.getAvailable(),r1.getCleanStatus(),r1.getPrice(),r1.getId() );
    }

    @Override
    public ArrayList<Room> searchRooms(String searchText) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Room WHERE id like ?||  bedType like?",
                searchText,searchText);
        ArrayList<Room> arrayList=new ArrayList<>();
        while (resultSet.next()){
            arrayList.add(new Room(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5)
            ));
        }
        return arrayList;

    }

    @Override
    public ResultSet autoGenerateId() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("select  id   from Room order by  id   desc limit 1");
        return resultSet;
    }

    @Override
    public ResultSet loadRoomId() throws SQLException {

        ResultSet set = CrudUtil.execute("select * from room where availability = 'Occupied'");
        return set;
    }

    @Override
    public boolean updateRoomCheckDetails(String id) throws SQLException {

        return CrudUtil.execute("update Room set availability='Available', cleanStatus='dirty' where id=?",id);
    }
}
