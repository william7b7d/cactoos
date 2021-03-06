/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2020 Yegor Bugayenko
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
package org.cactoos.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Decorator of {@link OutputStream} to prevent it
 * to be closed.
 *
 * <p>There is no thread-safety guarantee.
 *
 * @since 1.0.0
 */
public final class SafeOutputStream extends OutputStream {

    /**
     * Origin.
     */
    private final OutputStream origin;

    /**
     * Ctor.
     * @param origin Origin
     */
    public SafeOutputStream(final OutputStream origin) {
        super();
        this.origin = origin;
    }

    @Override
    public void write(final int data) throws IOException {
        this.origin.write(data);
    }

    @Override
    public void write(final byte[] buf) throws IOException {
        this.origin.write(buf);
    }

    @Override
    public void write(final byte[] buf, final int off, final int len)
        throws IOException {
        this.origin.write(buf, off, len);
    }

    @Override
    public void flush() throws IOException {
        this.origin.flush();
    }

    @Override
    @SuppressWarnings("PMD.UncommentedEmptyMethodBody")
    public void close() throws IOException {
    }

}
