import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by User on 16.04.2017.
 */
public class GeneralForm {
    static void showForm() {

        JFrame startFrame = new JFrame("Главная | Интернет-провайдер");
        startFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        startFrame.setSize(600,400);
        startFrame.setLocationRelativeTo(null);

        try {
            startFrame.setIconImage(ImageIO.read(new File("out/production/provider/images/diamond_ico.jpg")));
        } catch (IOException ie) {
            ie.printStackTrace();
        }

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        Insets standardInset = new Insets(5,0,0,0);
        c.insets = standardInset;

        JButton clientsButton = new JButton("Клиенты");
        clientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClientsForm();
            }
        });

        JButton myOrdersButton = new JButton("Мои документы");
        myOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrdersForm(main.activeManager.getId());
            }
        });

        mainPanel.add(clientsButton,c);
        mainPanel.add(myOrdersButton,c);

        if (main.activeManager.getSudo()) {
            JLabel sudoLabel = new JLabel("Рут функции:");

            JButton managersButton = new JButton("Менеджеры");
            managersButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ManagersForm();
                }
            });

            JButton ordersStatusesButton = new JButton("Статусы документов");
            ordersStatusesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ListAddForm("Статусы документов","document_statuses");
                }
            });

            JButton typesButton = new JButton("Типы документов");
            typesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ListAddForm("Типы документов","document_types");
                }
            });

            JButton ordersButton = new JButton("Все документы");
            ordersButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new OrdersForm(0);
                }
            });

            JButton queryButton = new JButton("Ручные запросы");
            queryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new QueryForm();
                }
            });

            JButton backupButton = new JButton("Работа с бэкапами");
            backupButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new BackupForm();
                }
            });

            c.insets = new Insets(15,0,5,0);
            mainPanel.add(sudoLabel,c);

            c.insets = standardInset;
            mainPanel.add(managersButton,c);
            mainPanel.add(ordersStatusesButton,c);
            mainPanel.add(typesButton,c);
            mainPanel.add(ordersButton,c);
            mainPanel.add(queryButton,c);
            mainPanel.add(backupButton,c);
        }

        startFrame.add(mainPanel,BorderLayout.CENTER);
        startFrame.setVisible(true);
    }
}
