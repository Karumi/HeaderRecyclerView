/*
 * Copyright (C) 2015 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.karumi.headerrecyclerview.testBaseClass;

import com.karumi.headerrecyclerview.BuildConfig;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Base class extended by every Robolectric test combined mockito.
 * <pre>
 * if (you do not need fake Android Runtime)
 *    use use {@link PowermockTest} as the Test base
 * else if(you don't need to feature provided by Powermock, like mock final,private or static method)
 *     use use this class
 * else
 *     use {@link RobolectricTestWithPowermock}</pre>
 */
@Config(constants = BuildConfig.class,
        sdk = 21)
@RunWith(RobolectricGradleTestRunner.class)
public abstract class RobolectricTestWithMockito {

}
