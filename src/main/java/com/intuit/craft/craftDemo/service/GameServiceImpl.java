package com.intuit.craft.craftDemo.service;

import com.intuit.craft.craftDemo.entity.Game;
import com.intuit.craft.craftDemo.exception.GameServiceException;
import com.intuit.craft.craftDemo.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.intuit.craft.craftDemo.constants.Constants.ADD_GAME_MSG;
import static com.intuit.craft.craftDemo.constants.Constants.LIMIT;

@Service
public class GameServiceImpl implements GameService {
    private final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);
    @Autowired
    GameRepository gameRepository;

    @Override
    public void addGame(Game game) throws GameServiceException {
        try {
            gameRepository.save(game);
            logger.info(ADD_GAME_MSG);
        }
        catch (Exception e) {
            throw new GameServiceException(e.getMessage());
        }

    }

    @Override
    public List<Game> getTopKScores() throws GameServiceException {
        try {
            return gameRepository.findTopKScores(LIMIT);
        } catch (Exception e) {
            throw new GameServiceException(e.getMessage());
        }
    }
}
