import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by User on 16.04.2017.
 */
public class main {
    private static final String db_url = "jdbc:mysql://localhost:3306/provider?useUnicode=true&characterEncoding=UTF-8";
    private static final String db_user = "root";
    private static final String db_password = "123";

    public static DatabaseController db;

    public static Manager activeManager;

    public static void main(String args[]){
        db = new DatabaseController(db_url,db_user,db_password);

        AuthForm.showForm();
    }

    public static String Backupdbtosql() {
        try {

        /*NOTE: Getting path to the Jar file being executed*/
        /*NOTE: YourImplementingClass-> replace with the class executing the code*/
            CodeSource codeSource = main.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();


        /*NOTE: Creating Database Constraints*/
            String dbName = "provider";
            String dbUser = "root";
            String dbPass = "123";

        /*NOTE: Creating Path Constraints for folder saving*/
        /*NOTE: Here the backup folder is created for saving inside it*/
            String folderPath = jarDir + "\\backup";

        /*NOTE: Creating Folder if it does not exist*/
            File f1 = new File(folderPath);
            f1.mkdir();

        /*NOTE: Creating Path Constraints for backup saving*/
        /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            LocalDateTime now = LocalDateTime.now();
            String savePath = "\"" + jarDir + "\\backup\\" + "backup" + dtf.format(now) + ".sql\"";

        /*NOTE: Used to create a cmd command*/
            String executeCmd = "\"C:\\Program Files\\MySQL\\MySQL Workbench 6.3 CE\\mysqldump.exe\" -u" + dbUser + " -p" + dbPass + " --databases " + dbName + " --routines -r " + savePath;

        /*NOTE: Executing the command here*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

        /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                System.out.println("Backup Complete");
                return savePath;
            } else {
                System.out.println("Backup Failure");
                return "fail";
            }

        } catch (URISyntaxException | IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
        }

        return "fail";
    }

    public static void Restoredbfromsql(String s) {
        try {
            /*NOTE: String s is the mysql file name including the .sql in its name*/
            /*NOTE: Getting path to the Jar file being executed*/
            /*NOTE: YourImplementingClass-> replace with the class executing the code*/
            CodeSource codeSource = main.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();

            /*NOTE: Creating Database Constraints*/
            String dbName = "provider";
            String dbUser = "root";
            String dbPass = "123";

            /*NOTE: Creating Path Constraints for restoring*/
            String restorePath = jarDir + "\\backup" + "\\" + s;

            /*NOTE: Used to create a cmd command*/
            /*NOTE: Do not create a single large string, this will cause buffer locking, use string array*/
            String[] executeCmd = new String[]{"\"C:\\Program Files\\MySQL\\MySQL Workbench 6.3 CE\\mysql.exe\"", dbName, "-u" + dbUser, "-p" + dbPass, "-e", " source " + restorePath};

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "База восстановлена");
            } else {
                JOptionPane.showMessageDialog(null, "Восстановление не удалось");
            }


        } catch (URISyntaxException | IOException | InterruptedException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error at Restoredbfromsql" + ex.getMessage());
        }

    }
}
