package com.chauhai.batsg.util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class Utf8ResourceBundleControlTest {

  static final Locale LOCALE = new Locale("ja");

  static final String BUNDLE_DIR = "./fixtures";

  static final String KEY_HELLO = "text.hello";

  @Test
  public void testNewBundleFromResource() throws URISyntaxException {
    String baseName = "fixtures.utf8";
    ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, LOCALE, new Utf8ResourceBundleControl());
    assertEquals("ハロー・ワールド", resourceBundle.getString(KEY_HELLO));
  }

  @Test
  public void testNewBundleFromFile() throws URISyntaxException {
    String dir = new File(getClass().getClassLoader().getResource(BUNDLE_DIR).toURI()).getAbsolutePath();
//    System.out.println("Dir " + dir);
    String baseName = "utf8";
    ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, LOCALE, new Utf8ResourceBundleControl(dir));
    assertEquals("ハロー・ワールド", resourceBundle.getString(KEY_HELLO));
  }

}
