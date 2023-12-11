package view.tm;

import javafx.scene.control.Button;

public class EmployeeTm {
    private String id;
    private String Ename;
    private String idNumber;
    private String email;
    private String job;
    private String number;
    private String age;
    private double salary;
    private Button btn;

    public EmployeeTm() {
    }

    public EmployeeTm(String id, String ename, String idNumber, String email, String job, String number, String age, double salary, Button btn) {
        this.setId(id);
        setEname(ename);
        this.setIdNumber(idNumber);
        this.setEmail(email);
        this.setJob(job);
        this.setNumber(number);
        this.setAge(age);
        this.setSalary(salary);
        this.setBtn(btn);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEname() {
        return Ename;
    }

    public void setEname(String ename) {
        Ename = ename;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
