package org.emp.gl.core.launcher;

import org.emp.gl.clients.Horloge ;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerService;
public class App {

    public static void main(String[] args) {

        testDuTimeService();
    }

    private static void testDuTimeService() {
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

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
