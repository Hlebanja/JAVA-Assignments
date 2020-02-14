package assignment3;

public class Employee { //Superclass

    final private String id;   //Attributes for class employee
    private String name;
    private double salary;
    private String type;

    protected Employee(String id, String name, double salary, String type){
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.type = type;
    }

    protected String getType() {
        return type;
    }

    protected void setType(String type) {
        this.type = type;
    }

    public Employee(){      //constructor
        this("930416","Simon",20000, "Employee");
    }

    protected String getId() {       //Set & Get
        return id;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    //redundant. We should use getGrossSalary for the rest of the code
    protected double getSalary() {
        return salary;
    }

    protected void setSalary(double salary) {
        this.salary = salary;
    }

    public double getGrossSalary() {
        return salary;
    }
    public double getNetSalary(){
        return salary - (salary * 0.1);
    }

    public String printString() {
        return "\n" + "ID: " + this.id + "\n" + "Name: " + this.name
                + "\n" + "Salary: " + this.salary + " SEK"
                + "\n" + "Position: " + this.getType();
    }
}
