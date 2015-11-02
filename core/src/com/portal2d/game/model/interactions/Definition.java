package com.portal2d.game.model.interactions;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;

/**
 * @deprecated
 * TODO: maybe use this to encapsulate body information of entities
 */
public class Definition {

    private BodyDef bodyDef;
    private FixtureDef interactionFixture;
    private FixtureDef[] additionalFixtures;
    private Shape shape;

    public Definition(BodyDef bodyDef, FixtureDef interactionFixture, Shape shape, FixtureDef... additionalFixtures){
        this.bodyDef = bodyDef;
        this.interactionFixture = interactionFixture;
        this.additionalFixtures = additionalFixtures;
        this.shape = shape;
    }

    public BodyDef getBodyDef(){
        return bodyDef;
    }

    public FixtureDef getFdef(){
        return interactionFixture;
    }

    public FixtureDef[] getAdditionalFixtures() {
        return additionalFixtures;
    }

    public void disposeShape(){
        shape.dispose();
    }

}
