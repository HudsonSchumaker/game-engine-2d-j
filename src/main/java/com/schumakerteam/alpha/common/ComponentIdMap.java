package com.schumakerteam.alpha.common;

import com.schumakerteam.alpha.component.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class ComponentIdMap {

    private static final HashMap<String, Integer> systemTypeId = new HashMap<>();

    static {
        systemTypeId.put(TransformComponent.class.getName(), 0);
        systemTypeId.put(RigidBodyComponent.class.getName(), 1);
        systemTypeId.put(SpriteComponent.class.getName(), 2);
        systemTypeId.put(TileMapComponent.class.getName(), 3);
        systemTypeId.put(TileComponent.class.getName(), 4);
        systemTypeId.put(AnimationComponent.class.getName(), 5);
        systemTypeId.put(BoxColliderComponent.class.getName(), 6);
        systemTypeId.put(AudioComponent.class.getName(), 7);
    }

    private ComponentIdMap() {}

    public static int getTypeId(String fqcn) {
        return systemTypeId.getOrDefault(fqcn, -1);
    }

    public static List<Integer> getTypeIds() {
        return new ArrayList<>(systemTypeId.values());
    }
}
