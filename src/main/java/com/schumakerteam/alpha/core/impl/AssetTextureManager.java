package com.schumakerteam.alpha.core.impl;

import com.schumakerteam.alpha.common.FileUtils;
import com.schumakerteam.alpha.gfx.TileMapTexture;
import com.schumakerteam.alpha.gfx.TileTexture;
import com.schumakerteam.alpha.io.GeImageLoader;
import com.schumakerteam.alpha.common.Pair;
import com.schumakerteam.alpha.gfx.Texture;
import com.schumakerteam.alpha.log.LogService;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author Hudson Schumaker
 */
public final class AssetTextureManager {
    private static final Map<String, Texture> TEXTURE_CACHE = new HashMap<>();
    private static final Map<String, TileMapTexture> TILEMAP_CACHE = new HashMap<>();
    private static final Map<String, TileTexture> TILE_TEXTURE_CACHE = new HashMap<>();

    public AssetTextureManager() {
        LogService.getInstance().engine("AssetManager created.");
    }

    public static void addTextureFromWeb(String url) {
        var image = new GeImageLoader().readAcceleratedFromWeb(url);
        assert image != null;
        var fileName = FileUtils.getName(url);
        TEXTURE_CACHE.put(fileName, new Texture(image.getWidth(null), image.getHeight(null), image));
    }

    public static void addTexture(String fileName) {
        var image = new GeImageLoader().readImageFromDisk(fileName);
        if (image != null)
            TEXTURE_CACHE.put(fileName, new Texture(image.getWidth(null), image.getHeight(null), image));
    }

    public static void addTexture(String spriteName, Image texture) {
        TEXTURE_CACHE.put(spriteName, new Texture(texture.getWidth(null), texture.getHeight(null), texture));
    }

    public static Texture getTexture(final String assetId) {
        return TEXTURE_CACHE.get(assetId);
    }

    public static Pair<Integer, Integer> getTextureDimension(String assetId) {
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

    public static void addTileMap(String fileName) {
        var image = new GeImageLoader().readTileMapTexture(fileName);
        if (image != null)
            TILEMAP_CACHE.put(fileName, new TileMapTexture(image.getWidth(null), image.getHeight(null), image));
    }

    public static TileMapTexture getTileMap(final String assetId) {
        return TILEMAP_CACHE.get(assetId);
    }

    public static void addTileTexture(String name, TileTexture tileTexture) {
        TILE_TEXTURE_CACHE.put(name, tileTexture);
    }

    public static TileTexture getTileTexture(String name) {
        return TILE_TEXTURE_CACHE.get(name);
    }
}
