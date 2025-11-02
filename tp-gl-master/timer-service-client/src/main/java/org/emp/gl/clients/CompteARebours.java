package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;
import java.beans.PropertyChangeEvent;
public class CompteARebours implements TimerChangeListener {

    private String name;
    private TimerService timerService;
    private int compteur;

    public CompteARebours(String name, TimerService timerService, int initialValue) {
        this.name = name;
        this.timerService = timerService;
        this.compteur = initialValue;
        
        // S'inscrire comme listener
        this.timerService.addTimeChangeListener(this);
        
        System.out.println(name + " initialisé avec " + compteur + " secondes");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Décrémenter à chaque changement de seconde
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            if (compteur > 0) {
                compteur--;
                System.out.println(name + " : " + compteur);
                
                // Se désinscrire quand on arrive à 0
                if (compteur == 0) {
                    System.out.println(name + " : TERMINÉ !");
                    arreter();
                }
            }
        }
    }

    public void arreter() {
        if (timerService != null) {
            this.timerService.removeTimeChangeListener(this);
            System.out.println(name + " désinscrit du timer");
        }
    }

    public int getCompteur() {
        return compteur;
    }
}