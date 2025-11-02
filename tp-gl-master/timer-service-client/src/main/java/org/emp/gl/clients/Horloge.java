package org.emp.gl.clients ; 

import org.emp.gl.timer.service.TimerService ;

import java.beans.PropertyChangeEvent;

import org.emp.gl.timer.service.TimerChangeListener;

public class Horloge implements TimerChangeListener {

    String name; 
    TimerService timerService ; 


    public Horloge (String name, TimerService timerService) {
        this.name = name ; 
        this.timerService = timerService;
 // S'inscrire comme listener auprès du TimerService
        this.timerService.addTimeChangeListener(this);
        System.out.println ("Horloge "+name+" initialized!") ;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Afficher l'heure à chaque changement de seconde
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            afficherHeure();
        }
    }
    public void afficherHeure () {
        if (timerService != null)
            System.out.println (name + " affiche " + 
                                timerService.getHeures() +":"+
                                timerService.getMinutes()+":"+
                                timerService.getSecondes()) ;
    }

    public void arreter() {
        if (timerService != null) {
            this.timerService.removeTimeChangeListener(this);
        }
    }
}
