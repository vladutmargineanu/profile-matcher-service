package com.profile.matcher.arhitecture;

public abstract class BaseCommand<T> extends BaseLogging {
    protected boolean canExecute() {
        return true;
    }

    public T execute() {
        if (canExecute()) {
            return doExecute();
        }

        throw new RuntimeException("shouldExecute() returned false");
    }

    protected abstract T doExecute();
}
