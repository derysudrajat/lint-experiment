package id.derysudrajat.fragmentcontainer

import android.content.Context
import android.util.TypedValue
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest{

    @Test
    fun addition_isCorrect() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        // actual R.color.colorPrimary value
        val actualPrimaryColor = appContext.getColor(R.color.purple_200)

        // R.attr.colorPrimary resolved with invalid theme
        val colorPrimary1 = TypedValue().also {
            appContext.theme.resolveAttribute(R.attr.colorPrimary, it, true)
        }.data

        // provided context has invalid theme so attribute resolution fails (returns 0)
        assertEquals(0, colorPrimary1)

        // be sure test context uses same theme as app
        appContext.setTheme(R.style.Theme_FragmentContainer)

        // R.attr.colorPrimary resolved from valid theme
        val colorPrimary2 = TypedValue().also {
            appContext.theme.resolveAttribute(R.attr.colorPrimary, it, true)
        }.data

        // valid theme returns proper color
        assertEquals(actualPrimaryColor, colorPrimary2)

    }
}