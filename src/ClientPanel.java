import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by User on 23.04.2017.
 */
public class ClientPanel extends JPanel {

    private Client client;

    private JTextField nameField = new JTextField("",30);
    private JTextField phoneField = new JTextField("",30);
    private JTextField emailField = new JTextField("",30);
    private JTextField addressField = new JTextField("",30);
    private JTextField contractField = new JTextField("",30);

    private JButton saveButton = new JButton("Сохранить");
    private JButton cancelButton = new JButton("Отмена");

    private JTable ordersTable;
    private OrderTableModel ordersModel;

    private boolean saved = false;

    public ClientPanel() {
        this.client = new Client();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5,5,5,5);


        c.gridx = 0;
        c.gridy = 0;
        add(new JLabel("№  контракта*:"),c);
        c.gridx = 1;
        add(contractField,c);

        c.gridx = 0;
        c.gridy = 1;
        add(new JLabel("ФИО*:"),c);
        c.gridx = 1;
        add(nameField,c);

        c.gridx = 0;
        c.gridy = 2;
        add(new JLabel("Телефон*:"),c);
        c.gridx = 1;
        add(phoneField,c);

        c.gridx = 0;
        c.gridy = 3;
        add(new JLabel("Email:"),c);
        c.gridx = 1;
        add(emailField,c);

        c.gridx = 0;
        c.gridy = 4;
        add(new JLabel("Адрес:"),c);
        c.gridx = 1;
        add(addressField,c);

        c.gridx = 0;
        c.gridy = 5;
        add(saveButton,c);
        c.gridy = 6;
        add(cancelButton,c);


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUser();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelDialog();
            }
        });
    }

   public ClientPanel (Client cln) {
        client = cln;

        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JPanel tablePanel = new JPanel(new BorderLayout());

        c.insets = new Insets(5,5,5,5);

       c.gridx = 0;
       c.gridy = 0;
       fieldsPanel.add(new JLabel("№ контракта*:"),c);
       c.gridx = 1;
       contractField.setText(client.getContract_num());
       fieldsPanel.add(contractField,c);

        c.gridx = 0;
        c.gridy = 1;
        fieldsPanel.add(new JLabel("ФИО*:"),c);
        c.gridx = 1;
        nameField.setText(client.getName());
        fieldsPanel.add(nameField,c);

        c.gridx = 0;
        c.gridy = 2;
        fieldsPanel.add(new JLabel("Телефон*:"),c);
        c.gridx = 1;
        phoneField.setText(client.getPhone());
        fieldsPanel.add(phoneField,c);

       c.gridx = 0;
       c.gridy = 3;
       fieldsPanel.add(new JLabel("Email:"),c);
       c.gridx = 1;
       emailField.setText(client.getEmail());
       fieldsPanel.add(emailField,c);

       c.gridx = 0;
       c.gridy = 4;
       fieldsPanel.add(new JLabel("Адрес:"),c);
       c.gridx = 1;
       addressField.setText(client.getAddress());
       fieldsPanel.add(addressField,c);

        c.gridx = 0;
        c.gridy = 5;
       fieldsPanel.add(saveButton,c);
        c.gridy = 6;
       fieldsPanel.add(cancelButton,c);


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUser();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelDialog();
            }
        });


       ordersModel = new OrderTableModel(client.getOrders());
       ordersModel.addModelListener(new ModelUpdateListener() {
           @Override
           public void modelUpdated() {
               updateModel();
           }
       });


       Window win = SwingUtilities.getWindowAncestor(this);
       ordersTable = new JTable(ordersModel);
       JScrollPane ordersScroll = new JScrollPane(ordersTable);

       new ButtonColumn(ordersTable, new AbstractAction() {
           @Override
           public void actionPerformed(ActionEvent e) {
               int modelRow = Integer.valueOf( e.getActionCommand() );

               Order order = ordersModel.getOrder(modelRow);
               if(order.getManager().getId() != main.activeManager.getId() && !main.activeManager.getSudo())
                   JOptionPane.showMessageDialog(win,"Недостаточно прав для редактирования.","Ошибка",JOptionPane.ERROR_MESSAGE);
               else
                editOrder(order);

           }
       },7);

       if (main.activeManager.getSudo()) {
           new ButtonColumn(ordersTable, new AbstractAction() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   JTable table = (JTable) e.getSource();
                   int modelRow = Integer.valueOf(e.getActionCommand());
                   ((OrderTableModel) table.getModel()).deleteValueAt(modelRow);
               }
           }, 8);
       }

       JButton addOrder = new JButton("Добавить");
       addOrder.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               JDialog orderDialog = new JDialog(win,"Добавление документа", Dialog.ModalityType.APPLICATION_MODAL);
               OrderPanel orderPanel = new OrderPanel(client);

               orderDialog.setSize(600,400);
               orderDialog.getContentPane().add(orderPanel);
               orderDialog.setLocationRelativeTo(null);

               orderDialog.setVisible(true);

               if (orderPanel.isSaved()) {
                   Order newOrder = orderPanel.getOrder();
                   if (!newOrder.save())
                       JOptionPane.showMessageDialog(win,"Невозможно добавить документ.","Ошибка",JOptionPane.ERROR_MESSAGE);
                   else {
                       updateModel();
                   }
               }
           }
       });

       tablePanel.add(ordersScroll,BorderLayout.NORTH);
       tablePanel.add(addOrder,BorderLayout.SOUTH);

        add(fieldsPanel,BorderLayout.NORTH);
        add(tablePanel,BorderLayout.SOUTH);
    }

    protected void updateModel() {
        client.fillOrders();
        ordersModel.update(client.getOrders());
    }

    private boolean editOrder(Order o) {
        Window win = SwingUtilities.getWindowAncestor(this);

        JDialog orderDialog = new JDialog(win,"Редактирование документа", Dialog.ModalityType.APPLICATION_MODAL);
        OrderPanel orderPanel = new OrderPanel(o);

        orderDialog.setSize(600,400);
        orderDialog.getContentPane().add(orderPanel);
        orderDialog.pack();
        orderDialog.setLocationRelativeTo(null);


        orderDialog.setVisible(true);

        if (orderPanel.isSaved()) {
            o = orderPanel.getOrder();
            Boolean res = o.save();
            if (!res)
                JOptionPane.showMessageDialog(win,"Невозможно изменить данные документа.","Ошибка",JOptionPane.ERROR_MESSAGE);

            updateModel();

            return res;
        }

        return false;
    }

    protected boolean isSaved() {
        return saved;
    }

    protected Client getClient() {
        return this.client;
    }

    private void saveUser() {
        Window win = SwingUtilities.getWindowAncestor(this);
        ArrayList<String> errors = new ArrayList<String>();

        if (contractField.getText().length() < 2)
            errors.add("Введите № контракта");
        else {
            int isSetLogin = 0;

            ResultSet isSetLoginRs = null;

            try {
                isSetLoginRs = main.db.select("count(*) as count", "clients",
                        "contract_num = ?" + (client.getId() != 0 ? " AND id != " + client.getId() : ""),
                        new String[]{contractField.getText()}, "", "");
                isSetLoginRs.next();
                isSetLogin = isSetLoginRs.getInt("count");
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                main.db.closeStatementSet();
            }

            if (isSetLogin > 0)
                errors.add("Такой номер контракта уже существует;");
        }
        if (nameField.getText().length() < 2)
            errors.add("Введите ФИО");
        if (phoneField.getText().length() < 2)
            errors.add("Введите телефон");

        if (errors.size() > 0) {
            String errorMessage = "";
            for (String error : errors) {
                errorMessage += error+"\n";
            }

            JOptionPane.showMessageDialog(win,errorMessage,"Ошибка",JOptionPane.ERROR_MESSAGE);
        } else {
            client.setName(nameField.getText());
            client.setPhone(phoneField.getText());
            client.setContract_num(contractField.getText());
            client.setEmail(emailField.getText());
            client.setAddress(addressField.getText());

            saved = true;
            win.dispose();
        }
    }

    private void cancelDialog() {
        Window win = SwingUtilities.getWindowAncestor(this);
        if (win != null)
            win.dispose();
    }
}

