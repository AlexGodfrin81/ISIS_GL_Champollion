package champollion;

import static champollion.TypeIntervention.CM;
import static champollion.TypeIntervention.TD;
import static champollion.TypeIntervention.TP;
import java.util.ArrayList;

public class Enseignant extends Personne {

    ArrayList<ServicePrevu> mesCours = new ArrayList<>();
    ArrayList<Intervention> CoursPlannifie = new ArrayList<>();

    public Enseignant(String nom, String email) {
        super(nom, email);
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures équivalent TD" Pour le calcul : 1 heure
     * de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut 0,75h
     * "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
        int res = 0;
        for (ServicePrevu sp : mesCours){
            res += sp.getCM()*1.5 + sp.getTD() + sp.getTP()*0.75;
        }
        return Math.round(res);
    }
    

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE spécifiée en "heures équivalent TD" Pour
     * le calcul : 1 heure de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure
     * de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
        int res = 0;
        for (ServicePrevu sp : mesCours){
            if (sp.getUE() == ue){
                res += sp.getCM()*1.5 + sp.getTD() + sp.getTP()*0.75;
            }
        }
        return Math.round(res);
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        this.mesCours.add(new ServicePrevu(volumeCM, volumeTD, volumeTP, this, ue));
    }
    
    public void ajouteIntervention(Intervention i){
        if (!CoursPlannifie.contains(i)){
            this.CoursPlannifie.add(i);
        }
    }
    
    public int heuresPlanifiees(){
        int res = 0;
        for (Intervention it : CoursPlannifie){
            switch (it.getType()){
                case CM:
                    res += it.getDuree()*1.5;
                    break;
                case TD:
                    res += it.getDuree();
                    break;
                case TP:
                    res += it.getDuree()*0.75;
                    break;
            }
        }
        return Math.round(res);
    }
    
    public boolean enSousService(){
        return (this.heuresPrevues() < this.heuresPlanifiees() && 
                this.heuresPlanifiees() >= 192);
    }
}
