/*
 * Created by Ankit on 20/11/17.
 */

package com.example.emojiconverterlibrary.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/**
 * EmojiConverterLibrary.
 *
 * @author .
 *
 */
public class Emoji implements Serializable {
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

    public Emoji() {
    }

    /** Emoji class params .
     *
     *
     * @param name ,
     * @param keywords ,
     * @param emojiChar ,
     * @param category ,
     */
    public Emoji(String name, List<String> keywords, String emojiChar, String category) {
        super();
        this.name = name;
        this.keywords = keywords;
        this.emojiChar = emojiChar;
        this.category = category;
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