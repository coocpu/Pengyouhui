package com.enrich.pengyouhui.activitys.mine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enrich.pengyouhui.R;
import com.enrich.pengyouhui.activitys.base.BaseActivity;
import com.enrich.pengyouhui.utils.Constants;
import com.enrich.pengyouhui.utils.NetWorkHelper;
import com.enrich.pengyouhui.utils.StringUtils;
import com.loopj.android.http.RequestParams;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @describe 注册页面
 * @date 2016年9月9日
 */
public class RegisterActivity extends BaseActivity {

    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.et_user_mobile)
    EditText phoneEditText;
    @InjectView(R.id.et_verification_code)
    EditText mEtVerificationCode;
    @InjectView(R.id.tv_register_getcode)
    TextView tvGetCode;
    @InjectView(R.id.et_input_pwd)
    EditText passwordEditText;
    @InjectView(R.id.et_input_repwd)
    EditText verifyPasswordEditText;
    @InjectView(R.id.tv_register)
    TextView mTvRegister;
    @InjectView(R.id.et_register_imgcode)
    EditText etImgCode;
    @InjectView(R.id.iv_register_imgcode)
    ImageView ivImgCode;
    @InjectView(R.id.id_layout_0)
    LinearLayout layoutImgCode;
    @InjectView(R.id.iv_title_back)
    ImageView mIvTitleBack;

    private int flag = 0;//注册与确认修改的标志
    private long timePre;
    private int seconds = 60;
    private String imgCodeUrl;
    private static boolean isSendingMessageCode = false;
    private String mobile;//手机号码
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    tvGetCode.setText("再次获取(" + (--seconds) + ")");
                    if (seconds <= 0) {
                        isSendingMessageCode = false;
                        tvGetCode.setEnabled(true);
                        tvGetCode.setText(R.string.str_get_verification_code);
                    }
                    break;
                case 1:
                    if (System.currentTimeMillis() - timePre >= 2000) {
                        timePre = System.currentTimeMillis();
                        getImgCodeBitmap();
                    }
                    break;
            }
        }
    };

    private void getImgCodeBitmap() {
        if (StringUtils.isEmpty(imgCodeUrl) || StringUtils.isEmpty(mobile))
            return;
        //        CoreHttpClient.getImg(imgCodeUrl + "?mobile=" + mobile, this, Constants.REQUEST_TYPE.GET_IMAGE_CODE_IMG);
    }

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
        setContentView(R.layout.activity_register);
    }

    @Override
    public void initView() {
        mTvTitle.setText(getResources().getString(R.string.str_register));
        mIvTitleBack.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        if (intent != null) {
            if (StringUtils.isEqual(intent.getStringExtra(Constants.IntentTag.PWD_FLAG), "1")) {
                mTvTitle.setText(getString(R.string.str_login_forget_pwd_1));
                mTvRegister.setText(getString(R.string.str_frogetpassword_submit));
                flag = 1;
            }
        }
    }

    @Override
    public void initDate() {

    }

    @Override
    public void initEvent() {

    }

    //注册
    protected void doRegister() {
        if (phoneEditText.getText() == null || StringUtils.isEmpty(phoneEditText.getText().toString())) {
            showToast(R.string.str_forgetpassword_phone_null);
            return;
        }
        if (!StringUtils.isPhoneNumberValid(phoneEditText.getText().toString())) {
            showToast(R.string.str_register_phone_err);
            return;
        }
        mobile = phoneEditText.getText().toString().trim();
        if (!passwordEditText.getEditableText().toString().equals(verifyPasswordEditText.getEditableText().toString())) {
            showToast(R.string.str_forgetpassword_pass_ununite);
            return;
        }
        //        params = new HashMap<>();
        //        //        params = new RequestParams();
        //        params.put("customerUsername", phoneEditText.getEditableText().toString());
        //        params.put("customerPassword", passwordEditText.getEditableText().toString());
        //        params.put("code", verifyEditText.getEditableText().toString());
        //		params.put("cusId", "1");

        if (NetWorkHelper.isNetworkAvailable(mContext)) {
            //            CoreHttpClient.post(HKUrls.getRegisterUrl(), params, this, Constants.REQUEST_TYPE.REGISTER);
            //            putAsyncTask(HttpTaskUtils.requestPost(params, this, true, HKUrls.getRegisterUrl(), Constants.REQUEST_TYPE.REGISTER));
        } else {
            showToast(getString(R.string.str_base_network_no));
        }
    }

    /**
     * 发送验证码
     */
    protected void doSendVerify() {
        if (phoneEditText.getText() == null || StringUtils.isEmpty(phoneEditText.getText().toString())) {
            showToast(R.string.str_forgetpassword_phone_null);
            return;
        }
        if (!StringUtils.isPhoneNumberValid(phoneEditText.getText().toString())) {
            showToast(R.string.str_register_phone_err);
            return;
        }
        mobile = phoneEditText.getText().toString().trim();
        RequestParams param = new RequestParams();
        param.put("mobile", mobile);
        if (!StringUtils.isEmpty(imgCodeUrl)) {
            if (etImgCode.getText() == null || StringUtils.isEmpty(etImgCode.getText().toString().trim())) {
                showToast(R.string.str_register_imgcode_null);
                return;
            }
            param.put("captcha", etImgCode.getText().toString().trim());
        }
        if (NetWorkHelper.isNetworkAvailable(mContext)) {
            //            CoreHttpClient.post(HKUrls.getMobileCodeUrl(), param, this, Constants.REQUEST_TYPE.SEND_VERIFY);
        } else {
            showToast(getString(R.string.str_base_network_no));
        }
    }

    @OnClick({R.id.iv_title_back, R.id.tv_register, R.id.iv_register_imgcode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                this.finish();
                break;
            case R.id.tv_register:
                if (flag == 0) {
                    doRegister();
                } else if (flag == 1) {
                    //忘记密码操作
                }
                break;
            case R.id.iv_register_imgcode://刷新图片验证码
                handler.sendEmptyMessageDelayed(1, 200);
                break;
            default:
                break;
        }
    }

    /**
     * 在验证码发送成功时调用
     */
    private void timeStart() {
        tvGetCode.setEnabled(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isSendingMessageCode) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(0);
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
