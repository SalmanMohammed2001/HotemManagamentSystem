package bo.Custom.impl;

import bo.Custom.RoomBo;
import dao.Custom.RoomDao;
import dao.DaoFactory;
import dao.DaoType;
import dto.RoomDto;
import entity.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomBoImpl implements RoomBo {

    private RoomDao dao= DaoFactory.getInstance().getDao(DaoType.ROOM);
    @Override
    public boolean saveRoom(RoomDto dto) throws SQLException {
        return dao.save(
                new Room(
                        dto.getId(),dto.getRoomType(),dto.getAvailable(),dto.getCleaned(),dto.getPrice()
                )
        );
    }

    @Override
    public boolean updateRoom(RoomDto dto) throws SQLException {
        return dao.update(
                new Room(
                        dto.getId(),dto.getRoomType(),dto.getAvailable(),dto.getCleaned(),dto.getPrice()
                )
        );
    }

    @Override
    public boolean deleteRoom(String id) throws SQLException {
        return dao.delete(id);
    }

    @Override
    public ArrayList<RoomDto> searchRoom(String searchText) throws SQLException {
        ArrayList<Room>entity=dao.searchRooms(searchText);
        ArrayList<RoomDto>arrayList=new ArrayList<>();
        for (Room r:entity
             ) {
            arrayList.add(new RoomDto(
                    r.getId(),r.getRoomType(),r.getAvailable(),r.getCleanStatus(),r.getPrice()
            ));

        }
        return arrayList;
    }

    @Override
    public ResultSet loadRoomId() throws SQLException {
        return dao.loadRoomId();
    }

    @Override
    public boolean updateRoomCheckDetails(String id) throws SQLException {
        return dao.updateRoomCheckDetails(id);
    }

    @Override
    public ResultSet autoGenerateId() throws SQLException {
        return dao.autoGenerateId();
    }
}
