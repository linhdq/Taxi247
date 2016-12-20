package com.example.linhdq.taxi.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.linhdq.taxi.R;
import com.example.linhdq.taxi.activity.HomeActivity;
import com.example.linhdq.taxi.activity.RegisterActivity;
import com.example.linhdq.taxi.constant.Constant;

/**
 * Created by LinhDQ on 12/20/16.
 */

public class EnterVerificationCodeFragment extends Fragment implements View.OnClickListener {
    //view
    private TextView txtPhoneNumber;
    private EditText edtVerificationCode;
    private Button btnConfirm;
    private Button btnReSendSMS;
    //
    private String verificationCode;
    //
    private CountDownTimer countDownTimer;
    //
    private Context context;
    //
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enter_verification_code, container, false);

        //set title
        ((RegisterActivity) getActivity()).changeTitle(getResources().getString(R.string.enter_verification_code));
        //init
        init(view);
        addListener();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //make count down timer
        countDownTimer.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        //
        countDownTimer.cancel();
    }

    private void init(View view) {
        //view
        txtPhoneNumber = (TextView) view.findViewById(R.id.txt_phone_number);
        edtVerificationCode = (EditText) view.findViewById(R.id.edt_verification_code);
        btnConfirm = (Button) view.findViewById(R.id.btn_confirm);
        btnReSendSMS = (Button) view.findViewById(R.id.btn_re_send_sms);
        //
        btnConfirm.setEnabled(false);
        //
        context = view.getContext();
        sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
        txtPhoneNumber.setText(sharedPreferences.getString(Constant.PHONE_NUMBER, "Error"));
        //
        countDownTimer = new CountDownTimer(300000, 1000) {

            @Override
            public void onTick(long l) {
                btnReSendSMS.setText(getResources().getString(R.string.re_send_sms) + " 00:" + String.format("%02d:%02d", (l / 60000), (l % 60000) / 1000));
            }

            @Override
            public void onFinish() {

            }
        };
    }

    private void addListener() {
        btnConfirm.setOnClickListener(this);
        btnReSendSMS.setOnClickListener(this);
        edtVerificationCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                verificationCode = edtVerificationCode.getText().toString().trim();
                if (verificationCode != null && verificationCode.toCharArray().length != 0) {
                    btnConfirm.setEnabled(true);
                } else {
                    btnConfirm.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                //save status
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(Constant.IS_LOGIN, true);
                editor.commit();
                //
                Intent intent = new Intent(context, HomeActivity.class);
                startActivity(intent);
                EnterVerificationCodeFragment.this.getActivity().finish();
                break;
            case R.id.btn_re_send_sms:

                break;
            default:
                break;
        }
    }
}
