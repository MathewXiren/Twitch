package com.xma.jupiter.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true) // ignore unknown key
@JsonInclude(JsonInclude.Include.NON_NULL) // ignore null value
@JsonDeserialize(builder = Game.Builder.class) //use builder class to create

public class Game {
//        private String name;
//        private String developer;
//        private String releaseDate;
//        private float price;
//
////    public Game(String name, String developer, float price) {
////        this.name = name;
////        this.developer = developer;
////        this.price = price;
////    }
//
//    private Game(GameBuilder gameBuilder) {
//        this.name = gameBuilder.name;
//        this.developer = gameBuilder.developer;
//        this.price = gameBuilder.price;
//        this.releaseDate = gameBuilder.releaseDate;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public Game setName(String name) {
//        this.name = name;
//        return this;
//    }
//
//    public String getDeveloper() {
//        return developer;
//    }
//
//    public Game setDeveloper(String developer) {
//        this.developer = developer;
//        return this;
//    }
//
//    public float getPrice() {
//        return price;
//    }
//
//    public Game setPrice(float price) {
//        this.price = price;
//        return this;
//    }
//
//
//    public static class GameBuilder {
//        private String name;
//        private String developer;
//        private String releaseDate;
//        private float price;
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public void setDeveloper(String developer) {
//            this.developer = developer;
//        }
//
//        public void setReleaseDate(String releaseDate) {
//            this.releaseDate = releaseDate;
//        }
//
//        public void setPrice(float price) {
//            this.price = price;
//        }
//        public Game build() {
//            return new Game(this);
//        }
//    }
//    public static void main(String[] args) {
//        Game.GameBuilder builder = new GameBuilder();
//        builder.setName("vincent");
//        builder.setDeveloper("mathew");
//        Game game = builder.build();
//    }

    //second version
//    @JsonProperty("name")
//    public String name;
//
//    @JsonProperty("developer")
//    public String developer;
//
//    @JsonProperty("release_time")
//    public String releaseTime;
//
//    @JsonProperty("website")
//    public String website;
//
//    @JsonProperty("price")
//    public double price;
//
//    public Game(String name, String developer, String releaseTime, String website, double price) {
//        this.name = name;
//        this.developer = developer;
//        this.releaseTime = releaseTime;
//        this.website = website;
//        this.price = price;
//    }

    @JsonProperty("id")
    private final String id;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("box_art_url")
    private final String boxArtUrl;


    private Game(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.boxArtUrl = builder.boxArtUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBoxArtUrl() {
        return boxArtUrl;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Builder {
        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("box_art_url")
        private String boxArtUrl;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder boxArtUrl(String boxArtUrl) {
            this.boxArtUrl = boxArtUrl;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }


}
// Game game = new Game();
// System.out.println("Game name is " + game.getName());
// game.setName("LOL").setDeveloper("me").setPrice(10);

//GameBuilder builder = new GameBuilder();
//builder.setName("vincent");
//builder.setDeveloper("mathew");
//Game game = builder.build();


//Game game = new Game();
//game.setName("vincent");


