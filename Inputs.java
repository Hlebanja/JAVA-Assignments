package assignment3;

import java.util.Scanner;

public class Inputs {

    private Scanner sc = new Scanner(System.in);

    //These methods are used to get user input and are here just to avoid repetition

    protected String readEmployeeID() {
        System.out.print("Type the ID of the employee: ");
        String id = sc.nextLine();
        return id;
    }

    protected String readEmployeeName() {
        System.out.print("Type the name of the employee: ");
        String name = sc.nextLine();
        return name;
    }

    protected Double readEmployeeSalary() {
        System.out.print("Type the salary of the employee: ");
        double salary = sc.nextDouble();
        sc.nextLine();
        return salary;
    }

    protected String readEmployeeType() {
        String type;

        do {
            System.out.println("Please enter the type" + "\n" +
                    "Employee, Manager, Director or Intern: ");
            type = sc.nextLine();
        } while (!(type.equalsIgnoreCase("Employee")
                || type.equalsIgnoreCase("Manager")
                || type.equalsIgnoreCase("Director")
                || type.equalsIgnoreCase("Intern")));

        return type;
    }

    protected String readEmployeeDegree() {
        String degree;

        do {
            System.out.println("Type the educational degree of the employee");
            System.out.print("BSc, MSc or PhD: ");
            degree = sc.nextLine();
        } while (!(degree.equalsIgnoreCase("BSc")
                || degree.equalsIgnoreCase("MSc")
                || degree.equalsIgnoreCase("PhD")));
        return degree;
    }

    protected String readEmployeeDepartment() {
        String department;

        do {
            System.out.println("Type the specific department");
            System.out.print("Human Resources, Technical or Business: ");
            department = sc.nextLine();

        } while (!(department.equalsIgnoreCase("Human Resources")
                || department.equalsIgnoreCase("Technical")
                || department.equalsIgnoreCase("Business")));
        return department;
    }

    protected int readEmployeeGpa() {
        int gpa;
        do {
            System.out.print("Type the GPA of the employee (between 0 and 10): ");
            gpa = sc.nextInt();
            sc.nextLine();
        } while (gpa < 0 || gpa > 10);
        return gpa;
    }

}
