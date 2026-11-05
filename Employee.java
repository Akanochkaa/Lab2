public class Employee {
    private String name;
    private Department department;

    public Employee(String name) {
        this.name = name;
    }

    public void setDepartment(Department department) {
        this.department = department;
        department.addEmployee(this);
    }

    public Department getDepartment() { return department; }
    public String getName() { return name; }

    @Override
    public String toString() {
        if (department == null) return name + " (без отдела)";

        if (department.getBoss() == this) {
            return name + " начальник отдела " + department.getName();
        }
        String bossName = (department.getBoss() != null) ? department.getBoss().getName() : "нет";
        return name + " работает в отделе " + department.getName() + ", начальник которого " + bossName;
    }
}