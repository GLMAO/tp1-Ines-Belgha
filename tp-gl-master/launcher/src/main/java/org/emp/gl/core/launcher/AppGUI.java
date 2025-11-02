package org.emp.gl.core.launcher;

import org.emp.gl.clients.HorlogeGraphique;
import org.emp.gl.clients.HorlogeAnalogique;
import org.emp.gl.clients.CompteARebours;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerService;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AppGUI {

    private static TimerService timerService;

    public static void main(String[] args) {
        // Initialiser le service de timer
        timerService = new DummyTimeServiceImpl();
        
        // Créer l'interface utilisateur
        SwingUtilities.invokeLater(() -> createMainWindow());
    }

    private static void createMainWindow() {
        JFrame mainFrame = new JFrame("Application Horloge - Pattern Observer");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);
        mainFrame.setLayout(new BorderLayout());

        // Panel de contrôle
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JButton btnHorlogeNumerique = new JButton("Horloge Numérique");
        JButton btnHorlogeAnalogique = new JButton("Horloge Analogique");
        JButton btnCompteARebours = new JButton("Compte à Rebours");
        JButton btnQuitter = new JButton("Quitter");

        controlPanel.add(btnHorlogeNumerique);
        controlPanel.add(btnHorlogeAnalogique);
        controlPanel.add(btnCompteARebours);
        controlPanel.add(btnQuitter);

        // Actions des boutons
        btnHorlogeNumerique.addActionListener(e -> {
            new HorlogeGraphique("Horloge Numérique", timerService);
        });

        btnHorlogeAnalogique.addActionListener(e -> {
            new HorlogeAnalogique("Horloge Analogique", timerService);
        });

        btnCompteARebours.addActionListener(e -> {
            Random random = new Random();
            int valeur = 5 + random.nextInt(10);
            new CompteARebours("Compte à Rebours", timerService, valeur);
        });

        btnQuitter.addActionListener(e -> {
            System.exit(0);
        });

        mainFrame.add(controlPanel, BorderLayout.NORTH);

        // Message de bienvenue
        JLabel welcomeLabel = new JLabel(
            "<html><center>Application de démonstration du Pattern Observer<br>" +
            "Cliquez sur les boutons pour créer différentes horloges</center></html>", 
            SwingConstants.CENTER
        );
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        mainFrame.add(welcomeLabel, BorderLayout.CENTER);

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}