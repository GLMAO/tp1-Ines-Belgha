package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class HorlogeAnalogique implements TimerChangeListener {

    private TimerService timerService;
    private JFrame frame;
    private AnalogClockPanel clockPanel;
    private String title;

    public HorlogeAnalogique(String title, TimerService timerService) {
        this.title = title;
        this.timerService = timerService;
        this.timerService.addTimeChangeListener(this);
        
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        frame.setLayout(new BorderLayout());

        clockPanel = new AnalogClockPanel();
        frame.add(clockPanel, BorderLayout.CENTER);

        // Ajouter un label pour l'heure numérique
        JLabel digitalLabel = new JLabel("", SwingConstants.CENTER);
        digitalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        digitalLabel.setForeground(Color.RED);
        frame.add(digitalLabel, BorderLayout.SOUTH);

        // Mettre à jour l'heure numérique
        Timer timer = new Timer(1000, e -> {
            if (timerService != null) {
                String time = String.format("%02d:%02d:%02d",
                        timerService.getHeures(),
                        timerService.getMinutes(),
                        timerService.getSecondes());
                digitalLabel.setText(time);
            }
        });
        timer.start();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Redessiner l'horloge analogique
        SwingUtilities.invokeLater(() -> {
            if (clockPanel != null) {
                clockPanel.repaint();
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

    // Panel pour dessiner l'horloge analogique
    private class AnalogClockPanel extends JPanel {
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int radius = Math.min(centerX, centerY) - 20;

            // Dessiner le cercle de l'horloge
            g2d.setColor(Color.WHITE);
            g2d.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
            g2d.setColor(Color.BLACK);
            g2d.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

            // Dessiner les marques des heures
            for (int i = 0; i < 12; i++) {
                double angle = Math.PI / 6 * i;
                int x1 = (int) (centerX + (radius - 10) * Math.sin(angle));
                int y1 = (int) (centerY - (radius - 10) * Math.cos(angle));
                int x2 = (int) (centerX + radius * Math.sin(angle));
                int y2 = (int) (centerY - radius * Math.cos(angle));
                g2d.drawLine(x1, y1, x2, y2);
            }

            if (timerService != null) {
                // Aiguille des heures
                int hours = timerService.getHeures() % 12;
                double hourAngle = Math.PI / 6 * hours + Math.PI / 360 * timerService.getMinutes();
                drawHand(g2d, centerX, centerY, hourAngle, radius * 0.5, Color.BLACK, 4);

                // Aiguille des minutes
                double minuteAngle = Math.PI / 30 * timerService.getMinutes();
                drawHand(g2d, centerX, centerY, minuteAngle, radius * 0.7, Color.BLUE, 3);

                // Aiguille des secondes
                double secondAngle = Math.PI / 30 * timerService.getSecondes();
                drawHand(g2d, centerX, centerY, secondAngle, radius * 0.9, Color.RED, 1);
            }
        }

        private void drawHand(Graphics2D g2d, int x, int y, double angle, double length, Color color, int thickness) {
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(thickness));
            int x2 = (int) (x + length * Math.sin(angle));
            int y2 = (int) (y - length * Math.cos(angle));
            g2d.drawLine(x, y, x2, y2);
        }
    }
}