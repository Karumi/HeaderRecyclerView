package com.karumi.headerrecyclerview.testBaseClass;

import com.karumi.headerrecyclerview.BuildConfig;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Known issue: when you use
 * <a href="http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html#spy">
 * Mockito.spy(Object) </a>, you will get a java.lang.IllegalAccessError exception<br>
 * if you do not use powermock feature, please use {@link RobolectricTestWithMockito} instead.<br>
 * Or you have to write your own stub to verify method call
 * <pre>
 * if (you do not need fake Android Runtime)
 *     use use {@link PowermockTest} as the Test base
 * else if(you don't need to feature provided by Powermock, like mock final,private or static method)
 *     use {@link RobolectricTestWithMockito}
 * else
 *     use this class</pre>
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
public abstract class RobolectricTestWithPowermock {
    @Rule
    public PowerMockRule rule = new PowerMockRule();
}
