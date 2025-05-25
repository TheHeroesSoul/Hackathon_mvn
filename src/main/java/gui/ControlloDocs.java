package main.java.gui;

import javax.swing.*;

public class ControlloDocs extends JDialog {
    private JPanel contentPane;
    private JList<String> list1;
    private JButton buttonInvia;
    private JButton buttonBack;

    public ControlloDocs() {
        contentPane = new JPanel();
        list1 = new JList<>();
        buttonInvia = new JButton("Invia");
        buttonBack = new JButton("Indietro");

        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("Documento1.pdf");
        model.addElement("Documento2.pdf");
        model.addElement("Documento3.pdf");
        list1.setModel(model);

        contentPane.add(new JScrollPane(list1));
        contentPane.add(buttonInvia);
        contentPane.add(buttonBack);

        setContentPane(contentPane);
        setModal(true);
        setTitle("Controllo Documenti");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        buttonInvia.addActionListener(e -> onInvia());
        buttonBack.addActionListener(e -> onCancel());
    }

    private void onInvia() {
        String selected = list1.getSelectedValue();
        if (selected != null) {
            JOptionPane.showMessageDialog(this, "Documento inviato: " + selected);
        } else {
            JOptionPane.showMessageDialog(this, "Seleziona un documento da inviare.");
        }
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ControlloDocs dialog = new ControlloDocs();
            dialog.setVisible(true);
        });
    }
}