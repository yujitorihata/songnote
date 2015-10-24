package jp.torihata.songnote;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter mSimpleCursorAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView1);
        // UIにバインドするデータのカラム名
        String[] from = {
                Lyrics.COLUMN_NAME_TITLE
        };
        // 指定したカラムのデータを表示するViewのIDを指定します。
        int[] to = {
                android.R.id.text1
        };

        // 第3引数のCursorはコールバックで設定されるのでnullを渡しています
        mSimpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_1, null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mListView.setAdapter(mSimpleCursorAdapter);

        // ローダの管理をするオブジェクト
        LoaderManager loaderManager = getLoaderManager();
        // ローダを初期化して非同期処理を開始する
        loaderManager.initLoader(0, null, this);

        // リスト項目がクリックされた時の処理
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                //final String strData = mSimpleCursorAdapter.getItem(position);

                Intent intent = new Intent();

                switch (position) {
                    case 0:
                        intent.setClass(MainActivity.this, Detail1.class);
                        break;
                }
                intent.putExtra("POSITION", position);
                //intent.putExtra("SELECTED_DATA", strData);
                startActivity(intent);
            }
        });


    }

    /*
    private void insert() {
        ContentValues values = new ContentValues();
        for (int i = 0; i < 3; i++) {
            values.clear();
            values.put(Book.COLUMN_NAME_BOOK_TITLE, "TITLE" + i);
            values.put(Book.COLUMN_NAME_BOOK_PUBLISHER, "PUBLISHER" + i);
            values.put(Book.COLUMN_NAME_BOOK_PRICE, "PRICE" + i);

            getContentResolver().insert(Book.CONTENT_URI, values);
        }
    }
    */

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // ここでデータの取得条件の指定が可能です。
        // CursorLoader (Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
        return new CursorLoader(this, Lyrics.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor c) {
        // 古いCursorと新しいCursorを入れ替えます。そのため最新のデータが表示されます。
        mSimpleCursorAdapter.swapCursor(c);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursor) {
        // 古いCursorと新しいCursorを入れ替えます。そのため最新のデータが表示されます。
        mSimpleCursorAdapter.swapCursor(null);
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
