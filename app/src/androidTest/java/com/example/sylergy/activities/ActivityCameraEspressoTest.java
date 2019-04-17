package com.example.sylergy.activities;

import android.provider.MediaStore;

import com.example.sylergy.R;
import com.example.sylergy.activities.utils.EspressoUtils;
import com.example.sylergy.activities.utils.ToastMatcher;
import com.example.sylergy.logs.Logs;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ActivityCameraEspressoTest {
    private IdlingResource idlingResource;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void cameraButtonUITest() throws InterruptedException {
        onView(allOf(withText("Barcode"),isDescendantOfA(withId(R.id.bottomNavigationView)),isDisplayed())).perform(click());
        Espresso.pressBack();
        Thread.sleep(750);
        onView(withText("Cancelled")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    // Test camera active

    /**@Test
    public void cameraCorrectBarcodeUITest() throws InterruptedException {
        onView(allOf(withText("Barcode"),isDescendantOfA(withId(R.id.bottomNavigationView)),isDisplayed())).perform(click());
        intended(hasAction(equalTo(MediaStore.ACTION_IMAGE_CAPTURE)));
    }*/

    @Before
    public void registerActivity(){
        idlingResource = mActivityRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(idlingResource);
    }
    @After
    public void unregisterActivity(){
        IdlingRegistry.getInstance().unregister(idlingResource);
    }
}