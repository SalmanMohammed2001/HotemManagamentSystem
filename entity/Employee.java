package entity;

public class Employee {
    private String id;
    private String Ename;
    private String idNumber;
    private String email;
    private String job;
    private String number;
    private String age;
    private double salary;


    public Employee() {
    }

    public Employee(String id, String ename, String idNumber, String email, String job, String number, String age, double salary) {
        this.id = id;
        Ename = ename;
        this.idNumber = idNumber;
        this.email = email;
        this.job = job;
        this.number = number;
        this.age = age;
        this.salary = salary;
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
}
