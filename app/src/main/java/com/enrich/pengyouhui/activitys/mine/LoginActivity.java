package com.enrich.pengyouhui.activitys.mine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.enrich.pengyouhui.R;
import com.enrich.pengyouhui.activitys.CommonWebActivity;
import com.enrich.pengyouhui.activitys.base.BaseActivity;
import com.enrich.pengyouhui.bean.event.CommonPostBean;
import com.enrich.pengyouhui.utils.Constants;
import com.enrich.pengyouhui.utils.LogUtils;
import com.enrich.pengyouhui.utils.NetWorkHelper;
import com.enrich.pengyouhui.utils.PYHUrls;
import com.enrich.pengyouhui.utils.SharedPreferencesUtils;
import com.enrich.pengyouhui.utils.StringUtils;
import com.enrich.pengyouhui.views.CircleImageView;
import com.loopj.android.http.RequestParams;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * @describe 登陆界面
 * @date 2016年9月9日
 */
public class LoginActivity extends BaseActivity {

    @InjectView(R.id.civ_head)
    CircleImageView mCivHead;
    @InjectView(R.id.et_user_mobile)
    EditText userName;
    @InjectView(R.id.et_user_pwd)
    EditText password;
    @InjectView(R.id.tv_forget_pwd)
    TextView mTvForgetPwd;
    @InjectView(R.id.tv_login)
    TextView mTvLogin;
    @InjectView(R.id.tv_hhang)
    TextView mTvHhang;
    @InjectView(R.id.tv_weixin)
    TextView mTvWeixin;
    @InjectView(R.id.tv_qq)
    TextView mTvQq;
    @InjectView(R.id.tv_sina)
    TextView mTvSina;
    private long timePre = 0;
    private RequestParams params;//网络请求参数

    private SHARE_MEDIA platform;//友盟枚举类
    private UMShareAPI umShareAPI;
    private String openid;//
    private String unionid;
    private String token;
    private String nickname = "";
    private String headimg;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0://海航
                    if (System.currentTimeMillis() - timePre > 1000) {
                        Intent hnLoginIntent = new Intent(mContext, CommonWebActivity.class);
                        hnLoginIntent.putExtra(Constants.STR_URL, PYHUrls.getHNLoginUrl());
                        hnLoginIntent.putExtra(Constants.TITLE, "海航登陆");
                        hnLoginIntent.putExtra("type", 3);
                        EventBus.getDefault().post(new CommonPostBean(Constants.EventPostCode.THRID_LOGIN_HN, null));
                        startActivity(hnLoginIntent);
                        timePre = System.currentTimeMillis();
                    }
                    break;
                case 1://新浪
                    if (System.currentTimeMillis() - timePre > 1000) {
                        platform = SHARE_MEDIA.SINA;
                        third2Login(platform);
                        timePre = System.currentTimeMillis();
                    }
                    break;
                case 2://QQ
                    if (System.currentTimeMillis() - timePre > 1000) {
                        platform = SHARE_MEDIA.QQ;
                        third2Login(platform);
                        timePre = System.currentTimeMillis();
                    }
                    break;
                case 3://微信
                    if (System.currentTimeMillis() - timePre > 2000) {
                        platform = SHARE_MEDIA.WEIXIN;
                        third2Login(platform);
                        timePre = System.currentTimeMillis();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        initView();
        initDate();
        initEvent();
    }

    @Override
    public void onLayout() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initView() {
        //        umShareAPI = UMShareAPI.get(mContext);
    }

    @Override
    public void initDate() {

    }

    @Override
    public void initEvent() {

    }

    /**
     * 登录
     */
    private void doLogin() {
        if (userName.getText() == null || StringUtils.isEmpty(userName.getText().toString().trim())) {
            showToast(R.string.str_login_infoisnull_notify);
            return;
        }
        if (!StringUtils.isPhoneNumberValid(userName.getText().toString().trim())) {
            showToast(R.string.str_register_phone_err);
            return;
        }
        if (password.getText() == null || StringUtils.isEmpty(password.getText().toString().trim())) {
            showToast(R.string.str_login_infoisnull_notify);
            return;
        }
        if (NetWorkHelper.isNetworkAvailable(mContext)) {

            params = new RequestParams();
            String userAccount = userName.getText().toString().trim();
            String passwordStr = password.getText().toString().trim();
            SharedPreferencesUtils.getInstance().setValue(Constants.UserInfo.USER_ACCOUNT, userAccount);
            params.put("username", userAccount);
            params.put("password", passwordStr);
            //            CoreHttpClient.post(PYHUrls.getLoginUrl(), params, this,
            //                    Constants.REQUEST_TYPE.LOGIN);
            showLoad(null);
        } else {
            showToast(getString(R.string.str_base_network_no));
        }
    }

    /**
     * 第三方登录
     *
     * @param platform
     */
    private void third2Login(SHARE_MEDIA platform) {
        if (platform != null) {
            umShareAPI.doOauthVerify(mActivity, platform, new UMAuthListener() {
                @Override
                public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                    LogUtils.e(share_media + "授权完成->" + map.toString());
                    if (share_media == SHARE_MEDIA.SINA) {
                        getThridInfo(map, share_media);
                    }
                    umShareAPI.getPlatformInfo(mActivity, share_media, new UMAuthListener() {
                        @Override
                        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                            LogUtils.e(share_media + "个人信息->" + map.toString());
                            if (map != null) {
                                switch (share_media) {
                                    case SINA:
                                        try {
                                            if (map.containsKey("result")) {
                                                JSONObject jsonObject = new JSONObject(map.get("result"));
                                                headimg = jsonObject.optString("profile_image_url");
                                                if (jsonObject.has("screen_name") && StringUtils.isEmpty(nickname)) {
                                                    nickname = jsonObject.optString("screen_name");
                                                }
                                                if (jsonObject.has("name") && StringUtils.isEmpty(nickname)) {
                                                    nickname = jsonObject.optString("name");
                                                }
                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    case QQ:
                                        getThridInfo(map, share_media);
                                        break;
                                    case WEIXIN:
                                        getThridInfo(map, share_media);
                                        break;
                                }
                            }
                            if (StringUtils.isEmpty(unionid)) {
                                unionid = openid;
                            }
                            if (StringUtils.isEmpty(token)) {
                                token = openid;
                            }
                            //                            HealthApplication.getInstance().setUsername(String.valueOf(nickname));
                            //                            HealthApplication.getInstance().setHeaderimg(String.valueOf(headimg));
                            //                            HealthApplication.getInstance().setUnionid(unionid);
                            diSanFangLogin();
                        }

                        @Override
                        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                            LogUtils.e(share_media + "个人信息获取失败->" + throwable.getMessage());
                        }

                        @Override
                        public void onCancel(SHARE_MEDIA share_media, int i) {
                            LogUtils.e(share_media + "取消获取个人信息->" + share_media);
                        }
                    });
                }

                @Override
                public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                    showToast(share_media + "授权失败！错误信息" + throwable.getMessage());
                }

                @Override
                public void onCancel(SHARE_MEDIA share_media, int i) {
                    showToast(share_media + "授权取消");
                }
            });

        }
    }

    /**
     * 第三方登录
     */
    private void diSanFangLogin() {
        params = new RequestParams();
        params.put("openid", String.valueOf(openid));
        params.put("unionid", String.valueOf(unionid));
        params.put("token", String.valueOf(token));
        params.put("headimg", String.valueOf(headimg));
        params.put("nickname", String.valueOf(nickname));
        //        CoreHttpClient.post(PYHUrls.getThridLoinUrl(), params, this,
        //                Constants.REQUEST_TYPE.THIRD_LOGIN);
    }

    private void getThridInfo(Map<String, String> info, SHARE_MEDIA shareMedia) {
        if (info != null && shareMedia != null) {
            switch (shareMedia) {
                case WEIXIN:
                    if (info.containsKey("unionid") && StringUtils.isEmpty(unionid)) {
                        unionid = info.get("unionid").toString();
                    }
                    if (info.containsKey("openid") && StringUtils.isEmpty(openid)) {
                        openid = info.get("openid").toString();
                    }
                    if (info.containsKey("nickname") && StringUtils.isEmpty(nickname)) {
                        nickname = info.get("nickname").toString();
                    }
                    if (info.containsKey("headimgurl") && StringUtils.isEmpty(headimg)) {
                        headimg = info.get("headimgurl").toString();
                    }
                    break;
                case QQ:
                    if (info.containsKey("uid") && StringUtils.isEmpty(unionid)) {
                        unionid = info.get("uid");
                    }
                    if (info.containsKey("openid") && StringUtils.isEmpty(openid)) {
                        openid = info.get("openid").toString();
                    }
                    if (info.containsKey("screen_name") && StringUtils.isEmpty(nickname)) {
                        nickname = info.get("screen_name").toString();
                    }
                    if (info.containsKey("profile_image_url")) {
                        headimg = info.get("profile_image_url").toString();
                    }
                    break;
                case SINA:
                    if (info.containsKey("uid")) {
                        openid = info.get("uid").toString();
                    }
                    if (info.containsKey("userName")) {
                        nickname = info.get("userName").toString();
                    }
                    if (info.containsKey("profile_image_url")) {
                        headimg = info.get("profile_image_url").toString();
                    }
                    if (info.containsKey("access_token")) {
                        token = info.get("access_token").toString();
                    }
                    break;
            }
        }
    }

    @OnClick({R.id.tv_forget_pwd, R.id.tv_login, R.id.tv_hhang, R.id.tv_weixin, R.id.tv_qq, R.id.tv_sina
            , R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.tv_forget_pwd://忘记密码
                Intent forgetIntent = new Intent(mContext, RegisterActivity.class);
                forgetIntent.putExtra(Constants.IntentTag.PWD_FLAG, "1");
                startActivity(forgetIntent);
                break;
            case R.id.tv_login:
                break;
            case R.id.tv_hhang:
                mHandler.sendEmptyMessageDelayed(0, 100);
                break;
            case R.id.tv_weixin:
                mHandler.sendEmptyMessageDelayed(3, 100);
                break;
            case R.id.tv_qq:
                mHandler.sendEmptyMessageDelayed(2, 100);
                break;
            case R.id.tv_sina:
                mHandler.sendEmptyMessageDelayed(1, 100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        umShareAPI.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
