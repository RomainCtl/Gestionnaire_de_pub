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
import java.util.HashMap;
import java.util.Scanner;

public abstract class Emplacements implements ajoutSuppr,ajoutLocRes,Serializable {
	private static final long serialVersionUID = 1L;

	private final int M=20;
	
	public int nump;
	private String format;
	private double tarif;
	private int visibilite;
	public double x;
	public double y;
	public String type;
	public String adress;
	private  HashMap <Date, Date> tabrecap;
	public ArrayList <Location> locEnCour;
	private ArrayList <Reservation> tabreserv;
	
	public Emplacements(String f, int v, double x, double y, String a, String t, int m) {
		File fil = new File(".\\emplacement");
		File[] listFile = fil.listFiles();
		
		String tmp="";
		for (File fi : listFile){
			tmp=fi.getName();
		}
		
		nump=Integer.parseInt(tmp)+1;
		format=f;
		visibilite=v;
		adress=a;
		type=t;
		tarif=0; //Pas de tarif a la creation
		this.x=x; //le x et le y correspondent a la localisation de l'emplacement sur la carte
		this.y=y;
		locEnCour = new ArrayList <Location> (m);
		tabrecap= new HashMap <Date, Date> (M);//recapitulatif de quoi?
		tabreserv= new ArrayList <Reservation> (M);
	}
	
	
	@Override
	public void ajouter() {
		try {
			FileOutputStream fil = new FileOutputStream(".\\emplacement\\"+nump);
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
		File fil = new File(".\\emplacement\\"+nump);
		fil.delete();
	}
	
	
	@Override
	public void ajouterLocation() {
		System.out.println("Rappel: - Le client d'une location doit déjà exister avant l'enregistrement de celle-ci.");
		System.out.println("        - Une location prend effet dès ca création. (Sur logiciel)");
		
		//Client
		System.out.println("Choisis un Client :");
		Clients c=null;;
		while (c==null){
			c=Principal.rechercherClient();
		}
		
		Scanner scann = new Scanner(System.in);
		
		int jour, mois, an;
		
		do {
			//Date de fin
			System.out.println("Date de fin de la location");
			System.out.println("Année :");
			an=scann.nextInt();
			System.out.println("Mois : ");
			mois=scann.nextInt();
			System.out.println("Jour : ");
			jour=scann.nextInt();
		} while (Principal.verifFormatDate(jour, mois, an));
		
				
		scann.close();
			
		GregorianCalendar g=new GregorianCalendar(an,mois,jour);
		Date d= g.getGregorianChange();
		
		if (verifDate(Calendar.getInstance().getTime(), d)){
			//creation
			Location l = new Location(this,c,d);
			
			//ajout
			ArrayList <Location> tmp1 = this.getTabLocEnCour();
			tmp1.add(l);
			this.setTabLocEnCour(tmp1);
			
			//ajout des date pour le tabrecap
			HashMap <Date,Date> tmp = this.getTabrecap();
			tmp.put(Calendar.getInstance().getTime(), d);
			this.setTabrecap(tmp);
			
			//ajout pour le client
			ArrayList<Location> tmp2 = c.getTabloc();
			tmp2.add(l);
			c.setTabloc(tmp2);
			
			//enregistrement
			l.ajouter();
			this.ajouter();
			c.ajouter();
		} else {
			System.out.println("Cette emplacement est deja louer sur cette periode, ou une partie de cette periode.");
		}
	}

	@Override
	public void ajouterReservation() {
		System.out.println("Rappel: - Le client d'une reservation doit déjà exister avant l'enregistrement de celle-ci.");
		
		//Client
		System.out.println("Choisis un Client :");
		Clients c=null;;
		while (c==null){
			c=Principal.rechercherClient();
		}
		
		Scanner scann = new Scanner(System.in);
		
		int an, mois, jour, an2, mois2, jour2;
		
		do {
			//Date de debut
			System.out.println("Date de debut de la location");
			System.out.println("Année :");
			an=scann.nextInt();
			System.out.println("Mois : ");
			mois=scann.nextInt();
			System.out.println("Jour : ");
			jour=scann.nextInt();
		} while (Principal.verifFormatDate(jour, mois, an));
				
		GregorianCalendar g=new GregorianCalendar(an,mois,jour);
		Date deb= g.getGregorianChange();
		
		do {
			//Date de fin
			System.out.println("Date de fin de la location");
			System.out.println("Année :");
			an2=scann.nextInt();
			System.out.println("Mois : ");
			mois2=scann.nextInt();
			System.out.println("Jour : ");
			jour2=scann.nextInt();
		} while (Principal.verifFormatDate(jour2, mois2, an2));
				
		GregorianCalendar g2=new GregorianCalendar(an2,mois2,jour2);
		Date fin= g2.getGregorianChange();
				
		scann.close();
		if (verifDate(deb, fin)){
			//creation
			Reservation r = new Reservation(this,c,deb,fin);
			
			//ajout
			ArrayList<Reservation> tmp1 = this.getTabreserv();
			tmp1.add(r);
			this.setTabreserv(tmp1);
			
			//ajout des date pour le tabrecap
			HashMap <Date,Date> tmp = this.getTabrecap();
			tmp.put(deb, fin);
			this.setTabrecap(tmp);
			
			//ajout pour le client
			ArrayList<Reservation> tmp2 = c.getTabreserv();
			tmp2.add(r);
			c.setTabreserv(tmp2);
			
			//enregistrement
			r.ajouter();
			this.ajouter();
			c.ajouter();
		 }
	}
	
	
	public boolean verifDate(Date deb, Date fin){
		boolean ok=true;
		
		for (Date d : tabrecap.keySet()){
			if (d.after(deb)){
				ok=false;
			} else if (tabrecap.get(d).before(fin)){
				ok=false;
			}
		}
		
		return ok;
	}
	
	
	public int getNump() {
		return nump;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public double getTarif() {
		return tarif;
	}

	public void setTarif(double tarif) {
		this.tarif = tarif;
	}

	public int getVisibilite() {
		return visibilite;
	}

	public void setVisibilite(int visibilite) {
		this.visibilite = visibilite;
	}
	
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public ArrayList <Location> getTabLocEnCour() {
		return locEnCour;
	}
	
	public void setTabLocEnCour(ArrayList <Location> locEnCour) {
		this.locEnCour = locEnCour;
	}

	public HashMap <Date, Date> getTabrecap() {
		return tabrecap;
	}

	public void setTabrecap(HashMap <Date, Date> tabrecap) {
		this.tabrecap = tabrecap;
	}


	public ArrayList <Reservation> getTabreserv() {
		return tabreserv;
	}

	public void setTabreserv(ArrayList <Reservation> tabreserv) {
		this.tabreserv = tabreserv;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void afficher(){
		System.out.println("Numero: "+nump);
		System.out.println("Format: "+format);
		System.out.println("Tarif: "+tarif);
		System.out.println("Visibilité: "+visibilite+"/5");
		System.out.println("Type: "+type);
		System.out.println("Localisation:");
		System.out.println("Pseudo-adresse: "+adress);
		System.out.println("X: "+x);
		System.out.println("Y: "+y);
		
		System.out.println("Locations :");
		System.out.println("Numero | Client | Debut | Fin | Duree");
		for (Location l : this.locEnCour){
			System.out.println(l.numLoc+" "+l.getClt().numc+" "+l.getDtdeb()+" "+l.getDtfin()+" "+l.duree());
		}
		
		System.out.println("Reservations :");
		System.out.println("Numero | Client | Debut | Fin | Duree");
		for (Reservation r : this.tabreserv){
			System.out.println(r.numRes+" "+r.getClt().numc+" "+r.getDtdeb()+" "+r.getDtfin()+" "+r.duree());
		}
		
		System.out.println("Tableau recapitulatif :");
		System.out.println("Date de debut | Date de fin  (de location/reservation)");
		for (Date d : tabrecap.keySet()){
			System.out.println(tabrecap.get(d).toString()+" "+d.toString());
		}
	}
}
