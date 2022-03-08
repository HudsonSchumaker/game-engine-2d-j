package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.common.ComponentIdMap;
import com.schumakerteam.alpha.ecs.impl.Component;
import com.schumakerteam.alpha.ecs.impl.Registry;

/**
 * @author Hudson Schumaker
 */
public class MovementComponent extends Component {

    public static final int COMPONENT_TYPE_ID = ComponentIdMap.getTypeId(MovementComponent.class.getName());
    private final int id;
    private boolean move;

    public MovementComponent() {
        this(true);
    }

    public MovementComponent(boolean move) {
        this.id = Registry.getInstance().getComponentId();
        this.move = true;
    }

    public boolean isMoving() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
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
        return "MovementComponent{" + "id=" + id + ", move=" + move + '}';
    }
}
