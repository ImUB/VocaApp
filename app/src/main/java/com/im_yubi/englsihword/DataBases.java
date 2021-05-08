package com.im_yubi.englsihword;

import android.provider.BaseColumns;

public class DataBases {
    public static final class CreateDB implements BaseColumns {
        public static final String e_word = "e_word";
        public static final String k_word = "k_word";
        public static final String _TABLENAME0 = "VocaApp";
        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                +e_word+" text not null , "
                +k_word+" text not null ); ";
    }
}
