package org.kframework.krun.ioserver.filesystem.portable;

import java.io.InputStream;
import java.io.IOException;
import java.util.Arrays;

public class InputStreamFile extends File {

    protected InputStream is;

    public InputStreamFile(InputStream is) {
        this.is = is;
    }

    public long tell() throws IOException {
        throw new IOException("ESPIPE");
    }

    public void seek(long pos) throws IOException {
        throw new IOException("ESPIPE");
    }

    public void putc(byte b) throws IOException {
        //since technically writing to file descriptor zero can succeed or fail depending on what
        //underlying file it points to and what mode it was opened in, we don't throw an
        //IOException here. An IOException in the api corresponds to a system call failing in a
        //well-defined fashion. Here we throw an UnsupportedOperationException because the behavior
        //is undefined.
        throw new UnsupportedOperationException();
    }

    public byte getc() throws IOException {
        int read;
        try {
            read = is.read();
        } catch (IOException e) {
            PortableFileSystem.processIOException(e);
            throw e; //unreachable
        }
        if (read == -1) {
            throw new IOException("EOF");
        }
        return (byte)read;
    }

    public byte[] read(int n) throws IOException {
        int read;
        byte[] bytes;
        try {
            bytes = new byte[n];
            read = is.read(bytes);
        } catch (IOException e) {
            PortableFileSystem.processIOException(e);
            throw e; //unreachable
        }
        if (read == -1) {
            throw new IOException("EOF");
        }
        return Arrays.copyOfRange(bytes, 0, read);
    }

    public void write(byte[] b) throws IOException {
        //see comment on putc
        throw new UnsupportedOperationException();
    }

    void close() throws IOException {
        try {
            is.close();
        } catch (IOException e) {
            PortableFileSystem.processIOException(e);
        }
    }
}
