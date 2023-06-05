package com.ck4u.spotify.Activities;

public class Song {
    private String songName,songUrl, subtitle;

    public Song() {
    }

    public Song(String songName, String songUrl, String subtitle) {
        this.songName = songName;
        this.songUrl = songUrl;
        this.subtitle = subtitle;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.songUrl = subtitle;
    }
}