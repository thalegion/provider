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


public class QueryForm {

    private JFrame startFrame;
    private JTable queryTable;

    private JTextArea queryField = new JTextArea(10,30);

    public QueryForm() {

        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");

        startFrame = new JFrame("Запросы | Интернет-провайдер");
        startFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        startFrame.setSize(900,400);
        startFrame.setLocationRelativeTo(null);

        try {
            startFrame.setIconImage(ImageIO.read(new File("out/production/provider/images/diamond_ico.jpg")));
        } catch (IOException ie) {
            ie.printStackTrace();
        }

        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JPanel tablePanel = new JPanel(new BorderLayout());

        c.insets = new Insets(5,5,5,5);

        c.gridx = 0;
        c.gridy = 0;
        fieldsPanel.add(new JLabel("Ваш запрос:"),c);
        c.gridy = 1;
        c.gridwidth = 5;
        fieldsPanel.add(queryField,c);
        c.gridwidth = 1;

        JButton queryButton = new JButton("Выполнить");
        c.gridy = 2;
        fieldsPanel.add(queryButton,c);

        c.gridy = 3;
        JButton procBtn1 = new JButton("Количество клиентов");
        procBtn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryField.setText("CALL get_clients()");
            }
        });
        JButton procBtn2 = new JButton("Количество документов");
        procBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryField.setText("CALL get_documents()");
            }
        });
        JButton procBtn3 = new JButton("Количество пользователей");
        procBtn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryField.setText("CALL get_users()");
            }
        });
        JButton procBtn4 = new JButton("Количество статусов");
        procBtn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryField.setText("CALL get_document_statuses()");
            }
        });
        JButton procBtn5 = new JButton("Количество типов");
        procBtn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryField.setText("CALL get_document_types()");
            }
        });

        fieldsPanel.add(procBtn1,c);
        c.gridx = 1;
        fieldsPanel.add(procBtn2,c);
        c.gridx = 2;
        fieldsPanel.add(procBtn3,c);
        c.gridx = 3;
        fieldsPanel.add(procBtn4,c);
        c.gridx = 4;
        fieldsPanel.add(procBtn5,c);
        c.gridx = 5;

        c.gridx = 0;
        c.gridy = 4;

        JButton fncBtn1 = new JButton("Сложение");
        fncBtn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryField.setText("SELECT plus(5,4) as result");
            }
        });
        JButton fncBtn2 = new JButton("Вычитание");
        fncBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryField.setText("SELECT minus(5,4) as result");
            }
        });
        JButton fncBtn3 = new JButton("Умножение");
        fncBtn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryField.setText("SELECT multiple(5,4) as result");
            }
        });
        JButton fncBtn4 = new JButton("Деление");
        fncBtn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryField.setText("SELECT divide(5,4) as result");
            }
        });
        JButton fncBtn5 = new JButton("Остаток");
        fncBtn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryField.setText("SELECT moding(5,4) as result");
            }
        });

        fieldsPanel.add(fncBtn1,c);
        c.gridx = 1;
        fieldsPanel.add(fncBtn2,c);
        c.gridx = 2;
        fieldsPanel.add(fncBtn3,c);
        c.gridx = 3;
        fieldsPanel.add(fncBtn4,c);
        c.gridx = 4;
        fieldsPanel.add(fncBtn5,c);
        c.gridx = 5;
        
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = queryField.getText();
                if (query.length() > 0) {
                    if (query.substring(0,6).equals("SELECT") || query.substring(0,4).equals("CALL")) {
                        ResultSet rs = null;
                        try {
                            rs = main.db.query(query, new String[]{});
                            ResultSetMetaData metaData = rs.getMetaData();

                            Vector<String> columnNames = new Vector<String>();
                            int columnCount = metaData.getColumnCount();
                            for (int column = 1; column <= columnCount; column++) {
                                columnNames.add(metaData.getColumnName(column));
                            }

                            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
                            while (rs.next()) {
                                Vector<Object> vector = new Vector<Object>();
                                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                                    vector.add(rs.getObject(columnIndex));
                                }
                                data.add(vector);
                            }

                            DefaultTableModel model = new DefaultTableModel(data, columnNames);
                            queryTable.setModel(model);
                            model.fireTableDataChanged();

                            startFrame.pack();
                        } catch (Exception ex) {
                            String message = "";
                            if (ex.getMessage() != null)
                                message = ex.getMessage();
                            else
                                message = "Ошибка в запросе.";
                            JOptionPane.showMessageDialog(startFrame,message,"Ошибка",JOptionPane.ERROR_MESSAGE);
                        } finally {
                            main.db.closeStatementSet(rs);
                        }
                    } else {
                        boolean go = true;
                        int result = 0;
                        try {
                            result = main.db.query(query);
                        } catch (SQLException se) {
                            JOptionPane.showMessageDialog(startFrame,se.getMessage(),"Ошибка",JOptionPane.ERROR_MESSAGE);
                            go = false;
                        }
                        if (go && result > 0)
                            JOptionPane.showMessageDialog(startFrame,"Запрос выполнен","Результат запроса",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });


        queryTable = new JTable();
        JScrollPane ordersScroll = new JScrollPane(queryTable);

        tablePanel.add(ordersScroll,BorderLayout.NORTH);

        startFrame.add(fieldsPanel,BorderLayout.NORTH);
        startFrame.add(tablePanel,BorderLayout.SOUTH);

        startFrame.setVisible(true);
    }
}