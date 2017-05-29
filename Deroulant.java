public class Deroulant extends Mecanique implements TmpAffichage {
	private static final long serialVersionUID = 1L;
	
	private int nbEmp;

	public Deroulant(String f, int v, double x, double y, int nb, String a) {
		super(f,x,y,v,a, "deroulant", nb);
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
