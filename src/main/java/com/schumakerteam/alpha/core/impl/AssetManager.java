package com.schumakerteam.alpha.core.impl;

import com.schumakerteam.alpha.common.GeImageLoader;
import com.schumakerteam.alpha.common.Pair;
import com.schumakerteam.alpha.gfx.Texture;
import com.schumakerteam.alpha.log.LogService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public final class AssetManager {
    private static Map<String, Texture> TEXTURE_CACHE = new HashMap<>();

    public AssetManager() {
        LogService.getInstance().engine("AssetManager created.");
    }

    public static void addTexture(String fileName) {
        var image = new GeImageLoader().readBufferImageFromDisk(fileName);
        if (image != null)
            TEXTURE_CACHE.put(fileName, new Texture(image.getWidth(), image.getHeight(), image));
    }

    public static Texture getTexture(String assetId) {
        return TEXTURE_CACHE.get(assetId);
    }

    public static Pair<Integer, Integer> getImageDimension(String assetId) {
        var texture = getTexture(assetId);
        return Pair.of(texture.getWidth(), texture.getHeight());
    }

    public static CompletableFuture<Void> loadInBatch(List<String> fileNames) {
        return CompletableFuture.runAsync(() -> {
            for (var fileName : fileNames) {
                addTexture(fileName);
            }
        });
    }
}
