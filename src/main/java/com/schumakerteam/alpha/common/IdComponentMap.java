package com.schumakerteam.alpha.common;

import com.schumakerteam.alpha.component.*;

import java.util.HashMap;

public final class IdComponentMap {

    private static final HashMap<String, Integer> systemTypeId = new HashMap<>();

    static {
        systemTypeId.put(TransformComponent.class.getName(), 0);
        systemTypeId.put(RigidBodyComponent.class.getName(), 1);
        systemTypeId.put(SpriteComponent.class.getName(), 2);
        systemTypeId.put(TileMapComponent.class.getName(), 3);
        systemTypeId.put(TileComponent.class.getName(), 4);

    }

    private IdComponentMap() {}

    public static int getTypeId(String fqnClass) {
        return systemTypeId.getOrDefault(fqnClass, -1);
    }
}
