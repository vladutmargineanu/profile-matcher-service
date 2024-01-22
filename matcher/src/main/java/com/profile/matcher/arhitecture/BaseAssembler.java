package com.profile.matcher.arhitecture;

import java.util.List;

public abstract class BaseAssembler<T, D> extends BaseLogging {
    public abstract D toResource(T t);

    public abstract List<D> toCollectionResource(List<T> t);
}
