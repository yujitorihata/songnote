package jp.torihata.songnote;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by torihatayuuji on 2015/10/12.
 */
public class Lyrics implements BaseColumns {
    public static final String TABLE_NAME = "Lyrics";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_BODY = "body";

    private String mTitle;
    private String mBody;

    private static final String AUTHORITY = "jp.torihata.songnote.LyricsContentProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/lyrics");

    //TODO Accesor
}
