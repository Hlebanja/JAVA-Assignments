package assignment3;

import java.util.ArrayList;

/*
The limitation of inheritance when we are dealing with promoting (or demoting)
employees between the different employee types is that,
even though directors, managers and interns inherit - directly or indirectly - from employees,
we cannot simply change the type attribute to update the position. Hence we are forced to
create a new object with everything it must have in order to be created.

The good aspect of our code is that the methods themselves (promoteToEmployee, promoteToManager,
promoteToDirector, promoteToIntern) are very lean regarding the amount of lines and, provided the proper
arguments to the methods, they are reusable. On the other hand, we had to do a lot of input treatment and
a lot of repeated code in the interface class in order to make a functional interface getting inputs from the user,
which is definitely not the best nor even a good solution for this problem.

The multilevel inheritance (employee -> Manager -> Director)
caused difficulties with the debugging of the code at some points. Also, making changes in the super classes may cause
unpredictable behavior in the child classes. Tight coupling.
*/


public class reusaxCorp {

    private ArrayList<Employee> employeeArray;
    private static double benefit;

    protected reusaxCorp() {    //constructor
        this.employeeArray = new ArrayList<>();
    }

    // --- CREATE ---

    protected String createEmployee(String id, String name, double salary, String employeeType) {
        Employee newEmployee = new Employee(id, name, salary, employeeType);
        employeeArray.add(newEmployee);
        return newEmployee.printString();
    }

    protected String createManager(String id, String name, double salary, String employeeType, String degree) {
        Manager newManager = new Manager(id, name, salary, employeeType, degree);
        employeeArray.add(newManager);
        return newManager.printString();
    }

    protected String createDirector(String id, String name, double salary, String employeeType, String degree, String department) {
        Director newDirector = new Director(id, name, salary, employeeType, degree, department);
        employeeArray.add(newDirector);
        return newDirector.printString();
    }

    protected String createIntern(String id, String name, double salary, String employeeType, int gpa) {
        Intern newIntern = new Intern(id, name, salary, employeeType, gpa);
        employeeArray.add(newIntern);
        return newIntern.printString();
    }

    //Director benefit static getters and setters

    protected static void setDirectorBenefit(double newBenefit) {
        benefit = newBenefit;
    }

    protected static double getDirectorBenefit() {
        return benefit;
    }

    // --- READ ---

    //printAllEmployees was not required!
    protected void printAllEmployees() {
        for (Employee anEmployeeArray : employeeArray) {
            if (anEmployeeArray != null) {
                System.out.println(anEmployeeArray.printString());
            }
        }
    }

    protected Employee retrieveEmployee(String id) {
        for (Employee anEmployeeArray : employeeArray) {
            if (anEmployeeArray != null && anEmployeeArray.getId().equals(id)) {
                return anEmployeeArray;
            }
        }
        return null;
    }

    protected String numberOfEmployees() {
        if (employeeArray.isEmpty()) {
            return "No employee registered";
        } else {
            int size = 0;
            for (int i = 0; i < employeeArray.size(); i++) {
                size = employeeArray.size();
            }
            return "ReusaxCorp have: " + size + " employees";
        }
    }

    protected String calcExpensesNet() {
        if (employeeArray.isEmpty()) {
            return "No employee registered";
        } else {
            double sum = 0;
            int i;
            for (i = 0; i < employeeArray.size(); i++) {
                sum += employeeArray.get(i).getNetSalary();
            }
            return "ReusaxCorp net salary expenses: " + sum + " SEK";
        }
    }

    protected String calcExpensesGross() {
        if (employeeArray.isEmpty()) {
           return "No employee registered";
        } else {
            double sum = 0;
            int i;
            for (i = 0; i < employeeArray.size(); i++) {
                sum += employeeArray.get(i).getGrossSalary();
            }
            return "ReusaxCorp gross salary expenses: " + sum + " SEK";
        }
    }

    // --- UPDATE ---

    protected void updateName(Employee foundEmployee, String newName) {
        foundEmployee.setName(newName);
    }

    protected void updateSalary(Employee foundEmployee, double newSalary) {
        foundEmployee.setSalary(newSalary);
    }

    protected void updateGpa(Employee foundEmployee, int newGpa) {
        ((Intern) foundEmployee).setGpa(newGpa);
    }

    protected void updateDegree(Employee foundEmployee, String newDegree) {
        ((Manager) foundEmployee).setAcademicDegree(newDegree);
    }

    protected void promoteToEmployee(String tempId, String tempName, double tempSalary, Employee foundEmployee) {
        Employee newEmployee = new Employee(tempId, tempName, tempSalary, "Employee");
        employeeArray.remove(foundEmployee);
        employeeArray.add(newEmployee);
    }

    protected void promoteToManager(String tempId, String tempName, double tempSalary, String tempDegree, Employee foundEmployee) {
        Employee newEmployee = new Manager(tempId, tempName, tempSalary, "Manager", tempDegree);
        employeeArray.remove(foundEmployee);
        employeeArray.add(newEmployee);
    }

    protected void promoteToIntern(String tempId, String tempName, double tempSalary,int tempGpa, Employee foundEmployee) {
        Employee newEmployee = new Intern(tempId, tempName, tempSalary, "Intern", tempGpa);
        employeeArray.remove(foundEmployee);
        employeeArray.add(newEmployee);
    }

    protected void promoteToDirector(String tempId, String tempName, double tempSalary,
                                  String tempDegree, String tempDepartment, Employee foundEmployee) {
        Employee newEmployee = new Director(tempId, tempName, tempSalary, "Director", tempDegree, tempDepartment);
        employeeArray.remove(foundEmployee);
        employeeArray.add(newEmployee);
    }

    // --- DELETE ---

    protected String removeEmployee(Employee foundEmployee) {
        if (employeeArray == null) {
           return "Error: No employee registered";
        } else {
            employeeArray.remove(foundEmployee);
            return foundEmployee.getId() + " was successfully removed";
        }
    }

}