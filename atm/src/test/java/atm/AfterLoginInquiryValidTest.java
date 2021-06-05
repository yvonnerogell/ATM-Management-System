//package atm;
//
//import org.assertj.swing.core.BasicRobot;
//import org.assertj.swing.core.ComponentLookupScope;
//import org.assertj.swing.core.GenericTypeMatcher;
//import org.assertj.swing.core.Robot;
//import org.assertj.swing.dependency.jsr305.Nonnull;
//import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
//import org.assertj.swing.edt.GuiActionRunner;
//import org.assertj.swing.finder.JOptionPaneFinder;
//import org.assertj.swing.fixture.FrameFixture;
//import org.assertj.swing.fixture.JButtonFixture;
//import org.assertj.swing.fixture.JOptionPaneFixture;
//import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
//import org.junit.Ignore;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import javax.swing.*;
//import java.util.concurrent.TimeUnit;
//
//import static com.jayway.awaitility.Awaitility.await;
//
//public class AfterLoginInquiryValidTest extends AssertJSwingJUnitTestCase {
//
//    private Robot robot = BasicRobot.robotWithNewAwtHierarchy();
//    private FrameFixture window;
//
//    @Override
//    protected void onSetUp() throws Exception {
//        FailOnThreadViolationRepaintManager.install();
//        robot.settings().componentLookupScope(ComponentLookupScope.ALL);
//    }
//
//    @Test
//    @Ignore
//    void inquiryValidPin() {
//
//        AfterLogin inquiryFrame = GuiActionRunner.execute(() -> new AfterLogin());
//        window = new FrameFixture(robot, inquiryFrame);
//        window.show();
//
//        AccountData testAccount = new AccountData("1234", "Ken", "Checking", "123456789", "500");
//
//        inquiryFrame.customerlist.add(testAccount);
//
//        boolean success = false;
//        try {
//            Thread inquiryThread = new Thread(() -> {
//                uiInquiry(robot);
//            });
//            inquiryThread.start();
//            success = inquiryFrame.inquiry("1234");
//            inquiryThread.join();
//        } catch (Exception e) {
//
//        }
//
//        Assertions.assertTrue(success);
//    }
//
//    public void uiInquiry(Robot robot) {
//        JOptionPaneFixture optionPaneInquiry = JOptionPaneFinder.findOptionPane().using(robot);
//
//        final JButtonFixture ok = optionPaneInquiry.button(new GenericTypeMatcher<JButton>(JButton.class) {
//            @Override
//            protected boolean isMatching(@Nonnull JButton component) {
//                return component.getText().equals("OK");
//            }
//        });
//        ok.click();
//
//        await()
//                .atMost(30, TimeUnit.SECONDS)
//                .until(optionPaneInquiry::requireNotVisible);
//    }
//}
