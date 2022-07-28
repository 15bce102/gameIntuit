package com.intuit.craft.craftDemo.constants;

public class Constants {
    public static final String TOPIC_GAME = "topic_game";
    public static final String SECRET = "SECRET_KEY";
    public static final long EXPIRATION_TIME = 900000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final int LIMIT = 2;

    public static final String TOP_K_SCORE_MSG = "Successfully get top k players";
    public static final String ADD_GAME_MSG = "Successfully add game details";
    public static final String PUBLISH_DATA_MSG = "Publishing Data in kafka";

    public static final String CONSUME_KAFKA_MSG = "Consuming Data from Kafka";

    public static final String BOOTSTRAP_SERVER = "localhost:9092";

    public static final String  APPLICATION_ID_CONFIG = "game-id-config";
}