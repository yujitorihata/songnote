package jp.torihata.songnote;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by torihatayuuji on 2015/10/12.
 */
public class LyricsContentProvider extends ContentProvider{

    private LyricsOpenHelper mLyricsOpenHelper;

    // 利用者がメソッドを呼び出したURIに対応する処理を判定処理に使用します
    private static final UriMatcher URI_MATCHER;
    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(Lyrics.CONTENT_URI.getAuthority(), Lyrics.TABLE_NAME, Lyrics.URL_CODE);
        URI_MATCHER.addURI(Lyrics.CONTENT_URI.getAuthority(), Lyrics.TABLE_NAME + "/#", Lyrics.URL_CODE2);
    }
    @SuppressWarnings("unused")
    private static final String TAG = LyricsContentProvider.class.getSimpleName();

    // アプリケーション起動時にメインスレッド上で呼ばれます。そのため、時間がかかる処理は行うのは禁止されています。
    // ここで必要な初期化処理を行います。
    @Override
    public boolean onCreate() {
        mLyricsOpenHelper = new LyricsOpenHelper(getContext());
        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        isValidUri(uri);

        SQLiteDatabase db = mLyricsOpenHelper.getWritableDatabase();
        int deletedCount = db.delete(Lyrics.TABLE_NAME, selection, selectionArgs);
        // 設定したURIのデータに変更があったことを通知します
        getContext().getContentResolver().notifyChange(uri, null);
        return deletedCount;
    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        isValidUri(uri);

        SQLiteDatabase db = mLyricsOpenHelper.getWritableDatabase();
        long rowId = db.insert(Lyrics.TABLE_NAME, null, values);
        // 追加された行のURIを生成。
        Uri insertedUri = ContentUris.withAppendedId(uri, rowId);
        // 設定したURIのデータに変更があったことを通知します
        getContext().getContentResolver().notifyChange(insertedUri, null);
        return insertedUri;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        isValidUri(uri);

        SQLiteDatabase db = mLyricsOpenHelper.getReadableDatabase();
        // URIからテーブル名を取得
        String tableName = uri.getPathSegments().get(0);

        Integer result = URI_MATCHER.match(uri);
        switch (result) {
            case 1:
                return db.query(tableName, projection, selection, selectionArgs, null, null, sortOrder);

            case 2:
                return db.query(tableName, projection, "_ID = ?", new String[]{uri.getLastPathSegment()}, null, null, sortOrder);

            default:
                return null;
        }

        // 設定したURIの変更を監視するように設定
        //cursor.setNotificationUri(getContext().getContentResolver(), uri);
        //return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        isValidUri(uri);

        SQLiteDatabase db = mLyricsOpenHelper.getWritableDatabase();
        // URIからテーブル名を取得
        String tableName = uri.getPathSegments().get(0);
        int updatedCount = db.update(tableName, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return updatedCount;
    }

    // このContentProviderで使用可能なURIかを判定します。
    // 使用不可の場合はIllegalArgumentExceptionを投げます。
    private void isValidUri(Uri uri) {
        if (URI_MATCHER.match(uri) != Lyrics.URL_CODE && URI_MATCHER.match(uri) != Lyrics.URL_CODE2) {
            throw new IllegalArgumentException("Unknown URI : " + uri + URI_MATCHER.match(uri));
        }
    }

}
