package com.epam.totalizator.config;

/**
 * SQL Field constants.
 */
public class SQLFieldConstant {

       public static final String ID = "id";
       public static final String BETS = "bets";
       public static final int NOT_FINISHED_BET = -1;
       public static final int WON_BET = 1;
       public static final int LOST_BET = 0;

       public SQLFieldConstant() { }

       public final class Account {
              public static final String LOGIN = "login";
              public static final String PASSWORD = "password";
              public static final String FIRST_NAME = "first_name";
              public static final String LAST_NAME = "last_name";
              public static final String EMAIL = "email";
              public static final String ROLE = "role";
              public static final String BIRTHDAY = "birthday";
              public static final String CREATE_DATE = "create_date";
              public static final String DOC_NUMB = "doc_numb";
              public static final String PHONE = "phone";
              public static final String DOC_TYPE = "doc_type";
       }

       public final class Bet {
              public static final String BET_ID = "bet_id";
              public static final String AMOUNT_COUNT = "amount_count";
              public static final String EXPECTED_WIN = "expected_win";
              public static final String BET_TYPE = "bet_type";
              public static final String BET_DATE = "bet_date";
              public static final String BET_COEFFICIENT = "bet_coef";
              public static final String RESULT = "result";
              public static final String ACCOUNTS_LOGIN = "accounts_login";
              public static final String CURRENCY = "bet_currency";
       }

       public final class Coefficient {
              public static final String WIN_FIRST = "win_first";
              public static final String WIN_SECOND = "win_second";
              public static final String FIRST_OR_NOBODY = "first_or_nobody";
              public static final String NOBODY = "nobody";
              public static final String FIRST_OR_SECOND = "first_or_second";
              public static final String SECOND_OR_NOBODY = "second_or_nobody";
       }

       public final class Event {
              public static final String EVENT_TYPE = "event_type";
              public static final String KIND_OF_SPORT = "kind_of_sport";
              public static final String DESCRIPTION = "description";
              public static final String START_DATE = "start_date";
              public static final String FIRST_SCORE = "first_score";
              public static final String SECOND_SCORE = "second_score";
              public static final String FIRST_COMPETITOR = "first_competitor";
              public static final String SECOND_COMPETITOR = "second_competitor";
       }

       public final class Invoice {
              public static final String INVOICE_ID = "id";
              public static final String INVOICE_CURRENCY = "inv_currency";
              public static final String BALLANCE = "ballance";
       }

       public final class Transaction {
              public static final String DATE = "date";
              public static final String TYPE = "type";
              public static final String TRANSACTION_TYPE = "tran_type";
              public static final String AMOUNT = "amount";
       }
}

