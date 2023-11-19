package PoolGame;

import PoolGame.items.Ball;
import PoolGame.items.Balls;

import PoolGame.items.Table;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Line;


public class Game {
    // tick() is called every frame, handle main game logic here
    private TranslateTransition translateTransition;

    private static final double stopVel = 0.1;
    public void tick(Balls balls, Table table, Double FRAMETIME) {
        // TODO: Implement game logic
        //小球移动逻辑
        for (Integer key: balls.getBalls().keySet()){
            balls.getBalls().get(key).setXPos(balls.getBalls().get(key).getXPos()+balls.getBalls().get(key).getXVel());
            balls.getBalls().get(key).setYPos(balls.getBalls().get(key).getYPos()+balls.getBalls().get(key).getYVel());
        }
        //检测碰撞
        Strategy.detectCollisionsOfBalls(balls, table);
        Strategy.detectCollisionsOfWall(balls, table);
        //速度衰减逻辑
        for (Integer key: balls.getBalls().keySet()){
            if(balls.getBalls().get(key).getXVel() > 0 && balls.getBalls().get(key).getYVel() > 0){
                balls.getBalls().get(key).setXVel((1-FRAMETIME)*balls.getBalls().get(key).getXVel());
                balls.getBalls().get(key).setYVel((1-FRAMETIME)*balls.getBalls().get(key).getYVel());
                if(Math.sqrt(Math.pow(balls.getBalls().get(key).getXVel(),2)+Math.pow(balls.getBalls().get(key).getYVel(),2)) <= stopVel){
                    balls.getBalls().get(key).setXVel(0);
                    balls.getBalls().get(key).setYVel(0);
                }
            }
            if(balls.getBalls().get(key).getXVel() > 0 && balls.getBalls().get(key).getYVel() < 0){
                balls.getBalls().get(key).setXVel((1-FRAMETIME)*balls.getBalls().get(key).getXVel());
                balls.getBalls().get(key).setYVel((1-FRAMETIME)*balls.getBalls().get(key).getYVel());
                if(Math.sqrt(Math.pow(balls.getBalls().get(key).getXVel(),2)+Math.pow(balls.getBalls().get(key).getYVel(),2)) <= stopVel){
                    balls.getBalls().get(key).setXVel(0);
                    balls.getBalls().get(key).setYVel(0);
                }
            }
            if(balls.getBalls().get(key).getXVel() < 0 && balls.getBalls().get(key).getYVel() > 0){
                balls.getBalls().get(key).setXVel((1-FRAMETIME)*balls.getBalls().get(key).getXVel());
                balls.getBalls().get(key).setYVel((1-FRAMETIME)*balls.getBalls().get(key).getYVel());
                if(Math.sqrt(Math.pow(balls.getBalls().get(key).getXVel(),2) + Math.pow(balls.getBalls().get(key).getYVel(),2)) <= stopVel){
                    balls.getBalls().get(key).setXVel(0);
                    balls.getBalls().get(key).setYVel(0);
                }
            }
            if(balls.getBalls().get(key).getXVel() < 0 && balls.getBalls().get(key).getYVel() < 0){
                balls.getBalls().get(key).setXVel((1-FRAMETIME)*balls.getBalls().get(key).getXVel());
                balls.getBalls().get(key).setYVel((1-FRAMETIME)*balls.getBalls().get(key).getYVel());
                if(Math.sqrt(Math.pow(balls.getBalls().get(key).getXVel(),2) + Math.pow(balls.getBalls().get(key).getYVel(),2)) <= stopVel){
                    balls.getBalls().get(key).setXVel(0);
                    balls.getBalls().get(key).setYVel(0);
                }
            }
        }

    }
}