/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.util;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ravbaker
 */
public class IMDBChartRecord implements Serializable {

    static final long serialVersionUID = 42L;
    
    private int rank;
    private float rating;
    private String title;
    private String votes;

    public IMDBChartRecord(List record) {
        parseListRecord(record);
    }

    public IMDBChartRecord(int rank, float rating, String title, String votes) {
        this.rank = rank;
        this.rating = rating;
        this.title = title;
        this.votes = votes;
    }

    private void parseListRecord(List record) {
        rank = Integer.parseInt((String) record.get(0));
        rating = Float.parseFloat((String) record.get(1));
        title = (String) record.get(2);
        votes = (String) record.get(3);
    }

    public int getRank() {
        return rank;
    }

    public float getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getVotes() {
        return votes;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public boolean equals(IMDBChartRecord result) {
        return (this.getRank() == result.getRank()
                && 0 == Float.compare(this.getRating(), result.getRating())
                && this.getTitle().equals(result.getTitle())
                && this.getVotes().equals(result.getVotes()));
    }
    
    public Object[] toArray() {
        return new Object[] {rank, rating, title, votes};
    }
}
