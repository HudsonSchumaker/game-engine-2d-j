package com.schumakerteam.alpha.core;

import com.schumakerteam.alpha.ecs.impl.BasicSystem;

import java.util.HashMap;

public class SystemTypeIdMap {
    private static final HashMap<Integer, BasicSystem> SYSTEM_MAP = new HashMap<>();

    public static void setSystem(int systemId, BasicSystem system) {
        SYSTEM_MAP.put(systemId, system);
    }

    public static BasicSystem getSystem(int systemId) {
        return SYSTEM_MAP.get(systemId);
    }
}
