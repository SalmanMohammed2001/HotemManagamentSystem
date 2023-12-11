package bo.Custom;

import dto.MealDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MelBo {
    public boolean saveMel(MealDto dto) throws SQLException;
    public boolean updateMel(MealDto dto) throws SQLException;
    public boolean deleteMel(String id) throws SQLException;
    public ArrayList<MealDto> searchMel(String searchText) throws SQLException;
}
