import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class Order {

    private int id;
    private String link;
    private Date date;
    private Library status;
    private Library type;
    private Client client;
    private Manager manager;
    private Date managed_date;


    public Order() {
        id = 0;
        date = new Date();
    }

    public Order(int id) {
        ResultSet rs = null;

        try {
            rs = main.db.select("*","documents","id = ?",new String[] {String.valueOf(id)},"","1");
            rs.next();

            this.id = rs.getInt("id");
            link = rs.getString("link");
            date = new Date(rs.getLong("date_of_creation") * 1000);
            managed_date = new Date(rs.getLong("managed_date") * 1000);
            status = new Library(rs.getInt("managed_status"),"document_statuses");
            type = new Library(rs.getInt("type"),"document_types");
            client = new Client(rs.getInt("client_id"));
            manager = new Manager(rs.getInt("managed_by"));

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            main.db.closeStatementSet(rs);
        }
    }

    public Order(ResultSet rs) {
        try{
            this.id = rs.getInt("id");
            link = rs.getString("link");
            date = new Date(rs.getLong("date_of_creation") * 1000);
            managed_date = new Date(rs.getLong("managed_date") * 1000);
            status = new Library(rs.getInt("managed_status"),"document_statuses");
            type = new Library(rs.getInt("type"),"document_types");
            client = new Client(rs.getInt("client_id"));
            manager = new Manager(rs.getInt("managed_by"));
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public int getId(){
        return id;
    }

    public Client getClient() {
        return client;
    }
    public String getLink() {return link;}
    public Date getDate() {
        return date;
    }
    public Date getManagedDate() {
        return managed_date;
    }
    public Library getStatus() {
        return status;
    }
    public Library getType() {
        return type;
    }
    public Manager getManager() {return manager;}

    public void setLink(String link) {this.link = link;}
    public void setClient(Client client) {
        this.client = client;
    }
    public void setManager(Manager mng) {this.manager = mng;}
    public void setStatus(Library status) {
        this.status = status;
    }
    public void setType(Library type) {
        this.type = type;
    }

    public boolean save() {
        if (id == 0) {
            id = main.db.insert("documents", "link,client_id,managed_by,managed_status,type",
                    new String[]{link,String.valueOf(client.getId()),String.valueOf(manager.getId()),String.valueOf(status.getId()),String.valueOf(type.getId())});
            if (id > 0)
                return true;
        }
        else {
            if (main.db.update("documents", new String[]{"link","managed_status","type"}, new String[]{link,String.valueOf(status.getId()),String.valueOf(type.getId())}, "id = ?", new String[]{String.valueOf(id)}) > 0)
                return true;
        }

        return false;
    }

    public boolean delete() {
        if (id == 0)
            return false;

        if (main.db.delete("documents","id = ?",new String[] {String.valueOf(id)}) > 0)
            return true;

        return false;
    }
}
