package com.xpeppers.linkingcommerce.merchantdashboard.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xpeppers.linkingcommerce.merchantdashboard.R;
import com.xpeppers.linkingcommerce.merchantdashboard.orders.OrdersListActivity;

import retrofit.RestAdapter;


public class SignInActivity extends AppCompatActivity{

    private static final String BACKOFFICE_URL = "http://private-46e03-linkingcommerceapi.apiary-mock.com/api";
    private EditText inputEmailField;
    private EditText inputPasswordField;
    private View progressView;
    private View loginFieldsView;
    private SignInPresenter signInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        inputEmailField = (EditText) findViewById(R.id.email);

        inputPasswordField = (EditText) findViewById(R.id.password);
        inputPasswordField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    signInPresenter.signIn();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                signInPresenter.signIn();
            }
        });

        loginFieldsView = findViewById(R.id.login_form);
        progressView = findViewById(R.id.login_progress);

        MessageAlert alertDialog = new MessageAlert() {
            @Override
            public void showMessage(String title, String message) {
                Toast.makeText(SignInActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        };

        SignInView signInView = AndroidSignInView.builder()
                .withContext(this)
                .withEmailField(inputEmailField)
                .withPasswordField(inputPasswordField)
                .withProgressView(progressView)
                .withLoginFieldsView(loginFieldsView)
                .withAlertDialog(alertDialog)
                .build();

        SignInSuccessListener signInSuccessListener = new SignInSuccessListener() {
            @Override
            public void signInSuccess(AuthToken authToken) {
                Intent intent = new Intent(SignInActivity.this, OrdersListActivity.class);
                intent.putExtra("TOKEN", authToken.getToken());
                startActivity(intent);
            }
        };

        RestAdapter adapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BACKOFFICE_URL)
                .build();

        SignInService signInService = adapter.create(SignInService.class);

        signInPresenter = new SignInPresenter(signInView, signInService, signInSuccessListener);
    }
}
