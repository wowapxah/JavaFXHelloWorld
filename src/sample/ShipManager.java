package sample;

import javafx.scene.Group;
import javafx.scene.shape.Circle;

import java.util.List;

public class ShipManager extends Thread {
    private boolean in2k;
    private boolean inRassv;
    private boolean to2k;
    private boolean toRassv;
    private long startTime;
    private long endTime;
    private TrajectoryManager trajectoryManager;
    private List<Circle> trajectoryPointsArray;
    private Group root;

    public ShipManager(List<Circle> trajectoryPointsArray, boolean in2k, boolean inRassv, boolean to2k, boolean toRassv, long startTime, long endTime, Group root) {
        this.trajectoryPointsArray = trajectoryPointsArray;
        this.in2k = in2k;
        this.inRassv = inRassv;
        this.to2k = to2k;
        this.toRassv = toRassv;
        this.startTime = startTime;
        this.endTime = endTime;
        this.root = root;
    }

    @Override
    public void run() {
        try {
            if (in2k) shipIn2K();
            if (inRassv) shipInRassv();
            if (to2k) shipMovingTo2K();
            if (toRassv) shipMovingToRassv();
        } catch (InterruptedException e) { return; }
    }

    private void shipIn2K() throws InterruptedException {
        trajectoryManager = new TrajectoryManager(root, trajectoryPointsArray, 1, startTime, endTime);
        trajectoryManager.start();
        trajectoryManager.join();
    }

    private void shipInRassv() throws InterruptedException {
        trajectoryManager = new TrajectoryManager(root, trajectoryPointsArray, 2, startTime, endTime);
        trajectoryManager.start();
        trajectoryManager.join();
    }

    private void shipMovingTo2K() throws InterruptedException {
        trajectoryManager = new TrajectoryManager(root, trajectoryPointsArray, 3, startTime, endTime);
        trajectoryManager.start();
        trajectoryManager.join();
    }

    private void shipMovingToRassv() throws InterruptedException {
        trajectoryManager = new TrajectoryManager(root, trajectoryPointsArray, 4, startTime, endTime);
        trajectoryManager.start();
        trajectoryManager.join();
    }
}
