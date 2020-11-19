/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package champollion;

import java.util.Date;

/**
 *
 * @author ALEX
 */
public class Intervention {

    private Date debut;
    private int duree;
    private boolean annulee = false;
    private Salle salle;
    private UE ue;
    private Enseignant ens;
    private TypeIntervention ti;
    
    public Intervention(Salle salle, UE ue, Enseignant ens, Date debut, int duree, TypeIntervention ti){
        this.salle = salle;
        this.ue = ue;
        this.ens = ens;
        this.debut = debut;
        this.duree = duree;
        this.ti = ti;
    }
    
    public Date getDebut() {
        return debut;
    }

    public int getDuree() {
        return duree;
    }

    public boolean isAnnulee() {
        return annulee;
    }

    public Salle getSalle() {
        return salle;
    }

    public UE getUe() {
        return ue;
    }

    public Enseignant getEns() {
        return ens;
    }
    
    public TypeIntervention getType(){
        return this.ti;
    }
}
