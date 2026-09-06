import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CancellationFrame extends JFrame {

    private JTextField pnrField;
    private JTextArea detailsArea;
    private JButton cancelButton;

    private String selectedPNR;

    public CancellationFrame() {

        setTitle("Cancel Reservation");
        setSize(500, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel top = new JPanel(new BorderLayout(10, 10));

        pnrField = new JTextField();

        pnrField.setBorder(
                BorderFactory.createTitledBorder("PNR Number")
        );

        JButton fetchButton =
                new JButton("Fetch Booking");

        top.add(pnrField, BorderLayout.CENTER);
        top.add(fetchButton, BorderLayout.EAST);

        detailsArea = new JTextArea();
        detailsArea.setEditable(false);

        cancelButton =
                new JButton("Confirm Cancellation");

        cancelButton.setEnabled(false);

        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        panel.add(top, BorderLayout.NORTH);
        panel.add(
                new JScrollPane(detailsArea),
                BorderLayout.CENTER
        );
        panel.add(cancelButton, BorderLayout.SOUTH);

        add(panel);

        fetchButton.addActionListener(
                e -> fetchBooking()
        );

        cancelButton.addActionListener(
                e -> cancelBooking()
        );

        setVisible(true);
    }

    private void fetchBooking() {

        String pnr =
                pnrField.getText().trim();

        if (pnr.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter PNR number."
            );

            return;
        }

        String sql =
                "SELECT * FROM reservations WHERE pnr=?";

        try (Connection con = Database.connect();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(1, pnr);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                selectedPNR = pnr;

                detailsArea.setText(
                        "PNR: "
                        + rs.getString("pnr")
                        + "\n\nPassenger: "
                        + rs.getString("passenger_name")
                        + "\n\nTrain Number: "
                        + rs.getInt("train_number")
                        + "\n\nTrain Name: "
                        + rs.getString("train_name")
                        + "\n\nClass: "
                        + rs.getString("class_type")
                        + "\n\nJourney Date: "
                        + rs.getString("journey_date")
                        + "\n\nSource: "
                        + rs.getString("source")
                        + "\n\nDestination: "
                        + rs.getString("destination")
                );

                cancelButton.setEnabled(true);

            } else {

                detailsArea.setText(
                        "Booking not found."
                );

                cancelButton.setEnabled(false);
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error: "
                    + e.getMessage()
            );
        }
    }

    private void cancelBooking() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to cancel "
                        + "this booking?",
                        "Confirm Cancellation",
                        JOptionPane.YES_NO_OPTION
                );

        if (result != JOptionPane.YES_OPTION) {
            return;
        }

        String sql =
                "DELETE FROM reservations WHERE pnr=?";

        try (Connection con = Database.connect();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(1, selectedPNR);

            int rows =
                    ps.executeUpdate();

            if (rows > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Reservation cancelled successfully."
                );

                detailsArea.setText("");
                pnrField.setText("");
                cancelButton.setEnabled(false);
                selectedPNR = null;

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cancellation failed."
            );
        }
    }
}