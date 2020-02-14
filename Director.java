package assignment3;

public class Director extends Manager {
    private String department;

    public Director(String id, String name, double salary, String type, String academicDegree, String department) {
        super(id, name, salary, type, academicDegree);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String newDepartment) {
        this.department = newDepartment;
    }

    @Override
    public double getGrossSalary() {
        return super.getGrossSalary() + reusaxCorp.getDirectorBenefit();
    }

    @Override
    public double getNetSalary() {
        if (this.getGrossSalary() < 30000) {
            return super.getNetSalary();

        } else if (this.getGrossSalary() >= 30000 && (this.getGrossSalary()) <= 50000) {
            return this.getGrossSalary() - (this.getGrossSalary()* 0.2);

        } else {
            return this.getGrossSalary() - (30000 * 0.2) - ((this.getGrossSalary() - 30000) * 0.4);
        }
    }

    public String printString() {
        return super.printString() + "\n" + "Department: " + this.getDepartment() +
                "\n" + "Benefit: " + reusaxCorp.getDirectorBenefit() + " SEK";
    }
}
