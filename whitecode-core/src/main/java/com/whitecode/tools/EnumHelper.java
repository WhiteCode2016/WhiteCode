package com.whitecode.tools;

import com.whitecode.bean.LabelValueBean;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

/**
 * Created by White on 2017/12/11.
 */
public abstract class EnumHelper {
    private static final Object[] EMPTY_OBJECT_ARRAY = {};
    private static final String DOT =".";

    /**
     * 创建自定义的下拉框
     * @param enumClass
     * @param messageSource
     * @param defineSelect 首个选项，如："--请选择--"
     * @param <E>
     * @return
     */
    public static <E extends Enum<E>> List<LabelValueBean<String>> createLabelValueBeanList(Class<E> enumClass, MessageSource messageSource,LabelValueBean<String> defineSelect) {
        List<LabelValueBean<String>> list = new ArrayList<LabelValueBean<String>>();
        if (defineSelect != null) {
            list.add(defineSelect);
        }
        if (enumClass == null) {
            return list;
        }
        EnumSet<E> enumSet = EnumSet.allOf(enumClass);
        for (E e : enumSet) {
            LabelValueBean<String> lb = new LabelValueBean<String>();
            lb.setLabel(getEnumResourceMessage(e,messageSource));
            lb.setValue(e.name());
            list.add(lb);
        }
        return list;
    }

    /**
     * 创建是否带有 "--请选择--" 下拉框
     * @param enumClass
     * @param messageSource
     * @param needPleaseSelect true/false 显示 "--请选择--"
     * @param <E>
     * @return
     */
    public static <E extends Enum<E>> List<LabelValueBean<String>> createLabelValueBeanList(Class<E> enumClass, MessageSource messageSource, boolean needPleaseSelect) {
        if (needPleaseSelect) {
            return createLabelValueBeanList(enumClass,messageSource,new LabelValueBean<String>("--请选择--",""));
        }else {
            return createLabelValueBeanList(enumClass,messageSource,null);
        }
    }

    public static String createEnumResourceKey(String enumDeclaringClassName, String enumName) {
        return enumDeclaringClassName + DOT + enumName;
    }

    public static String createEnumResourceKey(Enum<?> e) {
        if (e == null) {
            return null;
        }
        return createEnumResourceKey(e.getDeclaringClass().getName(), e.name());
    }

    public static String getEnumResourceMessage(String enumDeclaringClassName, String enumName, MessageSource messageSource) {
        return getEnumResourceMessageWithDefaultMessage(createEnumResourceKey(enumDeclaringClassName, enumName), enumName, messageSource);
    }

    public static String getEnumResourceMessage(Enum<?> e, MessageSource messageSource) {
        return getEnumResourceMessage(e, messageSource, LocaleContextHolder.getLocale());
    }

    public static String getEnumResourceMessage(Enum<?> e, MessageSource messageSource, Locale language) {
        if (e == null) {
            return null;
        }
        return getEnumResourceMessageWithDefaultMessage(createEnumResourceKey(e), e.name(), messageSource, language);
    }

    public static String getEnumResourceMessage(String enumResourceKey, MessageSource messageSource) {
        return getEnumResourceMessageWithDefaultMessage(enumResourceKey, null, messageSource, LocaleContextHolder.getLocale());
    }

    public static String getEnumResourceMessageWithDefaultMessage(String enumResourceKey, String defaultMessage, MessageSource messageSource) {
        return getEnumResourceMessageWithDefaultMessage(enumResourceKey, defaultMessage, messageSource, LocaleContextHolder.getLocale());
    }

    public static String getEnumResourceMessageWithDefaultMessage(String enumResourceKey, String defaultMessage, MessageSource messageSource, Locale language) {
        if (messageSource == null) {
            return defaultMessage;
        }
        return messageSource.getMessage(enumResourceKey, EMPTY_OBJECT_ARRAY, defaultMessage, language);
    }
}
