package com.hdbank.convertMTtoMXproject.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class TranslateMtToMx {

    Logger logger = LoggerFactory.getLogger(TranslateMtToMx.class);
    private static final String messageMt202 = "{1:F01AAAABEBBAXXX0000000000}{2:I202CCCCUS33AXXXN}{1:F01COPZBEB0AXXX0377002682}{3:{121:c8b66b47-2bd9-48fe-be90-93c2096f27d2}}{4:\n" +
            ":20:987\n" +
            ":21:090525/123COV\n" +
            ":13C:/SNDTIME/1249+0200\n" +
            ":32A:090527USD10500,00\n" +
            ":52A:BKAUATWW\n" +
            ":56A:TESTBICD\n" +
            ":57A:TESTBICE\n" +
            ":58A:TESTBICF\n" +
            ":72:/INS/CHASUS33\n" +
            "-}{5:{MAC:75D138E4}{CHK:DE1B0D71FA96}}";

    public static void main(String[] args) throws Exception {
        TranslateMtToMx mtToMx = new TranslateMtToMx();
        ArrayList<String> data = mtToMx.parseSwiftTo5Block(messageMt202);
        System.out.println(">>>>>>>>>Kiem tra data:\n" + data);

        SwiftMsgProcessor processor = new SwiftMsgProcessor();
        ArrayList<String> block1 = processor.ParseHeaderBlock(data.get(0));
        System.out.println(">>>>>>>>>Kiem tra block 1:\n" + block1);

        ArrayList<String> block2 = processor.parseApplicationHeaderBlock(data.get(1));
        System.out.println(">>>>>>>>>Kiem tra block 2:\n" + block2);

        ArrayList<TagBlock3> block3 = processor.ParseUserHeaderBlock(data.get(2));
        System.out.println(">>>>>>>>>Kiem tra block 3:");
        for (TagBlock3 tb3: block3) {
            System.out.println(tb3.getName() + ":" + tb3.getValue());
        }

        ArrayList<TagBlock4> block4 = processor.ParseTextBlock(data.get(3));
        System.out.println(">>>>>>>>>Kiem tra block 4:");
        for (TagBlock4 tb4: block4) {
            System.out.println(tb4.getFieldName() + ":");
            for(int i = 0; i < tb4.getFieldData().size(); i++) {
                System.out.println(tb4.getFieldData().get(i));
            }
        }

        ArrayList<TagBlock5> block5 = processor.ParseTrailerBlock(data.get(4));
        System.out.println(">>>>>>>>>Kiem tra block 5:");
        for (TagBlock5 tb5: block5) {
            System.out.println(tb5.getName() + ":" + tb5.getValue());
        }
    }

    public SwiftMessage parseMsgStringToObject(String messageString) {
        try {
            if (messageString.matches("(?s).*\\}[\\n\\r]+\\{[1-5]:.*")) {
                logger.error("Not valid format message");
                return null;
            } else {
                // logic code
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> parseSwiftTo5Block(String swiftMessage) {
        ArrayList<String> outData = new ArrayList<>();
        if(!swiftMessage.startsWith("{1:")) {
            logger.error("Basic Header Block is missing");
            return null;
        } else {
            int startblock2 = swiftMessage.indexOf("}{2:");
            if (swiftMessage.contains("{2:}")) {
                startblock2 = -1;
            }

            if (startblock2 != -1) {
                String inOutInd = swiftMessage.substring(startblock2 + 4, startblock2 + 5);
                if (inOutInd.equalsIgnoreCase("I")) {
                    outData = parseSwiftTo5BlocksI(swiftMessage);
                } else {
                    outData = parseSwiftTo5BlocksO(swiftMessage);
                }
            } else {
                if (!swiftMessage.startsWith("{1:F21")) {
                    logger.error("Application Header Block is missing");
                    return null;
                }

                if (swiftMessage.startsWith("{1:F21")) {
                    outData.add(swiftMessage.replaceAll("\\{1:(.*)}\\{4:(.*)}", "{1:$1}"));
                    outData.add(null);
                    outData.add(null);
                    outData.add(swiftMessage.replaceAll("\\{1:(.*)}\\{4:(.*)}", "{4:$2}"));
                    outData.add(null);
                }
            }
        }
        return outData;
    }

    public ArrayList<String> parseSwiftTo5BlocksI(String swiftMessage) {
        String block1 = "";
        String block2 = null;
        ArrayList<String> outData = new ArrayList<>();
        int startBlock1 = swiftMessage.lastIndexOf("{1:");
        int endblock1 = swiftMessage.substring(startBlock1).indexOf("}") + 1;
        block1 = swiftMessage.substring(startBlock1, startBlock1 + endblock1);
        outData.add(block1);

        int startblock2 = swiftMessage.indexOf("}{2:");
        if (swiftMessage.contains("{2:}")) {
            startblock2 = -1;
        }

        int startblock3;
        int startblock4;
        int startblock5;
        if (startblock2 != -1) {
            startblock3 = 0;
            startblock4 = swiftMessage.indexOf("}{3:");
            if (startblock4 != -1) {
                startblock3 = startblock4 + 1;
            } else {
                startblock5 = swiftMessage.indexOf("}{4:", startblock2);
                if (startblock5 != -1) {
                    startblock3 = startblock5 + 1;
                }
            }

            block2 = swiftMessage.substring(startblock2 + 1, startblock3);
            outData.add(block2);
        } else {
            outData.add("");
        }

        startblock3 = swiftMessage.indexOf("}{3:");
        if (swiftMessage.contains("{3:}")) {
            startblock3 = -1;
        }

        String block3;
        if (startblock3 != -1) {
            startblock4 = swiftMessage.indexOf("}}", startblock3 + 2);
            block3 = swiftMessage.substring(startblock3 + 1, startblock4 + 2);
            outData.add(block3);
        } else {
            block3 = "";
            outData.add(block3);
        }

        startblock4 = swiftMessage.indexOf("}{4:", startblock2);
        if (swiftMessage.contains("{4:}")) {
            startblock4 = -1;
        }

        String block4;
        if (startblock4 != -1) {
            startblock5 = swiftMessage.indexOf("-}", startblock4 + 2);
            block4 = swiftMessage.substring(startblock4 + 1, startblock5 + 2);
            outData.add(block4);
        } else {
            block4 = "";
            outData.add(block4);
        }

        startblock5 = swiftMessage.indexOf("}{5:");
        if (swiftMessage.contains("{5:}")) {
            startblock5 = -1;
        }

        String block5;
        if (startblock5 != -1) {
            int endblock5 = swiftMessage.indexOf("}}", startblock5 + 2);
            block5 = swiftMessage.substring(startblock5 + 1, endblock5 + 2);
            outData.add(block5);
        } else {
            block5 = "";
            outData.add(block5);
        }

        return outData;
    }

    public ArrayList<String> parseSwiftTo5BlocksO(String swiftMessage) {
        return null;
    }
}
