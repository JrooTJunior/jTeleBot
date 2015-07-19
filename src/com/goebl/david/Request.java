package com.goebl.david;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Builder for an HTTP request.
 * <br/>
 * You can some "real-life" usage examples at
 * <a href="https://github.com/hgoebl/DavidWebb">github.com/hgoebl/DavidWebb</a>.
 * <br/>
 *
 * @author hgoebl
 */
public class Request {
    public enum Method {
        GET, POST, PUT, DELETE
    }

    private final Webb webb;
    final Method method;
    final String uri;

    Map<String, Object> params;
    Map<String, Object> headers;
    Object payload;
    boolean streamPayload;
    boolean useCaches;
    Integer connectTimeout;
    Integer readTimeout;
    Long ifModifiedSince;
    Boolean followRedirects;
    boolean ensureSuccess;
    boolean compress;
    int retryCount;
    boolean waitExponential;

    Request(Webb webb, Method method, String uri) {
        this.webb = webb;
        this.method = method;
        this.uri = uri;
        this.followRedirects = webb.followRedirects;
    }

    /**
     * Set (or overwrite) a parameter.
     * <br/>
     * The parameter will be used to create a query string for GET-requests and as the body for POST-requests
     * with MIME-type <code>application/x-www-form-urlencoded</code>,
     * @param name the name of the parameter (it's better to use only contain ASCII characters)
     * @param value the value of the parameter; <code>null</code> will be converted to empty string, for all other
     *              objects to <code>toString()</code> method converts it to String
     * @return <code>this</code> for method chaining (fluent API)
     */
    public Request param(String name, Object value) {
        if (params == null) {
            params = new LinkedHashMap<String, Object>();
        }
        params.put(name, value);
        return this;
    }

    public Request params(Map<String, Object> value) {
        for (String key: value.keySet()) {
            this.param(key, value.get(key));
        }
        return this;
    }

    /**
     * Get the URI of this request.
     *
     * @return URI
     */
    public String getUri() {
        return uri;
    }

    /**
     * Set (or overwrite) a HTTP header value.
     * <br/>
     * Setting a header this way has the highest precedence and overrides a header value set on a {@link Webb}
     * instance ({@link Webb#setDefaultHeader(String, Object)}) or a global header
     * ({@link Webb#setGlobalHeader(String, Object)}).
     * <br/>
     * Using <code>null</code> or empty String is not allowed for name and value.
     *
     * @param name name of the header (HTTP-headers are not case-sensitive, but if you want to override your own
     *             headers, you have to use identical strings for the name. There are some frequently used header
     *             names as constants in {@link Webb}, see HDR_xxx.
     * @param value the value for the header. Following types are supported, all other types use <code>toString</code>
     *              of the given object:
     *              <ul>
     *              <li>{@link java.util.Date} is converted to RFC1123 compliant String</li>
     *              <li>{@link java.util.Calendar} is converted to RFC1123 compliant String</li>
     *              </ul>
     * @return <code>this</code> for method chaining (fluent API)
     */
    public Request header(String name, Object value) {
        if (headers == null) {
            headers = new LinkedHashMap<String, Object>();
        }
        headers.put(name, value);
        return this;
    }

    /**
     * Set the payload for the request.
     * <br/>
     * Using this method together with {@link #param(String, Object)} has the effect of <code>body</code> being
     * ignored without notice. The method can be called more than once: the value will be stored and converted
     * to bytes later.
     * <br/>
     * Following types are supported for the body:
     * <ul>
     *     <li>
     *         <code>null</code> clears the body
     *     </li>
     *     <li>
     *         {@link org.json.JSONObject}, HTTP header 'Content-Type' will be set to JSON, if not set
     *     </li>
     *     <li>
     *         {@link org.json.JSONArray}, HTTP header 'Content-Type' will be set to JSON, if not set
     *     </li>
     *     <li>
     *         {@link java.lang.String}, HTTP header 'Content-Type' will be set to TEXT, if not set;
     *         Text will be converted to UTF-8 bytes.
     *     </li>
     *     <li>
     *         <code>byte[]</code> the easiest way for DavidWebb - it's just passed through.
     *         HTTP header 'Content-Type' will be set to BINARY, if not set.
     *     </li>
     *     <li>
     *         {@link java.io.File}, HTTP header 'Content-Type' will be set to BINARY, if not set;
     *         The file gets streamed to the web-server and 'Content-Length' will be set to the number
     *         of bytes of the file. There is absolutely no conversion done. So if you want to upload
     *         e.g. a text-file and convert it to another encoding than stored on disk, you have to do
     *         it by yourself.
     *     </li>
     *     <li>
     *         {@link java.io.InputStream}, HTTP header 'Content-Type' will be set to BINARY, if not set;
     *         Similar to <code>File</code>. Content-Length cannot be set (which has some drawbacks compared
     *         to knowing the size of the body in advance).<br/>
     *         <strong>You have to care for closing the stream!</strong>
     *     </li>
     * </ul>
     *
     * @param body the payload
     * @return <code>this</code> for method chaining (fluent API)
     */
    public Request body(Object body) {
        if (method == Method.GET || method == Method.DELETE) {
            throw new IllegalStateException("body not allowed for request method " + method);
        }
        this.payload = body;
        this.streamPayload = body instanceof File || body instanceof InputStream;
        return this;
    }

    /**
     * Enable compression for uploaded data.<br/>
     * <br/>
     * Before you enable compression, you should find out, whether the web server you are talking to
     * supports this. As compression has not to be implemented for HTTP and standard RFC2616 had only
     * compression for downloaded resources in mind, in special cases it makes absolutely sense to
     * compress the posted data.<br/>
     * Your web application should inspect the 'Content-Encoding' header and implement the compression
     * token provided by this client. By now only 'gzip' encoding token is used. If you need 'deflate'
     * create an issue.
     *
     * @return <code>this</code> for method chaining (fluent API)
     */
    public Request compress() {
        compress = true;
        return this;
    }

    /**
     * See <a href="http://docs.oracle.com/javase/7/docs/api/java/net/URLConnection.html#useCaches">
     *     URLConnection.useCaches</a>
     * <br/>
     * If you don't want your requests delivered from a cache, you don't have to call this method,
     * because <code>false</code> is the default.
     *
     * @param useCaches If <code>true</code>, the protocol is allowed to use caching whenever it can.
     * @return <code>this</code> for method chaining (fluent API)
     */
    public Request useCaches(boolean useCaches) {
        this.useCaches = useCaches;
        return this;
    }

    /**
     * See <a href="http://docs.oracle.com/javase/7/docs/api/java/net/URLConnection.html#setIfModifiedSince(long)">
     *     URLConnection.setIfModifiedSince()</a>
     * @param ifModifiedSince A nonzero value gives a time as the number of milliseconds since January 1, 1970, GMT.
     *                        The object is fetched only if it has been modified more recently than that time.
     * @return <code>this</code> for method chaining (fluent API)
     */
    public Request ifModifiedSince(long ifModifiedSince) {
        this.ifModifiedSince = ifModifiedSince;
        return this;
    }

    /**
     * See <a href="http://docs.oracle.com/javase/7/docs/api/java/net/URLConnection.html#setConnectTimeout(int)">
     *     URLConnection.setConnectTimeout</a>
     * @param connectTimeout sets a specified timeout value, in milliseconds. <code>0</code> means infinite timeout.
     * @return <code>this</code> for method chaining (fluent API)
     */
    public Request connectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    /**
     * See <a href="http://docs.oracle.com/javase/7/docs/api/java/net/URLConnection.html#setReadTimeout(int)">
     *     </a>
     * @param readTimeout Sets the read timeout to a specified timeout, in milliseconds.
     *                    <code>0</code> means infinite timeout.
     * @return <code>this</code> for method chaining (fluent API)
     */
    public Request readTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    /**
     * See <a href="http://docs.oracle.com/javase/7/docs/api/java/net/HttpURLConnection.html#setInstanceFollowRedirects(boolean)">
     *     </a>.
     * <br/>
     * Use this method to set the behaviour for this single request when receiving redirect responses.
     * If you want to change the behaviour for all your requests, call {@link Webb#setFollowRedirects(boolean)}.
     * @param auto <code>true</code> to automatically follow redirects (HTTP status code 3xx).
     *             Default value comes from HttpURLConnection and should be <code>true</code>.
     * @return <code>this</code> for method chaining (fluent API)
     */
    public Request followRedirects(boolean auto) {
        this.followRedirects = auto;
        return this;
    }

    /**
     * By calling this method, the HTTP status code is checked and a <code>WebbException</code> is thrown if
     * the status code is not something like 2xx.<br/>
     * <br/>
     * Be careful! If you request resources e.g. with {@link #ifModifiedSince(long)}, an exception will also be
     * thrown in the positive case of <code>304 Not Modified</code>.
     *
     * @return <code>this</code> for method chaining (fluent API)
     */
    public Request ensureSuccess() {
        this.ensureSuccess = true;
        return this;
    }

    /**
     * Set the number of retries after the first request failed.
     * <br/>
     * When `waitExponential` is set, then there will be {@link Thread#sleep(long)} between
     * the retries. If the thread is interrupted, there will be an `InterruptedException`
     * in the thrown `WebbException`. You can check this with {@link WebbException#getCause()}.
     * The `interrupted` flag will be set to true in this case.
     *
     * @param retryCount This parameter holds the number of retries that will be made AFTER the
     *                   initial send in the event of a error. If an error occurs on the last
     *                   attempt an exception will be raised.<br/>
     *                   Values > 10 are ignored (we're not gatling)
     * @param waitExponential sleep during retry attempts (exponential backoff).
     *                        For retry-counts more than 3, <tt>true</tt> is mandatory.
     * @return <code>this</code> for method chaining (fluent API)
     */
    public Request retry(int retryCount, boolean waitExponential) {
        if (retryCount < 0) {
            retryCount = 0;
        }
        if (retryCount > 10) {
            retryCount = 10;
        }
        if (retryCount > 3 && !waitExponential) {
            throw new IllegalArgumentException("retries > 3 only valid with wait");
        }
        this.retryCount = retryCount;
        this.waitExponential = waitExponential;
        return this;
    }

    /**
     * Execute the request and expect the result to be convertible to <code>String</code>.
     * @return the created <code>Response</code> object carrying the payload from the server as <code>String</code>
     */
    public Response<String> asString() {
        return webb.execute(this, String.class);
    }

    /**
     * Execute the request and expect the result to be convertible to <code>JSONObject</code>.
     * @return the created <code>Response</code> object carrying the payload from the server as <code>JSONObject</code>
     */
    public Response<JSONObject> asJsonObject() {
        return webb.execute(this, JSONObject.class);
    }

    /**
     * Execute the request and expect the result to be convertible to <code>JSONArray</code>.
     * @return the created <code>Response</code> object carrying the payload from the server as <code>JSONArray</code>
     */
    public Response<JSONArray> asJsonArray() {
        return webb.execute(this, JSONArray.class);
    }

    /**
     * Execute the request and expect the result to be convertible to <code>byte[]</code>.
     * @return the created <code>Response</code> object carrying the payload from the server as <code>byte[]</code>
     */
    public Response<byte[]> asBytes() {
        return (Response<byte[]>) webb.execute(this, Const.BYTE_ARRAY_CLASS);
    }

    /**
     * Execute the request and expect no result payload (only status-code and headers).
     * @return the created <code>Response</code> object where no payload is expected or simply will be ignored.
     */
    public Response<Void> asVoid() {
        return webb.execute(this, Void.class);
    }

}
