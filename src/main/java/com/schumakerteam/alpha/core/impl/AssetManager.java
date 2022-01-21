package com.schumakerteam.alpha.core.impl;

import com.schumakerteam.alpha.common.GeImageLoader;
import com.schumakerteam.alpha.log.LogService;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public final class AssetManager {
    private static Map<String, Image> IMAGE_CACHE = new HashMap<>();

    public AssetManager() {
        LogService.getInstance().engine("AssetManager created.");
    }

    public static void addImage(String assetId, String fileName) {
        var image = new GeImageLoader().readFromDisk(fileName);
        IMAGE_CACHE.put(assetId, image);
    }

    public static Image getImage(String assetId) {
        return IMAGE_CACHE.get(assetId);
    }
}