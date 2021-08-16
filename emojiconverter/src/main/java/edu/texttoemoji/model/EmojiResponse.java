package edu.texttoemoji.model;

/*
 * Created by Ankit on 20/11/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/**
 * EmojiResponse class.
 */
public class EmojiResponse implements Serializable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("keywords")
    @Expose
    private List<String> keywords = null;
    @SerializedName("char")
    @Expose
    private String emojiChar;
    @SerializedName("category")
    @Expose
    private String category;

    public EmojiResponse() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getChar() {
        return emojiChar;
    }

    public void setChar(String emojiChar) {
        this.emojiChar = emojiChar;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
