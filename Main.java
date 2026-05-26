import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main extends JFrame {
    private final JTextField dateField = new JTextField(10);
    private final JTextField moneyField = new JTextField(10);
    private final JTextField nameField = new JTextField(15);

    private final JLabel totalLabel = new JLabel("Total: $0");
    private final JLabel averageLabel = new JLabel("Average: $0.00");

    private final DefaultTableModel tableModel = new DefaultTableModel(
            new Object[]{"Name", "Date", "Money"}, 0
    );

    public Main() {
        super("Caddy Tracker");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        Utility.loadLoops();

        add(buildInputPanel(), BorderLayout.NORTH);
        add(buildTablePanel(), BorderLayout.CENTER);
        add(buildSummaryPanel(), BorderLayout.SOUTH);

        refreshTable();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel buildInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Add Loop"));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        panel.add(new JLabel("Date (yyyy-mm-dd):"), c);

        c.gridx = 1;
        panel.add(dateField, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(new JLabel("Money earned:"), c);

        c.gridx = 1;
        panel.add(moneyField, c);

        c.gridx = 0;
        c.gridy = 2;
        panel.add(new JLabel("Name:"), c);

        c.gridx = 1;
        panel.add(nameField, c);

        JButton addButton = new JButton("Add Loop");
        addButton.addActionListener(e -> addLoopFromForm());

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        panel.add(addButton, c);

        getRootPane().setDefaultButton(addButton);

        return panel;
    }

    private JScrollPane buildTablePanel() {
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        return new JScrollPane(table);
    }

    private JPanel buildSummaryPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(totalLabel);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(averageLabel);
        return panel;
    }

    private void addLoopFromForm() {
        try {
            String[] parts = dateField.getText().trim().split("-");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Use date format yyyy-mm-dd.");
            }

            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);
            int money = Integer.parseInt(moneyField.getText().trim());
            String name = nameField.getText().trim();

            if (name.isEmpty()) {
                throw new IllegalArgumentException("Name cannot be blank.");
            }

            Loop loop = new Loop(new Date(year, month, day), money, name);
            Utility.addLoop(loop);

            clearForm();
            refreshTable();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Money, year, month, and day must be numbers.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void clearForm() {
        dateField.setText("");
        moneyField.setText("");
        nameField.setText("");
        dateField.requestFocus();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);

        List<Loop> loops = Utility.getLoops();
        for (Loop loop : loops) {
            tableModel.addRow(new Object[]{
                    loop.getName(),
                    loop.getDate(),
                    "$" + loop.getMoney()
            });
        }

        totalLabel.setText("Total: $" + Utility.totalMoney());
        averageLabel.setText(String.format("Average: $%.2f", Utility.summerAverage()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
