import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
public class NameFormatter
{
    private JPanel mainPanel;
    private JButton button;
    private JTextField surenameField;
    private JTextField fathernameField;
    private JPanel namePanel;
    private JTextField nameField;
    private JPanel panel;
    private static final String COLLAPSE = "Collapse";
    public static final String EXPAND = "Expand";

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public NameFormatter() {
        button.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel label = new JLabel();
                if (button.getText().equals(COLLAPSE)) {
                    collapse();
                } else if (button.getText().equals(EXPAND)) {
                    expand();
                }
            }
        });
    }

    private void collapse()
    {
        if (nameField.getText().equals("") || surenameField.getText().equals("")) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Введите имя и фамилию",
                    "Ошибка", JOptionPane.PLAIN_MESSAGE);
        } else if (!nameField.getText().equals("") && !surenameField.getText().equals("")) {
            String name = surenameField.getText() +
                    " " + nameField.getText() +
                    " " + fathernameField.getText();
            namePanel.remove(nameField);
            namePanel.remove(surenameField);
            namePanel.remove(fathernameField);
            namePanel.updateUI();

            JLabel label = new JLabel();
            label.setText(name);
            namePanel.add(label);

            button.setText(EXPAND);
        }
    }

    private void expand()
    {
        for (Component component : namePanel.getComponents()) {
            if (component.getClass().equals(JLabel.class)) {
                JLabel label = (JLabel) component;
                String name = label.getText();
                int spaces = 0;
                for (char ch : name.toCharArray()) {
                    if (ch == ' ') spaces++;
                }
                namePanel.remove(label);
                JTextField nameField = new JTextField();
                JTextField surenameField = new JTextField();
                JTextField fathernameField = new JTextField();

                namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                namePanel.setAlignmentY(Component.CENTER_ALIGNMENT);

                namePanel.add(nameField);
                nameField.setColumns(12);

                namePanel.add(surenameField);
                surenameField.setColumns(12);

                namePanel.add(fathernameField);
                fathernameField.setColumns(12);

                if (spaces == 1) {
                    int space1 = name.indexOf(' ');
                    String sub1 = name.substring(0, space1);
                    String sub2 = name.substring(sub1.length() + 1);

                    nameField.setText(sub1);
                    surenameField.setText(sub2);
                } else if (spaces == 2) {
                    int space1 = name.indexOf(' ');
                    int space2 = name.lastIndexOf(' ');
                    String sub1 = name.substring(0, space1);
                    String sub2 = name.substring(sub1.length() + 1, space2);
                    String sub3 = name.substring(space2 + 1);

                    nameField.setText(sub1);
                    surenameField.setText(sub2);
                    fathernameField.setText(sub3);
                }
                setSurenameField(surenameField);
                setNameField(nameField);
                setFathernameField(fathernameField);

                button.setText(COLLAPSE);
            }
        }
    }

    public void setSurenameField(JTextField surenameField) {
        this.surenameField = surenameField;
    }

    public void setFathernameField(JTextField fathernameField) {
        this.fathernameField = fathernameField;
    }

    public void setNameField(JTextField nameField) {
        this.nameField = nameField;
    }
}
