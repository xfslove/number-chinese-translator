package com.supwisdom.eams.infras.number;


/**
 * 中文数字翻译工具
 * Created by hanwen on 2016/11/28.
 */
public abstract class NumberZhTranslator {

  private NumberZhTranslator() {
  }

  /**
   * 简体中文0-9
   */
  public static final String[] SIMPLIFIED_ZH_029 = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

  /**
   * 简体中文10
   */
  public static final String SIMPLIFIED_ZH_10 = "十";

  /**
   * 简体中文100
   */
  public static final String SIMPLIFIED_ZH_100 = "百";

  /**
   * 简体中文1000
   */
  public static final String SIMPLIFIED_ZH_1000 = "千";

  /**
   * 简体中文10000
   */
  public static final String SIMPLIFIED_ZH_10000 = "万";

  /**
   * 简体中文1亿
   */
  public static final String SIMPLIFIED_ZH_HUNDRED_MILLION = "亿";

  /**
   * 简体负号
   */
  public static final String SIMPLIFIED_ZH_NEGATIVE = "负";

  /**
   * 简体中文点
   */
  public static final String SIMPLIFIED_ZH_DOT = "点";

  /**
   * 繁体中文0-9
   */
  public static final String[] TRADITIONAL_ZH_029 = {"零", "壹", "貳", "叁", "肆", "伍", "陸", "柒", "捌", "玖"};

  /**
   * 繁体中文10
   */
  public static final String TRADITIONAL_ZH_10 = "拾";

  /**
   * 繁体中文100
   */
  public static final String TRADITIONAL_ZH_100 = "佰";

  /**
   * 繁体中文1000
   */
  public static final String TRADITIONAL_ZH_1000 = "仟";

  /**
   * 繁体中文10000
   */
  public static final String TRADITIONAL_ZH_10000 = "萬";

  /**
   * 繁体中文1亿
   */
  public static final String TRADITIONAL_ZH_HUNDRED_MILLION = "億";

  /**
   * 繁体中文负号
   */
  public static final String TRADITIONAL_ZH_NEGATIVE = "負";

  /**
   * 繁体中文点
   */
  public static final String TRADITIONAL_ZH_DOT = "點";


  private static final Long MAX = 9999999999999999L;

  private static final String EMPTY = "";

  /**
   * @param number
   * @param simplified
   * @return
   * @see #translateNumberDirect(Long, boolean)
   */
  public static String translateNumberDirect(Integer number, boolean simplified) {
    return translateNumberDirect(Long.valueOf(number), simplified);
  }

  /**
   * @param number
   * @param simplified
   * @return
   * @see #translateNumber(Long, boolean)
   */
  public static String translateNumber(Integer number, boolean simplified) {
    return translateNumber(Long.valueOf(number), simplified);
  }

  /**
   * @param number
   * @param simplified
   * @return
   * @see #translateNumberDirect(Long, boolean)
   */
  public static String translateNumberDirect(Float number, boolean simplified) {
    return translateNumberDirect(Double.valueOf(number), simplified);
  }

  /**
   * @param number
   * @param simplified
   * @return
   * @see #translateNumber(Long, boolean)
   */
  public static String translateNumber(Float number, boolean simplified) {
    return translateNumber(Double.valueOf(number), simplified);
  }

  /**
   * @param number
   * @param simplified
   * @return
   * @see #translateNumberDirect(Long, boolean)
   */
  public static String translateNumberDirect(Double number, boolean simplified) {

    if (number == null) {
      return null;
    }

    String numberString = String.valueOf(number);

    String[] split = numberString.split("\\.");

    char[] integerArray = split[0].toCharArray();
    char[] array;

    String negativePrefix = EMPTY;
    if (number < 0) {

      array = new char[integerArray.length - 1];
      System.arraycopy(integerArray, 1, array, 0, integerArray.length - 1);

      negativePrefix = simplified ? SIMPLIFIED_ZH_NEGATIVE : TRADITIONAL_ZH_NEGATIVE;
    } else {

      array = integerArray;
    }

    Long floatPlace = Long.parseLong(split[1]);
    if (floatPlace == 0) {

      return negativePrefix + translateNumberDirect(array, simplified);
    }

    return negativePrefix +
        translateNumberDirect(array, simplified) +
        (simplified ? SIMPLIFIED_ZH_DOT : TRADITIONAL_ZH_DOT) +
        translateNumberDirect(split[1].toCharArray(), simplified);

  }

  /**
   * @param number
   * @param simplified
   * @return
   * @see #translateNumber(Long, boolean)
   */
  public static String translateNumber(Double number, boolean simplified) {

    if (number == null) {
      return null;
    }

    String numberString = String.valueOf(number);

    String[] split = numberString.split("\\.");

    char[] integerArray = split[0].toCharArray();
    char[] array;

    String negativePrefix = EMPTY;
    if (number < 0) {

      array = new char[integerArray.length - 1];
      System.arraycopy(integerArray, 1, array, 0, integerArray.length - 1);

      negativePrefix = simplified ? SIMPLIFIED_ZH_NEGATIVE : TRADITIONAL_ZH_NEGATIVE;
    } else {

      array = integerArray;
    }

    Long floatPlace = Long.parseLong(split[1]);
    if (floatPlace == 0) {

      return negativePrefix + translateNumber(array, simplified);
    }

    return negativePrefix +
        translateNumber(array, simplified) +
        (simplified ? SIMPLIFIED_ZH_DOT : TRADITIONAL_ZH_DOT) +
        translateNumberDirect(split[1].toCharArray(), simplified);

  }

  /**
   * 把数字直接翻译成中文，就是没有十，百，千，万，亿这种中文单位
   *
   * @param number
   * @param simplified 是否简体
   * @return
   */
  public static String translateNumberDirect(Long number, boolean simplified) {

    if (number == null) {
      return null;
    }

    char[] romanArray = String.valueOf(number).toCharArray();
    char[] array;

    String negativePrefix = EMPTY;
    if (number < 0) {

      array = new char[romanArray.length - 1];
      System.arraycopy(romanArray, 1, array, 0, romanArray.length - 1);

      negativePrefix = simplified ? SIMPLIFIED_ZH_NEGATIVE : TRADITIONAL_ZH_NEGATIVE;
    } else {

      array = romanArray;
    }

    return negativePrefix + translateNumberDirect(array, simplified);

  }

  /**
   * <pre>
   * 把数字翻译成中文写法
   * 因为中文写法有规范的最大数值到千万亿，也就是16位数，超过16位没有规范，有的是以亿进位，比如亿亿什么的，有的是更高的单位，比如兆，京，什么的...
   * 所以这里如果超过16位数直接翻译成罗马数字对应的中文，看{@link NumberZhTranslator#translateNumberDirect(Long, boolean)}
   * </pre>
   *
   * @param number
   * @param simplified 是否简体
   * @return
   */
  public static String translateNumber(Long number, boolean simplified) {

    if (number == null) {
      return null;
    }

    if (number > MAX) {
      return translateNumberDirect(number, simplified);
    }

    char[] romanArray = String.valueOf(number).toCharArray();
    char[] array;

    String negativePrefix = EMPTY;
    if (number < 0) {

      array = new char[romanArray.length - 1];
      System.arraycopy(romanArray, 1, array, 0, romanArray.length - 1);

      negativePrefix = simplified ? SIMPLIFIED_ZH_NEGATIVE : TRADITIONAL_ZH_NEGATIVE;
    } else {

      array = romanArray;
    }

    return negativePrefix + translateNumber(array, simplified);

  }


  private static String translateNumberDirect(char[] romanArray, boolean simplified) {

    String numberString = EMPTY;
    for (char romanChar : romanArray) {
      numberString += translate1NumberChar(romanChar, simplified, false);
    }

    return numberString;
  }

  private static String translateNumber(char[] romanArray, boolean simplified) {

    int length = romanArray.length;

    if (length == 1) {

      return translate1NumberChar(romanArray[0], simplified, false);
    } else if (length == 2) {

      return translate2NumberChar(romanArray[0], romanArray[1], simplified, true);
    } else if (length == 3) {

      return translate3NumberChar(romanArray[0], romanArray[1], romanArray[2], simplified);
    } else if (length == 4) {

      return translate4NumberChar(romanArray[0], romanArray[1], romanArray[2], romanArray[3], simplified);
    } else if (length <= 8) {

      int higherLength = length - 4;

      char[] higherArray = new char[higherLength];
      char[] lowerArray = new char[4];

      System.arraycopy(romanArray, 0, higherArray, 0, higherLength);
      System.arraycopy(romanArray, higherLength, lowerArray, 0, 4);

      String zh10000 = simplified ? SIMPLIFIED_ZH_10000 : TRADITIONAL_ZH_10000;
      return translateNumber(higherArray, simplified) + zh10000 + translateNumber(lowerArray, simplified);

    } else if (length <= 16) {

      int higherLength = length - 8;

      char[] higherArray = new char[higherLength];
      char[] lowerArray = new char[8];

      System.arraycopy(romanArray, 0, higherArray, 0, higherLength);
      System.arraycopy(romanArray, higherLength, lowerArray, 0, 8);

      String zhHundredMillion = simplified ? SIMPLIFIED_ZH_HUNDRED_MILLION : TRADITIONAL_ZH_HUNDRED_MILLION;
      return translateNumber(higherArray, simplified) + zhHundredMillion + translateNumber(lowerArray, simplified);

    }

    return EMPTY;
  }

  /**
   * 转换4位数
   *
   * @param kilobitChar
   * @param hundredsPlaceChar
   * @param decadeChar
   * @param theUnitChar
   * @param simplified
   * @return
   */
  private static String translate4NumberChar(char kilobitChar, char hundredsPlaceChar, char decadeChar, char theUnitChar, boolean simplified) {
    int kilobit = numberCharToInt(kilobitChar);
    int hundredsPlace = numberCharToInt(hundredsPlaceChar);
    int decade = numberCharToInt(decadeChar);
    int theUnit = numberCharToInt(theUnitChar);

    String zh1000 = simplified ? SIMPLIFIED_ZH_1000 : TRADITIONAL_ZH_1000;
    String zh0 = simplified ? SIMPLIFIED_ZH_029[0] : TRADITIONAL_ZH_029[0];

    if (kilobit == 0 && hundredsPlace == 0 && decade == 0 && theUnit == 0) {
      return EMPTY;
    }

    if (kilobit == 0 && hundredsPlace == 0 && decade == 0) {
      return zh0 + translate1NumberChar(theUnitChar, simplified, true);
    }

    if (kilobit == 0 && hundredsPlace == 0) {
      return zh0 + translate2NumberChar(decadeChar, theUnitChar, simplified, false);
    }

    if (kilobit == 0) {
      return zh0 + translate3NumberChar(hundredsPlaceChar, decadeChar, theUnitChar, simplified);
    }

    return translate1NumberChar(kilobitChar, simplified, true) + zh1000 + translate3NumberChar(hundredsPlaceChar, decadeChar, theUnitChar, simplified);
  }

  /**
   * 转换3位数
   *
   * @param hundredsPlaceChar
   * @param decadeChar
   * @param theUnitChar
   * @param simplified
   * @return
   */
  private static String translate3NumberChar(char hundredsPlaceChar, char decadeChar, char theUnitChar, boolean simplified) {
    int hundredsPlace = numberCharToInt(hundredsPlaceChar);
    int decade = numberCharToInt(decadeChar);
    int theUnit = numberCharToInt(theUnitChar);

    if (hundredsPlace == 0 && decade == 0 && theUnit == 0) {
      return EMPTY;
    }

    String zh100 = simplified ? SIMPLIFIED_ZH_100 : TRADITIONAL_ZH_100;
    String zh0 = simplified ? SIMPLIFIED_ZH_029[0] : TRADITIONAL_ZH_029[0];


    if (hundredsPlace == 0 && decade == 0) {
      return zh0 + translate1NumberChar(theUnitChar, simplified, true);
    }

    if (hundredsPlace == 0) {

      return zh0 + translate2NumberChar(decadeChar, theUnitChar, simplified, false);
    }

    return translate1NumberChar(hundredsPlaceChar, simplified, true) + zh100 + translate2NumberChar(decadeChar, theUnitChar, simplified, false);
  }

  /**
   * 转换2位数
   *
   * @param decadeChar
   * @param theUnitChar
   * @param simplified
   * @param habit       如果是10-20 得到的结果是不是符合习惯的写法，比如12 符合习惯得到的是十二，不是一十二
   * @return
   */
  private static String translate2NumberChar(char decadeChar, char theUnitChar, boolean simplified, boolean habit) {
    int decade = numberCharToInt(decadeChar);
    int theUnit = numberCharToInt(theUnitChar);

    if (decade == 0 && theUnit == 0) {
      return EMPTY;
    }

    String zh10 = simplified ? SIMPLIFIED_ZH_10 : TRADITIONAL_ZH_10;
    String zh0 = simplified ? SIMPLIFIED_ZH_029[0] : TRADITIONAL_ZH_029[0];

    if (decade == 0) {
      return zh0 + translate1NumberChar(theUnitChar, simplified, true);
    }

    String decadeString = translate1NumberChar(decadeChar, simplified, true);

    if (decade == 1 && habit) {
      decadeString = EMPTY;
    }

    return decadeString + zh10 + translate1NumberChar(theUnitChar, simplified, true);
  }

  /**
   * 转换1位数
   *
   * @param theUnitChar
   * @param simplified
   * @param emptyOnZero 如果是0 是得到""，还是0对应的中文
   * @return
   */
  private static String translate1NumberChar(char theUnitChar, boolean simplified, boolean emptyOnZero) {
    int theUnit = numberCharToInt(theUnitChar);

    if (theUnit == 0 && emptyOnZero) {
      return EMPTY;
    }

    return simplified ? SIMPLIFIED_ZH_029[theUnit] : TRADITIONAL_ZH_029[theUnit];
  }

  private static int numberCharToInt(char value) {
    return value - 48;
  }
}