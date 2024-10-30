package com.mirea.solovyevia.data.storage.models;

import java.util.List;
import java.util.Map;

public class Anime {

    private int malId;
    private String title;
    private String titleEnglish;
    private String titleJapanese;
    private Map<String, Map<String, String>> images;
    private String synopsis;
    private double score;
    private int episodes;

    public int getMalId() {
        return malId;
    }

    public void setMalId(int malId) {
        this.malId = malId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public void setTitleEnglish(String titleEnglish) {
        this.titleEnglish = titleEnglish;
    }

    public String getTitleJapanese() {
        return titleJapanese;
    }

    public void setTitleJapanese(String titleJapanese) {
        this.titleJapanese = titleJapanese;
    }

    public Map<String, Map<String, String>> getImages() {
        return images;
    }

    public void setImages(Map<String, Map<String, String>> images) {
        this.images = images;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

}
