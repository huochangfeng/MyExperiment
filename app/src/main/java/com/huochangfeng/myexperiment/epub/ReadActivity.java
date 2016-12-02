package com.huochangfeng.myexperiment.epub;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.huochangfeng.myexperiment.R;
import com.huochangfeng.myexperiment.epub.ReadView.ReadView;
import com.huochangfeng.myexperiment.epub.db.BookInfo;
import com.huochangfeng.myexperiment.epub.db.BookInfoDao;
import com.huochangfeng.myexperiment.epub.db.Chapter;
import com.huochangfeng.myexperiment.epub.db.ChapterDao;
import com.huochangfeng.myexperiment.epub.db.DBManager;

import org.greenrobot.greendao.query.QueryBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.epub.EpubReader;

/**
 * 作者：霍昌峰 on 2016/7/7 16:07<p>
 * 邮箱：553805864@qq.com<p>
 */
public class ReadActivity extends Activity {

    ReadView readView;
    private Book book;
    private StringBuilder sb = new StringBuilder();
    private int present = 90;
    List<Integer> chapter = new ArrayList<>();
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //不显示系统的标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_read);

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);

        InputStream epub_is;
        try {
            epub_is = getAssets().open("quanqiujinhua.epub");
            book = new EpubReader().readEpub(epub_is);
            Log.i("what", "");
            for (int i = 1; i < book.getContents().size(); i++) {
                String s = new String(book.getContents().get(i).getData());

                Document d = Jsoup.parse(s);
                Element b = d.body();
                sb.append(b.html().replace("<br>", "\t    "));
                chapter.add(sb.toString().getBytes().length);
//                Log.i("sbsb", sb.toString().getBytes().length + "");
            }

            Log.i("epublib", "author(s): " + book.getMetadata().getAuthors());

            Log.i("epublib", "title: " + book.getTitle());

            // Log the tale of contents
            Log.i("href", book.getContents().get(0).getHref());

            File f = new File(Environment.getExternalStorageDirectory() + "/小说/quanqiujinhua/quanqiujinhua.txt");
            if (!f.exists()) {
                f.createNewFile();
            }

            try {
                FileWriter fw = new FileWriter(f);
                fw.write(sb.toString());
                fw.flush();
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.i("what", "");

            readView = new ReadView(this, f.getAbsolutePath());

            rl.addView(readView);

            Button button = new Button(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(lp);
            button.setText("向后");
            rl.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    present ++;
//                    readView.setPresent(present);

                    readView.setChapter(chapter.get(page));
                    page++;
//                    recreate();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }


        BookInfo bi = new BookInfo();
        bi.setId(123456);
        bi.setName("听风吹夜雪");
        bi.setAuthor("霍");

        Chapter c1 = new Chapter();
        c1.setName("我嘞");
        c1.setProgress("已读完");
        ChapterDao cd = DBManager.getInstance(this).insert().getChapterDao();
        cd.insert(c1);

        Chapter c = new Chapter();
        c.setName("我擦嘞");
        c.setProgress("已读完");
        cd.insert(c);
        bi.setC(c);


        BookInfo bi2 = new BookInfo();
        bi2.setId(1234567);
        bi2.setName("悠然的旧时光");
        bi2.setAuthor("霍");

        DBManager db_manager = DBManager.getInstance(this);
        db_manager.insertUser(bi);
        db_manager.insertUser(bi2);
//        List<BookInfo> list = db_manager.queryUserList();
        QueryBuilder<BookInfo> qb = db_manager.query();
//        qb.where(BookInfoDao.Properties.Name.eq("悠然的旧时光"));
//        List<BookInfo> list = qb.list();
//        for (BookInfo b : list) {
//            Log.i("id", b.getId() + "");
//            Log.i("name", b.getName());
//            Log.i("author", b.getAuthor());
//        }

        qb.where(BookInfoDao.Properties.Name.eq("听风吹夜雪"));
        List<BookInfo> list2 = qb.list();
        Log.i("list", list2.size() + "");
        for (BookInfo b : list2) {
            Log.i("id", b.getC().getId() + "");
            Log.i("name", b.getC().getName());
            Log.i("author", b.getC().getProgress());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        readView.setOnPause();
    }
}
