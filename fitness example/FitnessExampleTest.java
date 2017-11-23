import fitnesse.wiki.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FitnessExampleTest {
    private PageData pageData;
    private PageCrawler crawler;
    private WikiPage root;
    private WikiPage testPage;
    private String expectedResultForTestCase = "<div class=\"setup\">\r\n" +
            "\t<div style=\"float: right;\" class=\"meta\"><a href=\"javascript:expandAll();\">Expand All</a> | <a href=\"javascript:collapseAll();\">Collapse All</a></div>\r\n" +
            "\t<a href=\"javascript:toggleCollapsable('');\">\r\n" +
            "\t\t<img src=\"/files/images/collapsableOpen.gif\" class=\"left\" id=\"img\"/>\r\n" +
            "\t</a>\r\n" +
            "&nbsp;<span class=\"meta\">Set Up: <a href=\"SuiteSetUp\">.SuiteSetUp</a> <a href=\"SuiteSetUp?edit&amp;redirectToReferer=true&amp;redirectAction=\">(edit)</a></span>\r\n" +
            "\t<div class=\"collapsable\" id=\"\">suiteSetUp</div>\r\n" +
            "</div>\r\n" +
            "<div class=\"setup\">\r\n" +
            "\t<div style=\"float: right;\" class=\"meta\"><a href=\"javascript:expandAll();\">Expand All</a> | <a href=\"javascript:collapseAll();\">Collapse All</a></div>\r\n" +
            "\t<a href=\"javascript:toggleCollapsable('');\">\r\n" +
            "\t\t<img src=\"/files/images/collapsableOpen.gif\" class=\"left\" id=\"img\"/>\r\n" +
            "\t</a>\r\n" +
            "&nbsp;<span class=\"meta\">Set Up: <a href=\"SetUp\">.SetUp</a> <a href=\"SetUp?edit&amp;redirectToReferer=true&amp;redirectAction=\">(edit)</a></span>\r\n" +
            "\t<div class=\"collapsable\" id=\"\">setup</div>\r\n" +
            "</div>\r\n" +
            "<span class=\"meta\">variable defined: TEST_SYSTEM=slim</span><br/>the content!include -teardown <a href=\"TearDown\">.TearDown</a><br/><div class=\"teardown\">\r\n" +
            "\t<div style=\"float: right;\" class=\"meta\"><a href=\"javascript:expandAll();\">Expand All</a> | <a href=\"javascript:collapseAll();\">Collapse All</a></div>\r\n" +
            "\t<a href=\"javascript:toggleCollapsable('');\">\r\n" +
            "\t\t<img src=\"/files/images/collapsableOpen.gif\" class=\"left\" id=\"img\"/>\r\n" +
            "\t</a>\r\n" +
            "&nbsp;<span class=\"meta\">Tear Down: <a href=\"SuiteTearDown\">.SuiteTearDown</a> <a href=\"SuiteTearDown?edit&amp;redirectToReferer=true&amp;redirectAction=\">(edit)</a></span>\r\n" +
            "\t<div class=\"collapsable\" id=\"\">suiteTearDown</div>\r\n" +
            "</div>\r\n";

    private String expectedResultForNonTestCase = "<div class=\"setup\">\r\n"+
            "\t<div style=\"float: right;\" class=\"meta\"><a href=\"javascript:expandAll();\">Expand All</a> | <a href=\"javascript:collapseAll();\">Collapse All</a></div>\r\n"+
            "\t<a href=\"javascript:toggleCollapsable('');\">\r\n"+
            "\t\t<img src=\"/files/images/collapsableOpen.gif\" class=\"left\" id=\"img\"/>\r\n"+
            "\t</a>\r\n"+
            "&nbsp;<span class=\"meta\">Set Up: <a href=\"SetUp\">.SetUp</a> <a href=\"SetUp?edit&amp;redirectToReferer=true&amp;redirectAction=\">(edit)</a></span>\r\n"+
            "\t<div class=\"collapsable\" id=\"\">setup</div>\r\n"+
            "</div>\r\n"+
            "<div class=\"setup\">\r\n"+
            "\t<div style=\"float: right;\" class=\"meta\"><a href=\"javascript:expandAll();\">Expand All</a> | <a href=\"javascript:collapseAll();\">Collapse All</a></div>\r\n"+
            "\t<a href=\"javascript:toggleCollapsable('');\">\r\n"+
            "\t\t<img src=\"/files/images/collapsableOpen.gif\" class=\"left\" id=\"img\"/>\r\n"+
            "\t</a>\r\n"+
            "&nbsp;<span class=\"meta\">Set Up: <a href=\"SuiteSetUp\">.SuiteSetUp</a> <a href=\"SuiteSetUp?edit&amp;redirectToReferer=true&amp;redirectAction=\">(edit)</a></span>\r\n"+
            "\t<div class=\"collapsable\" id=\"\">suiteSetUp</div>\r\n"+
            "</div>\r\n"+
            "<div class=\"setup\">\r\n"+
            "\t<div style=\"float: right;\" class=\"meta\"><a href=\"javascript:expandAll();\">Expand All</a> | <a href=\"javascript:collapseAll();\">Collapse All</a></div>\r\n"+
            "\t<a href=\"javascript:toggleCollapsable('');\">\r\n"+
            "\t\t<img src=\"/files/images/collapsableOpen.gif\" class=\"left\" id=\"img\"/>\r\n"+
            "\t</a>\r\n"+
            "&nbsp;<span class=\"meta\">Set Up: <a href=\"SetUp\">.SetUp</a> <a href=\"SetUp?edit&amp;redirectToReferer=true&amp;redirectAction=\">(edit)</a></span>\r\n"+
            "\t<div class=\"collapsable\" id=\"\">setup</div>\r\n"+
            "</div>\r\n"+
            "<span class=\"meta\">variable defined: TEST_SYSTEM=slim</span><br/>the content!include -teardown <a href=\"TearDown\">.TearDown</a><br/><div class=\"teardown\">\r\n"+
            "\t<div style=\"float: right;\" class=\"meta\"><a href=\"javascript:expandAll();\">Expand All</a> | <a href=\"javascript:collapseAll();\">Collapse All</a></div>\r\n"+
            "\t<a href=\"javascript:toggleCollapsable('');\">\r\n"+
            "\t\t<img src=\"/files/images/collapsableOpen.gif\" class=\"left\" id=\"img\"/>\r\n"+
            "\t</a>\r\n"+
            "&nbsp;<span class=\"meta\">Tear Down: <a href=\"SuiteTearDown\">.SuiteTearDown</a> <a href=\"SuiteTearDown?edit&amp;redirectToReferer=true&amp;redirectAction=\">(edit)</a></span>\r\n"+
            "\t<div class=\"collapsable\" id=\"\">suiteTearDown</div>\r\n"+
            "</div>\r\n"+
            "<div class=\"teardown\">\r\n"+
            "\t<div style=\"float: right;\" class=\"meta\"><a href=\"javascript:expandAll();\">Expand All</a> | <a href=\"javascript:collapseAll();\">Collapse All</a></div>\r\n"+
            "\t<a href=\"javascript:toggleCollapsable('');\">\r\n"+
            "\t\t<img src=\"/files/images/collapsableOpen.gif\" class=\"left\" id=\"img\"/>\r\n"+
            "\t</a>\r\n"+
            "&nbsp;<span class=\"meta\">Tear Down: <a href=\"TearDown\">.TearDown</a> <a href=\"TearDown?edit&amp;redirectToReferer=true&amp;redirectAction=\">(edit)</a></span>\r\n"+
            "\t<div class=\"collapsable\" id=\"\">teardown</div>\r\n"+
            "</div>\r\n";

    @Before
    public void setUp() throws Exception {
        root = InMemoryPage.makeRoot("RooT");
        crawler = root.getPageCrawler();
        testPage = addPage("TestPage", "!define TEST_SYSTEM {slim}\r\n" + "the content");
        addPage("SetUp", "setup");
        addPage("TearDown", "teardown");
        addPage("SuiteSetUp", "suiteSetUp");
        addPage("SuiteTearDown", "suiteTearDown");

        crawler.addPage(testPage, PathParser.parse("ScenarioLibrary"), "scenario library 2");

        pageData = testPage.getData();
    }

    private boolean includeSuiteSetup(boolean b) {
        return b;
    }

    private String removeMagicNumber(String expectedResult) {
        return expectedResult.replaceAll("[-]*\\d+", "");
    }

    private WikiPage addPage(String pageName, String content) throws Exception {
        return crawler.addPage(root, PathParser.parse(pageName), content);
    }

    @Test
    public void testableHtml() throws Exception {
        String expectedResult = removeMagicNumber(expectedResultForTestCase);
        String testableHtml = new FitnessExample().testableHtml(pageData, includeSuiteSetup(true));
        testableHtml = removeMagicNumber(testableHtml);
        assertThat(testableHtml, is(expectedResult));

        testableHtml = new FitnessExample().testableHtml(pageData, includeSuiteSetup(false));
        testableHtml = removeMagicNumber(testableHtml);
        expectedResultForNonTestCase = removeMagicNumber(expectedResultForNonTestCase);
        assertThat(testableHtml, is(expectedResultForNonTestCase));
    }
}
