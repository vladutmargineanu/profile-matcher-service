package com.profile.matcher.arhitecture;

public abstract class BaseCommand<T> extends BaseLogging {
    protected boolean shouldExecute() {
        return true;
    }

    public T execute() {
        if (shouldExecute()) {
            return doExecute();
        }

        throw new RuntimeException("shouldExecute() returned false");
    }

    protected abstract T doExecute();
}
