package sample;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.List;

public class TrajectoryInitializer extends Thread {
    private List<Circle> trajectoryPointsArray;
    private Stage stage;
    private Group root;
    private long timeFrom2k;


    TrajectoryInitializer(Stage stage, Group root, List<Circle> trajectoryPointsArray, long timeFrom2k) {
        this.trajectoryPointsArray = trajectoryPointsArray;
        this.stage = stage;
        this.root = root;
        this.timeFrom2k = timeFrom2k;
    }

    @Override
    public void run() {
        try {
            initializeTrajectory();
        } catch (InterruptedException e) { return; }
    }

    private void initializeTrajectory() throws InterruptedException {
        for (int i = 0; i < 66; i++) trajectoryPointsArray.add(new Circle());
        for (int i = 0; i < trajectoryPointsArray.size(); i++) {
            trajectoryPointsArray.get(i).setRadius(4);
            trajectoryPointsArray.get(i).setVisible(true);
            root.getChildren().add(trajectoryPointsArray.get(i));
        }
        trajectoryPointsArray.get(0).setCenterX(83);
        trajectoryPointsArray.get(0).setCenterY(176);
        trajectoryPointsArray.get(0).setRadius(6);

        trajectoryPointsArray.get(1).setCenterX(79);
        trajectoryPointsArray.get(1).setCenterY(193);

        trajectoryPointsArray.get(2).setCenterX(69);
        trajectoryPointsArray.get(2).setCenterY(206);

        trajectoryPointsArray.get(3).setCenterX(62);
        trajectoryPointsArray.get(3).setCenterY(221);

        trajectoryPointsArray.get(4).setCenterX(61);
        trajectoryPointsArray.get(4).setCenterY(237.5);

        trajectoryPointsArray.get(5).setCenterX(60);
        trajectoryPointsArray.get(5).setCenterY(254);

        trajectoryPointsArray.get(6).setCenterX(59);
        trajectoryPointsArray.get(6).setCenterY(270.5);

        trajectoryPointsArray.get(7).setCenterX(57);
        trajectoryPointsArray.get(7).setCenterY(286.9);

        trajectoryPointsArray.get(8).setCenterX(57);
        trajectoryPointsArray.get(8).setCenterY(303.4);

        trajectoryPointsArray.get(9).setCenterX(58);
        trajectoryPointsArray.get(9).setCenterY(319.9);

        trajectoryPointsArray.get(10).setCenterX(59);
        trajectoryPointsArray.get(10).setCenterY(336.4);

        trajectoryPointsArray.get(11).setCenterX(62.5);
        trajectoryPointsArray.get(11).setCenterY(352.6);

        trajectoryPointsArray.get(12).setCenterX(66);
        trajectoryPointsArray.get(12).setCenterY(368.8);

        trajectoryPointsArray.get(13).setCenterX(82.3);
        trajectoryPointsArray.get(13).setCenterY(366);

        trajectoryPointsArray.get(14).setCenterX(98.4);
        trajectoryPointsArray.get(14).setCenterY(362);

        trajectoryPointsArray.get(15).setCenterX(114.2);
        trajectoryPointsArray.get(15).setCenterY(357);

        trajectoryPointsArray.get(16).setCenterX(130);
        trajectoryPointsArray.get(16).setCenterY(352);

        trajectoryPointsArray.get(17).setCenterX(146.1);
        trajectoryPointsArray.get(17).setCenterY(348);

        trajectoryPointsArray.get(18).setCenterX(162.5);
        trajectoryPointsArray.get(18).setCenterY(346);

        trajectoryPointsArray.get(19).setCenterX(178.8);
        trajectoryPointsArray.get(19).setCenterY(349);

        trajectoryPointsArray.get(20).setCenterX(194.2);
        trajectoryPointsArray.get(20).setCenterY(355);

        trajectoryPointsArray.get(21).setCenterX(209.6);
        trajectoryPointsArray.get(21).setCenterY(361);

        trajectoryPointsArray.get(22).setCenterX(224.3);
        trajectoryPointsArray.get(22).setCenterY(368.5);

        trajectoryPointsArray.get(23).setCenterX(238.5);
        trajectoryPointsArray.get(23).setCenterY(377);

        trajectoryPointsArray.get(24).setCenterX(252.7);
        trajectoryPointsArray.get(24).setCenterY(385.5);

        trajectoryPointsArray.get(25).setCenterX(267.2);
        trajectoryPointsArray.get(25).setCenterY(393.5);

        trajectoryPointsArray.get(26).setCenterX(282);
        trajectoryPointsArray.get(26).setCenterY(401);

        trajectoryPointsArray.get(27).setCenterX(296.6);
        trajectoryPointsArray.get(27).setCenterY(408.8);

        trajectoryPointsArray.get(28).setCenterX(311);
        trajectoryPointsArray.get(28).setCenterY(417);

        trajectoryPointsArray.get(29).setCenterX(326);
        trajectoryPointsArray.get(29).setCenterY(424);

        trajectoryPointsArray.get(30).setCenterX(341);
        trajectoryPointsArray.get(30).setCenterY(431);

        trajectoryPointsArray.get(31).setCenterX(357.3);
        trajectoryPointsArray.get(31).setCenterY(434);

        trajectoryPointsArray.get(32).setCenterX(373.8);
        trajectoryPointsArray.get(32).setCenterY(435.5);

        trajectoryPointsArray.get(33).setCenterX(390.3);
        trajectoryPointsArray.get(33).setCenterY(436.5);

        trajectoryPointsArray.get(34).setCenterX(406.9);
        trajectoryPointsArray.get(34).setCenterY(436.8);

        trajectoryPointsArray.get(35).setCenterX(422.8);
        trajectoryPointsArray.get(35).setCenterY(441.5);

        trajectoryPointsArray.get(36).setCenterX(438);
        trajectoryPointsArray.get(36).setCenterY(448);

        trajectoryPointsArray.get(37).setCenterX(450.2);
        trajectoryPointsArray.get(37).setCenterY(459.2);

        trajectoryPointsArray.get(38).setCenterX(461.3);
        trajectoryPointsArray.get(38).setCenterY(471.4);

        trajectoryPointsArray.get(39).setCenterX(472);
        trajectoryPointsArray.get(39).setCenterY(484);

        trajectoryPointsArray.get(40).setCenterX(485.2);
        trajectoryPointsArray.get(40).setCenterY(494);

        trajectoryPointsArray.get(41).setCenterX(501.5);
        trajectoryPointsArray.get(41).setCenterY(497);

        trajectoryPointsArray.get(42).setCenterX(517.6);
        trajectoryPointsArray.get(42).setCenterY(493);

        trajectoryPointsArray.get(43).setCenterX(533.7);
        trajectoryPointsArray.get(43).setCenterY(489);

        trajectoryPointsArray.get(44).setCenterX(549.8);
        trajectoryPointsArray.get(44).setCenterY(485);

        trajectoryPointsArray.get(45).setCenterX(565.2);
        trajectoryPointsArray.get(45).setCenterY(479);

        trajectoryPointsArray.get(46).setCenterX(580.6);
        trajectoryPointsArray.get(46).setCenterY(473);

        trajectoryPointsArray.get(47).setCenterX(596);
        trajectoryPointsArray.get(47).setCenterY(467);

        trajectoryPointsArray.get(48).setCenterX(610.5);
        trajectoryPointsArray.get(48).setCenterY(459);

        trajectoryPointsArray.get(49).setCenterX(624.5);
        trajectoryPointsArray.get(49).setCenterY(450.2);

        trajectoryPointsArray.get(50).setCenterX(638.9);
        trajectoryPointsArray.get(50).setCenterY(442);

        trajectoryPointsArray.get(51).setCenterX(653.9);
        trajectoryPointsArray.get(51).setCenterY(435);

        trajectoryPointsArray.get(52).setCenterX(669.5);
        trajectoryPointsArray.get(52).setCenterY(429.5);

        trajectoryPointsArray.get(53).setCenterX(685.7);
        trajectoryPointsArray.get(53).setCenterY(426);

        trajectoryPointsArray.get(54).setCenterX(701.5);
        trajectoryPointsArray.get(54).setCenterY(421);

        trajectoryPointsArray.get(55).setCenterX(717.6);
        trajectoryPointsArray.get(55).setCenterY(417);

        trajectoryPointsArray.get(56).setCenterX(733.7);
        trajectoryPointsArray.get(56).setCenterY(413);

        trajectoryPointsArray.get(57).setCenterX(750);
        trajectoryPointsArray.get(57).setCenterY(410);

        trajectoryPointsArray.get(58).setCenterX(766.5);
        trajectoryPointsArray.get(58).setCenterY(411);

        trajectoryPointsArray.get(59).setCenterX(781.5);
        trajectoryPointsArray.get(59).setCenterY(418);

        trajectoryPointsArray.get(60).setCenterX(795);
        trajectoryPointsArray.get(60).setCenterY(427.6);

        trajectoryPointsArray.get(61).setCenterX(807);
        trajectoryPointsArray.get(61).setCenterY(439);

        trajectoryPointsArray.get(62).setCenterX(819.3);
        trajectoryPointsArray.get(62).setCenterY(450);

        trajectoryPointsArray.get(63).setCenterX(829.5);
        trajectoryPointsArray.get(63).setCenterY(463);

        trajectoryPointsArray.get(64).setCenterX(830);
        trajectoryPointsArray.get(64).setCenterY(479);

        trajectoryPointsArray.get(65).setCenterX(829);
        trajectoryPointsArray.get(65).setCenterY(492);
        trajectoryPointsArray.get(65).setRadius(6);

        root.getChildren().add(Main.shipModel);
        Main.shipModel.setX(250);
        Main.shipModel.setY(250);

        RandomizerTrajectoryPoints randomizerTrajectoryPoints = new RandomizerTrajectoryPoints(root, trajectoryPointsArray, timeFrom2k);
        try {
            randomizerTrajectoryPoints.start();
        } catch (Exception e) { return; }
    }
}
