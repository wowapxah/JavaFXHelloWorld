package sample;

import javafx.application.Platform;

import java.text.SimpleDateFormat;
import java.util.Date;

import static sample.Main.timerLabel;

public class TimerLabelManager extends Thread {
    private long endTime;

    public TimerLabelManager(long endTime) {
        this.endTime = endTime;
    }

    @Override
    public void run() {
        while (true) {
            long currentTime = new Date().getTime();

            long elapsedTime = endTime - currentTime;
            SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
            Date currentDateElapse = new Date(elapsedTime);

            Platform.runLater(() -> {
                //ваш код
                Main.timerLabel.setVisible(true);
                Main.shipModel.setVisible(true);

                timerLabel.setTranslateX(Main.shipModel.getX() - 20);
                timerLabel.setTranslateY(Main.shipModel.getY() + 30);
                timerLabel.setText(dateFormat.format(currentDateElapse));
            });

            try {
                sleep(10);
            } catch (InterruptedException e) { return; }
        }
    }
}
