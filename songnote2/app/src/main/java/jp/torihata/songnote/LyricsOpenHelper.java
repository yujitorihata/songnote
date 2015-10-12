package jp.torihata.songnote;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by torihatayuuji on 2015/10/12.
 */
public class LyricsOpenHelper extends SQLiteOpenHelper {

    // データーベースのバージョン
    // データベーススキーマを変える場合は、バージョンを上げること
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "SongNote.db";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + Lyrics.TABLE_NAME + " (" +
                    Lyrics._ID + " INTEGER PRIMARY KEY," +
                    Lyrics.COLUMN_NAME_TITLE + " TEXT NOT NULL, " +
                    Lyrics.COLUMN_NAME_BODY + " TEXT);";

    private static final String TABLE_DELETE =
            "DROP TABLE IF EXISTS " + Lyrics.TABLE_NAME;

    public LyricsOpenHelper(Context context) {
        // データベース名、バージョンを指定する
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        db.execSQL(TABLE_CREATE);

        // 初期データの流し込み
        ContentValues values = new ContentValues();
        values.put(Lyrics.COLUMN_NAME_TITLE, "TITLE1");
        values.put(Lyrics.COLUMN_NAME_BODY, "BODY1");

        // 戻り値はRowID（_ID）
        // エラーの場合は-1になる
        long rowId = db.insert(Lyrics.TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // ここでアップデート条件を判定する
        db.execSQL(TABLE_DELETE);
        onCreate(db);
    }

}
