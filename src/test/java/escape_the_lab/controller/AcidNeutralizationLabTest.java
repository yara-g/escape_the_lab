/// line 58-79, 92-99
package escape_the_lab.controller;

import com.example.escape_the_lab.controller.AcidNeutralizationLab;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.example.escape_the_lab.model.Substance;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

public class AcidNeutralizationLabTest {
    private AcidNeutralizationLab lab;

    @BeforeAll
    public static void initToolkit() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await();
    }
    @Test
    public void testExistsInTrue() {
        ArrayList<Substance> list = new ArrayList<>();
        ImageView img = new ImageView(new Image("/images/AAAAcidLab/rA.png"));
        Substance s = new Substance("Test", 1, img, 3,2);
        list.add(s);

        boolean result = Substance.existsIn(list, img);
        assertTrue(result, "The image should exist in the list");
    }

    @Test
    public void testExistsInFalse() {
        ArrayList<Substance> list = new ArrayList<>();
        ImageView img1 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/pA.png")).toExternalForm()));
        ImageView img2 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/rA.png")).toExternalForm()));
        Substance s = new Substance("Test", 1, img1, 3,2);
        list.add(s);

        boolean result = Substance.existsIn(list, img2);
        assertFalse(result, "The image should not exist in the list");
    }

    @Test
    public void testDetectDroppedSubstancesSuccess() throws InterruptedException{
        Platform.runLater(() -> {
            try {
                Stage primaryStage = new Stage();
                lab = new AcidNeutralizationLab(primaryStage);
                Substance s1 = new Substance("Test1", 1, new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/rA.png")).toExternalForm())), 3, 2);
                Substance s2 = new Substance("Test", 1, new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/puA.png")).toExternalForm())), 3, 2);
                lab.droppedSubstances = new ArrayList<>();
                lab.droppedSubstances.add(s1);
                lab.droppedSubstances.add(s2);

                lab.detectDroppedSubstances();

                assertTrue(lab.succeedLab, "Lab should succeed if correct substances are dropped");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread.sleep(1000);
    }
}

