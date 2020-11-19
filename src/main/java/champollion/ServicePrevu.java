package champollion;

public class ServicePrevu {
	
    private int volumeCM;
    private int volumeTD;
    private int volumeTP;
    private Enseignant ens;
    private UE ue;
    
    public ServicePrevu(int volumeCM, int volumeTD, int volumeTP, Enseignant ens, UE ue){
        this.volumeCM = volumeCM;
        this.volumeTD = volumeTD;
        this.volumeTP = volumeTP;
        this.ens =  ens;
        this.ue = ue;
    }
    
    public int getCM(){
        return this.volumeCM;
    }
    
    public int getTD(){
        return this.volumeTD;
    }
    
    public int getTP(){
        return this.volumeTP;
    }
    
    public Enseignant getEnseignant(){
        return this.ens;
    }
    
    public UE getUE(){
        return this.ue;
    }
    
}
