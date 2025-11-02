IASD_07

[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/t19xNtmg)
    
    
    
    TP Génie Logiciel - Design Pattern Observer

# Description

Implémentation du pattern Observer avec un service de timer permettant à différentes horloges et comptes à rebours de se mettre à jour automatiquement.


# Fonctionnalités Implémentées

   # Parties Obligatoires

    Service de Timer avec mécanisme Observer

    Horloges multiples affichant l'heure en console

    Compte à rebours avec désabonnement automatique

    Gestion de la concurrence avec PropertyChangeSupport

   # Bonus

    Interface graphique avec horloges numérique et analogique

    Application de démonstration avec contrôle des observateurs

# Compilation et Exécution

  # Compilation
      mvn clean install

  # Exécution

   # Application console
mvn -pl launcher exec:java -Dexec.mainClass="org.emp.gl.core.launcher.App"

   # Application graphique  
       mvn -pl launcher exec:java -Dexec.mainClass="org.emp.gl.core.launcher.AppGUI"

# Problèmes Résolus

1. Concurrence

    Problème : Modification liste observers pendant itération

    Solution : PropertyChangeSupport thread-safe

2. Désabonnement

    Problème : Observateurs ne se désabonnent pas

    Solution : Auto-désabonnement à compteur zéro

3. Dépendances

    Problème : Couplage fort entre classes

    Solution : Programmation vers interfaces