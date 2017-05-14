import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.scene.control.DatePicker;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class OrdersForm {

    private JFrame startFrame;
    private JTable listTable;
    private OrderTableModel model;

    private JTextField idSearchField;
    private JTextField fioSearchField;
    private JTextField mngfioSearchField;
    private JTextField dateSearchField;
    private JComboBox statusSearchField;
    private JComboBox typeSearchField;

    int mngId;

    public OrdersForm(int mngId) {

        this.mngId = mngId;

        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");

        startFrame = new JFrame((mngId > 0 ? "Мои " : "") + "Документы | Интернет-провайдер");
        startFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        startFrame.setSize(1200,400);
        startFrame.setLocationRelativeTo(null);

        try {
            startFrame.setIconImage(ImageIO.read(new File("out/production/provider/images/diamond_ico.jpg")));
        } catch (IOException ie) {
            ie.printStackTrace();
        }

        ResultSet  modelSet = null;
        modelSet = main.db.select("*","documents",(mngId > 0 ? "managed_by = " + mngId : ""),"date_of_creation desc","");

        model = new OrderTableModel(modelSet);
        model.addModelListener(new ModelUpdateListener() {
            @Override
            public void modelUpdated() {
                updateModel();
            }
        });

        listTable = new JTable(model);
        main.db.closeStatementSet();

        ButtonColumn editBtnColumn = new ButtonColumn(listTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            int modelRow = Integer.valueOf( e.getActionCommand() );

            Order order = model.getOrder(modelRow);
            editOrder(order);

            }
        },7);

        if (main.activeManager.getSudo()) {
            ButtonColumn deleteBtnColumn = new ButtonColumn(listTable, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                ((OrderTableModel) table.getModel()).deleteValueAt(modelRow);
                }
            }, 8);
        }

        JScrollPane scroll = new JScrollPane(listTable);

        JPanel controlPanel = new JPanel();
        JPanel searchPanel = new JPanel();

        idSearchField = new JTextField("",3);
        idSearchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                updateModel();
            }
        });
        idSearchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (idSearchField.getText().length() > 0)
                    updateModel();
            }
        });

        fioSearchField = new JTextField("",7);
        fioSearchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                updateModel();
            }
        });
        fioSearchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (fioSearchField.getText().length() > 0)
                    updateModel();
            }
        });


        mngfioSearchField = new JTextField("", 7);
        mngfioSearchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                updateModel();
            }
        });
        mngfioSearchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (mngfioSearchField.getText().length() > 0)
                    updateModel();
            }
        });


        dateSearchField = new JTextField("",7);
        dateSearchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (dateSearchField.getText().length() == 10) {
                    Pattern datePattern = Pattern.compile("\\d\\d\\.\\d\\d\\.\\d\\d\\d\\d");
                    Matcher dateMatcher = datePattern.matcher(dateSearchField.getText());
                    if (dateMatcher.matches())
                        updateModel();
                }
            }
        });
        dateSearchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (dateSearchField.getText().length() == 10) {
                    Pattern datePattern = Pattern.compile("\\d\\d\\.\\d\\d\\.\\d\\d\\d\\d");
                    Matcher dateMatcher = datePattern.matcher(dateSearchField.getText());
                    if (dateMatcher.matches())
                        updateModel();
                }
            }
        });

        statusSearchField = new JComboBox();
        statusSearchField.addItem(new ComboItem());
        ResultSet statuses = null;
        statuses = main.db.select("*","document_statuses","","","");
        try {
            while (statuses.next()) {
                statusSearchField.addItem(new ComboItem(statuses,"name","id"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            main.db.closeStatementSet(statuses);
        }
        statusSearchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateModel();
            }
        });

        typeSearchField = new JComboBox();
        typeSearchField.addItem(new ComboItem());
        statuses = null;
        statuses = main.db.select("*","document_types","","","");
        try {
            while (statuses.next()) {
                typeSearchField.addItem(new ComboItem(statuses,"name","id"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            main.db.closeStatementSet(statuses);

        }
        typeSearchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateModel();
            }
        });

        JButton addButton = new JButton("Добавить");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog orderDialog = new JDialog(startFrame,"Добавление документа", Dialog.ModalityType.APPLICATION_MODAL);
                OrderPanel orderPanel = new OrderPanel();

                orderDialog.setSize(600,400);
                orderDialog.getContentPane().add(orderPanel);
                orderDialog.setLocationRelativeTo(null);

                orderDialog.setVisible(true);

                if (orderPanel.isSaved()) {
                    Order newOrder = orderPanel.getOrder();
                    if (!newOrder.save())
                        JOptionPane.showMessageDialog(startFrame,"Невозможно добавить документ.","Ошибка",JOptionPane.ERROR_MESSAGE);
                    else {
                        updateModel();
                    }
                }
            }
        });

        controlPanel.add(addButton);

        searchPanel.add(new JLabel("№ док.:"));
        searchPanel.add(idSearchField);

        searchPanel.add(new JLabel("ФИО клиента:"));
        searchPanel.add(fioSearchField);

        if (mngId == 0) {
            searchPanel.add(new JLabel("ФИО менеджера:"));
            searchPanel.add(mngfioSearchField);
        }

        searchPanel.add(new JLabel("Дата оформления:"));
        searchPanel.add(dateSearchField);

        searchPanel.add(new JLabel("Статус:"));
        searchPanel.add(statusSearchField);

        searchPanel.add(new JLabel("Тип:"));
        searchPanel.add(typeSearchField);



        startFrame.add(searchPanel,BorderLayout.NORTH);
        startFrame.add(scroll, BorderLayout.CENTER);
        startFrame.add(controlPanel,BorderLayout.SOUTH);

        startFrame.setVisible(true);
    }

    private boolean editOrder(Order o) {
        JDialog orderDialog = new JDialog(startFrame,"Редактирование документа", Dialog.ModalityType.APPLICATION_MODAL);
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
                JOptionPane.showMessageDialog(startFrame,"Невозможно изменить данные документа.","Ошибка",JOptionPane.ERROR_MESSAGE);

            updateModel();

            return res;
        }

        return false;
    }

    private void updateModel() {
        ResultSet modelSet = null;

        if (idSearchField.getText().length() > 0 ||
                fioSearchField.getText().length() > 0 ||
                mngfioSearchField.getText().length() > 0 ||
                dateSearchField.getText().length() > 0 ||
                statusSearchField.getSelectedIndex() > 0 ||
                typeSearchField.getSelectedIndex() > 0) {
            ArrayList<String> whereValuesList = new ArrayList<String>();
            String where = "";
            Boolean clientJoin = false;
            Boolean managerJoin = false;

            if (idSearchField.getText().length() > 0) {
                where += "o.id = ? ";
                whereValuesList.add(idSearchField.getText());
            }
            if (fioSearchField.getText().length() > 0) {
                where += (where.length() > 0 ? "AND " : "") + "c.name LIKE ?";
                whereValuesList.add("%" + fioSearchField.getText() + "%");
                clientJoin = true;
            }
            if (mngfioSearchField.getText().length() > 0) {
                where += (where.length() > 0 ? "AND " : "") + "u.name LIKE ?";
                whereValuesList.add("%" + mngfioSearchField.getText() + "%");
                managerJoin = true;
            }

            if (mngId > 0) {
                where += (where.length() > 0 ? "AND " : "") + "o.managed_by = ?";
                whereValuesList.add(String.valueOf(mngId));
            }
            if (dateSearchField.getText().length() == 10) {
                Pattern datePattern = Pattern.compile("\\d\\d\\.\\d\\d\\.\\d\\d\\d\\d");
                Matcher dateMatcher = datePattern.matcher(dateSearchField.getText());

                if (dateMatcher.matches()) {
                    long unixStartTime = 0;
                    long unixEndTime = 0;
                    try {
                        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                        Date date = dateFormat.parse(dateSearchField.getText());
                        unixStartTime = (long) date.getTime() / 1000;
                        unixEndTime = unixStartTime + 86400;
                    } catch (ParseException pe) {
                        pe.printStackTrace();
                    }

                    if (unixEndTime > 0 && unixStartTime > 0) {
                        where += (where.length() > 0 ? "AND " : "") + "(o.date_of_creation BETWEEN ? AND ?) ";
                        whereValuesList.add(String.valueOf(unixStartTime));
                        whereValuesList.add(String.valueOf(unixEndTime));
                    }
                }
            }

            if (statusSearchField.getSelectedIndex() > 0) {
                where += (where.length() > 0 ? "AND " : "") + "o.managed_status = ? ";
                whereValuesList.add(String.valueOf(((ComboItem)statusSearchField.getSelectedItem()).getValue()));
            }

            if (typeSearchField.getSelectedIndex() > 0) {
                where += (where.length() > 0 ? "AND " : "") + "o.type = ? ";
                whereValuesList.add(String.valueOf(((ComboItem)typeSearchField.getSelectedItem()).getValue()));
            }

            String[] whereValues = new String[whereValuesList.size()];
            whereValuesList.toArray(whereValues);

            try {
                modelSet = main.db.query("SELECT o.* FROM documents as o " +
                                (clientJoin ? "JOIN clients as c ON o.client_id = c.id " : "") +
                                (managerJoin ? "JOIN users as u ON o.managed_by = u.id " : "") +
                                "WHERE " + where + "ORDER BY o.date_of_creation desc",
                        whereValues);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } else
            modelSet = main.db.select("*","documents",(mngId > 0 ? "managed_by = " + mngId : ""),"date_of_creation desc","");

        model.update(modelSet);
        main.db.closeStatementSet();
    }
}


class OrderTableModel extends AbstractTableModel {

    private ArrayList<Order> orders;
    private ModelUpdateListener listener;


    public OrderTableModel(ArrayList<Order> o) {
        orders = o;
    }

    public OrderTableModel(ResultSet rs) {
        orders = new ArrayList<Order>();

        try {
            while (rs.next()) {
                this.orders.add(new Order(rs));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            main.db.closeStatementSet();
        }
    }

    public void addModelListener(ModelUpdateListener listener) {
        this.listener = listener;
    }

    public void update(ArrayList<Order> o) {
        orders = o;

        fireTableDataChanged();
    }

    public void update(ResultSet rs) {
        orders.clear();

        try {
            while (rs.next()) {
                this.orders.add(new Order(rs));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            main.db.closeStatementSet();
        }

        fireTableDataChanged();
    }

    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return int.class;
            case 1:
            case 4:
            case 5:
            case 6:
                return String.class;
            case 2:
            case 3:
                return Date.class;
            case 7:
            case 8:
                return JButton.class;
        }

        return String.class;
    }

    public int getColumnCount() {
        return main.activeManager.getSudo() ? 9 : 8;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "№ документа";
            case 1:
                return "ФИО клиента";
            case 2:
                return "Дата оформления";
            case 3:
                return "Дата изменения статуса";
            case 4:
                return "Статус документа";
            case 5:
                return "Тип документа";
            case 6:
                return "Менеджер";
        }
        return "";
    }

    public int getRowCount() {
        if (orders == null)
            return  0;
        return orders.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Order order = orders.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return order.getId();
            case 1:
                return order.getClient().getName();
            case 2:
                return order.getDate();
            case 3:
                return order.getManagedDate();
            case 4:
                return order.getStatus().getName();
            case 5:
                return order.getType().getName();
            case 6:
                return order.getManager().getName();
            case 7:
                return "Редактировать";
            case 8:
                return "Удалить";
        }
        return "";
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 7 || columnIndex == 8)
            return true;
        return false;
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex) {
    }

    public Order getOrder(int rowIndex) {
        return orders.get(rowIndex);
    }

    public void deleteValueAt (int rowIndex) {
        if (JOptionPane.showConfirmDialog(null,"Вы уверены, что хотите удалить Документ?","Вы уверены?",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            Order order = orders.get(rowIndex);
            if (!order.delete())
                JOptionPane.showMessageDialog(null,"Невозможно удалить документ.","Ошибка", JOptionPane.ERROR_MESSAGE);

            if (listener != null)
                listener.modelUpdated();
        }
    }

}
