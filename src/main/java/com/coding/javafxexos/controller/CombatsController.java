package com.coding.javafxexos.controller;

import com.coding.javafxexos.model.Guerrier;
import com.coding.javafxexos.model.Player;
import com.coding.javafxexos.model.Sorcier;
import com.coding.javafxexos.model.Voleur;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CombatsController implements Initializable {

    @FXML
    private ComboBox boxJ1;

    @FXML
    private ComboBox boxJ2;

    @FXML
    private AnchorPane apnTour1;

    @FXML
    private AnchorPane apnTour2;

    @FXML
    private Button btnValider;

    @FXML
    private HBox hbxTitre1;

    @FXML
    private HBox hbxChoix1;

    @FXML
    private HBox hbxTitre2;

    @FXML
    private HBox hbxChoix2;

    @FXML
    private CheckBox cbxValider1;

    @FXML
    private CheckBox cbxValider2;

    @FXML
    private Text txtTitre1;

    @FXML
    private Text txtTitre2;

    @FXML
    private Button btnJ1Option1;

    @FXML
    private Button btnJ1Option2;

    @FXML
    private Button btnJ2Option1;

    @FXML
    private Button btnJ2Option2;

    @FXML
    private TextArea txtHistorique;

    int y = 1;
    int nbCombat = 0;

    Player player1 = new Player();

    Player player2 = new Player();

    void TourJ1() {
        apnTour1.getChildren().removeAll(hbxTitre1, hbxChoix1);
        apnTour2.getChildren().addAll(hbxTitre2, hbxChoix2);
//        cbxValider2.setSelected(false);
        txtTitre2.setText("Tour " + y + " : Joueur 2 (" + boxJ2.getValue() + ")");
    };

    void TourJ2() {
        if (y < 3) {
            apnTour1.getChildren().removeAll(hbxTitre1, hbxChoix1);
            apnTour2.getChildren().removeAll(hbxTitre2, hbxChoix2);
//            cbxValider1.setSelected(false);
            y++;
            txtTitre1.setText("Tour " + y + " : Joueur 1 (" + boxJ1.getValue() + ")");
            apnTour1.getChildren().addAll(hbxTitre1, hbxChoix1);
        }else {
            apnTour1.getChildren().removeAll(hbxTitre1, hbxChoix1);
            apnTour2.getChildren().removeAll(hbxTitre2, hbxChoix2);
            apnTour2.getChildren().addAll(hbxTitre2);

            if(player1.getLife() > player2.getLife()) {
                txtTitre2.setText("Le gagnant est le Joueur 1 qui est un " + boxJ1.getValue());
                txtHistorique.appendText("Combat n°" + nbCombat + "\nVainqueur : Player 1" + "\nPerdant : Player 2" + "\n\n");
            }else {
                txtTitre2.setText("Le gagnant est le Joueur 2 qui est un " + boxJ2.getValue());
                txtHistorique.appendText("Combat n°" + nbCombat + "\nVainqueur : Player 2" + "\nPerdant : Player 1" + "\n\n");
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        apnTour1.getChildren().removeAll(hbxTitre1, hbxChoix1);
        apnTour2.getChildren().removeAll(hbxTitre2, hbxChoix2);

        btnValider.setOnMouseClicked(btnCommodeAction -> {
            apnTour1.getChildren().removeAll(hbxTitre1, hbxChoix1);
            apnTour2.getChildren().removeAll(hbxTitre2, hbxChoix2);
            y = 1;
            nbCombat++;

            if (boxJ1.getValue() == "Guerrier") {
                player1 = new Guerrier();
                btnJ1Option1.setText("Attaquer");
                btnJ1Option2.setText("Se Protéger");

                System.out.println("J1 Guerrier" + player1);

            }else if (boxJ1.getValue() == "Sorcier") {
                player1 = new Sorcier();
                btnJ1Option1.setText("Attaquer");
                btnJ1Option2.setText("Magie");

                System.out.println("J1 Sorcier" + player1);

            }else if (boxJ1.getValue() == "Voleur") {
                player1 = new Voleur();
                btnJ1Option1.setText("Attaquer");
                btnJ1Option2.setText("Esquive");

                System.out.println("J1 Voleur" + player1);
            }


            if (boxJ2.getValue() == "Guerrier") {
                player2 = new Guerrier();
                btnJ2Option1.setText("Attaquer");
                btnJ2Option2.setText("Se Protéger");

                System.out.println("J2 Guerrier" + player2);

            }else if (boxJ2.getValue() == "Sorcier") {
                player2 = new Sorcier();
                btnJ2Option1.setText("Attaquer");
                btnJ2Option2.setText("Magie");

                System.out.println("J2 Sorcier" + player2);

            }else if (boxJ2.getValue() == "Voleur") {
                player2 = new Voleur();
                btnJ1Option1.setText("Attaquer");
                btnJ1Option2.setText("Esquive");

                System.out.println("J2 Voleur" + player2);
            }

            apnTour1.getChildren().addAll(hbxTitre1, hbxChoix1);
            txtTitre1.setText("Tour " + y + " : Joueur 1 (" + boxJ1.getValue() + ")");
        });

        btnJ1Option1.setOnMouseClicked(btnJ1Option1 -> {
            TourJ1();

            if (boxJ1.getValue() == "Voleur") {
                //critical truc
            }else {
                player1.doDamages(player2);
            }

        });

        btnJ1Option2.setOnMouseClicked(btnJ1Option2 -> {
            TourJ1();
            if (boxJ1.getValue() == "Guerrier") {
                player1.Shield();

            }else if (boxJ1.getValue() == "Sorcier") {
                player1.doDamagesMagic(player2);

            }else if (boxJ1.getValue() == "Voleur") {
                player1.doDamages(player2);
            }
        });

        btnJ2Option1.setOnMouseClicked(btnJ2Option1 -> {
            TourJ2();
            if (boxJ1.getValue() == "Voleur") {
                //critical truc
            }else {
                player2.doDamages(player1);
            }

            System.out.println(player1.getLife());
            System.out.println(player2.getLife());
        });

        btnJ2Option2.setOnMouseClicked(btnJ2Option2 -> {
            TourJ2();
            if (boxJ1.getValue() == "Guerrier") {
                player2.Shield();

            }else if (boxJ1.getValue() == "Sorcier") {
                player2.doDamagesMagic(player1);

            }else if (boxJ1.getValue() == "Voleur") {
                player2.doDamages(player1);
            }

            System.out.println(player1.getLife());
            System.out.println(player2.getLife());
        });




        boxJ1.getItems().addAll(
                "Guerrier",
                "Sorcier",
                "Voleur"
        );

        boxJ2.getItems().addAll(
                "Guerrier",
                "Sorcier",
                "Voleur"
        );

    }
}
