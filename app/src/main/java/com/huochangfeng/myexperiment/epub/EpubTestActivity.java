package com.huochangfeng.myexperiment.epub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.huochangfeng.myexperiment.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.TOCReference;
import nl.siegmann.epublib.epub.EpubReader;

/**
 * 作者：霍昌峰 on 2016/7/6 16:10<p>
 * 邮箱：553805864@qq.com<p>
 */
public class EpubTestActivity extends AppCompatActivity {


    private TextView tv;
    private ImageView iv;
    private WebView wv;
    private int page = 1;
    private StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epub_test);

        tv = (TextView) findViewById(R.id.tv);
        iv = (ImageView) findViewById(R.id.iv);
        wv = (WebView) findViewById(R.id.wv);

        try {
            InputStream epub_is = getAssets().open("quanqiujinhua.epub");
            final Book book = new EpubReader().readEpub(epub_is);

            Log.i("epublib", "author(s): " + book.getMetadata().getAuthors());

            Log.i("epublib", "title: " + book.getTitle());

            // Log the tale of contents
            Log.i("href", book.getContents().get(0).getHref());

//            book.getResources().getById("cover").getData();

            String str = new String(book.getContents().get(page).getData());
//            tv.setText(str);

            Document document = Jsoup.parse(str);
            Element body =document.body();
            String html = body.toString();


            int index = 0;
            Resource byIndex = book.getSpine().getResource(index);
            CharSequence cs = Html.fromHtml(str);
            tv.setText(body.html().replace("<br>","\t    "));
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        page ++ ;
                        CharSequence cs = Html.fromHtml(new String(book.getContents().get(page).getData()));
                        tv.setText(cs);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            wv.getSettings().setJavaScriptEnabled(true);
//            wv.loadDataWithBaseURL(null,str,"text/html","utf-8",null);
//            wv.loadData(,"text/html","utf-8");

//            File f = new File(Environment.getExternalStorageDirectory() + "/小说/quanqiujinhua.epub");
//            Toast.makeText(this, "" + f.exists(), Toast.LENGTH_SHORT).show();
//
//            wv.loadDataWithBaseURL(f.getAbsolutePath(), Arrays.toString(book.getContents().get(0).getData()), "text/html", "UTF-8", null);

            logTableOfContents(book.getTableOfContents().getTocReferences(), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logTableOfContents(List<TOCReference> tocReferences, int depth) {

        if (tocReferences == null) {

            return;

        }

        for (TOCReference tocReference : tocReferences) {

            StringBuilder tocString = new StringBuilder();

            for (int i = 0; i < depth; i++) {

                tocString.append("\t");

            }

            tocString.append(tocReference.getTitle());

            Log.i("epublib", tocString.toString());


            logTableOfContents(tocReference.getChildren(), depth + 1);

        }

    }

}
