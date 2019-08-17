package org.mlib.Network;

import android.util.Range;

import org.mlib.System.Exception.MException;
import org.mlib.System.Thread.MRunnable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class MHttp {
    private URL url;
    private URLConnection connection;
    private boolean isThreaded;
    private Vector<String> responses;
    private MNetworkListener listener;

    public MHttp() {
        this(null, true);
    }

    public MHttp(URL url) {
        this(url, true);
    }

    public MHttp(URL url, boolean isThreaded) {
        this.url = url;
        this.isThreaded = isThreaded;
        this.responses = new Vector<>();
        this.listener = new MNetworkListener();
    }

    public URLConnection connect() {
        return connect(this.url);
    }

    public URLConnection connect(URL url) {
        if (!checkURL())
            return null;

        if (checkURLConnection())
            dissconnect();

        try {
            this.connection = this.url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.connection;
    }

    public void dissconnect() {
        if (checkURLConnection())
            this.connection = null;
    }

    public void read() {
        if (!checkURLConnection())
            return;

        Vector<Object> data = new Vector<>();
        data.add(this.connection);
        data.add(this.responses);
        data.add(this.listener);

        MRunnable handler = new MRunnable(data) {
            @Override
            public void run() {
                StringBuilder builder = new StringBuilder();

                try {
                    InputStream stream = ((URLConnection) this.data.get(0)).getInputStream();
                    int bufferSize = 1024, byteCount;
                    byte[] buffer = new byte[bufferSize];

                    do {
                        byteCount = stream.read(buffer);
                        builder.append(new String(buffer, "CP1251"));
                    } while (byteCount == bufferSize);
                } catch (IOException e) {
                    e.printStackTrace();
                    ((MNetworkListener)this.data.get(2)).onFailed(e.getMessage());
                }

                int nullChar = builder.indexOf("\0");
                String response;
                if (nullChar != -1)
                    response = builder.substring(0, builder.indexOf("\0"));
                else
                    response = builder.toString();

                ((Vector<String>) this.data.get(1)).add(response);
                ((MNetworkListener) this.data.get(2)).onSuccess(response);
            }
        };

        if (this.isThreaded)
            new Thread(handler).start();
        else
            handler.run();
    }

    public URL getUrl() {
        return this.url;
    }

    public URLConnection getConnectionX() {
        return this.connection;
    }

    public MNetworkListener getListener() {
        return this.listener;
    }

    private boolean checkURL() {
        if (this.url == null) {
            new MException("URL не определен").printStackTrace();
            return false;
        }

        return true;
    }

    private boolean checkURLConnection() {
        if (this.connection == null) {
            new MException("Соединение не открыто").printStackTrace();
            return false;
        }

        return true;
    }
}