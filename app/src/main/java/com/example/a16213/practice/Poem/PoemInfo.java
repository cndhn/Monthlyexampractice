package com.example.a16213.practice.Poem;

public class PoemInfo {

    String title;
    String writer;
    String content;

    public PoemInfo(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
