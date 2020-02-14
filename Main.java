package assignment3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        reusaxCorp program = new reusaxCorp();
        Inputs inputs = new Inputs();
        int option;

        program.createEmployee("1", "Thiago", 100, "Employee");
        program.createEmployee("2", "Linda", 100, "Employee");

        program.createManager("3", "Simon", 100, "Manager", "BSc");
        program.createManager("4", "Yue", 100, "Manager", "PhD");

        program.createDirector("5", "Francisco", 100,
                "Director", "MSc", "Human resources");
        program.createDirector("6", "Jonnes", 100,
                "Director", "PhD", "Business");

        program.createIntern("7", "Bob", 100, "Intern", 5);
        program.createIntern("8", "Lana", 100, "Intern", 6);
        program.createIntern("9", "Carla", 100, "Intern", 8);

        do {
            System.out.println("");
            System.out.println("**********************");
            System.out.println("Welcome to ReusaxCorp");
            System.out.println("**********************");
            System.out.println("Choose an option below: ");
            System.out.println(" ");
            System.out.println(" 1. Register an employee. ");
            System.out.println(" 2. Print a specific employee ");
            System.out.println(" 3. Print all employees. ");
            System.out.println(" 4. Print expenses with net salaries ");
            System.out.println(" 5. Print expenses with gross salaries ");
            System.out.println(" 6. Print total number of employee ");
            System.out.println(" 7. Update employee name ");
            System.out.println(" 8. Update employee salary ");
            System.out.println(" 9. Update employee degree "); //
            System.out.println(" 10. Update employee GPA ");
            System.out.println(" 11. Update director's benefit. ");
            System.out.println(" 12. Promote to employee. ");
            System.out.println(" 13. Promote to Manager. ");
            System.out.println(" 14. Promote to Intern. ");
            System.out.println(" 15. Promote to Director. ");
            System.out.println(" 16. Remove an employee. ");
            System.out.println(" 17. quit this program ");

            option = sc.nextInt();
            sc.nextLine();

            Employee foundEmployee;
            String name;
            double salary;
            String id;

            //for the promotions
            String tempId;
            String tempName;
            double tempSalary;
            String tempDegree;
            String tempDepartment;
            int tempGpa;

            switch (option) {

                //register employee
                case 1:

                    do {
                        id = inputs.readEmployeeID();
                        foundEmployee = program.retrieveEmployee(id);
                        if (foundEmployee != null) {
                            System.out.println("Error: ID already taken");
                        }
                    } while (foundEmployee != null);
                    do {
                        name = inputs.readEmployeeName();
                    } while (name == null);
                    do {
                        salary = inputs.readEmployeeSalary();
                    } while (salary < 0);

                    String type = inputs.readEmployeeType();

                    if (type.equalsIgnoreCase("Employee")) {
                        System.out.println(program.createEmployee(id, name, salary, type));
                    }

                    if (type.equalsIgnoreCase("manager")) {
                        String degree = inputs.readEmployeeDegree();
                        System.out.println(program.createManager(id, name, salary, type, degree));
                    }

                    if (type.equalsIgnoreCase("Director")) {
                        String degree = inputs.readEmployeeDegree();
                        String department = inputs.readEmployeeDepartment();
                        program.createDirector(id, name, salary, type, degree, department);
                    }

                    if (type.equalsIgnoreCase("Intern")) {
                        int gpa = inputs.readEmployeeGpa();
                        program.createIntern(id, name, salary, type, gpa);
                    }
                    break;

                //print employee
                case 2:
                    id = inputs.readEmployeeID();
                    foundEmployee = program.retrieveEmployee(id);
                    if (foundEmployee == null) {
                        System.out.println("An employee of ID " + id + " is not registered in the system.");
                    } else {
                        System.out.println(foundEmployee.printString());
                    }
                    break;

                case 3:
                    program.printAllEmployees();
                    break;

                case 4:
                    System.out.println(program.calcExpensesNet());
                    break;

                case 5:
                    System.out.println(program.calcExpensesGross());
                    break;

                case 6:
                    System.out.println(program.numberOfEmployees());
                    break;

                //Update name
                case 7:
                    String newName;
                    id = inputs.readEmployeeID();
                    foundEmployee = program.retrieveEmployee(id);
                    if (foundEmployee == null) {
                        System.out.println("Error: no employee with id: " + id + " was found");
                    } else {
                        newName = inputs.readEmployeeName();
                        program.updateName(foundEmployee, newName);
                        System.out.println("updated employee " + foundEmployee.getId() + ":" + " new name is " + newName);
                    }
                    break;

                //Update salary
                case 8:
                    id = inputs.readEmployeeID();
                    foundEmployee = program.retrieveEmployee(id);
                    if (foundEmployee == null) {
                        System.out.println("Error: no employee with id: " + id + " was found");
                    } else {
                        System.out.println("Enter new gross salary");
                        double newSalary = inputs.readEmployeeSalary();
                        program.updateSalary(foundEmployee, newSalary);
                        System.out.println("updated employee " + foundEmployee.getId() + ":" + " new salary is " + newSalary);
                    }
                    break;

                //update Degree
                case 9:
                    id = inputs.readEmployeeID();
                    foundEmployee = program.retrieveEmployee(id);
                    if (foundEmployee == null) {
                        System.out.println("Error: no employee with id: " + id + " was found");
                    } else if (foundEmployee instanceof Manager) {
                        tempDegree = inputs.readEmployeeDegree();
                        program.updateDegree(foundEmployee, tempDegree);
                        System.out.println("updated employee " + foundEmployee.getId() + " degree to " + tempDegree);
                    } else {
                        System.out.println("Error: only Managers and Directors have a degree");
                    }
                    break;

                //update GPA
                case 10:
                    id = inputs.readEmployeeID();
                    foundEmployee = program.retrieveEmployee(id);
                    if (foundEmployee == null) {
                        System.out.println("Error: no employee with id: " + id + " was found");
                    } else if (foundEmployee instanceof Intern) {
                        tempGpa = inputs.readEmployeeGpa();
                        program.updateGpa(foundEmployee, tempGpa);
                        System.out.println("updated employee " + foundEmployee.getId() + " GPA to " + tempGpa);
                    } else {
                        System.out.println("Error: only Interns have a GPA");
                    }
                    break;

                //Update director's benefit
                case 11:
                    System.out.print("Type the amount of the benefit: ");
                    double newBenefit = sc.nextDouble();
                    sc.nextLine();
                    program.setDirectorBenefit(newBenefit);
                    System.out.println("New Director's benefit is set to: " + newBenefit + " SEK");
                    break;

                //Promote to employee
                case 12:
                    id = inputs.readEmployeeID();
                    foundEmployee = program.retrieveEmployee(id);
                    if (foundEmployee == null) {
                        System.out.println("Error: no employee was found");
                    } else if (!(foundEmployee instanceof Manager || foundEmployee instanceof Intern)) {
                        System.out.println("Error: this is already an employee!");
                    } else {
                        tempId = foundEmployee.getId();
                        tempName = foundEmployee.getName();
                        tempSalary = foundEmployee.getSalary();
                        program.promoteToEmployee(tempId, tempName, tempSalary, foundEmployee);
                        System.out.println("Employee " + tempId + " got promoted to a regular Employee");
                    }
                    break;

                //Promote to manager
                case 13:
                    id = inputs.readEmployeeID();
                    foundEmployee = program.retrieveEmployee(id);
                    if (foundEmployee == null) {
                        System.out.println("Error: no employee was found");
                    } else {
                        tempId = foundEmployee.getId();
                        tempName = foundEmployee.getName();
                        tempSalary = foundEmployee.getSalary();

                        if (foundEmployee instanceof Director) {
                            tempDegree = ((Director) foundEmployee).getAcademicDegree();
                            program.promoteToManager(tempId, tempName, tempSalary, tempDegree, foundEmployee);
                            System.out.println("Employee " + tempId + " got promoted to a Manager");

                        } else if (foundEmployee instanceof Manager) {
                            System.out.println("Error: the employee is already a Manager!");

                        } else {
                            tempDegree = inputs.readEmployeeDegree();
                            program.promoteToManager(tempId, tempName, tempSalary, tempDegree, foundEmployee);
                            System.out.println("Employee " + tempId + " got promoted to a Manager");
                        }
                    }
                    break;

                //promote to Intern
                case 14:
                    id = inputs.readEmployeeID();
                    foundEmployee = program.retrieveEmployee(id);
                    if (foundEmployee == null) {
                        System.out.println("Error: no employee was found");
                    } else if (foundEmployee instanceof Intern) {
                        System.out.println("Error: employee is already an Intern!");
                    } else {
                        tempId = foundEmployee.getId();
                        tempName = foundEmployee.getName();
                        tempSalary = foundEmployee.getSalary();
                        tempGpa = inputs.readEmployeeGpa();
                        program.promoteToIntern(tempId, tempName, tempSalary, tempGpa, foundEmployee);
                        System.out.println("Employee " + tempId + " got promoted to an Intern");
                    }
                    break;

                //promote to Director
                case 15:
                    id = inputs.readEmployeeID();
                    foundEmployee = program.retrieveEmployee(id);
                    if (foundEmployee == null) {
                        System.out.println("Error: no employee was found");
                    } else if (foundEmployee instanceof Director) {
                        System.out.println("Error: the employee is already a Director!");
                    } else {
                        tempId = foundEmployee.getId();
                        tempName = foundEmployee.getName();
                        tempSalary = foundEmployee.getSalary();
                        if (foundEmployee instanceof Manager) {
                            tempDegree = ((Manager) foundEmployee).getAcademicDegree();
                            tempDepartment = inputs.readEmployeeDepartment();
                            program.promoteToDirector(tempId, tempName, tempSalary, tempDegree, tempDepartment, foundEmployee);
                            System.out.println("Employee " + tempId + " got promoted to a Director");
                        } else {
                            tempDegree = inputs.readEmployeeDegree();
                            tempDepartment = inputs.readEmployeeDepartment();
                            program.promoteToDirector(tempId, tempName, tempSalary, tempDegree, tempDepartment, foundEmployee);
                            System.out.println("Employee " + tempId + " got promoted to a Director");
                        }
                    }
                    break;

                case 16:
                    id = inputs.readEmployeeID();
                    foundEmployee = program.retrieveEmployee(id);
                    if (foundEmployee == null) {
                        System.out.println("Error: can't find employee");
                    } else {
                        System.out.println(program.removeEmployee(foundEmployee));
                    }
                    break;

                case 17:
                    System.out.println("Bye have a nice day");
                    System.out.println();
                    break;

                default:
                    System.out.println("Option " + option + " is not valid.");
                    System.out.println();
                    break;
            }
        }
        while (option != 17);
    } // main bracket
}

