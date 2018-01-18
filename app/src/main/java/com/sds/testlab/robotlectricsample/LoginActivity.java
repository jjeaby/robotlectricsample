package com.sds.testlab.robotlectricsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "test:test","foo@example.com:foo", "bar@example.com:bar"
    };

    private EditText mEmailView;
    private EditText mPasswordView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);

        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mEmailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetErrors();
            }
        });
    }

    private void attemptLogin() {
        boolean validPwd = setErrorMessageIfEmptyText(mPasswordView);
        boolean validEmail = setErrorMessageIfEmptyText(mEmailView);

        if (validEmail && validPwd) {
            loginUser(mEmailView.getText().toString(), mPasswordView.getText().toString());
        }
    }

    private void loginUser(String email, String password) {
        for (String credential : DUMMY_CREDENTIALS) {
            String[] pieces = credential.split(":");
            if (pieces[0].equals(email) && pieces[1].equals(password)) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        }
        setErrorMessageAndRequestFocus(mPasswordView, "올바르지 않은 정보입니다");
        setErrorMessageAndRequestFocus(mEmailView, "올바르지 않은 정보입니다");
    }

    private boolean setErrorMessageIfEmptyText(TextView textView) {
        if (isEmpty(textView)) {
            setErrorMessageAndRequestFocus(textView,
                    "텍스트 필드를 채워주세요");
            return false;
        }
        return true;
    }

    private boolean isEmpty(TextView textView) {
        String text = textView.getText().toString();
        return TextUtils.isEmpty(text);
    }

    private void setErrorMessageAndRequestFocus(TextView textView, String errorMessage) {
        textView.setError(errorMessage);
        textView.requestFocus();
    }

    private void resetErrors() {
        mEmailView.setError(null);
        mPasswordView.setError(null);
    }
}