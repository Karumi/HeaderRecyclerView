package com.karumi.headerrecyclerview.testBaseClass;

import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * <pre>
 * if (you do not need fake Android Runtime)
 *    use this class as the Test base
 * else if(you don't need to feature provided by Powermock, like mock final,private or static method)
 *     use {@link RobolectricTestWithMockito}
 * else
 *     use {@link RobolectricTestWithPowermock}</pre>
 *
 */
@RunWith(PowerMockRunner.class)
public abstract class PowermockTest {
}
