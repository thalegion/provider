import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Client {

    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String contract_num;
    private ArrayList<Order> orders;

    public Client() {
        this.id = 0;
        this.name = "";
        this.phone = "";
        this.email = "";
        this.address = "";
        this.contract_num = "";
        this.orders = new ArrayList<Order>();
    }

    public Client(int id) {
        try {
            ResultSet rs = null;

            rs = main.db.select("*","clients","id = ?",new String[] {String.valueOf(id)},"","1");
            rs.next();

            this.id = rs.getInt("id");
            this.name = rs.getString("name");
            this.phone = rs.getString("phone");
            this.email = rs.getString("email");
            this.address = rs.getString("address");
            this.contract_num = rs.getString("contract_num");

            this.orders = new ArrayList<Order>();

            main.db.closeStatementSet(rs);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public Client(ResultSet rs) {
        try {
            this.id = rs.getInt("id");
            this.name = rs.getString("name");
            this.phone = rs.getString("phone");
            this.email = rs.getString("email");
            this.address = rs.getString("address");
            this.contract_num = rs.getString("contract_num");

            this.orders = new ArrayList<Order>();

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }
    public String getContract_num() {
        return contract_num;
    }
    public int getOrdersCount(){
        return orders.size();
    }
    public ArrayList<Order> getOrders() {return orders;}

    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setContract_num(String contract_num) {
        this.contract_num = contract_num;
    }

    public void fillOrders() {
        orders.clear();

        ResultSet ordersSet = null;
        try {
            ordersSet = main.db.select("*", "documents", "client_id = " + this.id, "date_of_creation desc", "");
            while (ordersSet.next()) {
                this.orders.add(new Order(ordersSet));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            main.db.closeStatementSet(ordersSet);
        }

    }

    public boolean save() {
        if (id == 0) {
            id = main.db.insert("clients", "name,phone,email,address,contract_num", new String[]{name,phone,email,address,contract_num});
            if (id > 0)
                return true;
        }
        else {
            if (main.db.update("clients", new String[]{"name","phone","email","address","contract_num"}, new String[]{name,phone,email,address,contract_num}, "id = ?", new String[]{String.valueOf(id)}) > 0)
                return true;
        }

        return false;
    }

    public boolean delete() {
        if (id == 0)
            return false;

        if (main.db.delete("clients","id = ?",new String[] {String.valueOf(id)}) > 0)
            return true;

        return false;
    }

}
