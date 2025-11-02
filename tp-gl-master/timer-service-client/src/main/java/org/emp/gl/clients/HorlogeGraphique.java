package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class HorlogeGraphique implements TimerChangeListener {

    private TimerService timerService;
    private JFrame frame;
    private JLabel timeLabel;
    private String title;

    public HorlogeGraphique(String title, TimerService timerService) {
        this.title = title;
        this.timerService = timerService;
        this.timerService.addTimeChangeListener(this);
        
        createGUI();
        updateTime(); // Afficher l'heure initiale
    }

    private void createGUI() {
        // Créer la fenêtre
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new BorderLayout());

        // Créer le label pour afficher l'heure
        timeLabel = new JLabel("", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timeLabel.setForeground(Color.BLUE);
        
        frame.add(timeLabel, BorderLayout.CENTER);
        
        // Positionner la fenêtre
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Mettre à jour l'affichage à chaque changement de temps
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName()) ||
            TimerChangeListener.MINUTE_PROP.equals(evt.getPropertyName()) ||
            TimerChangeListener.HEURE_PROP.equals(evt.getPropertyName())) {
            
            updateTime();
        }
    }

    private void updateTime() {
        // Mettre à jour l'affichage dans l'EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            if (timerService != null && timeLabel != null) {
                String time = String.format("%02d:%02d:%02d",
                        timerService.getHeures(),
                        timerService.getMinutes(),
                        timerService.getSecondes());
                timeLabel.setText(time);
            }
        });
    }

    public void arreter() {
        if (timerService != null) {
            this.timerService.removeTimeChangeListener(this);
        }
        if (frame != null) {
            frame.dispose();
        }
    }
}