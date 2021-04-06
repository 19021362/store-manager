package user;

public class User {
    protected String IdUser = null;
    protected String Username = null;
    protected String password = null;
    protected String Name = null;
    protected String jobTitle = null;
    protected long salary = 0;

    public User(String idUser, String username, String password, String name, String jobTitle, long salary) {
        IdUser = idUser;
        Username = username;
        this.password = password;
        Name = name;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    public User(String username, String password) {
        Username = username;
        this.password = password;
    }

    public User(User user) {
    }

    public void setIdUser(String idUser) {
        IdUser = idUser;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getIdUser() {
        return IdUser;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return Name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public long getSalary() {
        return this.salary;
    }

    @Override
    public String toString() {
        return "user{" +
                "IdUser='" + IdUser + '\'' +
                ", Username='" + Username + '\'' +
                ", password='" + password + '\'' +
                ", Name='" + Name + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", salary=" + salary +
                '}';
    }

}
