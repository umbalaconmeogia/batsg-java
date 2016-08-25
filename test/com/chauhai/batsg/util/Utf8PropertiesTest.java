package com.chauhai.batsg.util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

public class Utf8PropertiesTest {

  static final String CONFIG_FILE_NAME = "utf8_ja.properties";
  static final String CONFIG_FILE_PATH = "./fixtures/properties/" + CONFIG_FILE_NAME;

  static final String KEY_HELLO = "text.hello";

  private File configFile() throws URISyntaxException {
    File file = new File(getClass().getClassLoader().getResource(CONFIG_FILE_PATH).toURI());
    return file;
  }

  @Test
  public void testLoadInputStream() throws URISyntaxException, FileNotFoundException, IOException {
    Utf8Properties config = new Utf8Properties();
    config.load(new FileInputStream(configFile()));
    assertEquals("ハロー・ワールド", config.getProperty(KEY_HELLO));
  }

  @Test
  public void testLoadFile() throws URISyntaxException, FileNotFoundException, IOException {
    Utf8Properties config = new Utf8Properties();
    config.load(configFile());
    assertEquals("ハロー・ワールド", config.getProperty(KEY_HELLO));
  }

  @Test
  public void testLoadString() throws URISyntaxException, FileNotFoundException, IOException {
    Utf8Properties config = new Utf8Properties();
    config.load(configFile().toPath().toString());
    assertEquals("ハロー・ワールド", config.getProperty(KEY_HELLO));
  }
}
