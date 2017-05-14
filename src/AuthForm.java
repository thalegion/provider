import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by User on 16.04.2017.
 */
public class AuthForm {
    static void showForm() {

        JFrame startFrame = new JFrame("Вход | Интернет-провайдер");
        startFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            startFrame.setIconImage(ImageIO.read(new File("out/production/provider/images/diamond_ico.jpg")));
        } catch (IOException ie) {
            ie.printStackTrace();
        }

        startFrame.setSize(600,400);
        startFrame.setLocationRelativeTo(null);

        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;

        JLabel errorFieldLabel = new JLabel();
        errorFieldLabel.setForeground(Color.RED);
        JLabel loginFieldLabel = new JLabel("Логин:");
        JTextField loginField = new JTextField("",10);
        JLabel passwordFieldLabel = new JLabel("Пароль:");
        JPasswordField passwordField = new JPasswordField("",10);
        JButton submitButton = new JButton("Вход");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet loggedSet = null;

                errorFieldLabel.setText("");
                String login = loginField.getText();
                String password = String.valueOf(passwordField.getPassword());

                if ((login.length() > 0) && (password.length() > 0)) {
                    try {
                        loggedSet = main.db.select("*","users","login = ? AND password = md5(?) AND deleted != 1",new String[]{login,password},"","1");


                        while (loggedSet.next()) {
                            main.activeManager = new Manager(loggedSet);

                            startFrame.setVisible(false);
                            startFrame.dispose();

                            main.Backupdbtosql();
                            GeneralForm.showForm();
                            break;
                        }
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    } finally {
                        main.db.closeStatementSet();
                    }

                }

                errorFieldLabel.setText("Введены неверные логин и\\или пароль.");
            }
        });


        loginPanel.add(errorFieldLabel,c);
        loginPanel.add(loginFieldLabel,c);
        loginPanel.add(loginField,c);
        loginPanel.add(passwordFieldLabel,c);
        loginPanel.add(passwordField,c);
        loginPanel.add(submitButton,c);


        startFrame.add(loginPanel,BorderLayout.CENTER);
        startFrame.setVisible(true);

    }
}
