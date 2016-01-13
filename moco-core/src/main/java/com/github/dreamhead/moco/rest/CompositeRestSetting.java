package com.github.dreamhead.moco.rest;

import com.github.dreamhead.moco.HttpRequest;
import com.github.dreamhead.moco.ResponseHandler;
import com.github.dreamhead.moco.RestSetting;
import com.google.common.base.Optional;

public class CompositeRestSetting<T extends SimpleRestSetting> implements RestSetting {
    private final T[] settings;

    public CompositeRestSetting(final T[] settings) {
        this.settings = settings;
    }

    @Override
    public boolean isSimple() {
        return false;
    }

    @Override
    public Optional<ResponseHandler> getMatched(final String name, final HttpRequest httpRequest) {
        for (RestSetting setting : settings) {
            Optional<ResponseHandler> responseHandler = setting.getMatched(name, httpRequest);
            if (responseHandler.isPresent()) {
                return responseHandler;
            }
        }

        return Optional.absent();
    }

    public T[] getSettings() {
        return settings;
    }
}
