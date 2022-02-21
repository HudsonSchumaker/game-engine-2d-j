package com.schumakerteam.alpha.common;

import com.schumakerteam.alpha.component.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class ComponentIdMap {

    private static final HashMap<String, Integer> componentTypeId = new HashMap<>();

    static {
        componentTypeId.put(TransformComponent.class.getName(), 0);
        componentTypeId.put(RigidBodyComponent.class.getName(), 1);
        componentTypeId.put(SpriteComponent.class.getName(), 2);
        componentTypeId.put(TileMapComponent.class.getName(), 3);
        componentTypeId.put(TileComponent.class.getName(), 4);
        componentTypeId.put(AnimationComponent.class.getName(), 5);
        componentTypeId.put(BoxColliderComponent.class.getName(), 6);
        componentTypeId.put(AudioComponent.class.getName(), 7);
        componentTypeId.put(InputComponent.class.getName(), 8);
        componentTypeId.put(MovementComponent.class.getName(), 9);
    }

    private ComponentIdMap() {}

    public static int getTypeId(String fqcn) {
        return componentTypeId.getOrDefault(fqcn, -1);
    }

    public static List<Integer> getTypeIds() {
        return new ArrayList<>(componentTypeId.values());
    }
}
