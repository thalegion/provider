import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by User on 30.04.2017.
 */
public class OrderPanel extends JPanel {

    private Order order;

    private JComboBox clientField = new JComboBox();
    private JComboBox statusField = new JComboBox();
    private JComboBox typeField = new JComboBox();

    private JTextField linkField = new JTextField("",15);

    private JButton saveButton = new JButton("Сохранить");
    private JButton cancelButton = new JButton("Отмена");

    private boolean saved = false;

    public OrderPanel() {
        this.order = new Order();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5,5,5,5);

        clientField.addItem(new ComboItem());
        ResultSet fillSet = null;
        try {
            fillSet = main.db.select("*","clients","","name ASC","");
            while (fillSet.next()) {
                clientField.addItem(new ComboItem(fillSet,"name","id"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            main.db.closeStatementSet(fillSet);
        }

        fillSet = null;
        try {
            fillSet = main.db.select("*","document_statuses","","name ASC","");
            while (fillSet.next()) {
                statusField.addItem(new ComboItem(fillSet,"name","id"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            main.db.closeStatementSet(fillSet);
        }

        fillSet = null;
        try {
            fillSet = main.db.select("*","document_types","","name ASC","");
            while (fillSet.next()) {
                typeField.addItem(new ComboItem(fillSet,"name","id"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            main.db.closeStatementSet(fillSet);
        }

        c.gridx = 0;
        c.gridy = 0;
        add(new JLabel("Клиент*:"),c);
        c.gridx = 1;
        add(clientField,c);

        c.gridx = 0;
        c.gridy = 1;
        add(new JLabel("Ссылка*:"),c);
        c.gridx = 1;
        add(linkField,c);

        c.gridx = 0;
        c.gridy = 2;
        add(new JLabel("Статус:"),c);
        c.gridx = 1;
        add(statusField,c);

        c.gridx = 0;
        c.gridy = 3;
        add(new JLabel("Тип:"),c);
        c.gridx = 1;
        add(typeField,c);

        c.gridx = 0;
        c.gridy = 4;
        add(saveButton,c);
        c.gridy = 5;
        add(cancelButton,c);


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOrder();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelDialog();
            }
        });
    }

    public OrderPanel(Client cln) {
        this.order = new Order();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5,5,5,5);

        clientField.addItem(new ComboItem());
        ResultSet fillSet = null;
        try {
            fillSet = main.db.select("*","clients","","name ASC","");
            while (fillSet.next()) {
                ComboItem ci = new ComboItem(fillSet,"name","id");
                clientField.addItem(ci);

                if (cln.getId() == ci.getValue())
                    clientField.setSelectedItem(ci);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            main.db.closeStatementSet(fillSet);
        }

        fillSet = null;
        try {
            fillSet = main.db.select("*","document_statuses","","name ASC","");
            while (fillSet.next()) {
                statusField.addItem(new ComboItem(fillSet,"name","id"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            main.db.closeStatementSet(fillSet);
        }

        fillSet = null;
        try {
            fillSet = main.db.select("*","document_types","","name ASC","");
            while (fillSet.next()) {
                typeField.addItem(new ComboItem(fillSet,"name","id"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            main.db.closeStatementSet(fillSet);
        }

        c.gridx = 0;
        c.gridy = 0;
        add(new JLabel("Клиент*:"),c);
        c.gridx = 1;
        add(clientField,c);

        c.gridx = 0;
        c.gridy = 1;
        add(new JLabel("Ссылка*:"),c);
        c.gridx = 1;
        add(linkField,c);

        c.gridx = 0;
        c.gridy = 2;
        add(new JLabel("Статус:"),c);
        c.gridx = 1;
        add(statusField,c);

        c.gridx = 0;
        c.gridy = 3;
        add(new JLabel("Тип:"),c);
        c.gridx = 1;
        add(typeField,c);

        c.gridx = 0;
        c.gridy = 4;
        add(saveButton,c);
        c.gridy = 5;
        add(cancelButton,c);


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOrder();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelDialog();
            }
        });
    }

   public OrderPanel (Order ord) {
       order = ord;

       JPanel fieldsPanel = new JPanel(new GridBagLayout());
       GridBagConstraints c = new GridBagConstraints();

       JPanel tablePanel = new JPanel(new BorderLayout());


       c.insets = new Insets(5,5,5,5);

       clientField.addItem(order.getClient().getName());
       clientField.setEditable(false);

       ResultSet fillSet = null;
       try {
           fillSet = main.db.select("*","document_statuses","","name ASC","");
           while (fillSet.next()) {
               ComboItem ci = new ComboItem(fillSet,"name","id");
               statusField.addItem(ci);

               if (order.getStatus().getId() == ci.getValue())
                   statusField.setSelectedItem(ci);
           }
       } catch (SQLException se) {
           se.printStackTrace();
       } finally {
           main.db.closeStatementSet(fillSet);
       }

       fillSet = null;
       try {
           fillSet = main.db.select("*","document_types","","name ASC","");
           while (fillSet.next()) {
               ComboItem ci = new ComboItem(fillSet,"name","id");
               typeField.addItem(ci);

               if (order.getType().getId() == ci.getValue())
                   typeField.setSelectedItem(ci);
           }
       } catch (SQLException se) {
           se.printStackTrace();
       } finally {
           main.db.closeStatementSet(fillSet);
       }

       c.gridx = 0;
       c.gridy = 0;
       fieldsPanel.add(new JLabel("Клиент*:"),c);
       c.gridx = 1;
       fieldsPanel.add(clientField,c);

       c.gridx = 0;
       c.gridy = 1;
       fieldsPanel.add(new JLabel("Ссылка*:"),c);
       c.gridx = 1;
       linkField.setText(ord.getLink());
       fieldsPanel.add(linkField,c);

       c.gridx = 0;
       c.gridy = 2;
       fieldsPanel.add(new JLabel("Статус:"),c);
       c.gridx = 1;
       fieldsPanel.add(statusField,c);

       c.gridx = 0;
       c.gridy = 3;
       fieldsPanel.add(new JLabel("Тип:"),c);
       c.gridx = 1;
       fieldsPanel.add(typeField,c);

       c.gridx = 0;
       c.gridy = 5;
       fieldsPanel.add(saveButton,c);
       c.gridy = 6;
       fieldsPanel.add(cancelButton,c);

       saveButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               saveOrder();
           }
       });
       cancelButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               cancelDialog();
           }
       });

       add(fieldsPanel,BorderLayout.CENTER);
    }

    protected boolean isSaved() {
        return saved;
    }

    protected Order getOrder() {
        return this.order;
    }

    private void saveOrder() {
        Window win = SwingUtilities.getWindowAncestor(this);
        ArrayList<String> errors = new ArrayList<String>();

        if (order.getId() == 0) {
            if (clientField.getSelectedIndex() == 0)
                errors.add("Выберите клиента");
        }
        if (linkField.getText().length() == 0) {
            errors.add("Введите ссылку");
        }

        if (errors.size() > 0) {
            String errorMessage = "";
            for (String error : errors) {
                errorMessage += error+"\n";
            }

            JOptionPane.showMessageDialog(win,errorMessage,"Ошибка",JOptionPane.ERROR_MESSAGE);
        } else {
            order.setLink(linkField.getText());
            order.setStatus(new Library(((ComboItem)statusField.getSelectedItem()).getValue(),"document_statuses"));
            order.setType(new Library(((ComboItem)typeField.getSelectedItem()).getValue(),"document_types"));
            if (order.getId() == 0) {
                order.setClient(new Client(((ComboItem)clientField.getSelectedItem()).getValue()));
                order.setManager(main.activeManager);
            }

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

