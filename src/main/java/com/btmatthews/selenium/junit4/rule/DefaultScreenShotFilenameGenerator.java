/*
 * Copyright 2011-2012 Brian Matthews
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.btmatthews.selenium.junit4.rule;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.junit.runner.Description;

/**
 * Default implementation of the {@link ScreenShotFilenameGenerator} that generates
 *
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.5
 */
public class DefaultScreenShotFilenameGenerator implements ScreenShotFilenameGenerator {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddhhmmssSSS");

    /**
     * The root directory under which the screen shot files will be placed.
     */
    private final File target;

    /**
     * @param directory
     */
    public DefaultScreenShotFilenameGenerator(final File directory) {
        target = directory;
    }

    /**
     * Generates unique file names for the test case described by {@code description}. The
     *
     * @param description The test case description.
     * @return The generated unique file name.
     */
    @Override
    public File getTargetFilename(final Description description) {
        final StringBuilder builder = new StringBuilder();
        builder.append(description.getMethodName());
        builder.append('-');
        builder.append(new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date()));
        builder.append('-');
        builder.append(UUID.randomUUID().toString());
        builder.append(".png");
        return new File(new File(target, description.getTestClass().getCanonicalName().replace('.', File.separatorChar)),
                builder.toString());
    }
}
