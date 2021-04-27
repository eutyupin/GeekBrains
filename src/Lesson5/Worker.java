package Lesson5;

public class Worker {

    private String name;
    private String surname;
    private String lastName;
    private String position;
    private String email;
    private String phone;
    private int age;
    private int salary;

    public Worker(String surname, String name, String lastName,
                  String position, String email, String phone, int age, int salary){
        this.name = name;
        this.surname = surname;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.salary = salary;
    }

    public void printPrivateData(){
        System.out.printf("Фамилия: %s\nИмя: %s\nОтчество: %s\nДолжность: %s\nE-mail: %s\nТелефон: %s\nВозраст: %d\nЗарплата: %d\n\n",
                surname, name, lastName, position, email, phone, age, salary);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
