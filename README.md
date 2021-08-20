# EmojiConverter
This is a HMOS library used to convert text to Emoji's from a textfield or from other sources.  


## Source
The code in this repository was inspired from [AnkitKiet/EmojiConverter - Release 1.15](https://github.com/AnkitKiet/EmojiConverter). 
We are very thankful to AnkitKiet. 

## Feature
This library converts text to it's corresponding emoji .

![finaldemo](https://user-images.githubusercontent.com/60657039/129444769-961ac72c-85b2-41b5-b45d-aadd904349ce.gif)

## Dependency
1. For using EmojiConverter module in sample app, include the source code and add the below dependencies in entry/build.gradle to generate hap/support.har.
```
	dependencies {
		implementation project(':emojiconverterlibrary')
        	implementation fileTree(dir: 'libs', include: ['*.har'])
        	testImplementation 'junit:junit:4.13'
	}
```
2. For using EmojiConverter in separate application using har file, add the har file in the entry/libs folder and add the dependencies in entry/build.gradle file.
```
	dependencies {
		implementation fileTree(dir: 'libs', include: ['*.har'])
		testImplementation 'junit:junit:4.13'
	}

```

## Usage
Declare in XML (see xml attributes below for customization):

```
<TextField
        ohos:id="$+id:textfield"
        ohos:height="match_content"
        ohos:width="match_content"
        ohos:hint="Enter your text"
        ohos:text_size="70px"
        ohos:layout_alignment="center"
        ohos:top_margin="70px"
        ohos:bottom_margin="50px"
        />

    <Text
        ohos:id="$+id:text"
        ohos:height="match_content"
        ohos:width="match_content"
        ohos:background_element="$graphic:background_ability_main"
        ohos:layout_alignment="horizontal_center"
        ohos:text=" "
        ohos:text_size="50px"
        />
```

Include following code in your activity :
```
TextField textField = (TextField) findComponentById(ResourceTable.Id_textfield);
Text text = (Text) findComponentById(ResourceTable.Id_text);
EmojiConverter emojiConverter = new EmojiConverter(this);

//don't add a textobserver on the same textfield it will cause a stackoverflow error.
textField.addTextObserver((s, i, i1, i2) -> {
            String mod = emojiConverter.convertToEmoji(s);
            text.setText(mod);
        });
     
```

## Future works
Since the retrofit library is unavailable in hmos , emojis.json file was parsed from a local directory using zsonArray and zsonObject .
Once retrofit library is available in hmos , emoji.json can be parsed using it.

## License
 Copyright (C) 2020-21 Application Library Engineering Group

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.