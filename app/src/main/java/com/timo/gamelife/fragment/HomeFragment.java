package com.timo.gamelife.fragment;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.timo.gamelife.R;
import com.timo.timolib.BaseTools;
import com.timo.timolib.base_fragment.BaseFragment;
import com.timo.timolib.view.CommonWebView;
import com.timo.timolib.view.FloatTouchView;
import com.timo.timolib.view.TitleBar;
import com.timo.xflib.XFVoiceUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lykj on 2017/9/12.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.bt_icon)
    FloatTouchView mBtIcon;
    @BindView(R.id.drawlayout)
    DrawerLayout mDrawlayout;
    @BindView(R.id.web_mine)
    CommonWebView mWebMine;
    @BindView(R.id.web_title)
    TitleBar mWebTitle;

    @Override
    protected String setTitleName() {
        return "首页";
    }

    @Override
    protected void setTitle(View view) {
        super.setTitle(view);
    }

    @Override
    protected int getContentResId() {
        return R.layout.fragment_dream;
    }

    @Override
    protected void initEvent(View view) {
        mDrawlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        BaseTools.setTitleBar(mWebTitle, "个人介绍", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWebMine.canGoBack()) {
                    mWebMine.goBack();
                } else {
                    mDrawlayout.closeDrawers();
                }
            }
        });

        mDrawlayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                BaseTools.loadWeb(mWebMine, "file:///android_asset/my.html");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                XFVoiceUtils.getInstance().stopSpeak();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        mWebMine.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (url.contains("my.html")) {
                    XFVoiceUtils.getInstance().speakMessage(getContext(), getString(R.string.introduction_a));
                } else if (url.contains("resume.html")) {
                    XFVoiceUtils.getInstance().speakMessage(getContext(), getString(R.string.introduction_b));
                }
            }
        });

    }

    @OnClick({R.id.bt_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_icon:
                mDrawlayout.openDrawer(Gravity.START);
                mDrawlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                mDrawlayout.setScrimColor(Color.TRANSPARENT);
                break;
        }
    }

}