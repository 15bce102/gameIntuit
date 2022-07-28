package com.intuit.craft.craftDemo.service;

import com.intuit.craft.craftDemo.entity.Game;
import com.intuit.craft.craftDemo.exception.GameServiceException;

import java.util.List;

public interface GameService {
    void addGame(Game game) throws GameServiceException;

    List<Game> getTopKScores() throws GameServiceException;


}
