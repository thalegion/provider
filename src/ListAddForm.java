import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

/**
 * Created by User on 16.04.2017.
 */
public class ListAddForm {

    private String tableName;
    private JFrame startFrame;
    private JTable listTable;
    private LibraryTableModel model;

    private JTextField searchField;

    public ListAddForm(String title, String tblName) {

        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");

        tableName = tblName;

        startFrame = new JFrame(title + " | Интернет-провайдер");
        startFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        startFrame.setSize(600,400);
        startFrame.setLocationRelativeTo(null);

        try {
            startFrame.setIconImage(ImageIO.read(new File("out/production/provider/images/diamond_ico.jpg")));
        } catch (IOException ie) {
            ie.printStackTrace();
        }

        ResultSet modelSet = main.db.select("*",tableName,"","","");

        model = new LibraryTableModel(modelSet);
        model.addModelListener(new ModelUpdateListener() {
            @Override
            public void modelUpdated() {
                updateModel();
            }
        });
        main.db.closeStatementSet();

        listTable = new JTable(model);

        ButtonColumn deleteBtnColumn = new ButtonColumn(listTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                ((LibraryTableModel)table.getModel()).deleteValueAt(modelRow);
            }
        },2);

        JScrollPane scroll = new JScrollPane(listTable);
        startFrame.add(scroll,BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JLabel searchLabel = new JLabel("Поиск:");
        JTextField nameField = new JTextField("",30);
        searchField = new JTextField("",30);
        JButton addButton = new JButton("Добавить");


        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                updateModel();
            }
        });
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (searchField.getText().length() > 0)
                    updateModel();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String libText = nameField.getText();
                nameField.setText("");
                if (libText.length() > 0) {
                    Library lib = new Library(libText, tableName);
                    model.insert(lib);
                } else
                    JOptionPane.showMessageDialog(startFrame,"Значение должно быть задано.","Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        controlPanel.add(nameField);
        controlPanel.add(addButton);

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);

        startFrame.add(searchPanel,BorderLayout.NORTH);
        startFrame.add(controlPanel,BorderLayout.SOUTH);

        startFrame.setVisible(true);
    }

    private void updateModel() {
        ResultSet modelSet = null;

        if (searchField.getText().length() > 0) {
            String text = "%" + searchField.getText() + "%";
            modelSet = main.db.select("*", tableName, "name LIKE ?", new String[]{text}, "", "");
        } else
            modelSet = main.db.select("*",tableName,"","","");

        model.update(modelSet);
        main.db.closeStatementSet();
    }
}

class LibraryTableModel extends AbstractTableModel {

    private ArrayList<Library> libraries;
    private ModelUpdateListener listener;

    public LibraryTableModel(ResultSet rs) {
        libraries = new ArrayList<Library>();

        try {
            while (rs.next()) {
                this.libraries.add(new Library(rs));
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

    public void update(ResultSet rs) {
        libraries.clear();

        try {
            while (rs.next()) {
                this.libraries.add(new Library(rs));
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
                return String.class;
            case 2:
                return JButton.class;
        }

        return String.class;
    }

    public int getColumnCount() {
        return 3;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Код";
            case 1:
                return "Значение";
            case 2:
                return "";
        }
        return "";
    }

    public int getRowCount() {
        if (libraries == null)
            return  0;
        return libraries.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Library lib = libraries.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return lib.getId();
            case 1:
                return lib.getName();
            case 2:
                return "Удалить";
        }
        return "";
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0)
            return false;
        else
            return true;
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if (columnIndex == 1) {
            if (JOptionPane.showConfirmDialog(null,"Вы уверены, что хотите изменить значение?","Вы уверены?",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Library lib = libraries.get(rowIndex);
                lib.setName(value.toString());

                if (!lib.save())
                    JOptionPane.showMessageDialog(null,"Невозможно обновить значение.","Ошибка", JOptionPane.ERROR_MESSAGE);

                if (listener != null)
                    listener.modelUpdated();
            }
        }
    }

    public void insert (Library lib) {
        if (lib.save())
            libraries.add(lib);
        else
            JOptionPane.showMessageDialog(null,"Невозможно добавить это значение.","Ошибка", JOptionPane.ERROR_MESSAGE);

        if (listener != null)
            listener.modelUpdated();
    }

    public void deleteValueAt (int rowIndex) {
        if (JOptionPane.showConfirmDialog(null,"Вы уверены, что хотите удалить значение?","Вы уверены?",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            Library lib = libraries.get(rowIndex);
            if (!lib.delete())
                JOptionPane.showMessageDialog(null,"Невозможно удалить значение.","Ошибка", JOptionPane.ERROR_MESSAGE);

            if (listener != null)
                listener.modelUpdated();
        }
    }
}