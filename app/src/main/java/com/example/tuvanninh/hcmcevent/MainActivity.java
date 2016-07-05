package com.example.tuvanninh.hcmcevent;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class MainActivity extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 1000;
    TextView textView;
    LoginButton loginButton;
    CallbackManager callbackManager;
    Intent intent;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        AppEventsLogger.activateApp(this);
        intent = new Intent (this, MenuList.class);

        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken newAccessToken) {
                updateWithToken(newAccessToken);
            }
        };

        updateWithToken(AccessToken.getCurrentAccessToken());


    }

    private void updateWithToken(AccessToken newAccessToken) {
        if (newAccessToken != null) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    startActivity(intent);
                }
            }, SPLASH_TIME_OUT);
        } else {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    setContentView(R.layout.activity_main);
                    android.support.v7.app.ActionBar actionBar = getSupportActionBar();
                    actionBar.hide();

                    textView = (TextView) findViewById(R.id.logo);
                    textView.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf"));

                    callbackManager = CallbackManager.Factory.create();
                    loginButton = (LoginButton) findViewById(R.id.login_button);
                    loginButton.setReadPermissions("email, publish_actions, public_profile");



                    loginButton.registerCallback(callbackManager,
                            new FacebookCallback<LoginResult>() {
                                @Override
                                public void onSuccess(LoginResult loginResult) {
                                    // App code
                                    Toast.makeText(MainActivity.this, "Login succeed!", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancel() {
                                    // App code
                                    Toast.makeText(MainActivity.this, "Login cancelled!", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(FacebookException exception) {
                                    // App code
                                    Toast.makeText(MainActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();

                                }
                            });

                }
            }, SPLASH_TIME_OUT);
        }
    }

}
