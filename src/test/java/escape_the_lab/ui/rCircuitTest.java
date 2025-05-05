package escape_the_lab.ui;

import com.example.escape_the_lab.ui.rCircuit;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.layout.Pane;

class rCircuitTest {

    private rCircuit rCircuit;

    @BeforeEach
    public void setUp() {
        Stage stage = new Stage();
        rCircuit = new rCircuit(stage);
    }

    @Test
    public void testShowImage() {
        ImageView testImageView = new ImageView();
        rCircuit.showImage(testImageView);

        // Check if the image is visible and interactive
        assertTrue(testImageView.isVisible(), "Image should be visible.");
        assertFalse(testImageView.isMouseTransparent(), "Image should be clickable.");
    }

    @Test
    public void testHideImage() {
        ImageView testImageView = new ImageView();
        rCircuit.hideImage(testImageView);

        // Check if the image is hidden and not clickable
        assertFalse(testImageView.isVisible(), "Image should be hidden.");
        assertTrue(testImageView.isMouseTransparent(), "Image should not be clickable.");
    }

    @Test
    public void testMakeScene() {
        Scene scene = rCircuit.makeScene();

        // Check if the scene is not null
        assertNotNull(scene, "Scene should be created.");

        // Verify that the stackPane (primary container) is part of the scene's root
        assertTrue(scene.getRoot() instanceof Pane, "Root of the scene should be a Pane.");
        assertTrue(((Pane) scene.getRoot()).getChildren().get(0) instanceof StackPane,
                "StackPane should be present in the scene.");
    }

    @Test
    public void testStart() {
        Stage stage = new Stage();
        rCircuit.start();

        // Check if the scene has been set and the stage is showing
        assertEquals(stage.getScene(), rCircuit.makeScene(), "Scene should be the one created by makeScene.");
        assertTrue(stage.isShowing(), "Stage should be showing after start.");
    }

    @Test
    public void testPanelSceneLedOn() {
        // Setting the state so that the LED is on
        rCircuit.isLedOn = true;
        rCircuit.panelScene();

        Scene scene = rCircuit.makeScene();
        assertNotNull(scene, "Scene should not be null.");

        assertTrue(scene.getRoot() instanceof StackPane, "Root of the scene should be a StackPane.");

        // Test if the openedDoor image is visible when the LED is on
        StackPane stackPane = (StackPane) scene.getRoot();
        ImageView openedDoor = (ImageView) stackPane.getChildren().get(2);
        assertTrue(openedDoor.isVisible(), "Opened door should be visible when LED is on.");
    }

    @Test
    public void testPanelSceneResTooLow() {
        // Setting the state where resistor value is too low
        rCircuit.resTooLow = true;
        rCircuit.panelScene();

        // Check if the correct sound is played and other behaviors triggered (simplified here)
        // We won't test the sound but will check UI changes
        assertFalse(rCircuit.isLedOn, "LED should be off when resistor is too low.");
    }
}

