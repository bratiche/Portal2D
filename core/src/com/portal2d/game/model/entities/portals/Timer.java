package com.portal2d.game.model.entities.portals;

/**
 * A class that keeps track of the time, it's meant to be disposable.
 */
public class Timer {

    private float time;
    private float deltaTime;
    private float cooldown;

    public Timer(float deltaTime, float frames){
        time = 0;
        this.deltaTime = deltaTime;
        this.cooldown = frames * deltaTime;
    }

    public float getTime() {
        return time;
    }

    public float getCooldown() {
        return cooldown;
    }

    public void tick() {
        time += deltaTime;
    }

}
