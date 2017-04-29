package com.onlineSchool;

import com.onlineSchool.Controllerstest.Signupcheck;
import com.onlineSchool.Controllerstest.Studenttest;
import com.onlineSchool.Controllerstest.TeacherTest;
import com.onlineSchool.Controllerstest.Teachertest;
import com.onlineSchool.Controllerstest.Teachertest1;
import com.onlineSchool.Controllerstest.Teachertest2;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SystemTest {

  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(Teachertest.class);
    suite.addTestSuite(TeacherTest.class);
    suite.addTestSuite(Teachertest1.class);
    suite.addTestSuite(Teachertest2.class);
    suite.addTestSuite(Studenttest.class);
    suite.addTestSuite(Signupcheck.class);
    return suite;
  }

  public static void main(String[] args) {
    junit.textui.TestRunner.run(suite());
  }
}
