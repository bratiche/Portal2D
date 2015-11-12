package com.portal2d.game.model.level;

import com.portal2d.game.model.entities.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Extended by Level, used to notify when an Entity is added or removed.
 */
public abstract class Observable {

    private List<LevelObserver> observers;

    public Observable(LevelObserver...observers) {
        this.observers = new ArrayList<LevelObserver>();
        Collections.addAll(this.observers, observers);
    }

    /**
     * Notify the observers that an Entity was added or removed.
     * @param added whether the entity was added or removed
     */
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
            throw new NullPointerException("Observer cannot be null");
        }
        if(observers.contains(observer)) {
            return;
        }
        observers.add(observer);
    }

    public void removeObserver(LevelObserver observer) {
        if(!observers.contains(observer)) {
            return;
        }
        observers.remove(observer);
    }

}
