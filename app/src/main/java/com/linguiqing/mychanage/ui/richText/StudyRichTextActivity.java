package com.linguiqing.mychanage.ui.richText;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;

import org.xml.sax.XMLReader;

import butterknife.BindView;

/**
 * ***************************************
 * statement: 富文本的使用
 * auther: lingguiqin
 * date created : 2017/3/19 0019
 * ***************************************
 */

public class StudyRichTextActivity extends BaseActivity {

    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_study_rich_text;
    }

    @Override
    public View addCommonTitlebar(RelativeLayout root) {
        Titlebar titlebar = new Titlebar(this, root);
        titlebar.initTitlebar(true, "富文本的使用", "", null);
        return titlebar;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setRichText1();
        setRichText2();
        setRichText3();
    }


    /**
     * 设置丰富的文本1
     */
    private void setRichText1() {
        String text1 = "姓名：张三";
        SpannableString spannableString = new SpannableString(text1);
        int start = text1.indexOf("姓");
        int end = text1.indexOf(":") + 1; // 包含头，不包含尾，需要+1

        // 设置 字体大小
        spannableString.setSpan(new AbsoluteSizeSpan(18, true), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(14, true), 3, text1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置背景颜色
        spannableString.setSpan(new BackgroundColorSpan(getResources().getColor(R.color.orange)), 3, text1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置前景（字体）颜色
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置下划线
        spannableString.setSpan(new UnderlineSpan(), 3, text1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        textView1.setText(spannableString);
    }

    private void setRichText2() {
        String text2 = "百度http://www.baidu.com"
                + "\n13657972950"
                + "背景"
                + "前景"
                + "删除线"
                + "下划线"
                + "H1H2H3H4H5H6H7H8"
                + "X3X2X1"
                + "图片"
                + "字体"
                + "正常字体";
        SpannableString ss = new SpannableString(text2);
        // 设置URL 链接
        ss.setSpan(new URLSpan("http://www.baidu.com"), 0, 23, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(new URLSpan("tel:13657972950"), 23, 34, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        // 设置背景
        ss.setSpan(new BackgroundColorSpan(Color.BLUE), 34, 36, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        // 设置前景
        ss.setSpan(new ForegroundColorSpan(Color.GREEN), 36, 38, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        // 设置删除线
        ss.setSpan(new StrikethroughSpan(), 38, 41, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        // 设置下划线
        ss.setSpan(new UnderlineSpan(), 41, 44, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        // 设置文本大小
        // 绝对尺寸
        ss.setSpan(new AbsoluteSizeSpan(20, true), 44, 46, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(new AbsoluteSizeSpan(18, true), 46, 48, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(new AbsoluteSizeSpan(16, true), 48, 50, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(new AbsoluteSizeSpan(14, true), 50, 52, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        // 相对尺寸
        ss.setSpan(new RelativeSizeSpan(4), 52, 54, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(new AbsoluteSizeSpan(3, true), 54, 56, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(new AbsoluteSizeSpan(2, true), 56, 58, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(new AbsoluteSizeSpan(1, true), 58, 60, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        // 设置X方向的改变
        ss.setSpan(new ScaleXSpan(3), 60, 62, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ScaleXSpan(2), 62, 64, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ScaleXSpan(1), 64, 66, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置图片
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0, 0, 40, 40);
        ss.setSpan(new ImageSpan(drawable), 66, 68, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置字体
        ss.setSpan(new TypefaceSpan("monospace"), 66, 68, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        // 设置点击事件
        ss.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                showToast("我是正常字体哦", Toast.LENGTH_SHORT);
            }
        }, 68, 72, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.BLUE), 68, 72, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView2.setText(ss);
        // 设置超链接可以点击
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * 使用Html文字
     * Html类用于将HTML字符串处理成可显示的格式化文字，
     * 但是并不是所有的标签都支持。那么我们只需要编辑Html文本，
     * 再设置给TextView显示就行了。
     **/
    private void setRichText3() {
        String html = "<a href=\"http://www.baidu.com\">百度</a>"
                + " <a href=\"tel:13007147721\">13007147721</a>"
                + " <h1>H 1标题<h1>"
                + " <h2>H 1标题<h2>"
                + " <h3>H 1标题<h3>"
                + " <h4>H 1标题<h4>"
                + "<img src=\"ic_launcher.png\"><img>";

        // 处理 html 加载图片
        Spanned htmlSpanned = Html.fromHtml(html, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                return drawable;
            }
        }, new Html.TagHandler() {
            @Override
            public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {

            }
        });

        // 图片会加载不进来
        // textView3.setText(Html.fromHtml(html));
        textView3.setText(htmlSpanned);
        // 设置链接可以被点击
        textView3.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
