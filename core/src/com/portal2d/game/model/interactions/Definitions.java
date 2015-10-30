package com.portal2d.game.model.interactions;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;

/**
 *
 */
public class Definitions {

    BodyDef bdef;
    FixtureDef fdef;
    Shape shape;

    public Definitions(BodyDef bdef, FixtureDef fdef, Shape shape){
        this.bdef = bdef;
        this.fdef = fdef;
        this.shape = shape;
    }

    public BodyDef getBdef(){
        return bdef;
    }

    public FixtureDef getFdef(){
        return fdef;
    }

    public void disposeShape(){
        shape.dispose();
    }


}
