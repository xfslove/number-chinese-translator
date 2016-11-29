package com.supwisdom.eams.infras.number;

import org.testng.annotations.Test;

import static com.supwisdom.eams.infras.number.NumberZhTranslator.translateNumber;
import static com.supwisdom.eams.infras.number.NumberZhTranslator.translateNumberDirect;
import static org.testng.Assert.assertEquals;

/**
 * Created by hanwen on 2016/11/28.
 */
public class NumberZhTranslatorTest {

  @Test
  public void testTranslateIntDirect() throws Exception {

    Integer i = 0;

    assertEquals(translateNumberDirect(i, true), "零");
    assertEquals(translateNumberDirect(i, false), "零");

    Integer i1 = 9;

    assertEquals(translateNumberDirect(i1, true), "九");
    assertEquals(translateNumberDirect(i1, false), "玖");

    Integer i2 = 40;

    assertEquals(translateNumberDirect(i2, true), "四零");
    assertEquals(translateNumberDirect(i2, false), "肆零");

    Integer i3 = 93;

    assertEquals(translateNumberDirect(i3, true), "九三");
    assertEquals(translateNumberDirect(i3, false), "玖叁");

    Integer i4 = 103;

    assertEquals(translateNumberDirect(i4, true), "一零三");
    assertEquals(translateNumberDirect(i4, false), "壹零叁");

    Integer i5 = -130;

    assertEquals(translateNumberDirect(i5, true), "负一三零");
    assertEquals(translateNumberDirect(i5, false), "負壹叁零");

    Integer i6 = 500;

    assertEquals(translateNumberDirect(i6, true), "五零零");
    assertEquals(translateNumberDirect(i6, false), "伍零零");

    Integer i7 = 999;

    assertEquals(translateNumberDirect(i7, true), "九九九");
    assertEquals(translateNumberDirect(i7, false), "玖玖玖");

    Integer i8 = -1000;

    assertEquals(translateNumberDirect(i8, true), "负一零零零");
    assertEquals(translateNumberDirect(i8, false), "負壹零零零");

    Integer i9 = 1003;

    assertEquals(translateNumberDirect(i9, true), "一零零三");
    assertEquals(translateNumberDirect(i9, false), "壹零零叁");

    Integer i10 = 1030;

    assertEquals(translateNumberDirect(i10, true), "一零三零");
    assertEquals(translateNumberDirect(i10, false), "壹零叁零");

    Integer i11 = 1203;

    assertEquals(translateNumberDirect(i11, true), "一二零三");
    assertEquals(translateNumberDirect(i11, false), "壹貳零叁");

    Integer i12 = 1330;

    assertEquals(translateNumberDirect(i12, true), "一三三零");
    assertEquals(translateNumberDirect(i12, false), "壹叁叁零");

    Integer i13 = 1500;

    assertEquals(translateNumberDirect(i13, true), "一五零零");
    assertEquals(translateNumberDirect(i13, false), "壹伍零零");

    Integer i14 = 9865;

    assertEquals(translateNumberDirect(i14, true), "九八六五");
    assertEquals(translateNumberDirect(i14, false), "玖捌陸伍");

  }

  @Test(dependsOnMethods = "testTranslateIntDirect")
  public void testTranslateInt() throws Exception {

    Integer i = 0;

    assertEquals(translateNumber(i, true), "零");
    assertEquals(translateNumber(i, false), "零");

    Integer i1 = 9;

    assertEquals(translateNumber(i1, true), "九");
    assertEquals(translateNumber(i1, false), "玖");

    Integer ii = 10;

    assertEquals(translateNumber(ii, true), "十");
    assertEquals(translateNumber(ii, false), "拾");

    Integer ii1 = 16;

    assertEquals(translateNumber(ii1, true), "十六");
    assertEquals(translateNumber(ii1, false), "拾陸");

    Integer i2 = 40;

    assertEquals(translateNumber(i2, true), "四十");
    assertEquals(translateNumber(i2, false), "肆拾");

    Integer i3 = 93;

    assertEquals(translateNumber(i3, true), "九十三");
    assertEquals(translateNumber(i3, false), "玖拾叁");

    Integer i4 = 103;

    assertEquals(translateNumber(i4, true), "一百零三");
    assertEquals(translateNumber(i4, false), "壹佰零叁");

    Integer i5 = 130;

    assertEquals(translateNumber(i5, true), "一百三十");
    assertEquals(translateNumber(i5, false), "壹佰叁拾");

    Integer i6 = 500;

    assertEquals(translateNumber(i6, true), "五百");
    assertEquals(translateNumber(i6, false), "伍佰");

    Integer i7 = -919;

    assertEquals(translateNumber(i7, true), "负九百一十九");
    assertEquals(translateNumber(i7, false), "負玖佰壹拾玖");

    Integer i8 = 1000;

    assertEquals(translateNumber(i8, true), "一千");
    assertEquals(translateNumber(i8, false), "壹仟");

    Integer i9 = 1003;

    assertEquals(translateNumber(i9, true), "一千零三");
    assertEquals(translateNumber(i9, false), "壹仟零叁");

    Integer i10 = 1030;

    assertEquals(translateNumber(i10, true), "一千零三十");
    assertEquals(translateNumber(i10, false), "壹仟零叁拾");

    Integer i11 = 1203;

    assertEquals(translateNumber(i11, true), "一千二百零三");
    assertEquals(translateNumber(i11, false), "壹仟貳佰零叁");

    Integer i12 = 1330;

    assertEquals(translateNumber(i12, true), "一千三百三十");
    assertEquals(translateNumber(i12, false), "壹仟叁佰叁拾");

    Integer i13 = 1500;

    assertEquals(translateNumber(i13, true), "一千五百");
    assertEquals(translateNumber(i13, false), "壹仟伍佰");

    Integer i14 = 9865;

    assertEquals(translateNumber(i14, true), "九千八百六十五");
    assertEquals(translateNumber(i14, false), "玖仟捌佰陸拾伍");

    Integer i15 = -10100505;

    assertEquals(translateNumber(i15, true), "负一千零一十万零五百零五");
    assertEquals(translateNumber(i15, false), "負壹仟零壹拾萬零伍佰零伍");

    Integer i16 = 1000005;

    assertEquals(translateNumber(i16, true), "一百万零五");
    assertEquals(translateNumber(i16, false), "壹佰萬零伍");

    Integer i17 = 440000000;

    assertEquals(translateNumber(i17, true), "四亿四千万");
    assertEquals(translateNumber(i17, false), "肆億肆仟萬");

    Long i18 = 589098984342L;

    assertEquals(translateNumber(i18, true), "五千八百九十亿九千八百九十八万四千三百四十二");
    assertEquals(translateNumber(i18, false), "伍仟捌佰玖拾億玖仟捌佰玖拾捌萬肆仟叁佰肆拾貳");

    Long i19 = 9999999999999999L;

    assertEquals(translateNumber(i19, true), "九千九百九十九万九千九百九十九亿九千九百九十九万九千九百九十九");
    assertEquals(translateNumber(i19, false), "玖仟玖佰玖拾玖萬玖仟玖佰玖拾玖億玖仟玖佰玖拾玖萬玖仟玖佰玖拾玖");

    Long i20 = 10000000000000000L;

    assertEquals(translateNumber(i20, true), "一零零零零零零零零零零零零零零零零");
    assertEquals(translateNumber(i20, false), "壹零零零零零零零零零零零零零零零零");
  }

  @Test(dependsOnMethods = "testTranslateInt")
  public void testTranslateDoubleDirect() throws Exception {

    Double d = 99d;

    assertEquals(translateNumberDirect(d, true), "九九");
    assertEquals(translateNumberDirect(d, false), "玖玖");

    Double d1 = 0.99d;

    assertEquals(translateNumberDirect(d1, true), "零点九九");
    assertEquals(translateNumberDirect(d1, false), "零點玖玖");

    Double d2 = -10.99d;

    assertEquals(translateNumberDirect(d2, true), "负一零点九九");
    assertEquals(translateNumberDirect(d2, false), "負壹零點玖玖");

    Double d3 = 101.01;

    assertEquals(translateNumberDirect(d3, true), "一零一点零一");
    assertEquals(translateNumberDirect(d3, false), "壹零壹點零壹");
  }

  @Test(dependsOnMethods = "testTranslateDoubleDirect")
  public void testTranslateDouble() throws Exception {

    Double d = 99d;

    assertEquals(translateNumber(d, true), "九十九");
    assertEquals(translateNumber(d, false), "玖拾玖");

    Double d1 = 0.99d;

    assertEquals(translateNumber(d1, true), "零点九九");
    assertEquals(translateNumber(d1, false), "零點玖玖");

    Double d2 = -10.99d;

    assertEquals(translateNumber(d2, true), "负十点九九");
    assertEquals(translateNumber(d2, false), "負拾點玖玖");

    Double d3 = 101.01;

    assertEquals(translateNumber(d3, true), "一百零一点零一");
    assertEquals(translateNumber(d3, false), "壹佰零壹點零壹");

  }

}