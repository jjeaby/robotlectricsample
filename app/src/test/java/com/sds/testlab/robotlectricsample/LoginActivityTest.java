package com.sds.testlab.robotlectricsample;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class) // 사용할 TestRunner를 지정
@Config(constants = BuildConfig.class)// BuildConfig를 설정
public class LoginActivityTest {

    //Activity 의 Object 선언
    private EditText emailView;
    private EditText passwordView;
    private Button button;
    private Activity activity;

    @Before
    public void setUp() {
        //Activity 를 Roboylecric 으로 Build
        activity = Robolectric.buildActivity(LoginActivity.class).create().get();
        button = (Button) activity.findViewById(R.id.email_sign_in_button);
        emailView = (EditText) activity.findViewById(R.id.email);
        passwordView = (EditText) activity.findViewById(R.id.password);
    }

    // email 과 pw 를 정상적으로 입력한 경우 다음 Activity 가 정상적으로 나타나는지 확인 하는 테스트 케이스
    @Test
    public void loginSuccess() {
        emailView.setText("foo@example.com");
        passwordView.setText("foo");

        button.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);

        Intent expectedIntent = new Intent(activity, MainActivity.class);
        assertThat(expectedIntent.getComponent(), equalTo(application.getNextStartedActivity().getComponent()));

    }

    // email 과 pw 를 입력하지 않은 경우 나타나는 메세지를 확인하는 테스트 케이스
    @Test
    public void checkErrorMessageAndResetMessage() {

        emailView.setText("foo@example.com");
        button.performClick();
        assertEquals("텍스트 필드를 채워주세요", passwordView.getError());
        emailView.performClick();
        assertEquals(null, emailView.getError());
    }

    //잘못된 email 과 pw 를 입력한 경우 나타나는 에러 메세지를 확인하는 테스트 케이스
    @Test
    public void checkErrorMessage2() {
        emailView.setText("foo@example.com");
        passwordView.setText("gogo");
        button.performClick();
        assertEquals("Next activity has started", emailView.getError(), "올바르지 않은 정보입니다");
        assertEquals("Next activity has started", passwordView.getError(), "올바르지 않은 정보입니다");
    }
}
