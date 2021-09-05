package es.rubioric.rsp;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * 
 *
 *<p> 
 *  i18n configuration.
 *</p>
 *
 * @author Ricardo Rubio
 *
 */
@Configuration
public class I18nConfiguration implements WebMvcConfigurer {

  /**
   * 
   * <p>Returns a SessionLocaleResolver with default locale set to EN.
   * </p>
   * ¶@return a SessionLocaleResolver with default locale set to EN.
   */
  @Bean
  public LocaleResolver localeResolver() {
    SessionLocaleResolver localeResolver = new SessionLocaleResolver();
    localeResolver.setDefaultLocale(Locale.ENGLISH);

    return localeResolver;
  }

  /**
   * 
   * <p>Returns a LocaleChangeInterceptor with param name set to "lang".
   * </p>
   * ¶@return a LocaleChangeInterceptor with param name set to "lang".
   */
  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
    localeInterceptor.setIgnoreInvalidLocale(true);
    localeInterceptor.setParamName("lang");

    return localeInterceptor;
  }
  
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }
}
