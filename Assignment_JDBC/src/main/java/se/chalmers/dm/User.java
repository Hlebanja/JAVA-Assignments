package se.chalmers.dm;

public class User {
    public int ID;
    public String ssn;
    public String fName;
    public String lName;
    public String email;
    public Boolean isActive;

    User(int ID, String ssn, String fName, String lName, String email, Boolean isActive) {
        this.ID = ID;
        this.ssn = ssn;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return this.ID + ";" + this.ssn + ";" + this.fName + ";" + this.lName + email + isActive;
    }

}
