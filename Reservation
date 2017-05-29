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
import java.util.Scanner;

public class Reservation implements ajoutSuppr,modif,Serializable {
	private static final long serialVersionUID = 1L;
	
	public int numRes;
	private Emplacements emp;
	private Clients clt;
	private Date dtdeb;
	private Date dtfin;
	private long dure;
	
	public Reservation(Emplacements e, Clients c, Date d, Date f) {
		File fil = new File(".\\reservation");
		File[] listFile = fil.listFiles();
		
		numRes=listFile.length+1;
		setEmp(e);
		setClt(c);
		dtdeb=d;
		dtfin=f;
	}
	 
	@Override
	public void ajouter() {
		try {
			FileOutputStream fil = new FileOutputStream(".\\reservation\\"+numRes);
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
		File fil = new File(".\\reservation\\"+numRes);
		fil.delete(); 
	}
	
	//A modifier lors de la liaison avec l'interface
	@Override
	public void modifier() {
		//supprimer pour ajoter
		//Suppression de cette reservation de pour l'emplacement
		ArrayList<Reservation> tmp1 = this.emp.getTabreserv();
		tmp1.remove(this);
		
		//Suppression de cette reservation pour le client
		ArrayList<Reservation> tmp2 = this.clt.getTabreserv();
		tmp2.remove(this);
		
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
		
		//Date de debut
		System.out.println("Date de debut de la location");
		System.out.println("Année :");
		int an=scann.nextInt();
		System.out.println("Mois : ");
		int mois=scann.nextInt();
		System.out.println("Jour : ");
		int jour=scann.nextInt();
		
		GregorianCalendar g=new GregorianCalendar(an,mois,jour);
		Date deb= g.getGregorianChange();
		
		//Date de fin
		System.out.println("Date de fin de la location");
		System.out.println("Année :");
		int an2=scann.nextInt();
		System.out.println("Mois : ");
		int mois2=scann.nextInt();
		System.out.println("Jour : ");
		int jour2=scann.nextInt();
		
		GregorianCalendar g2=new GregorianCalendar(an2,mois2,jour2);
		Date fin= g2.getGregorianChange();
		
		scann.close();
		
		//verification des modifications
		if (this.getClt()!=c){
			this.setClt(c);
		}
		if (this.getEmp()!=e){
			this.setEmp(e);
		}
		if (this.getDtdeb()!=deb){
			if (Calendar.getInstance().getTime().before(deb)){
				this.setEmp(e);
			} else {
				System.out.println("Erreur !! la date de debut que vous avez entrée est périmé.");
			}
		}
		if (this.getDtfin()!=fin){
			if (this.getDtdeb().before(fin)){
				this.setDtfin(fin);
			} else {
				System.out.println("Erreur !! la date de fin que vous avez entrée est avant la date de debut.");
			}
		}
		
		//ajout de l'emplacement
		ArrayList<Reservation> tmp11 = e.getTabreserv();
		tmp11.add(this);
		e.setTabreserv(tmp11);
						
		//ajout pour le client
		ArrayList<Reservation> tmp22 = c.getTabreserv();
		tmp22.add(this);
		e.setTabreserv(tmp22);
		
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
		return dtdeb;
	}

	public Date getDtfin() {
		return dtfin;
	}

	public void setDtfin(Date dtfin) {
		this.dtfin = dtfin;
	}
	
	public int getNumRes() {
		return numRes;
	}

	public void setNumRes(int numRes) {
		this.numRes = numRes;
	}
}
