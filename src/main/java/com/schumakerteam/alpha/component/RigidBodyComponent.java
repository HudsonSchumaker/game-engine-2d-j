package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.geometry.Vector2D;
import com.schumakerteam.alpha.log.LogService;

public final class RigidBodyComponent extends Component {

    public static final int COMPONENT_TYPE_ID = 1;
    private final int id;
    private Vector2D velocity;

    public RigidBodyComponent() {
        this(Vector2D.Zero());
    }

    public RigidBodyComponent(Vector2D velocity) {
        this.id = Registry.getInstance().getComponentId();
        this.velocity = velocity;
        LogService.getInstance().engine("RigidBodyComponent created with id: " + id);
    }

    public double getX() {
        return this.velocity.getX();
    }

    public double getY() {
        return this.velocity.getY();
    }

    public void setX(double x) {
        this.velocity.setX(x);
    }

    public void setY(double y) {
        this.velocity.setY(y);
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getTypeId() {
        return COMPONENT_TYPE_ID;
    }
}
