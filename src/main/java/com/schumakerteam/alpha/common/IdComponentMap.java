package com.schumakerteam.alpha.common;

import com.schumakerteam.alpha.component.TransformComponent;

import java.util.HashMap;

public final class IdComponentMap {

    private static final HashMap<String, Integer> systemTypeId = new HashMap<>();

    static {
        systemTypeId.put(TransformComponent.class.getName(), 0);
    }

    private IdComponentMap() {}

    public static int getTypeId(String fqnClass) {
        return systemTypeId.getOrDefault(fqnClass, -1);
    }
}
