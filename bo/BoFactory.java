package bo;

import bo.Custom.impl.*;
import dao.Custom.impl.CustomerDaoImpl;
import dao.Custom.impl.EmployeeDaoImpl;
import dao.Custom.impl.MelDaoImpl;
import dao.Custom.impl.RoomDaoImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory(){}

    public static BoFactory getInstance(){
        return boFactory ==null?(boFactory =new BoFactory()): boFactory;
    }


    public <T> T getBo(BoType type){
        switch (type){
            case CUSTOMER:
                return (T) new CustomerBoImpl();
            case EMPLOYEE:
                return (T) new EmployeeBoImpl();
            case MEAL:
                return (T) new MelBoImpl();
            case ROOM:
                return (T) new RoomBoImpl();
            case ADMIN:
                return (T) new AdminRegisterBoImpl();

            case RECEPTIONIST:
                return (T) new ReceptionRegisterBoImpl();

            case BOOKING:
                return (T) new BookingBoImpl();

            case BOOKINGDETAILS:
                return (T) new BookingDetailsBoImpl();

            case MEALORDER:
                return (T) new MealOrderBoImpl();

            case QUERY:
             return (T) new QueryBoImpl();

            default:
                return null;

        }

    }
}
