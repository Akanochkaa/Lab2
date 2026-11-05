public class Name {
    private String lastName;
    private String firstName;
    private String middleName;


    public Name(String firstName) {
        this.firstName = firstName != null ? firstName.trim() : "";
        this.lastName = "";
        this.middleName = "";
    }

    public Name(String firstName, String lastName) {
        this.firstName = firstName != null ? firstName.trim() : "";
        this.lastName = lastName != null ? lastName.trim() : "";
        this.middleName = "";
    }

    public Name(String firstName, String lastName, String middleName) {
        this.firstName = firstName != null ? firstName.trim() : "";
        this.lastName = lastName != null ? lastName.trim() : "";
        this.middleName = middleName != null ? middleName.trim() : "";
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getMiddleName() { return middleName; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (!lastName.isEmpty()) {
            sb.append(lastName).append(" ");
        }
        if (!firstName.isEmpty()) {
            sb.append(firstName).append(" ");
        }
        if (!middleName.isEmpty()) {
            sb.append(middleName);
        }

        return sb.toString().trim();
    }
}