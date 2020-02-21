package assignment3;

public class Manager extends Employee {
    private String academicDegree;


    protected Manager(String id, String name, double salary, String type, String academicDegree) {
        super(id, name, salary, type);
        this.academicDegree = academicDegree;
    }

    protected String getAcademicDegree() {
        return this.academicDegree;
    }

    public void setAcademicDegree(String newAcademicDegree) {
        this.academicDegree = newAcademicDegree;
    }

    protected double calcManagerBonus() {
        if (this.academicDegree.equalsIgnoreCase("BSc")) {
            return getSalary() * 0.10;
        } else if (this.academicDegree.equalsIgnoreCase("MSc")) {
            return getSalary() * 0.20;
        } else if (this.academicDegree.equalsIgnoreCase("PhD")) {
            return getSalary() * 0.35;
        } else {
            return 0;
        }
    }

    @Override
    public double getGrossSalary() {
        return this.getSalary() + calcManagerBonus();
    }

    @Override
    public  double getNetSalary() {
        return getGrossSalary() - (getGrossSalary() * 0.1);
    }

    public String printString() {
        return super.printString() + "\n" +
                "Bonus: " + this.calcManagerBonus() + " SEK"
                + "\n" + "Academic degree: " + this.getAcademicDegree();
    }
}


