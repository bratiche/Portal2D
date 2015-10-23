package com.portal2d.game.model.entities.portals;

/**
 * A class that keeps track of the time, it's meant to be disposable.
 */

public class Timer {
    float time;
    float deltaTime;
    float cooldownTime;

    public Timer(float deltaTime, float frames){
        time = 0;
        this.deltaTime = deltaTime;
        this.cooldownTime = frames * deltaTime;
    }

    public void reset(){
        time = 0;
    }

    public float getTime() {
        //System.out.println("Time: "+time);
        return time;
    }

    public float getCooldown() {
        //System.out.println("Cooldown: "+cooldownTime);
        return cooldownTime;
    }

    public void tick() {
        time += deltaTime;
    }
}
