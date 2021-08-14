/*
 * Copyright (C) 2020-21 Application Library Engineering Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.emojiconverterlibrary;

import ohos.app.Context;
import ohos.global.resource.RawFileEntry;
import ohos.global.resource.Resource;
import ohos.utils.zson.ZSONArray;
import ohos.utils.zson.ZSONException;
import ohos.utils.zson.ZSONObject;
import com.example.emojiconverterlibrary.model.Emoji;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/*** EmojiConverter.
 *
 */

public class EmojiConverter {
    public static final String JSON_FILE_PATH = "resources/rawfile/emojis.json";

    private Context context;
    private List<Emoji> emojiList;

    /*** Constructor.
     *
     * @param context ;
     */
    public EmojiConverter(Context context) {
        this.context = context;
        initJson();
    }


    /*** ConvertToEmoji.
     *
     * @param s ;
     *
     * @return -return
     */
    public String convertToEmoji(String s) {
        String[] word = null;
        ArrayList<String> rawText = new ArrayList<>();
        word = s.split(" ");
        Collections.addAll(rawText, word);
        return processString(rawText);
    }

    /*** ProcessString.
     *
     * @param rawText ;
     *
     * @return -return
     */
    public String processString(List<String> rawText) {
        if (emojiList != null && rawText != null) {
            StringBuilder processedText1 = new StringBuilder();
            for (int i = 0; i < rawText.size(); i++) {

                for (Emoji model : emojiList) {
                    String raw = rawText.get(i);
                    List<String> keywords = model.getKeywords();
                    if (keywords.contains(raw)) {
                        rawText.set(i, model.getChar());
                    }

                }

            }

            for (String process : rawText) {
                processedText1.append(process).append(" ");
            }
            return processedText1.toString();
        } else {
            return "";

        }
    }

    /*** Initialising Json.
     *
     */
    public void initJson() {
        emojiList = new ArrayList<>();
        try {
            ZSONArray zsonArray = ZSONArray.stringToZSONArray(readFromJson());

            List<String> keys;


            for (int i = 0; i < zsonArray.size(); i++) {
                keys = new ArrayList<>();
                ZSONObject zsonObject = zsonArray.getZSONObject(i);

                Emoji emoji = new Emoji();
                emoji.setName(zsonObject.getString("name"));
                ZSONArray keywords = zsonObject.getZSONArray("keywords");
                for (int j = 0; j < keywords.size(); j++) {
                    keys.add(keywords.getString(j));
                }
                emoji.setKeywords(keys);
                emoji.setChar(zsonObject.getString("char"));
                emoji.setCategory(zsonObject.getString("category"));
                emojiList.add(emoji);
            }


        } catch (ZSONException e) {
            e.printStackTrace();
        }

    }

    /** ReadfromJson.
     *
     * @return - returns.
     */
    public String readFromJson() {
        String json = null;
        RawFileEntry rawFileEntry = context.getResourceManager().getRawFileEntry(JSON_FILE_PATH);
        try {

            Resource resource = rawFileEntry.openRawFile();
            byte[] buffer = new byte[(int) rawFileEntry.openRawFileDescriptor().getFileSize()];
            resource.read(buffer);
            resource.close();
            json = new String(buffer, StandardCharsets.UTF_8);
            return json;

        } catch (IOException e) {

            e.printStackTrace();
            return null;

        }
    }
}
