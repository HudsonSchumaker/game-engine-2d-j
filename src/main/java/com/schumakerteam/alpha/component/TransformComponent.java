package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.geometry.Vector2D;
import com.schumakerteam.alpha.log.LogService;

public final class TransformComponent extends Component {

    public static final int COMPONENT_TYPE_ID = 0;
    private final int id;
    private Vector2D position;
    private Vector2D scale;
    double rotation;

    public TransformComponent(Vector2D position, Vector2D scale, double rotation) {
        this.id = Registry.getInstance().getComponentId();
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
        LogService.getInstance().engine("TransformComponent created with id: " + id);
    }

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getScale() {
        return scale;
    }

    public double getRotation() {
        return rotation;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void setScale(Vector2D scale) {
        this.scale = scale;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public void setX(double x) {
        position.setX(x);
    }

    public void setY(double y) {
        position.setY(y);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getTypeId() {
        return COMPONENT_TYPE_ID;
    }

    @Override
    public String toString() {
        return "TransformComponent{" + "position=" + position + ", scale=" + scale + ", rotation=" + rotation + "}";
    }
}
