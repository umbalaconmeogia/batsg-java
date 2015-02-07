package com.chauhai.batsg.util;

/**
 * Copyright (C) 2015 umbalaconmeogia
 * https://github.com/umbalaconmeogia/java-batsg
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Sub class of ResourceBundle.Control that can process properties file in UTF-8 encoding.
 * It also allows to load from resource (in the code package) and from file (outside the code package).
 *
 * <p>Usage example:
 * <ul>
 * <li>
 *   <p>Load from resource ("com.example.i18n")
 *   <p><code>
 *      ResourceBundle bundle = ResourceBundle.getBundle("com.example.i18n", new Locale("ja"), new Utf8ResourceBundleControl());
 *   </code>
 * <li>
 *   <p>Load from file: Assume that resource files are put in C:/i18n, which contains the following files:
 *   <ul>
 *     <li>C:/i18n/message.properties
 *     <li>C:/i18n/message_ja.properties
 *   </ul>
 *   <p><code>
 *      ResourceBundle bundle = ResourceBundle.getBundle("message", new Locale("ja"), new Utf8ResourceBundleControl("C:/i18n"));
 *   </code>
 * </ul>
 *
 * @author umbalaconmeogia
 */
public class Utf8ResourceBundleControl extends ResourceBundle.Control {

  private String directory;

  /**
   * Create Utf8ResourceBundleControl with directory mean that it load resource from files outside the code package.
   * @param directory
   */
  public Utf8ResourceBundleControl(String directory) {
    super();
    this.directory = directory;
  }

  /**
   * Load resource from code package.
   */
  public Utf8ResourceBundleControl() {
    this(null);
  }

  @Override
  public List<String> getFormats(String baseName) {
    if (baseName == null)
      throw new NullPointerException();
    return Arrays.asList("properties");
  }

  @Override
  public ResourceBundle newBundle(String baseName,
      Locale locale,
      String format,
      ClassLoader loader,
      boolean reload) throws IOException {
    ResourceBundle bundle = null;

    if (format.equals("properties")) {
      InputStream stream = null;
      InputStreamReader reader = null;

      // Generate resource file name.
      String bundleName = toBundleName(baseName, locale);
      String resourceName = toResourceName(bundleName, format);
      // Get input stream.
      if (directory != null) {
        // Load from file.
        File file = new File(directory, resourceName);
        stream = new FileInputStream(file);
      } else {
        // Load from resource
        if (reload) {
          URL url = loader.getResource(resourceName);
          if (url != null) {
            URLConnection connection = url.openConnection();
            if (connection != null) {
              // Disable caches to get fresh data for reloading.
              connection.setUseCaches(false);
              stream = connection.getInputStream();
            }
          }
        } else {
          stream = loader.getResourceAsStream(resourceName);
        }
      }
      try {
        reader = new InputStreamReader(stream, "UTF-8");
        bundle = new PropertyResourceBundle(reader);
      } finally {
        try {
          reader.close();
        } catch (IOException e) {
          // Do nothing.
        }
        try {
          stream.close();
        } catch (IOException e) {
          // Do nothing.
        }
      }
    }
    return bundle;
  }
}
