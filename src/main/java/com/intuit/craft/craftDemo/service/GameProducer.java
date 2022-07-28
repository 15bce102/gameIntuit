package com.intuit.craft.craftDemo.service;

import com.intuit.craft.craftDemo.entity.Game;
import com.intuit.craft.craftDemo.exception.PublishDataException;

public interface GameProducer {

    void publishData(Game game) throws PublishDataException;
}
