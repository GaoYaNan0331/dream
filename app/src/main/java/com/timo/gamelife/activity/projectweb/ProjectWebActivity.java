package com.timo.gamelife.activity.projectweb;


import android.os.Bundle;
import android.view.View;

import com.timo.gamelife.R;
import com.timo.gamelife.mvp.MVPBaseActivity;
import com.timo.timolib.BaseTools;
import com.timo.timolib.view.CommonWebView;
import com.timo.timolib.view.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ProjectWebActivity extends MVPBaseActivity<ProjectWebContract.View, ProjectWebPresenter> implements ProjectWebContract.View {

    @BindView(R.id.webview)
    CommonWebView mWebview;
    @BindView(R.id.title)
    TitleBar mTitle;

    @Override
    protected int getContentResId() {
        return R.layout.activity_project;
    }

    @Override
    protected String setTitleName() {
        return getString(R.string.title_project);
    }

    @Override
    protected void initEvent(Bundle savedInstanceState) {
        BaseTools.setTitleBar(mTitle, getString(R.string.title_project), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        BaseTools.loadWeb(mWebview, "https://fir.im");
    }

}
