import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ReservationFrame extends JFrame {

    private JTextField passengerField;
    private JTextField trainNumberField;
    private JTextField trainNameField;
    private JTextField dateField;
    private JTextField sourceField;
    private JTextField destinationField;

    private JComboBox<String> classBox;

    public ReservationFrame() {

        setTitle("Online Reservation System");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(
                new GridLayout(9, 2, 10, 10)
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 25, 30)
        );

        passengerField = new JTextField();
        trainNumberField = new JTextField();
        trainNameField = new JTextField();
        dateField = new JTextField();

        sourceField = new JTextField();
        destinationField = new JTextField();

        trainNameField.setEditable(false);

        classBox = new JComboBox<>(
                new String[]{
                        "AC First Class",
                        "AC 2 Tier",
                        "AC 3 Tier",
                        "Sleeper",
                        "General"
                }
        );

        JButton trainButton = new JButton("Get Train");
        JButton bookButton = new JButton("Book Ticket");
        JButton cancelPageButton =
                new JButton("Cancel Ticket");

        panel.add(new JLabel("Passenger Name:"));
        panel.add(passengerField);

        panel.add(new JLabel("Train Number:"));
        panel.add(trainNumberField);

        panel.add(trainButton);
        panel.add(trainNameField);

        panel.add(new JLabel("Class Type:"));
        panel.add(classBox);

        panel.add(new JLabel("Journey Date (dd-MM-yyyy):"));
        panel.add(dateField);

        panel.add(new JLabel("Source Station:"));
        panel.add(sourceField);

        panel.add(new JLabel("Destination Station:"));
        panel.add(destinationField);

        panel.add(bookButton);
        panel.add(cancelPageButton);

        add(panel);

        trainButton.addActionListener(
                e -> findTrain()
        );

        bookButton.addActionListener(
                e -> bookTicket()
        );

        cancelPageButton.addActionListener(
                e -> new CancellationFrame()
        );

        setVisible(true);
    }

    private void findTrain() {

        String number = trainNumberField.getText().trim();

        if (!number.matches("\\d+")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Train number must be numeric."
            );

            return;
        }

        String sql =
                "SELECT train_name FROM trains WHERE train_number=?";

        try (Connection con = Database.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(number));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                trainNameField.setText(
                        rs.getString("train_name")
                );

            } else {

                trainNameField.setText("");

                JOptionPane.showMessageDialog(
                        this,
                        "Train not found."
                );
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Database error."
            );
        }
    }

    private void bookTicket() {

        String passenger =
                passengerField.getText().trim();

        String trainNumber =
                trainNumberField.getText().trim();

        String trainName =
                trainNameField.getText().trim();

        String classType =
                (String) classBox.getSelectedItem();

        String date =
                dateField.getText().trim();

        String source =
                sourceField.getText().trim();

        String destination =
                destinationField.getText().trim();

        if (passenger.isEmpty()
                || trainNumber.isEmpty()
                || trainName.isEmpty()
                || date.isEmpty()
                || source.isEmpty()
                || destination.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all required fields."
            );

            return;
        }

        if (!trainNumber.matches("\\d+")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Train number must be numeric."
            );

            return;
        }

        try {

            SimpleDateFormat format =
                    new SimpleDateFormat("dd-MM-yyyy");

            format.setLenient(false);

            Date d = format.parse(date);

            String pnr =
                    "PNR" +
                    UUID.randomUUID()
                       .toString()
                       .substring(0, 8)
                       .toUpperCase();

            String sql = """
                INSERT INTO reservations
                (pnr, passenger_name, train_number,
                 train_name, class_type, journey_date,
                 source, destination)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

            try (Connection con = Database.connect();
                 PreparedStatement ps =
                         con.prepareStatement(sql)) {

                ps.setString(1, pnr);
                ps.setString(2, passenger);
                ps.setInt(3, Integer.parseInt(trainNumber));
                ps.setString(4, trainName);
                ps.setString(5, classType);
                ps.setString(6, date);
                ps.setString(7, source);
                ps.setString(8, destination);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        this,
                        "BOOKING CONFIRMED!\n\n"
                        + "PNR: " + pnr + "\n"
                        + "Passenger: " + passenger + "\n"
                        + "Train: " + trainName + "\n"
                        + "Class: " + classType + "\n"
                        + "Date: " + date + "\n"
                        + "From: " + source + "\n"
                        + "To: " + destination,
                        "Reservation Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid date or database error."
            );
        }
    }

    private void clearFields() {

        passengerField.setText("");
        trainNumberField.setText("");
        trainNameField.setText("");
        dateField.setText("");
        sourceField.setText("");
        destinationField.setText("");
    }
}