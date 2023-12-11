package bo.Custom;

import dto.CustomDto;
import entity.Custom;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryBo {
    public ArrayList<CustomDto> loadItemJoinQuery(String id) throws SQLException;
    public ArrayList<CustomDto> loadBookingJoinQuery(String id) throws SQLException;
}
