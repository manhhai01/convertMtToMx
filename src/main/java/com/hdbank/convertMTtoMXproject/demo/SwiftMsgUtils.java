package com.hdbank.convertMTtoMXproject.demo;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SwiftMsgUtils {
    public static ArrayList<TagBlock4> createTagList(String block4Text, String newLineCharacter) {
        ArrayList<TagBlock4> tagList = new ArrayList<>();
        Pattern linePattern = Pattern.compile("(:(\\d{2}[A-Z]?):)?(.*)");
        String currentTagKey = null;
        ArrayList<String> currentTagValue = new ArrayList<>();
        String[] var6 = block4Text.split(newLineCharacter);

        for (String line : var6) {
            if (currentTagKey != null || line != null && !line.isEmpty()) {
                Matcher lineMatch = linePattern.matcher(line);
                if (lineMatch.find()) {
                    if (lineMatch.group(1) != null) {
                        if (currentTagKey != null) {
                            tagList.add(new TagBlock4(currentTagKey, currentTagValue));
                            currentTagValue = new ArrayList<>();
                        }

                        currentTagKey = lineMatch.group(2);
                    }

                    currentTagValue.add(lineMatch.group(3));
                }
            }
        }

        if (currentTagKey != null) {
            tagList.add(new TagBlock4(currentTagKey, currentTagValue));
        }

        return tagList;
    }
}
