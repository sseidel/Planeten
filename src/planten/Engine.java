package planten;

public class Engine {
	
	double counter;
	
	public Engine(){
		counter=0.0;
	}
	
	public Engine(double counter){
		this();
		this.counter=counter;
	}
	/**
	 * @param bezugspunkte
     * @param anzahlBezugspunkte
     * @param rotationsgeschwindigkeit
     * @return
     */
    private int[] berechneDrehmatrixergebnis(int[] bezugspunkte,int anzahlBezugspunkte, double rotationsgeschwindigkeit[]){
        if(anzahlBezugspunkte<1) return bezugspunkte;
        if(1!=anzahlBezugspunkte){
           bezugspunkte=berechneDrehmatrixergebnis(bezugspunkte,anzahlBezugspunkte-1,rotationsgeschwindigkeit);
        }
        double[][] drehmatrix = {{Math.cos(-this.counter*rotationsgeschwindigkeit[rotationsgeschwindigkeit.length-anzahlBezugspunkte/*anzahlBezugspunkte-1*/]),-Math.sin(-this.counter*rotationsgeschwindigkeit[rotationsgeschwindigkeit.length-anzahlBezugspunkte/*anzahlBezugspunkte-1*/])},
           {Math.sin(-this.counter*rotationsgeschwindigkeit[rotationsgeschwindigkeit.length-anzahlBezugspunkte/*anzahlBezugspunkte-1*/]),Math.cos(-this.counter*rotationsgeschwindigkeit[rotationsgeschwindigkeit.length-anzahlBezugspunkte/*anzahlBezugspunkte-1*/])}};
           int[] zielKoordinaten=new int[2];
            zielKoordinaten[0]=(int) (drehmatrix[0][0] * bezugspunkte[0] + drehmatrix[0][1] * bezugspunkte[1]);
            zielKoordinaten[1]=(int) (drehmatrix[1][0] * bezugspunkte[0] + drehmatrix[1][1] * bezugspunkte[1]);
            return zielKoordinaten;
    }

    /**
     *
     * @param bezugspunkte
     * @param anzahlBezugspunkte
     * @param rotationsgeschwindigkeit
     * @param koordinatenVater
     * @return
     */
    protected int[] berchneGedrehteKoordinaten(int[] bezugspunkte,int anzahlBezugspunkte, double rotationsgeschwindigkeit[],int[] koordinatenVater){
        int[] koordinaten =berechneDrehmatrixergebnis(bezugspunkte,anzahlBezugspunkte,rotationsgeschwindigkeit);
        if(anzahlBezugspunkte>0){
        	koordinaten[0]+=koordinatenVater[0];
        	koordinaten[1]+=koordinatenVater[1];
        }
        return koordinaten;
    }


}
