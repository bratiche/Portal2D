package com.portal2d.game.model.level;

import com.portal2d.game.model.entities.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Level extends this.
 */
public class Observable {

    List<LevelObserver> observers;

    public Observable(LevelObserver...observers) {
        this.observers = new ArrayList<LevelObserver>();
        Collections.addAll(this.observers, observers);
    }

    public void notifyObservers(Entity entity, boolean added) {
        if(added) {
            for (LevelObserver observer : observers) {
                observer.entityAdded(entity);
            }
        }

        else {
            for (LevelObserver observer : observers) {
                observer.entityRemoved(entity);
            }
        }
    }

    public void addObserver(LevelObserver observer) {
        if(observer == null) {
            throw new NullPointerException();
        }
        observers.add(observer);
    }
}
