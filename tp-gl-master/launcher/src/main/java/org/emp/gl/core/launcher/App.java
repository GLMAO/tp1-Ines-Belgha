package org.emp.gl.core.launcher;

import org.emp.gl.clients.CompteARebours;
import org.emp.gl.clients.Horloge ;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.clients.Horloge;
import java.util.Random;
public class App {

    public static void main(String[] args) {
         testCompteARebours();
        //testDuTimeService();
    }
    
    private static void testCompteARebours() {
        // 1. Instancier le TimerService
        TimerService timerService = new DummyTimeServiceImpl();
        
        // 2. Test avec un compte à rebours de 5 secondes
        System.out.println("=== Test avec compte à rebours de 5 ===");
        CompteARebours rebours5 = new CompteARebours("Rebours-5", timerService, 5);
        
        // 3. Test avec 10 comptes à rebours aléatoires
        System.out.println("\n=== Test avec 10 comptes à rebours aléatoires ===");
        Random random = new Random();
        CompteARebours[] compteurs = new CompteARebours[10];
        
        for (int i = 0; i < 10; i++) {
            int valeurInitiale = 10 + random.nextInt(11); // 10-20
            compteurs[i] = new CompteARebours("Rebours-" + (i+1), timerService, valeurInitiale);
        }
        
        // Laisser tourner pendant un moment
        try {
            Thread.sleep(25000); // 25 secondes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Fin du test");
    }
    /*private static void testDuTimeService() {
         TimerService timerService = new DummyTimeServiceImpl();
        
      // 2. Instancier plusieurs horloges avec injection du service
        Horloge horloge1 = new Horloge("Salon", timerService);
        Horloge horloge2 = new Horloge("Cuisine", timerService);
        Horloge horloge3 = new Horloge("Chambre", timerService);
        // Use horloge3 so the local variable is not reported as unused
        System.out.println(horloge3);
    
  // 3. Laisser tourner pendant un moment pour observer le résultat
        try {
            // Attendre 30 secondes pour voir plusieurs mises à jour
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Fin du test");
    }
*/

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    } 
 
}
