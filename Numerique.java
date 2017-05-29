public class Numerique extends Emplacements implements TmpAffichage {
	private static final long serialVersionUID = 1L;
	
	private int nbEmp;

	public Numerique(String f, int v, double x, double y, int nb, String a) {
		super(f,v,x,y,a, "numerique", nb);
		setNbEmp(nb);
	}

	public int getNbEmp() {
		return nbEmp;
	}

	public void setNbEmp(int nbEmp) {
		this.nbEmp = nbEmp;
	}

	@Override
	public int tempaff() {
		return 1/nbEmp;
	}
	
	public void afficher(){
		super.afficher();
		System.out.println("Nombre d'emplacement: "+nbEmp);
	}

}
