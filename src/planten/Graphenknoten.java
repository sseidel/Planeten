package planten;

public class Graphenknoten {
	protected Graphenknoten root;
	private String name;
	private int niveau;
	private double rotationsgeschwindigkeit;
	protected int koordinaten[],zeichnen[];
	
	/**
	 * standart konstructor sets koordinaten,name and root = null
	 */
	public Graphenknoten(){
		this.koordinaten=null;
		this.name=null;
		this.root=null;
	}
	
	/**
	 * initializes a Graphknoten object and sets the name of the object
	 * @param name is objects name
	 */
	Graphenknoten(String name){ 
		this();
		this.name=new String(name);
	}
	
	/**
	 *  initializes a Graphknoten object and sets the koordinaten of the object
	 * @param koordinaten awaits an int-array with 2 komponents
	 */
	Graphenknoten(int[] koordinaten){
		this();
		this.koordinaten=koordinaten;
	}
	
	Graphenknoten(int[] koordinaten,Graphenknoten root){
		this(koordinaten);
		this.setRoot(root);
		
	}
	
	public void setRoot(Graphenknoten root){
		this.root=root;
		this.setNiveau();
	}
	
	/**
	 * sets its niveau
	 */
	private void setNiveau(){
		this.niveau=0;
		Graphenknoten temp= this.root;
		while(temp==null){
			niveau++;
			temp=temp.root;
		}
	}
	
	public int getNiveau(){return this.niveau;}
	
	public double[] getRotationSpeed(){
		Graphenknoten temp=this.root;
		double[] rotationspeeds=new double[this.niveau];
		for(int i=0;i<this.niveau;i++){
			rotationspeeds[i]=temp.rotationsgeschwindigkeit;
			temp=temp.root;
		}
		return rotationspeeds;
		}
	
	/**
	 * returns the objects name
	 * @return objects name
	 */
	public String getName(){return new String(this.name);}
	
	/**
	 * sets the objects name
	 * @param name is the new objects name
	 */
	public void setName(String name){this.name =new String(name);}
	
	

}
