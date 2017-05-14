import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;


public class BackupForm {

    private JFrame startFrame;
    private JTextField backupField = new JTextField("",30);

    public BackupForm() {

        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");

        startFrame = new JFrame("Бэкапы | Интернет-провайдер");
        startFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        startFrame.setSize(600,400);
        startFrame.setLocationRelativeTo(null);

        try {
            startFrame.setIconImage(ImageIO.read(new File("out/production/provider/images/diamond_ico.jpg")));
        } catch (IOException ie) {
            ie.printStackTrace();
        }

        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        c.insets = new Insets(5,5,5,5);

        JButton makeBackupBtn = new JButton("Сделать бэкап");
        makeBackupBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String res = main.Backupdbtosql();
                if (res == "fail")
                    JOptionPane.showMessageDialog(startFrame,"Невозможно сделать бэкап.","Ошибка",JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(startFrame,"Бэкап сохранен в " + res,"Результат",JOptionPane.INFORMATION_MESSAGE);

            }
        });

        c.gridx = 0;
        c.gridy = 0;
        fieldsPanel.add(makeBackupBtn,c);

        c.gridy = 1;
        fieldsPanel.add(new JLabel("Восстановление бэкапа:"),c);
        c.gridy = 2;
        fieldsPanel.add(new JLabel("Название файла:"),c);
        c.gridx = 1;
        fieldsPanel.add(backupField,c);
        c.gridx = 0;
        c.gridy = 3;
        JButton restoreBtn = new JButton("Восстановить");
        fieldsPanel.add(restoreBtn,c);
        restoreBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (backupField.getText().length() > 0) {
                    String file = backupField.getText();
                    main.Restoredbfromsql(file);
                }
            }
        });

        startFrame.add(fieldsPanel,BorderLayout.NORTH);

        startFrame.setVisible(true);
    }
}