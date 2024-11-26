package com.ctfs.framework;

import org.apache.commons.lang3.ArrayUtils;

public class ColorConstants {
    public static String[] RED = {
            "rgba(217, 0, 8, 1)",
            "rgba(255, 0, 0, 1)",
            "rgba(252, 217, 218, 1)",
            "rgba(246, 29, 29, 1)",
            "rgba(237, 28, 36, 1)",
            "rgba(237, 28, 35, 1)",
            "rgba(216, 17, 24, 1)",
            "rgba(239, 50, 57, 1)",
            "rgba(213, 43, 30, 1)",
            "rgba(228, 77, 66, 1)",
            "rgb(217, 0, 8)",
            "rgb(216, 30, 5)",
            "rgba(216, 30, 5, 1)",
            "rgb(255, 0, 0)",
            "rgb(216, 17, 24)",
            "rgb(252, 217, 218)",
            "rgb(234, 38, 47)",
            "rgb(238, 50, 57)",
            "rgb(213, 43, 30)",
            "rgba(234, 38, 47, 1)",
            "rgba(231, 19, 26, 1)",
            "rgba(231, 19, 26, 1)",
            "rgba(85, 85, 85, 1)",
            "gb(0, 0, 0)",
            "rgb(193, 17, 24)",
            "rgb(204, 204, 204)",
            "rgba(216, 30, 5, 1)"
    };

    public static String[] ORANGE = {
            "rgba(245, 164, 35, 1)",
            "rgba(255, 165, 0, 1)"
    };

    public static String[] TEAL = {
            "rgba(120,223,212, 1)",
            "rgb(120,223,212)",
            "rgba(245, 164, 35, 1)",
            "rgba(255, 165, 0, 1)"
    };

    public static String[] GREEN = {
    		"rgba(219, 219, 219, 1)",
    		"rgba(230, 230, 230, 1)",
    		"rgba(0, 138, 32, 1)",
            "rgba(17, 143, 67, 1)",
            "rgba(0, 162, 70, 1)",
            "rgba(13, 131, 60, 1)",
            "rgba(92, 184, 92, 1)",
            //"rgba(237, 28, 36, 1)",
            "rgba(0, 166, 81, 1)",
            "rgba(0, 0, 0, 0)",
            "rgba(141, 198, 63, 1)",
            "rgb(16, 132, 62)",
            "rgb(0, 179, 86)",
            "rgb(0, 137, 67)",
            "rgba(0, 137, 67, 1)",
            "rgb(13, 131, 60)",
            "rgb(120, 223, 213)",
            "rgba(255, 255, 255, 1)",
            "rgba(24, 142, 2, 1)",
            "rgba(29, 143, 67, 1)"
    };

    public static String[] PINK = {
            "rgba(252, 217, 218, 1)",
            "rgb(252, 217, 218)"
    };

    public static String[] BLUE = {
            "rgb(19, 112, 197)",
            "rgba(19, 112, 197, 1)"
    };

    public static String[] YELLOW = {
            "rgb(240, 176, 13)",
            "rgba(240, 176, 13, 1)"
    };

    public static String[] BRIGHT_YELLOW = {
            "rgb(255, 255, 0)"
    };

    public static String[] DARK_GREY = {
            "rgba(93, 93, 93, 1)",
            "rgba(104, 103, 99, 1)", 
            "rgba(51, 51, 51, 1)",
            "rgb(170, 170, 170)"
    };

    public static String[] GREY = {
            "rgb(95, 98, 97)",
            "rgb(41, 41, 41)",
            "rgba(41, 41, 41, 1)",
            "rgba(68, 63, 60, 1)",
            "rgba(104, 103, 99, 1)",
            "rgba(228, 77, 66, 1)",
            "rgba(128, 128, 128, 1)",
            "rgba(138, 138, 138, 1)",
            "rgb(138, 138, 138)",
            "rgba(111, 111, 111, 1)",
            "rgba(85, 85, 85, 1)",
            "rgb(150, 150, 150)",
            "rgb(204, 204, 204)",
            "rgba(150, 150, 150, 1)",
            "rgba(222, 222, 222, 1)"
    };

    public static String[] ASH = {
            "rgb(195, 187, 166)",
            "rgb(207, 206, 205)",
            "rgb(130, 126, 119)",
            "rgb(90, 90, 90)",
            "rgba(130, 126, 119, 1)"
    };

    public static String[] BLACK = {
            "rgb(21, 21, 21)",
            "rgb(36, 36, 36)",
            "rgba(42, 38, 35, 1)",
            "rgba(0, 0, 0, 1)",
            "rgba(21, 21, 21, 1)",
            "rgba(36, 36, 36, 1)"
    };
    
    public static String [] WHITE = {
    		"rgba(0, 0, 0, 1)",
    		"rgb(255, 255, 255)",
    		"rgba(255, 255, 255, 1)"
    };
    
    public static String [] REDorORANGE = null;
    static {
    	REDorORANGE = ArrayUtils.addAll(RED, ORANGE);
    }

    public static String[] getConstantValueForColor(String color) {
        if (color.equalsIgnoreCase("black")) {
            return ColorConstants.BLACK;
        } else if (color.equalsIgnoreCase("grey")) {
            return ColorConstants.GREY;
        } else if (color.equalsIgnoreCase("red")) {
            return ColorConstants.RED;
        }else if  (color.equalsIgnoreCase("green")) {
            return ColorConstants.GREEN;
        } else if (color.equalsIgnoreCase("yellow")) {
            return ColorConstants.YELLOW;
        } else if (color.equalsIgnoreCase("orange")) {
            return ColorConstants.ORANGE;
        } else if (color.equalsIgnoreCase("DarkGrey")) {
            return ColorConstants.DARK_GREY;
        } else if (color.equalsIgnoreCase("Teal")) {
            return ColorConstants.TEAL;
        } else if (color.equalsIgnoreCase("Ash")) {
            return ColorConstants.ASH;
        } else if (color.equalsIgnoreCase("Pink")) {
            return ColorConstants.PINK;
        } else if (color.equalsIgnoreCase("Blue")) {
            return ColorConstants.BLUE;
        } else if (color.equalsIgnoreCase("BrightYellow")) {
            return ColorConstants.BRIGHT_YELLOW;
        } else if (color.equalsIgnoreCase("White")) {
            return ColorConstants.WHITE;
        } else if (color.equalsIgnoreCase("RedOrOrange")) {
            return ColorConstants.REDorORANGE;
        } else {
            return new String[]{};
        }
    }
}
