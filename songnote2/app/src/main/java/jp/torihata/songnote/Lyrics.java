package jp.torihata.songnote;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by torihatayuuji on 2015/10/12.
 */
public class Lyrics implements BaseColumns {
    public static final String TABLE_NAME = "lyrics";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_BODY = "body";
    public final static Integer URL_CODE = 1;
    public final static Integer URL_CODE2 = 2;

    private String mTitle;
    private String mBody;

    private static final String AUTHORITY = "jp.torihata.songnote.LyricsContentProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/lyrics");

    //TODO Accesor
}
