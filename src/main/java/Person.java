public class Person {

    //Set all the field a person can have
    //***********************************
    private String name;
    private String gender;
    private String address;
    private String klass;
    private int age;
    private int id;
    private String role;
    private String StaffType;


    //Constructor Overloaded
    //This constructor is used to set student/applicant information
    //***************************************************
    public Person(String name, String gender, String address, String klass, int age) {
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.klass = klass;
        this.age = age;
    }

    //This constructor is used to set teacher information
    //***************************************************
    public Person(String name, String gender, String address, String role, String klass) {
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.klass = klass;
        this.role = role;
    }

    //This constructor is used to set non academic staff information
    //**************************************************************
    public Person(String name, String gender, String address, String role) {
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.role = role;
    }

    //Get all the field of a person
    //*****************************
    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getKlass() {
        return klass;
    }

    public int getAge() {
        return age;
    }

    public String getRole() {
        return role;
    }

    //Address and Role of a person can change, set it to the new one
    //**************************************************************
    public void setAddress(String address) {
        this.address = address;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return
                //String.format("name= %s", getName());
                String.format(getName());
    }

}


