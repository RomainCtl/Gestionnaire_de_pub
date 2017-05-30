import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Scanner;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Clients implements ajoutSuppr,ajoutLocRes,modif,Serializable {
	private static final long serialVersionUID = 1L;

	private final int M=20;
	
	public String numc;
	public String nom;
	private String num;
	private String mail;
	private String adress;
	public String nomEnt;
	private ArrayList <Location> tabloc;
	private ArrayList <Reservation> tabreserv;
	
	public Clients(String no, String nu, String m, String a, String noe) {
		numc="";
		for (int i=0 ; i<10 ; i++){
			Random rand = new Random();
			int nbtmp = rand.nextInt(9 - 0 + 1) + 0;
			numc+=nbtmp;
		}
		nom=no;
		num=nu;
		mail=m;
		adress=a;
		nomEnt=noe;
		tabloc= new ArrayList <Location> (M);
		tabreserv=  new ArrayList <Reservation> (M);
	}
	
	
	@Override
	public void ajouter() {
		try {
			FileOutputStream fil = new FileOutputStream(".\\client\\"+numc+"$"+nom+"$"+nomEnt);
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
		File fil = new File(".\\client\\"+numc+"$"+nom+"$"+nomEnt);
		fil.delete(); 
	}
	
	
	@Override
	public void modifier() {
		Scanner scan = new Scanner(System.in);
		
		//Information preliminaire
		System.out.println("Nom ("+this.nom+") :");
		String nom=scan.next();
		System.out.println("Numero de telephone ("+this.num+") :");
		String num=scan.next();
		System.out.println("Adresse mail ("+this.mail+") :");
		String mail=scan.next();
		System.out.println("Nom de l'entreprise ("+this.nomEnt+") :");
		String nome=scan.next();
		System.out.println("Adresse de l'entreprise ("+this.adress+") :");
		String adress=scan.next();
		
		scan.close();
		
		if (this.getNom()!=nom){
			this.setNom(nom);
		}
		if (this.getNum()!=num){
			this.setNum(num);
		}
		if (this.getMail()!=mail){
			this.setMail(mail);
		}
		if (this.getNomEnt()!=nome){
			this.setNomEnt(nome);
		}
		if (this.getAdress()!=adress){
			this.setAdress(adress);
		}
		
		//enregistrer
		this.ajouter();
	}
	
	
	@Override
	public void ajouterLocation() {
		System.out.println("Rappel: - L'emplacement d'une location doit déjà exister avant l'enregistrement de celle-ci.");
		System.out.println("        - Une location prend effet dès ca création. (Sur logiciel)");
		
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
		
		int an, mois, jour;
		
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
				
		//creation & enregistrement
		Location l = new Location(e,this,d);
		l.ajouter();
	}

	@Override
	public void ajouterReservation() {
		System.out.println("Rappel: - L'emplacement d'une reservation doit déjà exister avant l'enregistrement de celle-ci.");
		
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
		
		int an, mois, jour, an2, jour2, mois2;
		
		do {
			//Date de debut
			System.out.println("Date de debut de la location");
			System.out.println("Année :");
			an=scann.nextInt();
			System.out.println("Mois : ");
			mois=scann.nextInt();
			System.out.println("Jour : ");
			jour=scann.nextInt();
		} while(Principal.verifFormatDate(jour, mois, an));
		
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
				
		//creation
		Reservation r = new Reservation(e,this,deb,fin);
		
		//ajout
		ArrayList<Reservation> tmp1 = this.getTabreserv();
		tmp1.add(r);
		this.setTabreserv(tmp1);
		
		//ajout pour le client
		ArrayList<Reservation> tmp2 = e.getTabreserv();
		tmp2.add(r);
		e.setTabreserv(tmp2);
		
		//enregistrement
		r.ajouter();
		this.ajouter();
		e.ajouter();
	}
	

	public String getNumc() {
		return numc;
	}
	
	public void setNumc(String numc) {
		this.numc=numc;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getNomEnt() {
		return nomEnt;
	}

	public void setNomEnt(String nomEnt) {
		this.nomEnt = nomEnt;
	}

	public ArrayList <Location> getTabloc() {
		return tabloc;
	}

	public void setTabloc(ArrayList <Location> tabloc) {
		this.tabloc = tabloc;
	}

	public ArrayList <Reservation> getTabreserv() {
		return tabreserv;
	}

	public void setTabreserv(ArrayList <Reservation> tabreserv) {
		this.tabreserv = tabreserv;
	}
	
	public String toString(){
		return numc+" "+nom+" "+num+" "+mail+" "+adress+" "+nomEnt;
	}
	
	public void afficher(){
		System.out.println("Numero client: "+numc);
		System.out.println("Nom du contact: "+nom);
		System.out.println("Tel: "+num);
		System.out.println("E-mail: "+mail);
		System.out.println("Adresse de l'entreprise: "+adress);
		System.out.println("Nom de l'entreprise: "+nomEnt);
		
		System.out.println("Locations :");
		System.out.println("Numero | Emplacement | Debut | Fin | Duree");
		for (Location l : this.tabloc){
			System.out.println(l.numLoc+" "+l.getEmp().nump+" "+l.getDtdeb()+" "+l.getDtfin()+" "+l.duree());
		}
		
		System.out.println("Reservations :");
		System.out.println("Numero | Emplacement | Debut | Fin | Duree");
		for (Reservation r : this.tabreserv){
			System.out.println(r.numRes+" "+r.getEmp().nump+" "+r.getDtdeb()+" "+r.getDtfin()+" "+r.duree());
		}
		System.out.println();
	}
}
