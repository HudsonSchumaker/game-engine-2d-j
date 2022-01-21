package com.schumakerteam.alpha.core.impl;

import com.schumakerteam.alpha.ecs.impl.BasicSystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class SystemMap {
    private static final HashMap<Integer, BasicSystem> SYSTEM_MAP = new HashMap<>();

    public static void setSystem(int systemId, BasicSystem system) {
        SYSTEM_MAP.put(systemId, system);
    }

    public static BasicSystem getSystem(int systemId) {
        return SYSTEM_MAP.get(systemId);
    }

    public static List<BasicSystem> getSystems() {
        return SYSTEM_MAP.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
