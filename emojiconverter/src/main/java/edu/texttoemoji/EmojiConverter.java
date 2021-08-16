package edu.texttoemoji;

import ohos.app.Context;
import ohos.global.resource.RawFileEntry;
import ohos.global.resource.Resource;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.utils.zson.ZSONArray;
import ohos.utils.zson.ZSONException;
import ohos.utils.zson.ZSONObject;
import edu.texttoemoji.model.EmojiResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * EmojiConverter class.
 */
public class EmojiConverter {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(0, 0, "EmojiConverter.class");
    public static final String EMPTY_STRING = "";
    public static final String SPACE_STRING = " ";
    public static final String JSON_FILE_PATH = "resources/rawfile/emojis.json";
    private Context context;
    private List<EmojiResponse> emojiList;

    /**
     * Constructor to initialize list of EmojiResponses.
     *
     * @param context context of EmojiConverter
     */
    public EmojiConverter(Context context) {
        this.context = context;
        initialiseJson();
    }

    /**
     * convertToEmoji method is used to convert the user input to corresponding emoji's.
     *
     * @param userInput string that user provided the convertion to emoji
     * @return processedString emoji-converted string
     */
    public String convertToEmoji(String userInput) {
        if (userInput == null) {
            return null;
        }
        String[] word = null;
        ArrayList<String> rawText = new ArrayList<>();
        word = userInput.split(" ");
        Collections.addAll(rawText, word);
        return processString(rawText);
    }

    /**
     * processString method will return the final string after inputting a list of words that user provided.
     *
     * @param userKeywords list of words that user entered
     * @return processedString emoji string generated from the user input
     */
    public String processString(List<String> userKeywords) {
        if (userKeywords == null) {
            return null;
        }
        if (emojiList != null) {
            StringBuilder processedText = new StringBuilder();
            for (int i = 0; i < userKeywords.size(); i++) {
                for (EmojiResponse model : emojiList) {
                    String keyWord = userKeywords.get(i);
                    List<String> emojiKeywords = model.getKeywords();
                    if (emojiKeywords.contains(keyWord)) {
                        userKeywords.set(i, model.getChar());
                    }
                }
            }
            for (int i = 0; i < userKeywords.size(); i++) {
                String generatedString = userKeywords.get(i);
                processedText.append(generatedString);
                if (i != userKeywords.size() - 1) {
                    processedText.append(SPACE_STRING);
                }
            }
            return processedText.toString();
        } else {
            return EMPTY_STRING;
        }
    }

    /**
     * initialiseJson method will add all EmojiResponse objects into the emojiList.
     */
    public void initialiseJson() {
        emojiList = new ArrayList<>();
        try {
            ZSONArray zsonArray = ZSONArray.stringToZSONArray(readFromJson());
            for (int i = 0; i < zsonArray.size(); i++) {
                List<String> keys = new ArrayList<>();
                ZSONObject zsonObject = zsonArray.getZSONObject(i);
                EmojiResponse emojiResponse = new EmojiResponse();
                emojiResponse.setName(zsonObject.getString("name"));
                ZSONArray keywords = zsonObject.getZSONArray("keywords");
                for (int j = 0; j < keywords.size(); j++) {
                    keys.add(keywords.getString(j));
                }
                emojiResponse.setKeywords(keys);
                emojiResponse.setChar(zsonObject.getString("char"));
                emojiResponse.setCategory(zsonObject.getString("category"));
                emojiList.add(emojiResponse);
            }
        } catch (ZSONException e) {
            HiLog.debug(LABEL_LOG, e.getLocalizedMessage());
        }
    }

    /**
     * readFromJson method will convert the emoji.json file into a string .
     *
     * @return - returns the generated string from json file.
     */
    public String readFromJson() {
        String jsonString = null;
        RawFileEntry rawFileEntry = context.getResourceManager().getRawFileEntry(JSON_FILE_PATH);
        try {
            Resource resource = rawFileEntry.openRawFile();
            byte[] buffer = new byte[(int) rawFileEntry.openRawFileDescriptor().getFileSize()];
            resource.read(buffer);
            resource.close();
            jsonString = new String(buffer, StandardCharsets.UTF_8);
            return jsonString;
        } catch (IOException e) {
            HiLog.debug(LABEL_LOG, e.getLocalizedMessage());
            return null;
        }
    }
}