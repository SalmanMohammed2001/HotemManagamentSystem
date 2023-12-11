package entity;

public class Customer {
    private String id;
    private String name;
    private String country;
    private String email;
    private String mobileNo;
    private String age;
    private String gender;


    public Customer() {

    }

    public Customer(String name, String country, String email, String mobileNo, String age, String gender) {
        this.name = name;
        this.country = country;
        this.email = email;
        this.mobileNo = mobileNo;
        this.age = age;
        this.gender = gender;
    }

    public Customer(String id, String name, String country, String email, String mobileNo, String age, String gender) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.email = email;
        this.mobileNo = mobileNo;
        this.age = age;
        this.gender = gender;
    }




    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
