package com.schumakerteam.alpha.core.impl;

import com.schumakerteam.alpha.ecs.impl.BasicSystem;
import com.schumakerteam.alpha.log.LogService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class SystemMap {
    private static final HashMap<Integer, BasicSystem> SYSTEM_MAP = new HashMap<>();

    public SystemMap() {
        LogService.getInstance().engine("SystemMap created.");
    }

    public static void setSystem(int systemId, BasicSystem system) {
        SYSTEM_MAP.put(systemId, system);
    }

    public static BasicSystem getSystem(int systemId) {
        return SYSTEM_MAP.get(systemId);
    }

    public static List<BasicSystem> getSystems() {
        return new ArrayList<>(SYSTEM_MAP.values());
    }
}
