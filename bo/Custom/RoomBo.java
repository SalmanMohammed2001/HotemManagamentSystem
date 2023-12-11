package bo.Custom;

import dto.RoomDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomBo {
    public boolean saveRoom(RoomDto dto) throws SQLException;
    public boolean updateRoom(RoomDto  dto) throws SQLException;
    public boolean deleteRoom(String id) throws SQLException;
    public ArrayList<RoomDto> searchRoom(String searchText) throws SQLException;
    public ResultSet loadRoomId() throws SQLException;
    public boolean updateRoomCheckDetails(String id) throws SQLException;
    public ResultSet autoGenerateId() throws SQLException;
}
