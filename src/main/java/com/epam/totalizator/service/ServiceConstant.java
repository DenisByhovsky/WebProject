package com.epam.totalizator.service;

public class  ServiceConstant {

    public static final class ResultBet {
         public static final int NOT_FINISHED = 0;
         public static final int FINISHED = 1;
         public static final int WON_BET = 1;
         public static final int LOST_BET = 0;
        }

    public static final class ResultType {
         public static final String TYPE_FIRST_VICTORY = "First victory";
         public static final String TYPE_FIRST_OR_NOBODY = "First victory or dead heat";
         public static final String TYPE_FIRST_OR_SECOND = "First or Second victory";
         public static final String TYPE_SECOND_VICTORY = "Second victory";
         public static final String TYPE_SECOND_OR_NOBODY = "Second victory or dead heat";
         public static final String TYPE_DEAD_HEAT = "Dead heat";
        }
}
