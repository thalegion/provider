import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by User on 16.04.2017.
 */
public class Manager {
    private int id;
    private String login;
    private String password;
    private String name;
    private String phone;
    private String email;
    private boolean sudo;
    private boolean deleted;

    private boolean passwordChanged;

    public Manager() {
        this.id = 0;
        this.login = "";
        this.password = "";
        this.name = "";
        this.phone = "";
        this.email = "";

        this.sudo = false;
        this.deleted = false;
        this.passwordChanged = false;
    }

    public Manager(int id) {
        try {
            ResultSet rs = null;

            rs = main.db.select("*","users","id = ?",new String[] {String.valueOf(id)},"","1");
            rs.next();

            this.id = rs.getInt("id");
            this.login = rs.getString("login");
            this.password = rs.getString("password");
            this.name = rs.getString("name");
            this.phone = rs.getString("phone");
            this.email = rs.getString("email");

            this.sudo = rs.getInt("sudo") == 1 ? true : false;
            this.deleted = rs.getInt("deleted") == 1 ? true : false;
            this.passwordChanged = false;

            main.db.closeStatementSet(rs);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public Manager(ResultSet rs) {
        try {
            this.id = rs.getInt("id");
            this.login = rs.getString("login");
            this.password = rs.getString("password");
            this.name = rs.getString("name");
            this.phone = rs.getString("phone");
            this.email = rs.getString("email");

            this.sudo = rs.getInt("sudo") == 1 ? true : false;
            this.deleted = rs.getInt("deleted") == 1 ? true : false;
            this.passwordChanged = false;
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public int getId() {
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }
    public String getName() {
        return this.name;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPhone() {
        return this.phone;
    }

    public boolean getSudo() {
        return this.sudo;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSudo (Boolean sudo) {
        this.sudo = sudo;
    }

    public String changePassword(String password) {
        this.password = password;
        this.passwordChanged = true;
        return password;
    }

    public boolean save() {
        if (id == 0) {
            id = main.db.insert("users", "login,password,name,phone,email,sudo,deleted", new String[]{login,password,name,phone,email,sudo?"1":"0",deleted?"1":"0"});
            if (id > 0)
                return true;
        }
        else {
            int result = 0;
            if (passwordChanged)
                result = main.db.update("users", new String[]{"login","password","name","phone","email","sudo","deleted"}, new String[]{login,password,name,phone,email,sudo?"1":"0",deleted?"1":"0"}, "id = ?", new String[]{String.valueOf(id)});
            else
                result = main.db.update("users", new String[]{"login","name","phone","email","sudo","deleted"}, new String[]{login,name,phone,email,sudo?"1":"0",deleted?"1":"0"}, "id = ?", new String[]{String.valueOf(id)});
            if (result > 0)
                return true;
        }

        return false;
    }

    public boolean delete() {
        if (id == 0)
            return false;

        if (main.db.update("users", new String[]{"deleted"}, new String[]{"1"}, "id = ?", new String[]{String.valueOf(id)}) > 0)
            return true;

        return false;
    }
}
