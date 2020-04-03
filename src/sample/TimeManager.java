package sample;

import javafx.scene.Group;
import javafx.scene.shape.Circle;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeManager extends Thread {
    private List<Interval> in2k = new ArrayList<>();
    private List<Interval> inRassv = new ArrayList<>();
    private List<Interval> to2k = new ArrayList<>();
    private List<Interval> toRassv = new ArrayList<>();
    private List<Circle> trajectoryPointsArray;
    private long timeFrom2k;
    protected ShipManager shipManager;
    private Group root;

    public TimeManager(Group root, List<Circle> trajectoryPointsArray, Long timeFrom2k) {
        this.root = root;
        this.trajectoryPointsArray = trajectoryPointsArray;
        this.timeFrom2k = timeFrom2k;
    }

    public long getTimeFrom2k() {
        return timeFrom2k;
    }

    @Override
    public void run() {
        try {
            timeFrom2k = Long.parseLong(new BufferedReader(new InputStreamReader(new BufferedInputStream(new URL("http://195.161.41.215/server_aa/timeFrom2k").openStream()))).readLine());
            System.out.println("Время отплытия из Двух Корон: " + new SimpleDateFormat().format(new Date(timeFrom2k)));
        } catch (Exception e) {
            System.out.println("Не удается получить время отплытия из Двух Корон с сервера. (" + e.getMessage() + ")");
        }
        long startTime, endTime;

        startTime = timeFrom2k;
        endTime = timeFrom2k + (10 * 60 * 1000) + 19420;

        for (int i = 1; i < 1000; i++) {
            toRassv.add(new Interval(startTime, endTime));

            startTime = endTime;
            endTime = endTime + (20 * 60 * 1000);
            inRassv.add(new Interval(startTime, endTime));

            startTime = endTime;
            endTime = endTime + (10 * 60 * 1000) + 49350;
            to2k.add(new Interval(startTime, endTime));

            startTime = endTime;
            endTime = endTime + (20 * 60 * 1000);
            in2k.add(new Interval(startTime, endTime));

            startTime = endTime;
            endTime = endTime + (10 * 60 * 1000) + 19420;
        }
        findCurrentPosition();
    }

    private void findCurrentPosition() {
        long now = new Date().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat();

        //now = now - 4 * 60 * 1000;
        System.out.println("new long..... " + now + " (" + new SimpleDateFormat().format(new Date(now)) + ")");
        for (int i = 0; i < in2k.size(); i++) {
            if (in2k.get(i).inInterval(now)) {
                shipManager = new ShipManager(trajectoryPointsArray, true, false, false, false, in2k.get(i).startTime, in2k.get(i).endTime, root);
                try {
                    shipManager.start();
                    shipManager.join();
                } catch (InterruptedException e) { return; }

                findCurrentPosition();
            }
        }

        for (int i = 0; i < toRassv.size(); i++) {
            if (toRassv.get(i).inInterval(now)) {
                shipManager = new ShipManager(trajectoryPointsArray, false, false, false, true, toRassv.get(i).startTime, toRassv.get(i).endTime, root);
                try {
                    shipManager.start();
                    shipManager.join();
                } catch (InterruptedException e) { return; }
                findCurrentPosition();
            }
        }

        for (int i = 0; i < inRassv.size(); i++) {
            if (inRassv.get(i).inInterval(now)) {
                shipManager = new ShipManager(trajectoryPointsArray, false, true, false, false, inRassv.get(i).startTime, inRassv.get(i).endTime, root);
                try {
                    shipManager.start();
                    shipManager.join();
                } catch (InterruptedException e) { return; }
                findCurrentPosition();
            }
        }

        for (int i = 0; i < to2k.size(); i++) {
            if (to2k.get(i).inInterval(now)) {
                shipManager = new ShipManager(trajectoryPointsArray, false, false, true, false, to2k.get(i).startTime, to2k.get(i).endTime, root);
                try {
                    shipManager.start();
                    shipManager.join();
                } catch (InterruptedException e) { return; }
                findCurrentPosition();
            }
        }

        System.out.println("not found");
    }

    private class Interval {
        private long startTime;
        private long endTime;

        public Interval(long startTime, long endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public boolean inInterval(long now) {
            if (now > startTime && now < endTime) return true;
            else return false;
        }
    }
}
