package com.schumakerteam.alpha.core.impl;

import com.schumakerteam.alpha.ecs.impl.BasicSystem;
import com.schumakerteam.alpha.log.LogService;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public final class AssetManager {
    private static Map<String, Image> IMAGE_CACHE = new HashMap<>();

    public AssetManager() {
        LogService.getInstance().engine("AssetManager created.");
    }

    public static void addImage(String assetId, String path) {

    }

    public static Image getImage(String assetId) {

        return null;
    }
}
