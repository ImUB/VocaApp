package com.im_yubi.englsihword;

import android.provider.BaseColumns;

public final class DataBases {
    public static final class CreateDB implements BaseColumns{
        public static final String E_Word = "e_word";
        public static final String K_Word = "k_word";
        public static final String _TABLENAME1 = "officer";
        public static final String _TABLENAME2 = "sat";
        public static final String _TABLENAME3 = "toeic";
        public static final String _CREATE1 = "create table if not exists "+_TABLENAME1+"("
                +_ID+" integer primary key autoincrement, "
                +E_Word+" text not null, "
                +K_Word+" text not null );";
        public static final String _CREATE2 = "create table if not exists "+_TABLENAME2+"("
                +_ID+" integer primary key autoincrement, "
                +E_Word+" text not null, "
                +K_Word+" text not null );";
        public static final String _CREATE3 = "create table if not exists "+_TABLENAME3+"("
                +_ID+" integer primary key autoincrement, "
                +E_Word+" text not null, "
                +K_Word+" text not null );";
    }
}
