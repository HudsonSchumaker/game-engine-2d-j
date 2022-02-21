package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.common.ComponentIdMap;
import com.schumakerteam.alpha.ecs.impl.Component;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.geometry.Scale2D;
import com.schumakerteam.alpha.geometry.Vector2D;
import com.schumakerteam.alpha.log.LogService;

public final class TransformComponent extends Component {

    public static final int COMPONENT_TYPE_ID = ComponentIdMap.getTypeId(TransformComponent.class.getName());
    private final int id;
    private Vector2D position;
    private Scale2D scale;
    private double rotation;

    public TransformComponent() {
        this(Vector2D.Zero(), Scale2D.scale(), 0.0);
    }

    public TransformComponent(Vector2D position) {
        this(position, Scale2D.scale(), 0.0);
    }

    public TransformComponent(Scale2D scale) {
        this(Vector2D.Zero(), scale, 0.0);
    }

    public TransformComponent(Vector2D position, double rotation) {
        this(position, Scale2D.scale(), rotation);
    }

    public TransformComponent(Vector2D position, Scale2D scale) {
        this(position, scale, 0.0);
    }

    public TransformComponent(Vector2D position, Scale2D scale, double rotation) {
        this.id = Registry.getInstance().getComponentId();
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
        LogService.getInstance().engine("TransformComponent created with id: " + id);
    }

    public Vector2D getPosition() {
        return position;
    }

    public Scale2D getScale() {
        return scale;
    }

    public double getRotation() {
        return rotation;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void setScale(Scale2D scale) {
        this.scale = scale;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public void setX(double x) {
        this.position.setX(x);
    }

    public void setY(double y) {
        this.position.setY(y);
    }

    public double getX() {
        return this.position.getX();
    }

    public double getY() {
        return this.position.getY();
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
