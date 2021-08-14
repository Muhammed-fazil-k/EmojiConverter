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

import com.example.emojiconverterlibrary.EmojiConverter;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.app.Context;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class EmojiConverterTest {

    EmojiConverter emojiConverter;
    String hello="hello";
    @Before
    public void setup() {
        Context context=AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        emojiConverter = new EmojiConverter(context);
    }

    @Test
    public void testProcessStringNull() {
        ArrayList<String> testList = new ArrayList<>();
        String output=emojiConverter.processString(testList);
        assertEquals( "",output);

    }

    @Test
    public void testProcessString() {
        ArrayList<String> testList = new ArrayList<>();
        testList.add(hello);
        String output=emojiConverter.processString(testList);
        assertEquals("👋 ",output);

    }

    @Test
    public void testProcessStringMultiple() {
        ArrayList<String> testList = new ArrayList<>();
        testList.add(hello);
        testList.add("attack");
        String output=emojiConverter.processString(testList);
        assertEquals("👋 👊 ",output);

    }

    @Test
    public void testConvertToEmojiNull() {
        String output=emojiConverter.convertToEmoji("");
        assertEquals( " ",output);

    }

    @Test
    public void testConvertToEmoji() {
        String output=emojiConverter.convertToEmoji(hello);
        assertEquals("👋 ",output);

    }

    @Test
    public void testConvertToEmojiMultiple() {
        String output=emojiConverter.convertToEmoji("hello attack");
        assertEquals("👋 👊 ",output);

    }





}
