package com.hdbank.convertMTtoMXproject.demo;
import java.util.ArrayList;
import java.util.Vector;

public class SwiftMsgProcessor {
    public ArrayList<String> ParseHeaderBlock(String block1) {
        ArrayList<String> outRow = new ArrayList<>();
        outRow.add(block1.substring(3, 4));
        outRow.add(block1.substring(4, 6));
        outRow.add(block1.substring(6, 18));
        outRow.add(block1.substring(18, 22));
        outRow.add(block1.substring(22, 28));
        return outRow;
    }

    public ArrayList<String> parseApplicationHeaderBlock(String block2) throws Exception {
        ArrayList<String> outRow = new ArrayList<>();
        if (block2 != null && !block2.trim().equals("")) {
            if (block2.charAt(3) == 'O') {
                outRow.add(block2.substring(3, 4));
                outRow.add(block2.substring(4, 7));
                outRow.add(block2.substring(7, 11));
                outRow.add(block2.substring(11, 39));
                outRow.add(block2.substring(39, 45));
                outRow.add(block2.substring(45, 49));
                outRow.add(block2.substring(49, 50));
            } else {
                outRow.add(block2.substring(3, 4));
                outRow.add(block2.substring(4, 7));
                outRow.add(block2.substring(7, 19));
                outRow.add(block2.substring(19, 20));
                if (block2.length() == 25) {
                    outRow.add(block2.substring(20, 21));
                    outRow.add(block2.substring(21, 24));
                } else if (block2.length() == 22) {
                    outRow.add(block2.substring(20, 21));
                }
            }
        }

        return outRow;
    }

    protected ArrayList<TagBlock3> ParseUserHeaderBlock(String block3) {
        String tag103 = "";
        String tag108 = "";
        String tag113 = "";
        String tag115 = "";
        String tag119 = "";
        String tag111 = "";
        String tag121 = "";
        String tag433 = "";
        String tag434 = "";
        ArrayList<TagBlock3> outRowTag = new ArrayList<>();
        if (block3 != null && !block3.trim().equals("")) {
            int start103 = block3.indexOf("103:");
            int start108;
            if (start103 != -1) {
                start108 = block3.indexOf("}", block3.indexOf("103:"));
                tag103 = block3.substring(start103 + 4, start108);
            }

            start108 = block3.indexOf("108:");
            int start113;
            if (start108 != -1) {
                start113 = block3.indexOf("}", block3.indexOf("108:"));
                tag108 = block3.substring(start108 + 4, start113);
            }

            start113 = block3.indexOf("113:");
            int start115;
            if (start113 != -1) {
                start115 = block3.indexOf("}", block3.indexOf("113:"));
                tag113 = block3.substring(start113 + 4, start115);
            }

            start115 = block3.indexOf("115:");
            int start119;
            if (start115 != -1) {
                start119 = block3.indexOf("}", block3.indexOf("115:"));
                tag115 = block3.substring(start115 + 4, start119);
            }

            start119 = block3.indexOf("119:");
            int start111;
            if (start119 != -1) {
                start111 = block3.indexOf("}", block3.indexOf("119:"));
                tag119 = block3.substring(start119 + 4, start111);
            }

            start111 = block3.indexOf("111:");
            int start121;
            if (start111 != -1) {
                start121 = block3.indexOf("}", block3.indexOf("111:"));
                tag111 = block3.substring(start111 + 4, start121);
            }

            start121 = block3.indexOf("121:");
            int start433;
            if (start121 != -1) {
                start433 = block3.indexOf("}", block3.indexOf("121:"));
                tag121 = block3.substring(start121 + 4, start433);
            }

            start433 = block3.indexOf("433:");
            int start434;
            if (start433 != -1) {
                start434 = block3.indexOf("}", block3.indexOf("433:"));
                tag433 = block3.substring(start433 + 4, start434);
            }

            start434 = block3.indexOf("434:");
            if (start434 != -1) {
                int end434 = block3.indexOf("}", block3.indexOf("434:"));
                tag434 = block3.substring(start434 + 4, end434);
            }

            ArrayList<String> outRow4;
            if (!tag103.equalsIgnoreCase("")) {
                outRowTag.add(new TagBlock3("103", tag103));
            }

            if (!tag108.equalsIgnoreCase("")) {
                outRowTag.add(new TagBlock3("108", tag108));
            }

            if (!tag113.equalsIgnoreCase("")) {
                outRowTag.add(new TagBlock3("113", tag113));
            }

            if (!tag115.equalsIgnoreCase("")) {
                outRowTag.add(new TagBlock3("115", tag115));
            }

            if (!tag119.equalsIgnoreCase("")) {
                outRowTag.add(new TagBlock3("119", tag119));
            }

            if (!tag111.equalsIgnoreCase("")) {
                outRowTag.add(new TagBlock3("111", tag111));
            }

            if (!tag121.equalsIgnoreCase("")) {
                outRowTag.add(new TagBlock3("121", tag121));
            }

            if (!tag433.equalsIgnoreCase("")) {
                outRowTag.add(new TagBlock3("433", tag433));
            }

            if (!tag434.equalsIgnoreCase("")) {
                outRowTag.add(new TagBlock3("434", tag434));
            }
        }

        return outRowTag;
    }

    public ArrayList<TagBlock4> ParseTextBlock(String block4) {
        block4 = block4.replaceFirst("(?s)(\\{4:)?(.*)-}", "$2");
        ArrayList<TagBlock4> tagList = SwiftMsgUtils.createTagList(block4, "\n");
        return tagList;
    }

    public ArrayList<TagBlock5> ParseTrailerBlock(String block5) {
        String tagCHK = "";
        String tagMAC = "";
        String tagTNG = "";
        String tagPDE = "";
        String tagSYS = "";
        String tagPDM = "";
        String tagDLM = "";
        String tagPAC = "";
        String tagMRF = "";
        ArrayList<TagBlock5> outRowTag = new ArrayList<>();
        if (block5 != null) {
            int startCHK = block5.indexOf("CHK:");
            int startMAC;
            if (startCHK != -1) {
                startMAC = block5.indexOf("}", block5.indexOf("CHK:"));
                tagCHK = block5.substring(startCHK + 4, startMAC);
            }

            startMAC = block5.indexOf("MAC:");
            int startTNG;
            if (startMAC != -1) {
                startTNG = block5.indexOf("}", block5.indexOf("MAC:"));
                tagMAC = block5.substring(startMAC + 4, startTNG);
            }

            startTNG = block5.indexOf("TNG:");
            int startPDE;
            if (startTNG != -1) {
                startPDE = block5.indexOf("}", block5.indexOf("TNG:"));
                tagTNG = block5.substring(startTNG + 4, startPDE);
            }

            startPDE = block5.indexOf("PDE:");
            int startSYS;
            if (startPDE != -1) {
                startSYS = block5.indexOf("}", block5.indexOf("PDE:"));
                tagPDE = block5.substring(startPDE + 4, startSYS);
            }

            startSYS = block5.indexOf("SYS:");
            int startPDM;
            if (startSYS != -1) {
                startPDM = block5.indexOf("}", block5.indexOf("SYS:"));
                tagSYS = block5.substring(startSYS + 4, startPDM);
            }

            startPDM = block5.indexOf("PDM:");
            int startDLM;
            if (startPDM != -1) {
                startDLM = block5.indexOf("}", block5.indexOf("PDM:"));
                tagPDM = block5.substring(startPDM + 4, startDLM);
            }

            startDLM = block5.indexOf("DLM:");
            int startPAC;
            if (startDLM != -1) {
                startPAC = block5.indexOf("}", block5.indexOf("DLM:"));
                tagDLM = block5.substring(startDLM + 4, startPAC);
            }

            startPAC = block5.indexOf("PAC:");
            int startMRF;
            if (startPAC != -1) {
                startMRF = block5.indexOf("}", block5.indexOf("PAC:"));
                tagPAC = block5.substring(startPAC + 4, startMRF);
            }

            startMRF = block5.indexOf("MRF:");
            if (startMRF != -1) {
                int endMRF = block5.indexOf("}", block5.indexOf("MRF:"));
                tagMRF = block5.substring(startMRF + 4, endMRF);
            }

            if (!tagCHK.equalsIgnoreCase("")) {
                outRowTag.add(new TagBlock5("CHK", tagCHK));
            }

            if (!tagMAC.equalsIgnoreCase("")) {
                outRowTag.add(new TagBlock5("MAC", tagMAC));
            }

            if (startTNG > -1) {
                outRowTag.add(new TagBlock5("TNG", tagTNG));
            }

            if (startPDE > -1) {
                outRowTag.add(new TagBlock5("PDE", tagPDE));
            }

            if (startSYS > -1) {
                outRowTag.add(new TagBlock5("SYS", tagSYS));
            }

            if (startPDM > -1) {
                outRowTag.add(new TagBlock5("PDM", tagPDM));
            }

            if (startDLM > -1) {
                outRowTag.add(new TagBlock5("DLM", tagDLM));
            }

            if (startPAC > -1) {
                outRowTag.add(new TagBlock5("PAC", tagPAC));
            }

            if (startMRF > -1) {
                outRowTag.add(new TagBlock5("MRF", tagMRF));
            }
        }

        return outRowTag;
    }

    public boolean checkCategory(String msgType) {
        char categ = msgType.charAt(0);
        return categ >= '1' && categ <= '9';
    }
}
