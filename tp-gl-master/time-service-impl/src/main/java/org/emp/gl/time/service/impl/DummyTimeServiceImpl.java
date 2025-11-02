/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gl.time.service.impl;

import java.beans.PropertyChangeSupport;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

/**
 *
 * @author tina
 */
public class DummyTimeServiceImpl implements TimerService {

    int dixiemeDeSeconde;
    int minutes;
    int secondes;
    int heures;
    PropertyChangeSupport propertyChangeSupport;

    /**
     * Constructeur du DummyTimeServiceImpl: ici, 
     * nous nous avons utilisé un objet Timer, qui permet de
     * réaliser des tics à chaque N millisecondes
     */
    public DummyTimeServiceImpl() {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        setTimeValues();
        // initialize schedular
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
             @Override
            public void run() {
                timeChanged();
            }
        };
        timer.scheduleAtFixedRate(task, 100, 100);
    }

    private void setTimeValues() {
        LocalTime localTime = LocalTime.now();

        setSecondes(localTime.getSecond());
        setMinutes(localTime.getMinute());
        setHeures(localTime.getHour());
        setDixiemeDeSeconde(localTime.getNano() / 100000000);
    }

    @Override
    public void addTimeChangeListener(TimerChangeListener pl) {
        propertyChangeSupport.addPropertyChangeListener(pl);
    }

    @Override
    public void removeTimeChangeListener(TimerChangeListener pl) {
        propertyChangeSupport.removePropertyChangeListener(pl);
    }

    private void timeChanged() {
        setTimeValues();
    }

    public void setDixiemeDeSeconde(int newDixiemeDeSeconde) {
        if (dixiemeDeSeconde == newDixiemeDeSeconde)
            return;

        int oldValue = dixiemeDeSeconde;
        dixiemeDeSeconde = newDixiemeDeSeconde;

        // informer les listeners avec PropertyChangeSupport !
        propertyChangeSupport.firePropertyChange(TimerChangeListener.DIXEME_DE_SECONDE_PROP,
                   oldValue, dixiemeDeSeconde);
    }

    public void setSecondes(int newSecondes) {
        if (secondes == newSecondes)
            return;

        int oldValue = secondes;
        secondes = newSecondes;

        // informer les listeners avec PropertyChangeSupport !
        propertyChangeSupport.firePropertyChange(TimerChangeListener.SECONDE_PROP,
                   oldValue, secondes);
    }

    public void setMinutes(int newMinutes) {
        if (minutes == newMinutes)
            return;

        int oldValue = minutes;
        minutes = newMinutes;

        // informer les listeners avec PropertyChangeSupport !
        propertyChangeSupport.firePropertyChange(TimerChangeListener.MINUTE_PROP,
                   oldValue, minutes);
    }

    public void setHeures(int newHeures) {
        if (heures == newHeures)
            return;

        int oldValue = heures;
        heures = newHeures;

        // informer les listeners avec PropertyChangeSupport !
        propertyChangeSupport.firePropertyChange(TimerChangeListener.HEURE_PROP,
                   oldValue, heures);
    }

    @Override
    public int getDixiemeDeSeconde() {
        return dixiemeDeSeconde;
    }

    @Override
    public int getHeures() {
        return heures;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public int getSecondes() {
        return secondes;
    }
}