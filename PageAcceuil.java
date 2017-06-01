package Menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PageAcceuil extends Stage {
	//Les objets de la fenetre
	
	//Les zones
	BorderPane root = new BorderPane();
	VBox RightPane = new VBox();
	HBox TopPane = new HBox();
	HBox boutonEmp = new HBox();
	VBox emp = new VBox();
	HBox boutonCli = new HBox();
	VBox cli = new VBox();
	HBox boutonRes = new HBox();
	VBox res = new VBox();
	HBox boutonRad = new HBox();
	HBox clients = new HBox();
	VBox ensemble = new VBox();
	
	//La liste deroulante
	ComboBox<String> liste = new ComboBox<String>();
	
	//Les Boutons
	Button BNAjoutEmp = new Button("Ajouter...");
	Button BNListeEmp = new Button("Gérer...");
	Button BNAjoutCli = new Button("Ajouter...");
	Button BNListeCli = new Button("Gérer...");
	Button BNAjoutRes = new Button("Ajouter...");
	Button BNListeRes = new Button("Gérer...");
	Button BNAfficher = new Button("Afficher");
	
	//Les radioBouton
	RadioButton BNTous = new RadioButton();
	RadioButton BNOccupes = new RadioButton();
	RadioButton BNLibres = new RadioButton();
	//L'ensemble des boutons Radio
	ToggleGroup gr = new ToggleGroup();
	
	//Les Etiquettes
	Label Emp = new Label("Emplacements");
	Label Cli = new Label("Clients");
	Label Res = new Label("Reservations");
	Label Inf = new Label("Informations");
	Label In1 = new Label("Information1");
	
	//Le constructeur
	public PageAcceuil(){
		//Decalaration de la Scene
		Scene laScene = new Scene(creerContenu());
		this.setX(100);
		this.setY(100);
		this.setTitle("");
		this.setResizable(true);
		this.setMinWidth(1000);
		this.setMinHeight(500);
		//On redefinis la taille de la police des etiquettes
		Emp.setFont(Font.font("", 20));
		Cli.setFont(Font.font("", 20));
		Res.setFont(Font.font("", 20));
		this.setScene(laScene);
	}

	private Parent creerContenu() {
		//--------------------------------------------On s'occupe des boutons--------------------------------------------------
		
		//Leurs taille
		BNAjoutEmp.setPrefSize(35, 35);
		BNListeEmp.setPrefSize(50, 35);
		BNAjoutCli.setPrefSize(35, 35);
		BNListeCli.setPrefSize(50, 35);
		BNAjoutRes.setPrefSize(35, 35);
		BNListeRes.setPrefSize(50, 35);
		BNAfficher.setPrefSize(35, 35);
		
		//La taille de police des boutons
		BNAjoutEmp.setFont(Font.font("", 15));
		BNListeEmp.setFont(Font.font("", 13));
		
		//Leurs actions
		BNAjoutEmp.setOnAction(e->{
			
		});
		
		BNListeEmp.setOnAction(e->{
			
		});
		
		BNAjoutCli.setOnAction(e->{
			
		});
		
		BNListeCli.setOnAction(e->{
			
		});
		
		BNAjoutRes.setOnAction(e->{
			
		});
		
		BNListeRes.setOnAction(e->{
			
		});
		
		BNAfficher.setOnAction(e->{
			
		});
		
		//---------------------------------------------------------On s'occupe des RadioBoutton---------------------------------------------------
		gr.getToggles().addAll(BNTous,BNOccupes,BNLibres);
		gr.selectToggle(BNTous);
		
		//-------------------------------------------------------Les zonnes-----------------------------------------------------------------------
		//----------------------------------------La zone du haut
		//------------------------Les emplacements
		boutonEmp.getChildren().addAll(BNAjoutEmp,BNListeEmp);
		HBox.setMargin(BNAjoutEmp, new Insets(10,15,10,50));
		HBox.setMargin(BNListeEmp, new Insets(10,15,10,25));
		emp.getChildren().addAll(Emp,boutonEmp);
		emp.setAlignment(Pos.TOP_CENTER);
		VBox.setMargin(Emp, new Insets(15,0,0,0));
		emp.setMinWidth(220);
		
		//----------------------Les clients
		boutonCli.getChildren().addAll(BNAjoutCli,BNListeCli);
		HBox.setMargin(BNAjoutCli, new Insets(10,15,10,50));
		HBox.setMargin(BNListeCli, new Insets(10,15,10,25));
		cli.getChildren().addAll(Cli,boutonCli);
		cli.setAlignment(Pos.TOP_CENTER);
		VBox.setMargin(Cli, new Insets(15,0,0,0));
		cli.setMinWidth(220);
		//----------------------Les reservations
		boutonRes.getChildren().addAll(BNAjoutRes,BNListeRes);
		HBox.setMargin(BNAjoutRes, new Insets(10,15,10,50));
		HBox.setMargin(BNListeRes, new Insets(10,15,10,25));
		res.getChildren().addAll(Res,boutonRes);
		res.setAlignment(Pos.TOP_CENTER);
		VBox.setMargin(Res, new Insets(15,0,0,0));
		res.setMinWidth(220);
		
		//---------------------Les boutons radio
		boutonRad.getChildren().addAll(BNTous,BNOccupes,BNLibres);
		HBox.setMargin(BNTous, new Insets(15,15,15,15));
		HBox.setMargin(BNOccupes, new Insets(15,15,15,15));
		HBox.setMargin(BNLibres, new Insets(15,15,15,15));
		//On s'occupe de la combobox
		liste.setEditable(true);
		clients.getChildren().addAll(liste,BNAfficher);
		HBox.setMargin(liste, new Insets(15,15,15,15));
		HBox.setMargin(BNAfficher, new Insets(15,15,15,15));
		ensemble.getChildren().addAll(boutonRad,clients);
		ensemble.setMinWidth(300);
		
		//----------------------On definit le haut du BorderPane
		TopPane.setStyle("-fx-background-color: #969E9D");
		TopPane.getChildren().addAll(emp,cli,res,ensemble);
		
		//-------------------------------------------------------------La partie droite
		RightPane.setStyle("-fx-background-colo: #969E9D");
		RightPane.getChildren().addAll(Inf,In1);
		
		//----------------------------------------------------------------------Le border Pane
		root.setTop(TopPane);
		root.setRight(RightPane);
		
		return root;
	}

}
