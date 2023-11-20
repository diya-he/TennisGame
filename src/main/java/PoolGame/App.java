package PoolGame;

import PoolGame.items.Ball;
import PoolGame.items.Balls;
import PoolGame.items.Factory;
import PoolGame.items.Table;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.util.Duration;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App extends Application {

    private static final String TITLE = "Tennis Game";
    private static final double FRAMETIME = 1.0/60.0;

    private Line dragLine = new Line();
    private boolean isDragging = false;
    public App() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        // setup drawables
        Game game = new Game();

        Table table = Factory.createTable();//工厂模式生成Table
        Balls balls = Factory.createBalls();//工厂模式生成Balls

        double length = table.getLength();
        double height = table.getHeight();
//        System.out.println(height);
        double friction = table.getFriction();
        double radius = table.getRadius();
        List<List<Double>> holes = table.getHoles();
        Canvas canvas = new Canvas(length, height);
        root.getChildren().add(canvas);
        Rectangle rectangle = new Rectangle(length, height);
        rectangle.setFill(Color.valueOf(table.getColour()));
        List<Circle> list = new ArrayList<>();
        Circle hole1 = new Circle(holes.get(1).get(0), holes.get(1).get(1), radius, Color.valueOf("black"));
        list.add(hole1);
        Circle hole2 = new Circle(holes.get(2).get(0), holes.get(2).get(1), radius, Color.valueOf("black"));
        list.add(hole2);
        Circle hole3 = new Circle(holes.get(3).get(0), holes.get(3).get(1), radius, Color.valueOf("black"));
        list.add(hole3);
        Circle hole4 = new Circle(holes.get(4).get(0), holes.get(4).get(1), radius, Color.valueOf("black"));
        list.add(hole4);
        Circle hole5 = new Circle(holes.get(5).get(0), holes.get(5).get(1), radius, Color.valueOf("black"));
        list.add(hole5);
        Circle hole6 = new Circle(holes.get(6).get(0), holes.get(6).get(1), radius, Color.valueOf("black"));
        list.add(hole6);

        Map<Integer, List<Double>> bluePosList = new HashMap<>();
        for(Map.Entry<Integer, Ball> entry: balls.getBalls().entrySet())
        {
            if("blue".equals(entry.getValue().getColour()))
            {
                List<Double> list1 = new ArrayList<>();
                list1.add(entry.getValue().getXPos());
                list1.add(entry.getValue().getYPos());
                bluePosList.put(entry.getKey(), list1);
            }
        }
        root.getChildren().add(rectangle);
        root.getChildren().add(hole1);root.getChildren().add(hole2);root.getChildren().add(hole3);root.getChildren().add(hole4);root.getChildren().add(hole5);root.getChildren().add(hole6);
        for (Integer key: balls.getBalls().keySet()){
            root.getChildren().add(balls.getBalls().get(key).getShape());
        }
        root.getChildren().add(dragLine);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();
        stage.setWidth(length);
        stage.setHeight(height+28);
        //设置json文件第一个球可以拉
        setupMouseEvents(scene, balls.getBalls().get(0));

        // setup frames
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        Strategy.init(balls);
        KeyFrame frame = new KeyFrame(Duration.seconds(FRAMETIME), (actionEvent) -> game.tick(balls,table,FRAMETIME, list, bluePosList));
        timeline.getKeyFrames().add(frame);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void setupMouseEvents(Scene scene, Ball ball) {
        scene.setOnMousePressed(event -> {
            if (ball.getShape().getBoundsInParent().contains(event.getX(), event.getY())) {
                isDragging = true;

                // 设置拖拽虚线的起始位置（圆心坐标）
                dragLine.setStartX(ball.getShape().getCenterX());
                dragLine.setStartY(ball.getShape().getCenterY());
                // 设置拖拽虚线的结束位置（暂时和起始位置一样）
                dragLine.setEndX(ball.getShape().getCenterX());
                dragLine.setEndY(ball.getShape().getCenterY());
                // 显示拖拽虚线
                dragLine.setVisible(true);
            }
            event.consume();
        });

        scene.setOnMouseDragged(event -> {
            if (isDragging) {
                // 更新拖拽虚线的结束位置
                dragLine.setEndX(ball.getXPos() + (event.getSceneX() - ball.getXPos()));
                dragLine.setEndY(ball.getYPos() + (event.getSceneY() - ball.getYPos()));
            }
            event.consume();
        });

        scene.setOnMouseReleased(event -> {
            if (isDragging) {
                isDragging = false;
                // 隐藏拖拽虚线
                dragLine.setVisible(false);
                // 计算拖拽的距离和方向
                double mouseX = event.getSceneX();
                double mouseY = event.getSceneY();
                double ballX = ball.getXPos();
                double ballY = ball.getYPos();
                double length = Math.sqrt(Math.pow((mouseX-ballX), 2) + Math.pow(mouseY-ballY,2));
                double speedFactor = length/25;
//                System.out.println(length);
                ball.setXVel((ballX-mouseX)/length*speedFactor);
                ball.setYVel((ballY-mouseY)/length*speedFactor);
                event.consume();
            }
            event.consume();
        });
    }
}
