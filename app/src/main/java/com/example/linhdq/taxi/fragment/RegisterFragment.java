package com.example.linhdq.taxi.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linhdq.taxi.R;
import com.example.linhdq.taxi.activity.RegisterActivity;
import com.example.linhdq.taxi.activity.SplashScreenActivity;
import com.example.linhdq.taxi.constant.Constant;
import com.example.linhdq.taxi.utils.StringUtil;

import java.util.Locale;

/**
 * Created by LinhDQ on 12/19/16.
 */

public class RegisterFragment extends Fragment implements View.OnClickListener {
    //view
    private EditText edtPhoneNumber;
    private EditText edtUserName;
    private EditText edtEmail;
    private EditText edtReferralCode;
    private ImageView imvCheckBox;
    private ImageView imvVietnam;
    private ImageView imvEnglish;
    private TextView txtTermAndCondition;
    private TextView txtVietNam;
    private TextView txtEnglish;
    private Button btnRegister;
    private Button btnHasActivationCode;
    //animation
    private Animation animationIn;
    private Animation animationOut;
    //
    private Context context;
    //
    private SharedPreferences sharedPreferences;
    //
    private String languageCode;
    private String phoneNumber;
    private String userName;
    private String email;
    private String referralCode;
    private boolean isAcceptTermConditions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        //set title
        ((RegisterActivity) getActivity()).changeTitle(getResources().getString(R.string.register));
        //init
        init(view);
        addListener();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //
        getDataFromSharedPreferences();
        changeStatusLanguageButton();
        fillData();
    }

    @Override
    public void onPause() {
        super.onPause();
        //save data to shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.LANGUAGE_KEY, languageCode);
        editor.putString(Constant.PHONE_NUMBER, phoneNumber);
        editor.putString(Constant.USER_NAME, userName);
        editor.putString(Constant.EMAIL, email);
        editor.putString(Constant.REFERRAL_CODE, referralCode);
        editor.putBoolean(Constant.IS_AGREE, isAcceptTermConditions);
        editor.commit();
    }

    private void init(View view) {
        //view
        edtPhoneNumber = (EditText) view.findViewById(R.id.edt_phone_numbers);
        edtUserName = (EditText) view.findViewById(R.id.edt_user_name);
        edtEmail = (EditText) view.findViewById(R.id.edt_email);
        edtReferralCode = (EditText) view.findViewById(R.id.edt_referral_code);
        imvCheckBox = (ImageView) view.findViewById(R.id.ic_check_box);
        imvVietnam = (ImageView) view.findViewById(R.id.ic_vietnam_flag);
        imvEnglish = (ImageView) view.findViewById(R.id.ic_english_flag);
        txtTermAndCondition = (TextView) view.findViewById(R.id.txt_term_and_conditions);
        txtVietNam = (TextView) view.findViewById(R.id.txt_vietnamese);
        txtEnglish = (TextView) view.findViewById(R.id.txt_english);
        btnRegister = (Button) view.findViewById(R.id.btn_register);
        btnHasActivationCode = (Button) view.findViewById(R.id.btn_has_activation_code);
        //
        context = view.getContext();
        //animation
        animationIn = AnimationUtils.loadAnimation(context, R.anim.zoom_in);
        animationOut = AnimationUtils.loadAnimation(context, R.anim.zoom_out);
        //
        sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCE_KEY,
                Context.MODE_PRIVATE);
    }

    private void addListener() {
        btnRegister.setOnClickListener(this);
        btnHasActivationCode.setOnClickListener(this);
        imvCheckBox.setOnClickListener(this);
        txtTermAndCondition.setOnClickListener(this);
        imvVietnam.setOnClickListener(this);
        imvEnglish.setOnClickListener(this);
        txtVietNam.setOnClickListener(this);
        txtEnglish.setOnClickListener(this);
        edtPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edtPhoneNumber.setTextColor(getResources().getColor(R.color.grey_900));
                edtPhoneNumber.setHintTextColor(getResources().getColor(R.color.grey_500));
                phoneNumber = edtPhoneNumber.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                userName = edtUserName.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                email = edtEmail.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtReferralCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                referralCode = edtReferralCode.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void getDataFromSharedPreferences() {
        languageCode = sharedPreferences.getString(Constant.LANGUAGE_KEY, "vi");
        phoneNumber = sharedPreferences.getString(Constant.PHONE_NUMBER, "");
        userName = sharedPreferences.getString(Constant.USER_NAME, "");
        email = sharedPreferences.getString(Constant.EMAIL, "");
        referralCode = sharedPreferences.getString(Constant.REFERRAL_CODE, "");
        isAcceptTermConditions = sharedPreferences.getBoolean(Constant.IS_AGREE, true);
    }

    private void fillData() {
        edtPhoneNumber.setText(phoneNumber);
        edtUserName.setText(userName);
        edtEmail.setText(email);
        edtReferralCode.setText(referralCode);
        changeStatusAgreeOrNot(isAcceptTermConditions);
        //set language
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    private void changeStatusAgreeOrNot(boolean isAgree) {
        isAcceptTermConditions = isAgree;
        btnRegister.setEnabled(isAcceptTermConditions);
        if (isAcceptTermConditions) {
            imvCheckBox.setColorFilter(getResources().getColor(R.color.teal_600));
            txtTermAndCondition.setTextColor(getResources().getColor(R.color.grey_900));
        } else {
            imvCheckBox.setColorFilter(getResources().getColor(R.color.grey_500));
            txtTermAndCondition.setTextColor(getResources().getColor(R.color.grey_500));
        }
    }

    private void changeStatusLanguageButton() {
        boolean isVietNam = true;
        if (!languageCode.equalsIgnoreCase("vi")) {
            isVietNam = false;
            txtVietNam.setTextColor(getResources().getColor(R.color.teal_600));
            txtEnglish.setTextColor(getResources().getColor(R.color.grey_500));
        } else {
            txtVietNam.setTextColor(getResources().getColor(R.color.grey_500));
            txtEnglish.setTextColor(getResources().getColor(R.color.teal_600));
        }
        imvVietnam.setEnabled(!isVietNam);
        txtVietNam.setEnabled(!isVietNam);
        imvEnglish.setEnabled(isVietNam);
        txtEnglish.setEnabled(isVietNam);
    }

    private boolean dataValidation() {
        //validate phone number
        if (!StringUtil.validatePhoneNumber(edtPhoneNumber.getText().toString())) {
            edtPhoneNumber.setTextColor(getResources().getColor(R.color.red_600));
            edtPhoneNumber.setHintTextColor(getResources().getColor(R.color.red_600));
            Toast.makeText(context, getResources().getString(R.string.error_phone_number), Toast.LENGTH_SHORT).show();
            return false;
        }
        //validate username if it is not null
        if (userName != null && userName.toCharArray().length != 0) {
            if (!StringUtil.validateName(userName)) {
                Toast.makeText(context, getResources().getString(R.string.error_user_name), Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        //validate email if it is not null
        if (email != null && email.toCharArray().length != 0) {
            if (!StringUtil.validateEmail(email)) {
                Toast.makeText(context, getResources().getString(R.string.error_email), Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    private void changeLanguage() {
        //
        Intent intent = new Intent(getActivity(), SplashScreenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                if (dataValidation()) {
                    //do something
                    ((RegisterActivity) getActivity()).openFragment(new EnterVerificationCodeFragment(), true);
                }
                break;
            case R.id.btn_has_activation_code:
                ((RegisterActivity) getActivity()).openFragment(new EnterVerificationCodeFragment(), true);
                break;
            case R.id.ic_check_box:
            case R.id.txt_term_and_conditions:
                changeStatusAgreeOrNot(!isAcceptTermConditions);
                break;
            case R.id.ic_vietnam_flag:
            case R.id.txt_vietnamese:
                imvVietnam.startAnimation(animationIn);
                txtVietNam.startAnimation(animationIn);
                animationOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        languageCode = "vi";
                        changeLanguage();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                imvVietnam.startAnimation(animationOut);
                txtVietNam.startAnimation(animationOut);
                break;
            case R.id.ic_english_flag:
            case R.id.txt_english:
                imvEnglish.startAnimation(animationIn);
                txtEnglish.startAnimation(animationIn);
                animationOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        languageCode = "en";
                        changeLanguage();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                imvEnglish.startAnimation(animationOut);
                txtEnglish.startAnimation(animationOut);
                break;
            default:
                break;
        }
    }
}
