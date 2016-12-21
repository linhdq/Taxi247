package com.example.linhdq.taxi.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.linhdq.taxi.R;

/**
 * Created by LinhDQ on 12/21/16.
 */

public class FeedbackFragment extends Fragment implements View.OnClickListener {
    //view
    private EditText edtTitle;
    private EditText edtContent;
    private LinearLayout btnHotline;
    private LinearLayout btnSend;
    //
    private Context context;
    //
    private String title;
    private String content;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        //init
        init(view);
        addListener();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //
        btnHotline.setEnabled(true);
    }

    private void init(View view) {
        //view
        edtTitle = (EditText) view.findViewById(R.id.edt_feedback_title);
        edtContent = (EditText) view.findViewById(R.id.edt_feedback_content);
        btnHotline = (LinearLayout) view.findViewById(R.id.layout_btn_hotline);
        btnSend = (LinearLayout) view.findViewById(R.id.layout_btn_send);
        //
        context = view.getContext();
    }

    private void addListener() {
        btnHotline.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        edtTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                title = edtTitle.getText().toString().trim();
                edtTitle.setHintTextColor(getResources().getColor(R.color.grey_500));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });
        edtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                content = edtContent.getText().toString().trim();
                edtContent.setHintTextColor(getResources().getColor(R.color.grey_500));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private boolean dataValidation() {
        if (title == null || title.toCharArray().length == 0) {
            edtTitle.setHintTextColor(getResources().getColor(R.color.red_600));
            return false;
        }
        if (content == null || content.toCharArray().length == 0) {
            edtContent.setHintTextColor(getResources().getColor(R.color.red_600));
            return false;
        }
        return true;
    }

    private void callAction(String phoneNumber) {
        if (((TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE)).getPhoneType()
                != TelephonyManager.PHONE_TYPE_NONE) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
            try {
                startActivity(intent);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        }else{

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_btn_hotline:
                btnHotline.setEnabled(false);
                callAction("900");
                break;
            case R.id.layout_btn_send:
                //do something
                if (dataValidation()) {
                    Toast.makeText(context, getResources().getString(R.string.send_feedback_result), Toast.LENGTH_SHORT).show();
                    edtTitle.setText("");
                    edtContent.setText("");
                }
                break;
            default:
                break;
        }
    }
}
