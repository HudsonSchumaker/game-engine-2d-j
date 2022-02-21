package com.schumakerteam.alpha.common;

import com.schumakerteam.alpha.log.LogService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public interface Mapper<S, T> {

    default T from(S source) {
        return null;
    }

    default List<T> from(Iterable<S> sources) {
        return from(sources, (s, t) -> {
        });
    }

    default List<T> from(Iterable<S> sources, BiConsumer<S, T> postProcessor) {
        List<T> targetList = new ArrayList<>();
        for (S entity : sources) {
            try {
                T dto = from(entity);
                postProcessor.accept(entity, dto);
                targetList.add(dto);
            } catch (RuntimeException e) {
                LogService.getInstance().warning("Cannot map, skipping");
            }
        }
        return targetList;
    }

    default void map(S source, T target) {}
}