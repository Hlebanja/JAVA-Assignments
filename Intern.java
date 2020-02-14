package assignment3;

public class Intern extends Employee {
    private int gpa;

    public Intern(String id, String name, double salary, String type, int gpa) {      //constructor
        super(id, name, salary, type);
        this.gpa = gpa;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(int newGpa) {
        this.gpa = newGpa;
    }

    public double calcInternSalary() {
        if (this.gpa <= 5) {
            return 0;
        } else if (this.gpa < 8) {
            return getSalary();
        } else {
            return getSalary() + 1000;
        }
    }

    @Override
    public double getGrossSalary() {
        return calcInternSalary();
    }

    @Override
    public double getNetSalary() {
        return calcInternSalary();
    }

    @Override
    public String printString() {
        return "\n" + "ID: " + this.getId() + "\n" + "Name: " + this.getName()
                + "\n" + "Salary: " + calcInternSalary() + " SEK"
                + "\n" + "Position: " + this.getType()
                + "\n" + "GPA: " + this.getGpa();
    }
}
