import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Principal {
	
	public static void main(String[] args) throws IOException {
		//Calendar d = Calendar.getInstance();
		//d.add(Calendar.MONTH, 1);
		
		//Date dt=d.getTime();
		
		//Location l = new Location(null, null, dt);
		
		//System.out.println(l.duree());
		
		//Clients clt = new Clients("Toto", "0123456789", "toto@mail.com", "Lannion", "Entreprise");
		
		//Clients clt2 = new Clients("Titi", "0987654321", "titi@mail.com", "Rennes", "Entre_truc");
		
		//Clients clt3 = new Clients("Tata", "1234509876", "tata@gmail.com", "Vitre", "LPP3");
		
		//clt.ajouter();
		//clt2.ajouter();
		//clt3.ajouter();
		
		//Clients cl=rechercherClient();
		
		
		//System.out.println(cl.toString());
		
		//clt.supprimer();
		
		//Numerique emp = new Numerique("540x345", 5, 456.67, 234.4, 2);
		
		//emp.ajouter();
		
		//Emplacements e=rechercherEmplacement();
		
		Scanner clav = new Scanner (System.in);
		BufferedReader buffer=new BufferedReader(new InputStreamReader (System.in));
		
		//variable
		boolean b = true;
		boolean b1, b2, b3, b4, bbb, bbb2, bbb3, tt, ttt;
		int n, n2, n3, n4, n5;
		Clients c, cc, ccc;
		Emplacements e, ee;
		Numerique num;
		Mecanique m;
		Colonne col;
		Deroulant d;
		Statique s;
		
		while (b){
			System.out.println("##########################");
			System.out.println("#          Menu          #");
			System.out.println("##########################");
			System.out.println("# 1 - Gerer Clients      #");
			System.out.println("# 2 - Gerer Emplacements #");
			System.out.println("# 3 - Gerer Locations    #");
			System.out.println("# 4 - Gerer Reservations #");
			System.out.println("# 5 - Quitter            #");
			System.out.println("##########################");
			n = clav.nextInt();
			
			switch (n){
			case 1:
				b1=true;
				
				while(b1){
					System.out.println("##########################");
					System.out.println("#      Gerer Clients     #");
					System.out.println("##########################");
					System.out.println("# 1 - Afficher liste     #");
					System.out.println("# 2 - Afficher detail    #");
					System.out.println("# 3 - Creer              #");
					System.out.println("# 4 - Modifier           #");
					System.out.println("# 5 - Supprimer          #");
					System.out.println("# 6 - Retour             #");
					System.out.println("##########################");
					
					n2 = buffer.read();
					
					switch (n2){
					case 1:
						afficherClients();
						break;
					case 2:
						System.out.println("Detail d'un client");
						System.out.println("Entrï¿½ le maximum d'information ci-dessous afin de trouver un client.");
						bbb = true;
						c=null;
						while(bbb){
							c=rechercherClient();
							if (c!=null){
								bbb=false;
							}
						}
						c.afficher();
						break;
					case 3:
						ajouterClient();
						break;
					case 4:
						System.out.println("Modifier");
						System.out.println("Entrï¿½ le maximum d'information ci-dessous afin de trouver un client.");
						bbb2 = true;
						cc=null;
						while(bbb2){
							cc=rechercherClient();
							if (cc!=null){
								bbb2=false;
							}
						}
						cc.modifier();
						//enregistrer
						cc.ajouter();
						break;
					case 5:
						System.out.println("Supprimer");
						System.out.println("Entrï¿½ le maximum d'information ci-dessous afin de trouver un client.");
						bbb3 = true;
						ccc=null;
						while(bbb3){
							ccc=rechercherClient();
							if (ccc!=null){
								bbb3=false;
							}
						}
						ccc.supprimer();
						break;
					case 6:
						b1=false;
						break;
					}
				}
				break;
			case 2:
				b2=true;
				
				while(b2){
					System.out.println("##########################");
					System.out.println("#   Gerer Emplacements   #");
					System.out.println("##########################");
					System.out.println("# 1 - Afficher liste     #");
					System.out.println("# 2 - Afficher detail    #");
					System.out.println("# 3 - Creer              #");
					System.out.println("# 4 - Supprimer          #");
					System.out.println("# 5 - Retour             #");
					System.out.println("##########################");
					
					n3 = buffer.read(); // trezjkhgfjkdwhgjklsdjkghjk
					
					switch (n3){
					case 1:
						afficherEmplacements();
						break;
					case 2:
						System.out.println("Detail d'un emplacement");
						System.out.println("Entrï¿½ le maximum d'information ci-dessous afin de trouver un emplacement.");
						tt = true;
						e=null;
						while (tt){
							e=rechercherEmplacement();
							if (e!=null){
								tt=true;
							}
						}
						switch (e.type){
						case "numerique":
							num = (Numerique) e;
							num.afficher();
							break;
						case "mecanique":
							m = (Mecanique) e;
							switch (m.sousType){
							case "colonne":
								col = (Colonne) m;
								col.afficher();
								break;
							case "deroulant":
								d = (Deroulant) m;
								d.afficher();
								break;
							case "stattique":
								s = (Statique) m;
								s.afficher();
								break;
							}
							break;
						}
						break;
					case 3:
						ajouterEmplacement();
						break;
					case 4:
						System.out.println("Supprimer");
						System.out.println("EntrÃ© le maximum d'information ci-dessous afin de trouver un emplacement.");
						ttt = true;
						ee=null;
						while(ttt){
							ee=rechercherEmplacement();
							if (ee!=null){
								ttt=false;
							}
						}
						ee.supprimer();
						break;
					case 5:
						b2=false;
						break;
					}
				}
				break;
			case 3:
				b3=true;
				
				while (b3){
					System.out.println("##########################");
					System.out.println("#     Gerer Locations    #");
					System.out.println("##########################");
					System.out.println("# 1 - Afficher liste     #");
					System.out.println("# 2 - Afficher detail    #");
					System.out.println("# 3 - Creer              #");
					System.out.println("# 4 - Modifier           #");
					System.out.println("# 5 - Supprimer          #");
					System.out.println("# 6 - Retour             #");
					System.out.println("##########################");
					
					n4 = clav.nextInt();
					
					switch (n4){
					case 1:
						afficherLocations();
						break;
					case 2:
						rechercherLocation();
						break;
					case 3:
						ajouterLocation();
						break;
					case 4:
						break;
					case 5:
						break;
					case 6:
						b3=false;
						break;
					}
				}
				break;
			case 4:
				b4=true;
				
				while (b4){
					System.out.println("##########################");
					System.out.println("#    Gerer Reservation   #");
					System.out.println("##########################");
					System.out.println("# 1 - Afficher liste     #");
					System.out.println("# 2 - Afficher detail    #");
					System.out.println("# 3 - Creer              #");
					System.out.println("# 4 - Modifier           #");
					System.out.println("# 5 - Supprimer          #");
					System.out.println("# 6 - Quitter            #");
					System.out.println("##########################");
					
					n5 = clav.nextInt();
					
					switch (n5){
					case 1:
						afficherReservations();
						break;
					case 2:
						rechercherReservation();
						break;
					case 3:
						ajouterReservation();
						break;
					case 4:
						break;
					case 5:
						break;
					case 6:
						b4=false;
						break;
					}
				}
				break;
			case 5:
				b=false;
				break;
			}
		}
		
		clav.close();
	}
	
	/**
	 * Les Differentes recherches des Clients/Emplacements/location/Reservation
	 */
	
	public static Clients rechercherClient(){
		Clients c = null;
		
		//demande d'information consernant le client
		Scanner sc = new Scanner(System.in);
		System.out.println("Nom : ");
		String no = sc.next();
		System.out.println("Numero d'idendifiquation : ");
		String i =sc.next();
		System.out.println("Nom de l'entreprise : ");
		String noe = sc.next();
		sc.close();
		if (no.equals("null")){
			no=null;
		}
		if (i.equals("null")){
			i=null;
		}
		if (noe.equals("null")){
			noe=null;
		}
		
		//Initialisation du PAttern pour le recherche du fichier client
		Pattern p=null;
		if (no==null && i==null && noe==null){
			System.out.println("Erreur !! Impossible de trouver un client sans information...");
		} else if (no==null && i==null){
			p = Pattern.compile("[0-9]{10}ï¿½[a-zA-Z]{3,}ï¿½"+noe);
		} else if (i==null && noe==null){
			p = Pattern.compile("[0-9]{10}ï¿½"+no+"ï¿½[a-zA-Z_0-9]{3,}");
		} else if (no==null && noe==null){
			p = Pattern.compile(i+"ï¿½[a-zA-Z]{3,}ï¿½[a-zA-Z_0-9]{3,}");
		} else if (no==null) {
			p = Pattern.compile(i+"ï¿½[a-zA-Z]{3,}ï¿½"+noe);
		} else if (i==null){
			p = Pattern.compile("[0-9]{10}ï¿½"+no+"ï¿½"+noe);
		} else if (noe==null){
			p = Pattern.compile(i+"ï¿½"+no+"ï¿½[a-zA-Z_0-9]{3,}");
		} else {
			p = Pattern.compile(i+"ï¿½"+no+"ï¿½"+noe);
		}
		
		File f = new File(".\\client");
		File[] listFile = f.listFiles();
		//System.out.println(p);
		ArrayList <String> listNom = new ArrayList <String> (500);
		
		for (File fil : listFile){
			Matcher m = p.matcher(fil.getName());
			if (m.matches()){
				listNom.add(fil.getName());
			}
		}
		
		if (listNom.size()>1){
			System.out.println("Il y a "+listNom.size()+" clients correspondant ï¿½ votre recherche :");
			for (String s : listNom){
				//System.out.println(s);
				
				//deserialisation pour afficher le nom + nom de l'entreprise + numero idendification
				try {
					FileInputStream fis = new FileInputStream(".\\client\\"+s);
					ObjectInputStream ois= new ObjectInputStream(fis);

					try {
						c = (Clients) ois.readObject();
						System.out.println(c.numc+" "+c.nom+" "+c.nomEnt);
					} catch (ClassNotFoundException e) {
						System.out.println("Erreur !! Impossible de lire le fichier client");
						e.printStackTrace();
					} finally {
						try {
						ois.close();
						} finally {
							fis.close();
						}
					}
				} catch (FileNotFoundException e) {
					System.out.println("Erreur !! Fichier client introuvable");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("Erreur !! Impossible de deserialize le fichier");
					e.printStackTrace();
				}
			}
			
			System.out.println("Votre recherche n'est pas assï¿½ prï¿½cise ! Pensez ï¿½ remplir le maximum d'information.");
		} else if (listNom.size()==1){
			//deserialization pour retourner le client
			try {
				FileInputStream fis = new FileInputStream(".\\client\\"+listNom.get(0));
				ObjectInputStream ois= new ObjectInputStream(fis);

				try {
					c = (Clients) ois.readObject();
					System.out.println(c.numc+" "+c.nom+" "+c.nomEnt);
				} catch (ClassNotFoundException e) {
					System.out.println("Erreur !! Impossible de lire le fichier client");
					e.printStackTrace();
				} finally {
					try {
					ois.close();
					} finally {
						fis.close();
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("Erreur !! Fichier client introuvable");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Erreur !! Impossible de deserialize le fichier");
				e.printStackTrace();
			}
		} else {
			System.out.println("Clients inexistant, Attention ï¿½ ne pas faire de faute dans les informations entrï¿½es !");
		}
		
		return c;
	}
	
	public static Emplacements rechercherEmplacement(){
		Emplacements e=null;
		
		//demande le numero de l'emplacement
		Scanner sc =  new Scanner(System.in);
		System.out.println("Numero de l'emplacement :");
		String numEmp = sc.next();
		sc.close();
		
		//deserialization du fichier
		try {
			FileInputStream fis = new FileInputStream(".\\emplacement\\"+numEmp);
			ObjectInputStream ois= new ObjectInputStream(fis);

			try {
				e = (Emplacements) ois.readObject();
				System.out.println(e.nump+" Localisation : "+e.x+", "+e.y+" | "+e.adress);
			} catch (ClassNotFoundException en) {
				System.out.println("Erreur !! Impossible de lire le fichier de l'emplacement");
				en.printStackTrace();
			} finally {
				try {
				ois.close();
				} finally {
					fis.close();
				}
			}
		} catch (FileNotFoundException en) {
			System.out.println("Emplacement inexsistant, Attention ï¿½ ne pas faire de faute dans les informations entrï¿½es !");
			//en.printStackTrace();
		} catch (IOException en) {
			System.out.println("Erreur !! Impossible de deserialize le fichier");
			en.printStackTrace();
		}
		
		return e;
	}
	
	public static Location rechercherLocation(){
		Location l =null;
		
		//demande le numero de l'emplacement
		Scanner sc =  new Scanner(System.in);
		System.out.println("Numero de l'emplacement :");
		String numEmp = sc.next();
		sc.close();
			
		//deserialization du fichier
		try {
			FileInputStream fis = new FileInputStream(".\\location\\"+numEmp);
			ObjectInputStream ois= new ObjectInputStream(fis);

			try {
				l = (Location) ois.readObject();
				System.out.println(l.numLoc);
			} catch (ClassNotFoundException en) {
				System.out.println("Erreur !! Impossible de lire le fichier de location");
				en.printStackTrace();
			} finally {
				try {
				ois.close();
				} finally {
					fis.close();
				}
			}
		} catch (FileNotFoundException en) {
			System.out.println("Location inexsistante, Attention ï¿½ ne pas faire de faute dans les informations entrï¿½es !");
			//en.printStackTrace();
		} catch (IOException en) {
			System.out.println("Erreur !! Impossible de deserialize le fichier");
			en.printStackTrace();
		}
		
		return l;
	}
	
	public static Reservation rechercherReservation(){
		Reservation r =null;
		
		//demande le numero de l'emplacement
		Scanner sc =  new Scanner(System.in);
		System.out.println("Numero de l'emplacement :");
		String numEmp = sc.next();
		sc.close();
			
		//deserialization du fichier
		try {
			FileInputStream fis = new FileInputStream(".\\location\\"+numEmp);
			ObjectInputStream ois= new ObjectInputStream(fis);

			try {
				r = (Reservation) ois.readObject();
				System.out.println(r.numRes);
			} catch (ClassNotFoundException en) {
				System.out.println("Erreur !! Impossible de lire le fichier de Reservation");
				en.printStackTrace();
			} finally {
				try {
				ois.close();
				} finally {
					fis.close();
				}
			}
		} catch (FileNotFoundException en) {
			System.out.println("Reservation inexsistante, Attention ï¿½ ne pas faire de faute dans les informations entrï¿½es !");
			//en.printStackTrace();
		} catch (IOException en) {
			System.out.println("Erreur !! Impossible de deserialize le fichier");
			en.printStackTrace();
		}
		
		return r;
	}


	/**
	 * Pour ajouter un Client/emplacement/location/reservation
	 */
	
	public static void ajouterClient(){
		Scanner scan = new Scanner(System.in);
		
		//Information preliminaire
		System.out.println("Nom :");
		String nom=scan.next();
		System.out.println("Numero de telephone :");
		String num=scan.next();
		System.out.println("Adresse mail :");
		String mail=scan.next();
		System.out.println("Nom de l'entreprise :");
		String nome=scan.next();
		System.out.println("Adresse de l'entreprise :");
		String adress=scan.next();
		
		Clients c = new Clients(nom, num, mail, adress, nome);
		
		//Demande de la location (Et oui !  il faut louer pour etre client)
		System.out.println("Votre location");
		System.out.println("Elle debute dï¿½s aujourd'hui, qu'elle sera la date de fin ?");
		System.out.println("Annï¿½e :");
		int an=scan.nextInt();
		System.out.println("Mois : ");
		int mois=scan.nextInt();
		System.out.println("Jour : ");
		int jour=scan.nextInt();
		
		GregorianCalendar g=new GregorianCalendar(an,mois,jour);
		
		Date d= g.getGregorianChange();
		
		//Il ne manque plus que l'emplacement a louer
		System.out.println("L'emplacement que vous souhaiter louer ?");
		//on affiche les emplacements disponible
		afficherEmplacementDisponible();
		int n=scan.nextInt();
		
		scan.close();
		
		//Et on recupere les informations de l'emplacement
		Emplacements e=null;
		try {
			FileInputStream fis = new FileInputStream(".\\emplacement\\"+n);
			ObjectInputStream ois= new ObjectInputStream(fis);

			try {
				e = (Emplacements) ois.readObject();
			} catch (ClassNotFoundException en) {
				System.out.println("Erreur !! Impossible de lire le fichier de l'emplacement");
				en.printStackTrace();
			} finally {
				try {
				ois.close();
				} finally {
					fis.close();
				}
			}
		} catch (FileNotFoundException en) {
			System.out.println("Emplacement inexsistant, Attention ï¿½ ne pas faire de faute dans les informations entrï¿½es !");
			//en.printStackTrace();
		} catch (IOException en) {
			System.out.println("Erreur !! Impossible de deserialize le fichier");
			en.printStackTrace();
		}
		
		//on finalise la location
		Location l = new Location(e,c,d);
		l.ajouter();
		
		//on lie la location a l'emplacement
		ArrayList<Location> tmp1 = new ArrayList <Location> (1);
		tmp1.add(l);
		e.setTabLocEnCour(tmp1);
		//on enregistre l'emplacement
		e.ajouter();
		
		//on lie la location au client
		c.setTabloc(tmp1);
		//enregistre
		c.ajouter();
	}
	
	public static void ajouterEmplacement() throws IOException{
		Scanner sca = new Scanner(System.in);
		BufferedReader entree=new BufferedReader(new InputStreamReader (System.in));
		
		//type du panneau
		System.out.println("Quel est le type d'emplacements ?\n1 - Numerique\n2 - Mï¿½canique");
		int tmp1 = sca.nextInt();
		switch (tmp1){
		case 1:
			//Numerique
			//Information preliminaire
			System.out.println("Localisation");
			System.out.println("X :");
			double x=sca.nextDouble();
			System.out.println("Y :");
			double y=sca.nextDouble();
			System.out.println("Adress (rue etc..) :");
			String a=entree.readLine();
			System.out.println("Format (longeur x hauteur) :");
			String form=entree.readLine();
			System.out.println("Visibilitï¿½e (Sur /5) :");
			int visi=sca.nextInt();
			System.out.println("Capacitï¿½ publicitaire (nombre de pub sur le meme emplacement) :");
			int nbE=sca.nextInt();
			
			//creation & enregistrement
			Numerique num = new Numerique(form, visi, x, y, nbE, a);
			num.ajouter();
			break;
		case 2:
			//mecanique
			//type de panneau mecanique
			System.out.println("1 - Deroulant\n2 - Colonne\n3 - Statique");
			int tmp2 = sca.nextInt();
			switch (tmp2){
			case 1:
				//Deroulant
				//Information preliminaire
				System.out.println("Localisation");
				System.out.println("X :");
				double xx=sca.nextDouble();
				System.out.println("Y :");
				double yy=sca.nextDouble();
				System.out.println("Adress (rue etc..) :");
				String aa=entree.readLine();
				System.out.println("Format (longeur x hauteur) :");
				String f=entree.readLine();
				System.out.println("Visibilitï¿½e (Sur /5) :");
				int v=sca.nextInt();
				System.out.println("Capacitï¿½ publicitaire (nombre de pub sur le meme emplacement) :");
				int nb=sca.nextInt();
				
				//creation & enregistrement
				Deroulant der=new Deroulant(f, v, xx, yy, nb, aa);
				der.ajouter();
				break;
			case 2:
				//colonne
				//Information preliminaire
				System.out.println("Localisation");
				System.out.println("X :");
				double x1=sca.nextDouble();
				System.out.println("Y :");
				double y1=sca.nextDouble();
				System.out.println("Adress (rue etc..) :");
				String a1=entree.readLine();
				System.out.println("Format (longeur x hauteur) :");
				String fo=entree.readLine();
				System.out.println("Visibilitï¿½e (Sur /5) :");
				int vi=sca.nextInt();
				System.out.println("Capacitï¿½ publicitaire (nombre de pub sur le meme emplacement) :");
				int nb1=sca.nextInt();
				
				//creation & enregistrement
				Colonne col= new Colonne(fo, vi, x1, y1, nb1, a1);
				col.ajouter();
				break;
			case 3:
				//Statique
				//Information preliminaire
				System.out.println("Localisation");
				System.out.println("X :");
				double x2=sca.nextDouble();
				System.out.println("Y :");
				double y2=sca.nextDouble();
				System.out.println("Adress (rue etc..) :");
				String a2=entree.readLine();
				System.out.println("Format (longeur x hauteur) :");
				String fom=entree.readLine();
				System.out.println("Visibilitï¿½e (Sur /5) :");
				int vis=sca.nextInt();
				
				//creation et enregistrement
				Statique sta = new Statique(fom, x2, y2, vis, a2);
				sta.ajouter();
				break;
			}
			break;
		}
		
		sca.close();
	}
	
	public static void ajouterLocation(){
		System.out.println("Rappel: - Le client et l'emplacement d'une location doivent dï¿½jï¿½ exister avant l'enregistrement de celle-ci.");
		System.out.println("        - Une location prend effet dï¿½s ca crï¿½ation. (Sur logiciel)");
		
		//Client
		System.out.println("Choisis un Client :");
		Clients c=null;;
		while (c==null){
			c=rechercherClient();
		}
		
		//Emplacement
		System.out.println("Choisisr l'emplacement :");
		Emplacements e=null;
		int i=0;
		while (e==null){
			if (i>0){
				System.out.println("Votre recherche n'est pas assï¿½ prï¿½cise ! Pensez ï¿½ remplir le maximum d'information.");
			}
			e=rechercherEmplacement();
			i++;
		}
		
		Scanner scann = new Scanner(System.in);
		
		//Date de fin
		System.out.println("Date de fin de la location");
		System.out.println("Annï¿½e :");
		int an=scann.nextInt();
		System.out.println("Mois : ");
		int mois=scann.nextInt();
		System.out.println("Jour : ");
		int jour=scann.nextInt();
		
		scann.close();
		
		GregorianCalendar g=new GregorianCalendar(an,mois,jour);
		Date d= g.getGregorianChange();
		
		//creation
		Location l = new Location(e,c,d);
		
		//ajout de l'emplacement
		ArrayList<Location> tmp1 = e.getTabLocEnCour();
		tmp1.add(l);
		e.setTabLocEnCour(tmp1);
				
		//ajout pour le client
		ArrayList<Location> tmp2 = e.getTabLocEnCour();
		tmp2.add(l);
		e.setTabLocEnCour(tmp2);
		
		//enregistrement
		l.ajouter();
		e.ajouter();
		c.ajouter();
	}
	
	public static void ajouterReservation(){
		System.out.println("Rappel: - Le client et l'emplacement d'une reservation doivent dï¿½jï¿½ exister avant l'enregistrement de celle-ci.");
		
		//Client
		System.out.println("Choisis un Client :");
		Clients c=null;;
		while (c==null){
			c=rechercherClient();
		}
		
		//Emplacement
		System.out.println("Choisisr l'emplacement :");
		Emplacements e=null;
		int i=0;
		while (e==null){
			if (i>0){
				System.out.println("Votre recherche n'est pas assï¿½ prï¿½cise ! Pensez ï¿½ remplir le maximum d'information.");
			}
			e=rechercherEmplacement();
			i++;
		}
		
		Scanner scann = new Scanner(System.in);
		
		//Date de debut
		System.out.println("Date de debut de la location");
		System.out.println("Annï¿½e :");
		int an=scann.nextInt();
		System.out.println("Mois : ");
		int mois=scann.nextInt();
		System.out.println("Jour : ");
		int jour=scann.nextInt();
		
		GregorianCalendar g=new GregorianCalendar(an,mois,jour);
		Date deb= g.getGregorianChange();
		
		//Date de fin
		System.out.println("Date de fin de la location");
		System.out.println("Annï¿½e :");
		int an2=scann.nextInt();
		System.out.println("Mois : ");
		int mois2=scann.nextInt();
		System.out.println("Jour : ");
		int jour2=scann.nextInt();
		
		GregorianCalendar g2=new GregorianCalendar(an2,mois2,jour2);
		Date fin= g2.getGregorianChange();
		
		scann.close();
		
		//creation
		Reservation r = new Reservation(e,c,deb,fin);
		
		//ajout de l'emplacement
		ArrayList<Reservation> tmp1 = e.getTabreserv();
		tmp1.add(r);
		e.setTabreserv(tmp1);
						
		//ajout pour le client
		ArrayList<Reservation> tmp2 = e.getTabreserv();
		tmp2.add(r);
		e.setTabreserv(tmp2);
		
		//enregistrement
		r.ajouter();
		c.ajouter();
		e.ajouter();
	}
	
	
	/**
	 * Afficher les emplacements disponible
	 */
	
	public static void afficherEmplacementDisponible(){
		Emplacements e=null;
		//recuperation de la liste d'emplacement
		File f = new File(".\\emplacement");
		File[] listFile = f.listFiles();
		
		ArrayList <String> listNom = new ArrayList <String> (500);
		
		for (File fil : listFile){
			listNom.add(fil.getName());
		}
		
		//puis affichage des emplacements disponible
		for (String s : listNom){
			try {
				FileInputStream fis = new FileInputStream(".\\emplacement\\"+s);
				ObjectInputStream ois= new ObjectInputStream(fis);

				try {
					e = (Emplacements) ois.readObject();
					if (e.locEnCour.size()==0){
						System.out.println(e.nump+" Localisation : "+e.x+", "+e.y+" | "+e.adress);
					}
				} catch (ClassNotFoundException en) {
					System.out.println("Erreur !! Impossible de lire le fichier de l'emplacement");
					en.printStackTrace();
				} finally {
					try {
					ois.close();
					} finally {
						fis.close();
					}
				}
			} catch (FileNotFoundException en) {
				System.out.println("Erreur !! Fichier introuvable ..?");
				//en.printStackTrace();
			} catch (IOException en) {
				System.out.println("Erreur !! Impossible de deserialize le fichier");
				en.printStackTrace();
			}
		}
	}
	
	/**
	 * Affichage des clients/emplacements/locations/reservations
	 */
	
	public static void afficherClients(){
		//on recupere les fichies
		File f = new File(".\\client");
		File[] listFile = f.listFiles();
		
		//puis les noms des fichiers
		ArrayList <String> listNom = new ArrayList <String> (500);
		for (File fil : listFile){
			listNom.add(fil.getName());
		}
		
		System.out.println("Il y a "+listNom.size()+" clients :");
		System.out.println("Numero | Nom | Nom entreprise");
		for (String s : listNom){
			//deserialisation pour afficher le nom + nom de l'entreprise + numero idendification
			try {
				FileInputStream fis = new FileInputStream(".\\client\\"+s);
				ObjectInputStream ois= new ObjectInputStream(fis);

				try {
					Clients c = (Clients) ois.readObject();
					//affichage
					System.out.println(c.numc+" "+c.nom+" "+c.nomEnt);
				} catch (ClassNotFoundException e) {
					System.out.println("Erreur !! Impossible de lire le fichier client");
					e.printStackTrace();
				} finally {
					try {
					ois.close();
					} finally {
						fis.close();
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("Erreur !! Fichier client introuvable");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Erreur !! Impossible de deserialize le fichier");
				e.printStackTrace();
			}
		}
	}
	
	public static void afficherEmplacements(){
		//on recupere les fichies
		File f = new File(".\\emplacement");
		File[] listFile = f.listFiles();
		
		//puis les noms des fichiers
		ArrayList <String> listNom = new ArrayList <String> (500);
		for (File fil : listFile){
			listNom.add(fil.getName());
		}
		
		System.out.println("Il y a "+listNom.size()+" emplacements :");
		for (String s : listNom){
			//deserialisation pour afficher le numero idendification + la localisation
			try {
				FileInputStream fis = new FileInputStream(".\\emplacement\\"+s);
				ObjectInputStream ois= new ObjectInputStream(fis);

				try {
					Emplacements e = (Emplacements) ois.readObject();
					//affichage
					System.out.println(e.nump+" Localisation : "+e.x+", "+e.y+" | "+e.adress);
				} catch (ClassNotFoundException en) {
					System.out.println("Erreur !! Impossible de lire le fichier de l'emplacement");
					en.printStackTrace();
				} finally {
					try {
					ois.close();
					} finally {
						fis.close();
					}
				}
			} catch (FileNotFoundException en) {
				System.out.println("Erreur !! Fichier de l'emplacement introuvable");
				en.printStackTrace();
			} catch (IOException en) {
				System.out.println("Erreur !! Impossible de deserialize le fichier");
				en.printStackTrace();
			}
		}
	}
	
	public static void afficherLocations(){
		//on recupere les fichies
		File f = new File(".\\location");
		File[] listFile = f.listFiles();
		
		//puis les noms des fichiers
		ArrayList <String> listNom = new ArrayList <String> (500);
		for (File fil : listFile){
			listNom.add(fil.getName());
		}
		
		System.out.println("Il y a "+listNom.size()+" locations :");
		System.out.println("Num loc | num cli | num emp");
		for (String s : listNom){
			//deserialisation  puis affichage
			try {
				FileInputStream fis = new FileInputStream(".\\location\\"+s);
				ObjectInputStream ois= new ObjectInputStream(fis);

				try {
					Location l = (Location) ois.readObject();
					//affichage
					System.out.println(l.numLoc+" "+l.getClt().numc+" "+l.getEmp().nump);
				} catch (ClassNotFoundException e) {
					System.out.println("Erreur !! Impossible de lire le fichier de location");
					e.printStackTrace();
				} finally {
					try {
					ois.close();
					} finally {
						fis.close();
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("Erreur !! Fichier de location introuvable");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Erreur !! Impossible de deserialize le fichier");
				e.printStackTrace();
			}
		}
	}
	
	public static void afficherReservations(){
		//on recupere les fichies
		File f = new File(".\\reservation");
		File[] listFile = f.listFiles();
		
		//puis les noms des fichiers
		ArrayList <String> listNom = new ArrayList <String> (500);
		for (File fil : listFile){
			listNom.add(fil.getName());
		}
		
		System.out.println("Il y a "+listNom.size()+" reservations :");
		System.out.println("Num Res | num cli | num emp");
		for (String s : listNom){
			//deserialisation puis affichage
			try {
				FileInputStream fis = new FileInputStream(".\\reservation\\"+s);
				ObjectInputStream ois= new ObjectInputStream(fis);

				try {
					Reservation r = (Reservation) ois.readObject();
					//affichage
					System.out.println(r.numRes+" "+r.getClt().numc+" "+r.getEmp().nump);
				} catch (ClassNotFoundException e) {
					System.out.println("Erreur !! Impossible de lire le fichier de location");
					e.printStackTrace();
				} finally {
					try {
					ois.close();
					} finally {
						fis.close();
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("Erreur !! Fichier de location introuvable");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Erreur !! Impossible de deserialize le fichier");
				e.printStackTrace();
			}
		}
	}
	
	
}
