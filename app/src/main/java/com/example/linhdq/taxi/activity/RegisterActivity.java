package com.example.linhdq.taxi.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.linhdq.taxi.R;
import com.example.linhdq.taxi.fragment.RegisterFragment;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //start animation
        overridePendingTransition(R.anim.trans_in, R.anim.trans_out);
        //change title
        changeTitle(getResources().getString(R.string.register));
        //open default fragment
        openFragment(new RegisterFragment(), false);
    }

    public void changeTitle(String title){
        RegisterActivity.this.setTitle(title);
    }

    private void openFragment(Fragment fragment, boolean addToBackStack) {
        if (!fragment.isAdded()) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.layout_register_container, fragment);
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(fragment.toString());
            }
            fragmentTransaction.commit();
        }
    }
}
