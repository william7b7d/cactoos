/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2022 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cactoos.proc;

import java.io.EOFException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.llorllale.cactoos.matchers.Assertion;

/**
 * Test case for {@link CheckedProc}.
 *
 * @since 0.32
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class CheckedProcTest {

    @Test(expected = IllegalStateException.class)
    public void runtimeExceptionIsNotWrapped() throws Exception {
        new CheckedProc<>(
            input -> {
                throw new IllegalStateException("runtime1");
            },
            IOException::new
        ).exec(true);
    }

    @Test(expected = IOException.class)
    public void checkedExceptionIsWrapped() throws Exception {
        new CheckedProc<>(
            input -> {
                throw new EOFException("runtime2");
            },
            IOException::new
        ).exec(true);
    }

    @Test
    public void extraWrappingIgnored() {
        try {
            new CheckedProc<>(
                input -> {
                    throw new IOException("runtime3");
                },
                IOException::new
            ).exec(true);
        } catch (final IOException exp) {
            new Assertion<>(
                "Extra wrapping of IOException has been added",
                exp.getCause(),
                new IsNull<>()
            ).affirm();
        }
    }

    @Test
    public void noExceptionThrown() throws Exception {
        final AtomicInteger counter = new AtomicInteger();
        new CheckedProc<>(
            input -> counter.incrementAndGet(),
            exp -> exp
        ).exec(false);
        new Assertion<>(
            "Must not throw an exception",
            counter.get(),
            new IsEqual<>(1)
        ).affirm();
    }
}
