package com.testapplication2.views;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.layout.layer.FloatingActionButton;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.myfxui.FXUICircle;
import com.testapplication2.TestApplication2;
import java.util.Arrays;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class SecondaryView extends View {

    public SecondaryView(String name) {
        super(name);
        
        getStylesheets().add(SecondaryView.class.getResource("secondary.css").toExternalForm());
        
        StackPane root = new StackPane();
        
        Color color = new Color(0.75, 0.25, 0, 0.25d);
        Color color1 = new Color(0, 0.25, 0.75, 1.0d);
        Color color2 = new Color(1, 0, 0, 0.25d);
        Color color3 = new Color(0.0, 0, 0, 0.0d);
        
        StackPane pane11 = FXUICircle.createAnimatedCircle(color, new Color(0, 0, 1, 0.25d));
        StackPane pane12 = FXUICircle.createStaticCircle(color1, new Color(0, 0, 1, 0.25d));
        StackPane pane13 = FXUICircle.createStaticCircle(color2, new Color(0, 0, 1, 0.25d));

        StackPane pane21 = FXUICircle.createStaticCircle(color1, new Color(0, 0, 1, 0.25d));
        StackPane pane22 = FXUICircle.createStaticCircle(color2, new Color(0, 0, 1, 0.25d));
        StackPane pane23 = FXUICircle.createSpotedCircle(color3, Color.BLACK);

        StackPane pane31 = FXUICircle.createSpotedCircle(color3, Color.BLACK);
        StackPane pane32 = FXUICircle.createStaticCircle(color1, new Color(0, 0, 1, 0.25d));
        StackPane pane33 = FXUICircle.createAnimatedCircle(color2, color1);
        
        
        GridPane grid = new GridPane();
        
        grid.setHgap(20);
        grid.setVgap(20);
        
        grid.addRow(0, pane11, pane12, pane13);
        grid.addRow(1, pane21, pane22, pane23);
        grid.addRow(2, pane31, pane32, pane33);
        
        grid.setBlendMode(BlendMode.SRC_OVER);
        grid.setEffect(new DropShadow(150.0d, Color.WHITESMOKE));
        
        Rectangle rect = new Rectangle(this.getWidth(),this.getHeight());
        
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, Arrays.asList(new Stop[] { new Stop(0, Color.BLACK), new Stop(1, Color.BLUE) }));
        
        RadialGradient radialGradient = new RadialGradient(0.5d, 1.0d, 0, 0, 1.0d, true, CycleMethod.NO_CYCLE, Arrays.asList(new Stop[] { new Stop(0, Color.BLACK), new Stop(1, Color.BLUE) }));
        
        rect.setFill(radialGradient);
        
        grid.setAlignment(Pos.CENTER);
        
        root.getChildren().add(rect);
        root.getChildren().add(grid);
        
        setCenter(root);
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(TestApplication2.MENU_LAYER)));
        appBar.setTitleText("Secondary");
        appBar.getActionItems().add(MaterialDesignIcon.FAVORITE.button(e -> System.out.println("Favorite")));
    }
    
}
