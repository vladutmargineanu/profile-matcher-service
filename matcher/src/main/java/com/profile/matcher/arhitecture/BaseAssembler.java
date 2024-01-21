package com.profile.matcher.arhitecture;

import java.util.List;

public abstract class BaseAssembler<T, D> extends BaseLogging {
    public abstract D toModel(T t);

    public abstract List<D> toCollectionModel(List<T> t);
}
