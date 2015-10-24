package jp.torihata.songnote;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by torihatayuuji on 2015/10/12.
 */
public class LyricsOpenHelper extends SQLiteOpenHelper {

    // 初期データ
    public String title1 = "音タイム";
    public String body1 = "さぁ　始めよう 音タイム　音タイム　音タイム\n" +
            "さぁ　歌いましょう\n" +
            "音タイム　音タイム　音タイム\n" +
            "\n" +
            "君と僕とは　遠い昔に\n" +
            "出会ってたような　気がしてる\n" +
            "\n" +
            "さぁ　踊りましょう\n" +
            "サンダンス　ムーンダンス　音タイム\n" +
            "\n" +
            "朝が来たなら　朝を歌おう\n" +
            "夜が更けたら　夜の歌を\n" +
            "素敵なメロディー　口ずさめば\n" +
            "思い出せるよ　懐かしきストーリーを\n" +
            "\n" +
            "さぁ　始めよう\n" +
            "音タイム　音タイム　音タイム\n" +
            "さぁ　歌いましょう…\n" +
            "音タイム　音タイム　音タイム\n" +
            "音タイム　音タイム　音タイム\n" +
            "音タイム　音タイム　音タイム";
    public String title2 = "林檎殺人事件";
    public String body2 = "アア 哀しいね 哀しいね \n" +
            "\n" +
            "殺人現場に林檎が落ちていた\n" +
            "がぶりとかじった歯形がついていた \n" +
            "\n" +
            "捜査一課の腕ききたちも\n" +
            "鑑識課員も頭をひねってた\n" +
            "\n" +
            "霧に浮かんだ真赤な林檎\n" +
            "謎が謎よぶ殺人事件\n" +
            "\n" +
            "アア パイプくわえて探偵登場\n" +
            "フニフニフニフニ フニフニフニフニ\n" +
            "男と女の愛のもつれだよ\n" +
            "\n" +
            "アダムとイブが林檎を食べてから\n" +
            "フニフニフニフニ 跡をたたない\n" +
            "\n" +
            "アア 哀しいね 哀しいね\n" +
            "\n" +
            "歯型に三つの虫歯のあとがある\n" +
            "キャンディ好きだとにらんだ探偵は\n" +
            "\n" +
            "聞き込み 張り込み 尾行をつづけ\n" +
            "こいつと信じた男をおびき出す\n" +
            "\n" +
            "闇にまぎれて大きな男\n" +
            "やって来ました殺人現場\n" +
            "\n" +
            "アア パイプくわえて探偵失神\n" +
            "フニフニフニフニ フニフニフニフニ\n" +
            "男と女の愛のもつれだよ\n" +
            "\n" +
            "アダムとイブが林檎を食べてから\n" +
            "フニフニフニフニ 跡をたたない\n" +
            "\n" +
            "アア 哀しいね 哀しいね\n" +
            "\n" +
            "アダムとイブが林檎を食べてから\n" +
            "フニフニフニフニ 跡をたたない\n" +
            "\n" +
            "アア 哀しいね 哀しいね\n";


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
        values.put(Lyrics.COLUMN_NAME_TITLE, title1);
        values.put(Lyrics.COLUMN_NAME_BODY, body1);
        // 戻り値はRowID（_ID）
        // エラーの場合は-1になる
        db.insert(Lyrics.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(Lyrics.COLUMN_NAME_TITLE, title2);
        values.put(Lyrics.COLUMN_NAME_BODY, body2);
        db.insert(Lyrics.TABLE_NAME, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // ここでアップデート条件を判定する
        db.execSQL(TABLE_DELETE);
        onCreate(db);
    }

}
