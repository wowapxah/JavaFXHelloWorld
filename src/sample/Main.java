package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main extends Application {
    private static Properties properties;
    private static boolean isPropertiesAllowed;
    private static double posX;
    private static double posY;
    private static ImageView closeButton, minimizeButton;
    private List<Circle> trajectoryPointsArray = new ArrayList<>();
    private static long timeFrom2k;
    public static ImageView shipModel = new ImageView(new Image("shipModel.png", 15, 45, false, false));
    public static Label timerLabel = new Label();
    public static long elapsedTime;
    public static Label elapsedTimeToPointLabel = new Label();

    static {
        try {
            properties = new Properties();
            if (!Files.exists(Paths.get("properties"))) Files.createFile(Paths.get("properties"));
            properties.load(new FileInputStream("properties"));
            isPropertiesAllowed = true;

            Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();

            posX = Double.parseDouble(properties.getProperty("posX", String.valueOf(screenDimension.width / 2 - 250)));
            posY = Double.parseDouble(properties.getProperty("posY", String.valueOf(screenDimension.height / 2 - 250)));

            timeFrom2k = Long.parseLong(properties.getProperty("timeFrom2k"));
        } catch (Exception e) {
            isPropertiesAllowed = false;
            System.out.println("Программа не может работать правильно в этой директории " + e.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Image image = new Image("backgroundMainFrame.png");
        ImageView imageView = new ImageView(image);

        Group root = new Group();

        imageView.setLayoutX(0);
        imageView.setLayoutY(0);

        Scene scene = new Scene(root, 891, 670);
        scene.setFill(Color.rgb(0,0,0,0));

        primaryStage.setScene(scene);
        initializeOnStart(primaryStage);
        actionOnClose(primaryStage);
        initializeButtons(primaryStage, scene);

        root.getChildren().add(imageView);
        root.getChildren().add(closeButton);
        root.getChildren().add(minimizeButton);

        MainFrameMover mainFrameMover = new MainFrameMover(scene, primaryStage);
        mainFrameMover.activate();

        TrajectoryInitializer trajectoryInitializer = new TrajectoryInitializer(primaryStage, root, trajectoryPointsArray, timeFrom2k);
        trajectoryInitializer.setDaemon(true);
        trajectoryInitializer.start();

        primaryStage.show();

        elapsedTimeToPointLabel.setVisible(false);

        shipModel.setVisible(false);
        root.getChildren().add(timerLabel);
        root.getChildren().add(elapsedTimeToPointLabel);

        Image imageForLabelBackground = new Image("backgroundLabel.png", 15, 45, false, false);
        BackgroundImage backgroundImageForlabel = new BackgroundImage(imageForLabelBackground, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        timerLabel.setBackground(new Background(backgroundImageForlabel));
        timerLabel.setVisible(false);
        timerLabel.setFont(new Font("Arial", 20));
        timerLabel.setTextFill(Color.AQUA);
        timerLabel.setText("00:00");
        timerLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-background-repeat: round; " +
                "-fx-background-position: center;" +
                "-fx-effect: dropshadow(gaussian, black, 10.0, 0.0, 5.0, 5.0);" +
                "-fx-arc-width: 20px; -fx-arc-height: 20px;" +
                "-fx-label-padding: 10px;" +
                "-fx-stroke: white; -fx-stroke-width: 3px;" +
                "-fx-stroke-type: outside;");

        elapsedTimeToPointLabel.setFont(new Font("Arial", 18));
        elapsedTimeToPointLabel.setTextFill(Color.AQUA);
        elapsedTimeToPointLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-effect: dropshadow(gaussian, black, 5.0, 0.0, 5.0, 5.0);");
    }

    protected static void initializeOnStart(Stage primaryStage) {
        primaryStage.setX(posX);
        primaryStage.setY(posY);
        primaryStage.setTitle("my app title");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
    }

    private static void initializeButtons(Stage primaryStage, Scene scene) {
        Image inactiveCloseButton = new Image("grayCrest.png", 30, 30, false, false);
        Image activeCloseButton = new Image("blueCrest.png", 30,30,false,false);
        Image inactiveMinimizeButton = new Image("grayMinimize.png", 30,30,false,false);
        Image activeMinimizeButton = new Image("blueMinimize.png", 30,30,false,false);

        closeButton = new ImageView(inactiveCloseButton);
        closeButton.setX(840);
        closeButton.setY(631);
        closeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                closeButton.setImage(activeCloseButton);
            }
        });
        closeButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                closeButton.setImage(inactiveCloseButton);
            }
        });
        closeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Platform.exit();
            }
        });

        minimizeButton = new ImageView(inactiveMinimizeButton);
        minimizeButton.setX(800);
        minimizeButton.setY(631);
        minimizeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                minimizeButton.setImage(activeMinimizeButton);
            }
        });
        minimizeButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if ((event.getX() <= 800 || event.getX() >= 830) || (event.getY() <= 631 || event.getY() >= 661))// || event.getY() < 631 || event.getY() > 661)
                    minimizeButton.setImage(inactiveMinimizeButton);
            }
        });

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if ((event.getX() >= 800 && event.getX() <= 830) && (event.getY() >= 631 && event.getY() <= 661)) {
                    minimizeButton.setImage(inactiveMinimizeButton);
                    scene.getOnMouseExited();
                    primaryStage.setIconified(true);
                }
            }
        });
    }

    protected static void actionOnClose(Stage primaryStage) {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                if (isPropertiesAllowed) {
                    try {
                        properties.setProperty("posX", String.valueOf(primaryStage.getX()));
                        properties.setProperty("posY", String.valueOf(primaryStage.getY()));
                        properties.store(new FileOutputStream("properties"), "");
                    } catch (Exception e1) { return; }
                }

                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
