package main.java.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VisualizzaDoc extends JDialog {
    private JTextPane documentoTextPane;
    private JComboBox<Integer> votoComboBox;
    private JButton valutaButton;
    private JButton cancellaButton;
    private JPanel contentPane;

    public VisualizzaDoc() {
        setTitle("Valutazione Documento");
        setSize(600, 400);
        setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextArea messaggioArea = new JTextArea("Messaggio del team:\nAbbiamo completato l'upload.");
        messaggioArea.setEditable(false);
        messaggioArea.setBackground(panel.getBackground());
        messaggioArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        panel.add(messaggioArea, BorderLayout.NORTH);

        documentoTextPane = new JTextPane();
        documentoTextPane.setText("public class ProgressoTeam {\n    // Codice e spiegazione del progresso qui\n}");
        documentoTextPane.setEditable(false);
        panel.add(new JScrollPane(documentoTextPane), BorderLayout.CENTER);

        JPanel sud = new JPanel(new FlowLayout(FlowLayout.LEFT));
        votoComboBox = new JComboBox<>();
        for (int i = 1; i <= 10; i++) {
            votoComboBox.addItem(i);
        }
        sud.add(votoComboBox);

        valutaButton = new JButton("Valuta");
        cancellaButton = new JButton("Cancella");

        sud.add(valutaButton);
        sud.add(cancellaButton);

        panel.add(sud, BorderLayout.SOUTH);
        setContentPane(panel);

        valutaButton.addActionListener(e -> {
            int voto = (Integer) votoComboBox.getSelectedItem();
            JOptionPane.showMessageDialog(this, "Hai assegnato il voto: " + voto);
            dispose();
        });

        cancellaButton.addActionListener(e -> dispose());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VisualizzaDoc dialog = new VisualizzaDoc();
            dialog.setVisible(true);
        });
    }
}