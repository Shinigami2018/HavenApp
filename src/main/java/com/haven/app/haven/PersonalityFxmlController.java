package com.haven.app.haven;


import com.jfoenix.controls.JFXButton;
import java.net.URL;
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



    public void initialize(URL url, ResourceBundle rb) {
        // Animate button 1
        animateButton(sad_button);

        // Animate button 2
        animateButton(des_button);

        // Animate button 3
        animateButton(cur_button);

        // Animate button 4
        animateButton(joy_button);
        animateButton(gra_button);
        animateButton(cur_button);
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
        transition.setToY(-20);  // Move up by 200px
        transition.setAutoReverse(true);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.play();
    }
}