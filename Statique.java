public class Statique extends Mecanique {
	private static final long serialVersionUID = 1L;

	public Statique(String f, double x, double y, int v, String a) {
		super(f,x,y,v,a, "stattique", 1);
	}
	
	public void afficher(){
		super.afficher();
	}
}
