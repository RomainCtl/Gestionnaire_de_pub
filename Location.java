import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Scanner;

public class Location implements ajoutSuppr,modif,Serializable {
	private static final long serialVersionUID = 1L;
	
	public String numLoc;
	private Emplacements emp;
	private Clients clt;
	private Calendar dtdeb;
	private Date dtfin;
	private long dure;
	
	public Location(Emplacements e, Clients c, Date f) {
		numLoc="";
		for (int i=0 ; i<10 ; i++){
			Random rand = new Random();
			int nbtmp = rand.nextInt(9 - 0 + 1) + 0;
			numLoc+=nbtmp;
		}
		setEmp(e);
		setClt(c);
		dtdeb=Calendar.getInstance();
		dtfin=f;
	}
	
	
	@Override
	public void ajouter() {
		try {
			FileOutputStream fil = new FileOutputStream(".\\location\\"+numLoc);
			ObjectOutputStream obj= new ObjectOutputStream(fil);
			
			try {
				obj.writeObject(this);
				
				//vider le tampon
				obj.flush();
			} finally {
				obj.close();
				fil.close();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Erreur !! Impossible d'ouvrir le fichier client");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Erreur !! Impossible de serialize le fichier");
			e.printStackTrace();
		}
	}

	@Override
	public void supprimer() {
		File fil = new File(".\\location\\"+numLoc);
		fil.delete(); 
	}
	
	
	@Override
	public void modifier() {
		//supprimer pour ajoter
		//Suppression de cette reservation de pour l'emplacement
		ArrayList<Location> tmp1 = this.emp.getTabLocEnCour();
		tmp1.remove(this);
		
		//Suppression de cette reservation pour le client
		ArrayList<Location> tmp2 = this.clt.getTabloc();
		tmp2.remove(this);
		
		System.out.println("Rappel: - Le client et l'emplacement d'une location doivent déjà exister avant l'enregistrement de celle-ci.");
		System.out.println("        - Une location prend effet dès ca création. (Sur logiciel)");
		
		//Client
		System.out.println("Choisis un Client :");
		Clients c=null;;
		while (c==null){
			c=Principal.rechercherClient();
		}
		
		//Emplacement
		System.out.println("Choisisr l'emplacement :");
		Emplacements e=null;
		int i=0;
		while (e==null){
			if (i>0){
				System.out.println("Votre recherche n'est pas assé précise ! Pensez à remplir le maximum d'information.");
			}
			e=Principal.rechercherEmplacement();
			i++;
		}
		
		Scanner scann = new Scanner(System.in);
		
		//Date de fin
		System.out.println("Date de fin de la location");
		System.out.println("Année :");
		int an=scann.nextInt();
		System.out.println("Mois : ");
		int mois=scann.nextInt();
		System.out.println("Jour : ");
		int jour=scann.nextInt();
		
		scann.close();
		
		GregorianCalendar g=new GregorianCalendar(an,mois,jour);
		Date d= g.getGregorianChange();
		
		//verification des modifications
		if (this.getClt()!=c){
			this.setClt(c);
		}
		if (this.getEmp()!=e){
			this.setEmp(e);
		}
		if (this.getDtfin()!=d){
			if (this.getDtdeb().before(d)){
				this.setDtfin(d);
			} else {
				System.out.println("Erreur !! la date de fin que vous avez entrée est avant la date de debut.");
			}
		}
		
		//ajout de l'emplacement
		ArrayList<Location> tmp11 = e.getTabLocEnCour();
		tmp11.add(this);
		e.setTabLocEnCour(tmp11);
		
		//ajout pour le client
		ArrayList<Location> tmp22 = c.getTabloc();
		tmp22.add(this);
		e.setTabLocEnCour(tmp22);
		
		//enregistrement
		this.ajouter();
		c.ajouter();
		e.ajouter();
	}
	
	
	public long duree(){
		dure=this.getDtfin().getTime()-this.getDtdeb().getTime();
		return dure/(1000*60*60*24);  //duree en jour
	}
	

	public Emplacements getEmp() {
		return emp;
	}

	public void setEmp(Emplacements emp) {
		this.emp = emp;
	}

	public Clients getClt() {
		return clt;
	}

	public void setClt(Clients clt) {
		this.clt = clt;
	}

	public Date getDtdeb() {
		return dtdeb.getTime();
	}

	public Date getDtfin() {
		return dtfin;
	}

	public void setDtfin(Date dtfin) {
		this.dtfin = dtfin;
	}

	public String getNumLoc() {
		return numLoc;
	}

	public void setNumLoc(String numLoc) {
		this.numLoc = numLoc;
	}
}
