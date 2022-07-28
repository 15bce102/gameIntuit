package com.intuit.craft.craftDemo.controller;

import com.intuit.craft.craftDemo.dto.TopPlayerScore;
import com.intuit.craft.craftDemo.entity.Game;
import com.intuit.craft.craftDemo.entity.User;
import com.intuit.craft.craftDemo.repository.GameRepository;
import com.intuit.craft.craftDemo.service.AuthenticationServiceImpl;
import com.intuit.craft.craftDemo.service.GameProducerImpl;
import com.intuit.craft.craftDemo.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.intuit.craft.craftDemo.constants.Constants.LIMIT;

@RestController
@RequestMapping("/game")
public class GameController {
    private final Logger logger = LoggerFactory.getLogger(GameRepository.class);

    @Autowired
    GameService gameService;
    @Autowired
    GameProducerImpl gameProducerImpl;

    @Autowired
    AuthenticationServiceImpl authenticationService;

    @PostMapping("/")
    public ResponseEntity<?> addGame(@Valid @RequestBody Game game, @RequestAttribute("userId") String userId) {
        game.setUserId(userId);
        try {
            gameProducerImpl.publishData(game);
            return ResponseEntity.ok("DATA SENT");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<?> getGame() throws Exception {
        try {
            List<Game> list= gameService.getTopKScores();
            List<TopPlayerScore> result = new ArrayList<>();
            for(int i=0;i<LIMIT;i++){
                User user = authenticationService.findUserByUserId(list.get(i).getUserId());
                TopPlayerScore topPlayerScore = new TopPlayerScore();
                topPlayerScore.setUsername(user.getUsername());
                topPlayerScore.setScore(list.get(i).getScore());
                result.add(topPlayerScore);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}