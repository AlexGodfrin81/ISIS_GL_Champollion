package champollion;

import static champollion.TypeIntervention.CM;
import static champollion.TypeIntervention.TD;
import static champollion.TypeIntervention.TP;
import java.util.Date;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java;
		
	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");		
	}
	

	@Test
	public void testNouvelEnseignantSansService() {
		assertEquals(0, untel.heuresPrevues(),
                        "Un nouvel enseignant doit avoir 0 heures prévues");
	}
	
	@Test
	public void testAjouteHeures() {
                // 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		assertEquals(10, untel.heuresPrevuesPourUE(uml),
                        "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

                // 20h TD pour UML
                untel.ajouteEnseignement(uml, 0, 20, 0);
                
		assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                         "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");		
		
	}
        
        @Test
        public void testHeurePrevu(){
                untel.ajouteEnseignement(uml, 2, 10, 5);
                untel.ajouteEnseignement(java, 2, 0, 0);
                assertEquals(Math.round(2*1.5+10+5*0.75+2), untel.heuresPrevues());
        }
        
        @Test
        public void testIntervention1(){
                Intervention i0 = new Intervention(new Salle("EB111",25), java, untel, new Date(), 2, CM);
                Intervention i1 = new Intervention(new Salle("EB110",30), uml, untel, new Date(), 3, TD);
                Intervention i2 = new Intervention(new Salle("EB110",30), uml, untel, new Date(), 4, TP);
                untel.ajouteIntervention(i0);
                untel.ajouteIntervention(i1);
                untel.ajouteIntervention(i2);
                assertEquals(Math.round((2*1.5)+3+(4*0.75)), untel.heuresPlanifiees());
        }
        
        @Test
        public void TestIntervention2(){
                Intervention i0 = new Intervention(new Salle("EB111",25), java, untel, new Date(), 2, CM);
                Intervention i1 = new Intervention(new Salle("EB110",30), uml, untel, new Date(), 3, TD);
                Intervention i2 = new Intervention(new Salle("EB110",30), uml, untel, new Date(), 4, TP);
                untel.ajouteIntervention(i1);
                untel.ajouteIntervention(i2);
                assertEquals(Math.round(3+(4*0.75)), untel.heuresPlanifiees());
        }
        
        @Test
        public void TestSousService(){
                //Une petite pensée pour l'enseignant qui devra faire ce cours de 100h 
                Intervention i0 = new Intervention(new Salle("EB111",25), java, untel, new Date(), 100, CM);
                Intervention i1 = new Intervention(new Salle("EB110",30), uml, untel, new Date(), 50, TD);
                Intervention i2 = new Intervention(new Salle("EB110",30), uml, untel, new Date(), 75, TP);
                untel.ajouteIntervention(i0);
                untel.ajouteIntervention(i1);
                untel.ajouteIntervention(i2);
                //Heure intervention = 150 + 50 + 56.25 = 256.25 = 256
                untel.ajouteEnseignement(uml, 102, 50, 75);
                //Heure Enseignement = 153 + 50 + 56.25 = 259.25 = 259
                assertFalse(untel.enSousService());
        }
        
}
