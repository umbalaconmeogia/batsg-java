package com.chauhai.batsg.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Sub class of Properties that can process properties file in UTF-8 encoding.
 *
 * @author umbalaconmeogia
 */
public class Utf8Properties extends Properties {

  /**
   * Load from InputStream, using UTF-8 character encoding.
   */
  @Override
  public void load(InputStream inStream) throws IOException {
    load(new InputStreamReader(inStream, "UTF-8"));
  }

  /**
   * Load from file, using UTF-8 character encoding.
   * @param file
   * @throws FileNotFoundException
   * @throws IOException
   */
  public void load(File file) throws FileNotFoundException, IOException {
    load(new FileInputStream(file));
  }

  /**
   * Load from file, using UTF-8 character encoding.
   * @param file
   * @throws FileNotFoundException
   * @throws IOException
   */
  public void load(String file) throws FileNotFoundException, IOException {
    load(new File(file));
  }
}
