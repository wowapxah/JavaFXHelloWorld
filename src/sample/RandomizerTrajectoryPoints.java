package sample;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;
import java.util.Random;

public class RandomizerTrajectoryPoints extends Thread {
    private List<Circle> trajectoryPointsArray;
    private long timeFrom2k;
    private Group root;

    public RandomizerTrajectoryPoints(Group root, List<Circle> trajectoryPointsArray, long timeFrom2k) {
        this.trajectoryPointsArray = trajectoryPointsArray;
        this.timeFrom2k = timeFrom2k;
        this.root = root;
    }

    @Override
    public void run() {
        runOnStart();
        prepare();

        TimeManager timeManager = new TimeManager(root, trajectoryPointsArray, timeFrom2k);
        timeManager.setDaemon(true);
        timeManager.start();
    }

    private void runOnStart() {
        int count = 0;
        Random random = new Random(System.currentTimeMillis());
        while (count < 300) {
            int randomItem = random.nextInt(trajectoryPointsArray.size());
            int r = random.nextInt(255);
            int g = random.nextInt(255);
            int b = random.nextInt(255);
            trajectoryPointsArray.get(randomItem).setFill(Color.rgb(r, g, b));
            count++;
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) { return; }
        }
    }

    public void prepare() {
        for (int i = 0; i < trajectoryPointsArray.size(); i++) {
            trajectoryPointsArray.get(i).setFill(Color.WHITE);
            double tempX = trajectoryPointsArray.get(i).getCenterX();
            double tempY = trajectoryPointsArray.get(i).getCenterY();
            final int tempI = i;

            trajectoryPointsArray.get(i).setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    //Main.elapsedTimeToPointLabel.setText("entered " + tempI + 1 + ", x = " + tempX + ", y = " + tempY + " ");
                    Main.elapsedTimeToPointLabel.setTranslateX(event.getX() - 20);
                    Main.elapsedTimeToPointLabel.setTranslateY(event.getY() - 40);
                    Main.elapsedTimeToPointLabel.setVisible(true);
                }
            });

            trajectoryPointsArray.get(i).setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Main.elapsedTimeToPointLabel.setVisible(false);
                }
            });

            try {
                sleep(10);
            } catch (InterruptedException e) { return; }
        }
    }
}
