package org.techtown.notepad;

import android.provider.BaseColumns;

public final class memo_warehouse {
    private memo_warehouse() {

    }
    public static class MemoEntry implements BaseColumns {
        public static final String TABLE_NAME = "memo";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_CONTENTS = "contents";

    }
}
