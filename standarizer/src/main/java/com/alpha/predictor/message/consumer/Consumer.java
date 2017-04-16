package com.alpha.predictor.message.consumer;

/**
 * Created by dfs1 on 08-04-17.
 */
public interface Consumer<T> {
    public void receive(String message);
}
