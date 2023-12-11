package controller;

import bo.BoFactory;
import bo.BoType;
import bo.Custom.BookingBo;
import bo.Custom.impl.BookingBoImpl;
import dao.Custom.BookingDao;
import dao.Custom.BookingDetailsDao;
import dao.Custom.impl.BookingDaoImpl;
import dao.Custom.impl.BookingDetailsDaoImpl;
import dao.DaoFactory;
import dao.DaoType;
import db.DBConnection;
import dto.BookingDetailsDto;
import dto.BookingDto;
import dto.CustomerDto;
import dto.RoomDto;
import entity.Booking;
import entity.BookingDetails;
import entity.Customer;
import entity.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.CartTm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

public class BookingFormController {
    public AnchorPane root;
    public ComboBox<String> cbmCustomerId;
    public TextField txtBookingId;
    public TextField BookingDate;
    public TextField txtName;
    public TextField txtCountry;
    public TextField txtEmail;
    public ComboBox<String>cmdRoomId;
    public TextField txtBedType;
    public TextField txtAvailbility;
    public TextField txtPrice;
    public TextField txtCleanStatus;
    public TableView tblCart;
    public TableColumn colCode;
    public TableColumn colBedType;
    public TableColumn colAvailability;
    public TableColumn colCleanStatus;
    public TableColumn colPrice;
    public TableColumn colTotal;
    public TableColumn colOption;
    public Label lblTotal;
    public TextField txtMobileNo;
    public TextField txtAge;
    public TextField txtGender;
    public TextField txtDayCount;
    public TableColumn colDayConut;

  //  BookingDao bookingDao = new BookingDaoImpl();
   // BookingDetailsDao bookingDetailsDao = new BookingDetailsDaoImpl();

   // BookingDao bookingDao= DaoFactory.getInstance().getDao(DaoType.BOOKING);

  //  BookingDetailsDao bookingDetailsDao=DaoFactory.getInstance().getDao(DaoType.BOOKINGDETAILS);

    private BookingBo bookingBo= BoFactory.getInstance().getBo(BoType.BOOKING);

    public void initialize(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBedType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("available"));
        colCleanStatus.setCellValueFactory(new PropertyValueFactory<>("cleaned"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDayConut.setCellValueFactory(new PropertyValueFactory<>("dayCount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadDate();
        loadCustomerId();

        loadRoomId();

        setBookingId();

        cbmCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null!=newValue){
                setCustomerDetails();
            }

        });

        cmdRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setIRoomDetails();
        });

    }

    private void setIRoomDetails() {

        try {
            ArrayList<RoomDto> arrayList=  bookingBo.bookingSetRoomDetails(cmdRoomId.getValue());

            for(RoomDto r:arrayList){
                txtBedType.setText(r.getRoomType());
                txtAvailbility.setText(r.getAvailable());
                txtCleanStatus.setText(r.getCleaned());
                txtPrice.setText(String.valueOf(r.getPrice()));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void loadDate() {
        BookingDate.setText(new SimpleDateFormat("yyy-MM-dd").format(new Date()));
    }

    private void setCustomerDetails() {

        try {

            ArrayList<CustomerDto> customerArrayList=bookingBo.bookingSetCustomerDetails(cbmCustomerId.getValue());
            for (CustomerDto c:customerArrayList){
                txtName.setText(c.getName());
                txtCountry.setText(c.getCountry());
                txtEmail.setText(c.getEmail());
                txtMobileNo.setText(c.getMobileNo());
                txtAge.setText(c.getAge());
                txtGender.setText(c.getGender());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadRoomId() {

        try {

           ResultSet set = bookingBo.bookingLoadRoomId();

            ArrayList<String>roomList=new ArrayList<>();
            while (set.next()){
                roomList.add(set.getString(1));
            }
            ObservableList<String> obList= FXCollections.observableArrayList(roomList);
            cmdRoomId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerId() {

        try {
           ResultSet set= bookingBo.bookingLoadCustomerId();
            ArrayList<String>customerList=new ArrayList<>();
            while (set.next()){
                customerList.add(set.getString(1));
            }
            ObservableList<String> oblist= FXCollections.observableArrayList(customerList);
            cbmCustomerId.setItems(oblist);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void btnBackToHomeAction(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ReceptionistDashBoardForm.fxml"))));
    }

    ObservableList<CartTm>obList=FXCollections.observableArrayList();
    public void btnAddTocardOnAction(ActionEvent actionEvent) {
        double price = Double.parseDouble(txtPrice.getText());
        int dayCount= Integer.parseInt(txtDayCount.getText());
        double total=price*dayCount;
        Button btn=new Button("Delete");
        int row= isAlerdyExists(cmdRoomId.getValue());

        if(row==-1){
            CartTm tm=new CartTm(cmdRoomId.getValue(),txtBedType.getText(),txtAvailbility.getText(),
                    txtCleanStatus.getText(),price,dayCount,total,btn);
            obList.add(tm);
            tblCart.setItems(obList);

        }else {
            int tempDayCount = obList.get(row).getDayCount() + dayCount;
            double temtotal = price * tempDayCount;
            obList.get(row).setDayCount(tempDayCount);
            obList.get(row).setTotal(temtotal);
            tblCart.refresh();
        }
        calculateTotal();
        clearField();
        cmdRoomId.requestFocus();


        btn.setOnAction(event -> {
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Do you want delete",ButtonType.YES,ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();

            if(buttonType.get()==ButtonType.YES){
                for (CartTm tm:obList
                ) {
                   // if(tm.getId().equals(tm.getId())){
                        obList.remove(tm);
                        calculateTotal();
                        tblCart.refresh();
                        return;
                 //   }
                }
            }
        });


    }

    private int isAlerdyExists(String code) {
        for (int i = 0; i <obList.size(); i++) {
            if(obList.get(i).getId().equals(code)){
                return i;
            }

        }
        return -1;
    }

    public void jesperReport(){
        String billPath = "E:\\GDSE\\New folder (2)\\System 2\\Report\\Blank_A4.jrxml";
        String sql = "select c.id as id,c.name as name,bd.RoomId as RoomId,bd.price as Price,bd.dayCount as dayCount,b.totalCost as totalCost from bookingdetails bd inner join booking b on bd.bookingId = b.bookingid inner join customer c on b.customer where b.bookingId = '"+txtBookingId.getText()+"'";
        JasperDesign jasdi = null;
        String savePath = "E:\\GDSE\\New folder (2)\\System 2\\saveReport" + LocalDate.now().getYear() + LocalDate.now().getMonth().toString() + LocalDate.now().getDayOfMonth() + LocalTime.now().getHour() + LocalTime.now().getMinute() + LocalTime.now().getSecond() + ".pdf";
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("total",lblTotal.getText());
        try {
            jasdi = JRXmlLoader.load(billPath);
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jasdi.setQuery(newQuery);
            JasperReport js = JasperCompileManager.compileReport(jasdi);
            JasperPrint jp = JasperFillManager.fillReport(js, hm, DBConnection.getInstance().getConnection());
             JasperExportManager.exportReportToPdfFile(jp, savePath);
            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.show();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }




    public void btnOrderBookingOnAction(ActionEvent actionEvent) throws SQLException {
        if (obList.isEmpty()) return;


        ArrayList<BookingDetailsDto> details = new ArrayList<>();

        for (CartTm tm : obList) {
            details.add(new BookingDetailsDto(tm.getId(),txtBookingId.getText() ,tm.getAvailable(), tm.getPrice(), tm.getDayCount()));

        }


      /*  Booking b = new Booking(
                txtBookingId.getText(), new Date(), cbmCustomerId.getValue(),
                Double.parseDouble(lblTotal.getText()), details);*/



                bookingBo.bookingOrderSave(new BookingDto(
                        txtBookingId.getText(),
                        new Date(),
                        cbmCustomerId.getValue(),
                        Double.parseDouble(lblTotal.getText())
                ),details);
                jesperReport();
                clearAll();



      /*  Connection con=null;

        try {

            con=DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);


           {

           boolean add= bookingDao.save(
                   new Booking(txtBookingId.getText(),new Date(),cbmCustomerId.getValue(), Double.parseDouble(lblTotal.getText())));

            if(add){
                   boolean isAllUpdate= manageRoom(details);
                   if(isAllUpdate){
                     con.commit();
                       jesperReport();
                       new Alert(Alert.AlertType.INFORMATION," All Update").show();
                       clearAll();
                   }else {
                       con.setAutoCommit(true);
                       con.rollback();
                       new Alert(Alert.AlertType.INFORMATION," Try again").show();

                   }

                }else {
                    con.setAutoCommit(true);
                    con.rollback();
                    new Alert(Alert.AlertType.INFORMATION," Not Update").show();

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            con.setAutoCommit(true);
        }
    }

    private boolean manageRoom(ArrayList<BookingDetails>details) {

        try{

            for (BookingDetails d:details) {



            boolean isBookingDetailsSaved=bookingDetailsDao.save(new BookingDetails(d.getRoomId(),txtBookingId.getText(),d.getAvailability(),d.getPrice(),d.getDayCount()));

                if(isBookingDetailsSaved){
                    boolean isAvailabilityUpdate=update(d);
                    if(!isAvailabilityUpdate){
                        return false;
                    }

                }else {
                    return false;
                }



            }



        }catch (SQLException  e){
            e.printStackTrace();
        }



        return true;
    }

    private boolean update(BookingDetails d) {


        try {

          return   bookingDetailsDao.update(new BookingDetails(d.getRoomId()));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }*/
    }

    private void setBookingId() {

        try {

           ResultSet resultSet= bookingBo.bookingOrderId();
            boolean next = resultSet.next();
            if (next) {
                String userId = resultSet.getString(1);

                userId = userId.substring(1, userId.length());
                int intId = Integer.parseInt(userId);

                intId++;

                if (intId < 10) {
                    txtBookingId.setText("B00" + intId);

                } else if (intId < 100) {
                    txtBookingId.setText("B0" + intId);

                } else
                    txtBookingId.setText("B" + intId);


            } else {
                txtBookingId.setText("B001");


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public void clearField(){
        cmdRoomId.setValue(null);
        txtBedType.clear();
        txtCleanStatus.clear();
        txtAvailbility.clear();
        txtPrice.clear();
        txtDayCount.clear();
    }

    public void calculateTotal(){
        double total=0.0;
        for (CartTm tm:obList
             ) {
            total+=tm.getTotal();

        }
        lblTotal.setText(String.valueOf(total));

    }

    public void clearAll(){
        obList.clear();
        calculateTotal();

        txtName.clear();
        txtAge.clear();
        txtCountry.clear();
        txtMobileNo.clear();
        txtEmail.clear();
        txtGender.clear();

        cbmCustomerId.setValue(null);
        cmdRoomId.setValue(null);

        setBookingId();
        clearField();

        cbmCustomerId.requestFocus();

    }
}
