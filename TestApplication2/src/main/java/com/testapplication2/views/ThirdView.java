/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testapplication2.views;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.testapplication2.TestApplication2;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;



/**
 *
 * @author Chandramouliswaran
 */
public class ThirdView extends View {

    public ThirdView(String name) {
        super(name);
        
        Label label = new Label();

//        EventStreams.animationTicks()
//                .latestN(100)
//                .map(ticks -> {
//                    int n = ticks.size() - 1;
//                    return n * 1_000_000_000.0 / (ticks.get(n) - ticks.get(0));
//                })
//                .map(d -> String.format("FPS: %.3f", d))
//                .feedTo(label.textProperty());

        setCenter(new StackPane(label));
        
    }
    
    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(TestApplication2.MENU_LAYER)));
        appBar.setTitleText("Third");
        appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> System.out.println("Search")));
    }
}
