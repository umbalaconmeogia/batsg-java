package com.chauhai.batsg.jsp.jstl;

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
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;

import com.chauhai.batsg.util.Utf8ResourceBundleControl;

/**
 * Set bundle for JSTL fmt from resource files outside the code package.
 *
 * @author umbalaconmeogia
 */
public class Fmt {
  /**
   * Set bundle for JSTL fmt from resource files outside the code package.
   * @param pageContext
   * @param directory
   * @param baseName
   */
  public static void setBundle(PageContext pageContext, String directory, String baseName) {
    System.out.println(directory + ", " + baseName);
    // Get current locale of fmt.
    Locale locale = (Locale) Config.get(pageContext, Config.FMT_LOCALE, PageContext.PAGE_SCOPE);
    // Load ResourceBundle from specified path.
    ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, locale, new Utf8ResourceBundleControl(directory));
    // Create LocalizationContext, set to FMT_LOCALIZATION_CONTEXT
    LocalizationContext localizationContext = new LocalizationContext(resourceBundle);
    Config.set(pageContext, Config.FMT_LOCALIZATION_CONTEXT, localizationContext, PageContext.PAGE_SCOPE);
  }
}
