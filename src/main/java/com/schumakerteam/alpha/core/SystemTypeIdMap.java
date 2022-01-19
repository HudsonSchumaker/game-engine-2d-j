package com.schumakerteam.alpha.core;

import com.schumakerteam.alpha.ecs.impl.BasicSystem;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SystemTypeIdMap {
    private static final HashMap<Integer, BasicSystem> SYSTEM_MAP = new HashMap<>();

    public static void setSystem(int systemId, BasicSystem system) {
        SYSTEM_MAP.put(systemId, system);
    }

    public static BasicSystem getSystem(int systemId) {
        return SYSTEM_MAP.get(systemId);
    }

    public static List<BasicSystem> getSystems() {
        return SYSTEM_MAP.entrySet().stream().map(entry -> {
            return entry.getValue();
        }).collect(Collectors.toList());
    }
}
