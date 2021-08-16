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

package com.example.emojiconverter;

import edu.texttoemoji.EmojiConverter;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.app.Context;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class EmojiConverterTest {
    EmojiConverter emojiConverter;
    String sampleText="hello";

    @Before
    public void setup() {
        Context context=AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        emojiConverter = new EmojiConverter(context);
    }

    @Test
    public void testProcessStringIsNull() {
        ArrayList<String> testList = new ArrayList<>();
        testList=null;
        String output=emojiConverter.processString(testList);
        assertEquals( null,output);
    }

    @Test
    public void testProcessStringIsEmpty() {
        ArrayList<String> testList = new ArrayList<>();
        String output=emojiConverter.processString(testList);
        assertEquals( "",output);
    }

    @Test
    public void testProcessStringWithOneInput() {
        ArrayList<String> testList = new ArrayList<>();
        testList.add(sampleText);
        String output=emojiConverter.processString(testList);
        assertEquals("👋",output);
    }

    @Test
    public void testProcessStringWithMultipleInput() {
        ArrayList<String> testList = new ArrayList<>();
        testList.add(sampleText);
        testList.add("attack");
        String output=emojiConverter.processString(testList);
        assertEquals("👋 👊",output);
    }

    @Test
    public void testConvertToEmojiIsNull() {
        String output=emojiConverter.convertToEmoji(null);
        assertEquals( null,output);
    }

    @Test
    public void testConvertToEmojiIsEmpty() {
        String output=emojiConverter.convertToEmoji("");
        assertEquals( "",output);
    }

    @Test
    public void testConvertToEmojiWithSingleInput() {
        String output=emojiConverter.convertToEmoji(sampleText);
        assertEquals("👋",output);
    }

    @Test
    public void testConvertToEmojiWithMultipleInput() {
        String output=emojiConverter.convertToEmoji("hello attack");
        assertEquals("👋 👊",output);
    }
}