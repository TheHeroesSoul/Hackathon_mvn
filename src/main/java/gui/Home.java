package main.java.gui;

import main.java.controller.Controller;
import main.java.model.Hackathon;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The type Home.
 */
public class Home {
    private JButton logoutButton;
    private JList<Hackathon> list1;
    private JButton creaHackathonButton;
    private JFrame frame;
    private DefaultListModel<Hackathon> listModel;

    /**
     * Instantiates a new Home.
     *
     * @param controller      the controller
     * @param isAuthenticated the is authenticated
     */
    public Home(Controller controller, boolean isAuthenticated) {
        frame = new JFrame("Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        JPanel mainPanel = new JPanel(new BorderLayout());

        listModel = new DefaultListModel<>();
        aggiornaLista(controller.getHackathonList());
        list1 = new JList<>(listModel);
        mainPanel.add(new JScrollPane(list1), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        logoutButton = new JButton("Logout");
        creaHackathonButton = new JButton("Crea Hackathon");
        buttonPanel.add(logoutButton);
        buttonPanel.add(creaHackathonButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.setContentPane(mainPanel);
        frame.setVisible(true);

        logoutButton.addActionListener(e -> {
            frame.dispose();
            controller.showLogin(isAuthenticated);
        });

        creaHackathonButton.addActionListener(e -> {

            main.java.model.Utente utente = controller.getAuthenticatedUser();
            CreazioneHackathon dialog = new CreazioneHackathon(utente, controller);
            dialog.setVisible(true);
            aggiornaLista(controller.getHackathonList());
        });
        list1.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Hackathon selezionato = list1.getSelectedValue();
                if (selezionato != null) {
                    controller.mostraPaginaHackathon(selezionato);
                }
            }
        });

    }

    /**
     * Nascondi.
     */
    public void nascondi() {
        frame.setVisible(false);
    }

    /**
     * Mostra.
     */
    public void mostra() {
        frame.setVisible(true);
    }

    /**
     * Gets frame.
     *
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }

    private void aggiornaLista(List<Hackathon> hackathons) {
        listModel.clear();
        for (Hackathon h : hackathons) {
            listModel.addElement(h);
        }
    }


}