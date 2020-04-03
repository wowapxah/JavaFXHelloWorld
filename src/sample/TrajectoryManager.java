package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static sample.Main.timerLabel;

public class TrajectoryManager extends Thread {
    private Group root;
    private List<Circle> trajectoryPointsArray;
    private int stage;
    private long startTime;
    private long endTime;
    private SimpleDateFormat dateFormat = new SimpleDateFormat();
    private double x1, x2, y1, y2;
    private double totalDistance = 0.0;
    private double speed = 0.0;
    private long elapsedTime;

    /**
     * @param stage
     * 1: shipIn2K();
     * 2: shipInRassv();
     * 3: shipTo2K();
     * 4: shipToRassv();
     **/

    public TrajectoryManager(Group root, List<Circle> trajectoryPointsArray, int stage, long startTime, long endTime) {
        this.root = root;
        this.trajectoryPointsArray = trajectoryPointsArray;
        this.stage = stage;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void run() {
        for (int i = 0; i < trajectoryPointsArray.size() - 1; i++) {
            double x1 = trajectoryPointsArray.get(i).getCenterX();
            double y1 = trajectoryPointsArray.get(i).getCenterY();
            double x2 = trajectoryPointsArray.get(i+1).getCenterX();
            double y2 = trajectoryPointsArray.get(i+1).getCenterY();
            totalDistance += distanceBetweenTwoPoints(x1, y1, x2, y2);
        }

        if (stage == 1) shipIn2K();
        if (stage == 2) shipInRassv();
        if (stage == 3) shipTo2K();
        if (stage == 4) shipToRassv();
    }

    private double distanceBetweenTwoPoints(double x1, double y1, double x2, double y2) {
        double result = Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * ( y2 - y1)));
        return result;
    }

    private void shipIn2K() {
        System.out.println("Находится в Двух Коронах");
        System.out.println(dateFormat.format(new Date(startTime)));
        System.out.println(dateFormat.format(new Date(endTime)));
        boolean flag = true;

        Main.shipModel.setX(trajectoryPointsArray.get(0).getCenterX()-7.5);
        Main.shipModel.setY(trajectoryPointsArray.get(0).getCenterY()-22.5);

        timerLabel.setTranslateX(Main.shipModel.getX() - 20);
        timerLabel.setTranslateY(Main.shipModel.getY() - 45);

        x1 = trajectoryPointsArray.get(0).getCenterX();
        y1 = trajectoryPointsArray.get(0).getCenterY();
        x2 = trajectoryPointsArray.get(1).getCenterX();
        y2 = trajectoryPointsArray.get(1).getCenterY();

        double angle = getAngle(x1, y1, x2, y2) + 90.0;
        Main.shipModel.setRotate(angle);
        System.out.println("ship " + Main.shipModel.getRotate());

        TimerLabelManager labelManager = new TimerLabelManager(endTime);
        labelManager.start();


        while (true) {
            long currentTime = new Date().getTime();
            if (currentTime > endTime) {
                labelManager.interrupt();
                return;
            }

            flag = flag ? false : true;


            for (int i = 0; i < trajectoryPointsArray.size(); i++) {
                if (flag)
                    trajectoryPointsArray.get(i).setFill(Color.RED);
                else {
                    trajectoryPointsArray.get(i).setFill(Color.WHITE);
                }

                try {
                    sleep(50);
                } catch (InterruptedException e) { return; }
            }
        }
    }

    private void shipInRassv() {
        System.out.println("Находится на Полуострове Рассвета");
        System.out.println(dateFormat.format(new Date(startTime)));
        System.out.println(dateFormat.format(new Date(endTime)));
        boolean flag = true;

        timerLabel.setTranslateX(trajectoryPointsArray.get(trajectoryPointsArray.size()-1).getCenterX() - 30);
        timerLabel.setTranslateY(trajectoryPointsArray.get(trajectoryPointsArray.size()-1).getCenterY() + 20);

        Main.shipModel.setX(trajectoryPointsArray.get(trajectoryPointsArray.size()-1).getCenterX()-7.5);
        Main.shipModel.setY(trajectoryPointsArray.get(trajectoryPointsArray.size()-1).getCenterY()-22.5);

        x1 = trajectoryPointsArray.get(trajectoryPointsArray.size()-1).getCenterX();
        y1 = trajectoryPointsArray.get(trajectoryPointsArray.size()-1).getCenterY();
        x2 = trajectoryPointsArray.get(trajectoryPointsArray.size()-2).getCenterX();
        y2 = trajectoryPointsArray.get(trajectoryPointsArray.size()-2).getCenterY();

        double angle = getAngle(x1, y1, x2, y2) + 90.0;
        System.out.println("angle in2k: " + angle);
        Main.shipModel.setRotate(angle);
        System.out.println("ship " + Main.shipModel.getRotate());

        TimerLabelManager labelManager = new TimerLabelManager(endTime);
        labelManager.start();

        while (true) {
            long currentTime = new Date().getTime();
            if (currentTime > endTime) {
                labelManager.interrupt();
                return;
            }

            flag = flag ? false : true;
            for (int i = trajectoryPointsArray.size()-1; i >= 0; i--) {
                if (flag)
                    trajectoryPointsArray.get(i).setFill(Color.RED);
                else trajectoryPointsArray.get(i).setFill(Color.WHITE);

                Date currentDate = new Date(elapsedTime);
                SimpleDateFormat elapsedDateFormat = new SimpleDateFormat("mm:ss");

                try {
                    sleep(50);
                } catch (InterruptedException e) { return; }
            }
        }
    }

    private void shipTo2K() {
        System.out.println("Плывет к Двум Коронам");
        System.out.println(dateFormat.format(new Date(startTime)));
        System.out.println(dateFormat.format(new Date(endTime)));

        Main.shipModel.setX(trajectoryPointsArray.get(0).getCenterX()-7.5);
        Main.shipModel.setY(trajectoryPointsArray.get(0).getCenterY()-22.5);

        timerLabel.setTranslateX(trajectoryPointsArray.get(trajectoryPointsArray.size()-1).getCenterX() - 30);
        timerLabel.setTranslateY(trajectoryPointsArray.get(trajectoryPointsArray.size()-1).getCenterY() + 20);

        int totalPoints = trajectoryPointsArray.size() - 1;
        long timeForPoint = ((10 * 60 * 1000) + 19420) / totalPoints;

        long currentTime = new Date().getTime();
        int currentPoint = (int) ((currentTime - startTime) / timeForPoint);

        totalPoints -= currentPoint;

        TimerLabelManager labelManager = new TimerLabelManager(endTime);
        labelManager.start();

        x1 = trajectoryPointsArray.get(totalPoints).getCenterX();
        y1 = trajectoryPointsArray.get(totalPoints).getCenterY();
        x2 = trajectoryPointsArray.get(totalPoints-1).getCenterX();
        y2 = trajectoryPointsArray.get(totalPoints-1).getCenterY();

        moveShip(x2, y2);

        while (true) {
            currentTime = new Date().getTime();
            if (currentTime > endTime) {
                labelManager.interrupt();
                return;
            }

            x1 = trajectoryPointsArray.get(totalPoints).getCenterX();
            y1 = trajectoryPointsArray.get(totalPoints).getCenterY();
            x2 = trajectoryPointsArray.get(totalPoints-1).getCenterX();
            y2 = trajectoryPointsArray.get(totalPoints-1).getCenterY();

            double angle = getAngle(x1, y1, x2, y2) + 90.0;
            Main.shipModel.setRotate(angle);
            System.out.println("ship " + Main.shipModel.getRotate());

            for (int i = 1; i < 10; i++) {
                //double tempX = x1 - (((x1 + x2) / 10) * i);
                //double tempY = y1 - (((y1 + y2) / 10) * i);

                double tempX = i * ((x1 + x2) / 10);
                double tempY = i * ((y1 + y2) / 10);

                KeyValue valueX = new KeyValue(Main.shipModel.xProperty(), x2 - 7.5);
                KeyValue valueY = new KeyValue(Main.shipModel.yProperty(), y2 - 22.5);

                KeyFrame keyFrame = new KeyFrame(Duration.millis(timeForPoint), valueX, valueY);
                Timeline timeline = new Timeline();
                timeline.getKeyFrames().add(keyFrame);
                timeline.play();

                try {
                    sleep(timeForPoint / 10);
                } catch (InterruptedException e) { }
            }

            totalPoints--;
            System.out.println("totalPoints " + totalPoints);
        }
    }

    private void shipToRassv() {
        System.out.println("Плывет к Полуострову Рассвета");
        System.out.println(dateFormat.format(new Date(startTime)));
        System.out.println(dateFormat.format(new Date(endTime)));

        Main.shipModel.setX(trajectoryPointsArray.get(0).getCenterX() - 7.5);
        Main.shipModel.setY(trajectoryPointsArray.get(0).getCenterY() - 22.5);

        int totalPoints = trajectoryPointsArray.size() - 1;
        long timeForPoint = ((10 * 60 * 1000) + 19420) / totalPoints;

        long currentTime = new Date().getTime();
        int currentPoint = (int) ((currentTime - startTime) / timeForPoint);

        totalPoints -= currentPoint;

        TimerLabelManager labelManager = new TimerLabelManager(endTime);
        labelManager.start();

        /*x1 = trajectoryPointsArray.get(0).getCenterX();
        y1 = trajectoryPointsArray.get(0).getCenterX();
        x2 = trajectoryPointsArray.get(1).getCenterX();
        y2 = trajectoryPointsArray.get(1).getCenterX();
        double x3 = trajectoryPointsArray.get(2).getCenterX();
        double y3 = trajectoryPointsArray.get(2).getCenterY();
        double x4 = trajectoryPointsArray.get(3).getCenterX();
        double y4 = trajectoryPointsArray.get(3).getCenterY();
        double x5 = trajectoryPointsArray.get(4).getCenterX();
        double y5 = trajectoryPointsArray.get(4).getCenterY();
        double x6 = trajectoryPointsArray.get(5).getCenterX();
        double y6 = trajectoryPointsArray.get(5).getCenterY();
        double x7 = trajectoryPointsArray.get(6).getCenterX();
        double y7 = trajectoryPointsArray.get(6).getCenterY();
        double x8 = trajectoryPointsArray.get(7).getCenterX();
        double y8 = trajectoryPointsArray.get(7).getCenterY();



        Path path = new Path();
        path.getElements().add(new MoveTo(x1, y1));
        path.getElements().add(new CubicCurveTo(x4, y4, x2, y2, x3, y3));
        //path.getElements().add(new MoveTo(x4, y4));
        path.getElements().add(new CubicCurveTo(x8, y8, x6, y6, x7, y7));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setPath(path);
        pathTransition.setNode(Main.shipModel);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        //pathTransition.setAutoReverse(true);
        pathTransition.play();*/


        while (true) {
            currentTime = new Date().getTime();
            if (currentTime > endTime) {
                labelManager.interrupt();
                return;
            }

            x1 = trajectoryPointsArray.get(trajectoryPointsArray.size() - totalPoints - 1).getCenterX();
            y1 = trajectoryPointsArray.get(trajectoryPointsArray.size() - totalPoints - 1).getCenterY();
            x2 = trajectoryPointsArray.get(trajectoryPointsArray.size() - totalPoints).getCenterX();
            y2 = trajectoryPointsArray.get(trajectoryPointsArray.size() - totalPoints).getCenterY();

            double angle = getAngle(x1, y1, x2, y2) + 90.0;
            Main.shipModel.setRotate(angle);

            for (int i = 1; i < 10; i++) {
                double tempX = i * ((x1 + x2) / 10);
                double tempY = i * ((y1 + y2) / 10);

                KeyValue valueX = new KeyValue(Main.shipModel.xProperty(), x2);
                KeyValue valueY = new KeyValue(Main.shipModel.yProperty(), y2);

                KeyFrame keyFrame = new KeyFrame(Duration.millis(timeForPoint), valueX, valueY);
                Timeline timeline = new Timeline();
                timeline.getKeyFrames().add(keyFrame);
                timeline.play();

                //moveShip(x2, y2);

                try {
                    sleep(timeForPoint);
                } catch (InterruptedException e) { }
            }

            totalPoints--;
            System.out.println("totalPoints " + totalPoints);
        }
    }

    private double getAngle(Double x1, Double y1, Double x2, Double y2) {
        double angle = (180 / Math.PI) * Math.atan2(y2 - y1, x2 - x1);
        return angle;
    }

    private void moveShip(Double newX, Double newY) {
        Main.shipModel.setX(newX - 7.5);
        Main.shipModel.setY(newY - 22.5);
        timerLabel.setTranslateX(newX - 30);
        timerLabel.setTranslateY(newY + 20);
    }
}
