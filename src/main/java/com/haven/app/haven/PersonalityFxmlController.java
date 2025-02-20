package com.haven.app.haven;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class PersonalityFxmlController implements Initializable {
    public JFXButton sad_button;
    public JFXButton des_button;
    public JFXButton cur_button;
    public JFXButton joy_button;
    public JFXButton gra_button,exc_button,hop_button,con_button,ind_button,conf_button,nos_button;
    public JFXButton gui_button,sha_button,ang_button,fea_button,str_button,hopl_button,fra_button,empt_button;
    public JFXButton anx_button,wor_button,par_button,hel_button,ent_button,rel_button,pri_button,cont_button,sat_button;
    public JFXButton pea_button,emp_button,bor_button,lon_button;

    private Map<JFXButton, Integer> buttonScores = new HashMap<>();
    private int score = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // Initialize button scores
        initializeButton();

        // Add event handlers to buttons
        addEventHandlers();

        // Animate buttons
        animateButtons();
    }

    private void initializeButton()
    {
        buttonScores.put(hel_button, 1);
        buttonScores.put(par_button, 1);
        buttonScores.put(anx_button, 1);
        buttonScores.put(empt_button, 1);
        buttonScores.put(des_button, 1);
        buttonScores.put(wor_button, 1);
        // Add scores for other buttons similarly
    }

    private void addEventHandlers()
    {
        hel_button.setOnAction(event -> handleButtonClick(hel_button));
        par_button.setOnAction(event -> handleButtonClick(par_button));
        anx_button.setOnAction(event -> handleButtonClick(anx_button));
        empt_button.setOnAction(event -> handleButtonClick(empt_button));
        des_button.setOnAction(event -> handleButtonClick(des_button));
        wor_button.setOnAction(event -> handleButtonClick(wor_button));
        sad_button.setOnAction(event -> handleButtonClick(sad_button));
        cur_button.setOnAction(event -> handleButtonClick(cur_button));
        joy_button.setOnAction(event -> handleButtonClick(joy_button));
        gra_button.setOnAction(event -> handleButtonClick(gra_button));
        exc_button.setOnAction(event -> handleButtonClick(exc_button));
        hop_button.setOnAction(event -> handleButtonClick(hop_button));
        con_button.setOnAction(event -> handleButtonClick(con_button));
        ind_button.setOnAction(event -> handleButtonClick(ind_button));
        conf_button.setOnAction(event -> handleButtonClick(conf_button));
        nos_button.setOnAction(event -> handleButtonClick(nos_button));
        gui_button.setOnAction(event -> handleButtonClick(gui_button));
        sha_button.setOnAction(event -> handleButtonClick(sha_button));
        ang_button.setOnAction(event -> handleButtonClick(ang_button));
        fea_button.setOnAction(event -> handleButtonClick(fea_button));
        str_button.setOnAction(event -> handleButtonClick(str_button));
        hopl_button.setOnAction(event -> handleButtonClick(hopl_button));
        fra_button.setOnAction(event -> handleButtonClick(fra_button));
        ent_button.setOnAction(event -> handleButtonClick(ent_button));
        rel_button.setOnAction(event -> handleButtonClick(rel_button));
        pri_button.setOnAction(event -> handleButtonClick(pri_button));
        cont_button.setOnAction(event -> handleButtonClick(cont_button));
        sat_button.setOnAction(event -> handleButtonClick(sat_button));
        pea_button.setOnAction(event -> handleButtonClick(pea_button));
        emp_button.setOnAction(event -> handleButtonClick(emp_button));
        bor_button.setOnAction(event -> handleButtonClick(bor_button));
        lon_button.setOnAction(event -> handleButtonClick(lon_button));
        // Add event handlers for other buttons similarly
    }

    private void handleButtonClick(JFXButton button)
    {
        System.out.println(button.getText() + " clicked");
        score += buttonScores.getOrDefault(button, 0);
        states(score);
    }

    private void states(int score)
    {
        String status;
        if(score < 2)
        {
            status = "Low";
        }
        else if(score < 3)
        {
            status = "Medium";
        }
        else{
            status = "High";
        }
        System.out.println("Mental Status: " + status);
    }

    private void animateButtons() {
        animateButton(sad_button);
        animateButton(des_button);
        animateButton(cur_button);
        animateButton(joy_button);
        animateButton(gra_button);
        animateButton(exc_button);
        animateButton(hop_button);
        animateButton(con_button);
        animateButton(ind_button);
        animateButton(conf_button);
        animateButton(nos_button);
        animateButton(gui_button);
        animateButton(sha_button);
        animateButton(ang_button);
        animateButton(fea_button);
        animateButton(str_button);
        animateButton(hopl_button);
        animateButton(fra_button);
        animateButton(empt_button);
        animateButton(anx_button);
        animateButton(wor_button);
        animateButton(par_button);
        animateButton(hel_button);
        animateButton(ent_button);
        animateButton(rel_button);
        animateButton(pri_button);
        animateButton(cont_button);
        animateButton(sat_button);
        animateButton(pea_button);
        animateButton(emp_button);
        animateButton(bor_button);
        animateButton(lon_button);
    }

    private void animateButton(JFXButton button) {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(5));
        transition.setNode(button);
        transition.setToY(-20);  // Move up by 20px
        transition.setAutoReverse(true);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.play();
    }
}