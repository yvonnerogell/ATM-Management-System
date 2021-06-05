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
//import org.assertj.swing.fixture.DialogFixture;
//import org.assertj.swing.fixture.FrameFixture;
//import org.assertj.swing.fixture.JButtonFixture;
//import org.assertj.swing.fixture.JOptionPaneFixture;
//import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
//import org.junit.Ignore;
//import org.junit.jupiter.api.Test;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.concurrent.TimeUnit;
//
//import static com.jayway.awaitility.Awaitility.await;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//public class AdminEditValidPinTest extends AssertJSwingJUnitTestCase {
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
//    void editValidPin() {
//
//        Admin editFrame = GuiActionRunner.execute(() -> new Admin());
//        window = new FrameFixture(robot, editFrame);
//        window.show();
//
//        AccountData testAccount = new AccountData("1234", "Danni", "Checking", "123456789", "500");
//
//        editFrame.customerlist.add(testAccount);
//
//        boolean success = false;
//        try {
//            Thread editThread = new Thread(() -> {
//                uiEdit(robot);
//            });
//            editThread.start();
//            editFrame.edit("1111");
//            editThread.join();
//        } catch (Exception e) {
//
//        }
//
//        // Assert
//        AfterLogin al = new AfterLogin();
//        al.loadPersons();
//        for (Object atm : al.customerlist) {
//            assertNotEquals(testAccount, atm);
//        }
//    }
//
//    public void uiEdit(Robot robot) {
//        JOptionPaneFixture optionPaneEdit = JOptionPaneFinder.findOptionPane().using(robot);
//
//        final JButtonFixture yes = optionPaneEdit.button(new GenericTypeMatcher<JButton>(JButton.class) {
//            @Override
//            protected boolean isMatching(@Nonnull JButton component) {
//                return component.getText().equals("Yes");
//            }
//        });
//        yes.click();
//
//        optionPaneEdit = JOptionPaneFinder.findOptionPane().using(robot);
//
//        final DialogFixture inputOption = optionPaneEdit.dialog(new GenericTypeMatcher<Dialog>(Dialog.class) {
//            @Override
//            protected boolean isMatching(@Nonnull Dialog component) {
//                return component.getTitle().equals("EDITING MENU");
//            }
//        });
//        inputOption.textBox().enterText("1");
//
//        final JButtonFixture okOption = optionPaneEdit.button(new GenericTypeMatcher<JButton>(JButton.class) {
//            @Override
//            protected boolean isMatching(@Nonnull JButton component) {
//                return component.getText().equals("OK");
//            }
//        });
//        okOption.click();
//
//        final DialogFixture inputPin = optionPaneEdit.dialog(new GenericTypeMatcher<Dialog>(Dialog.class) {
//            @Override
//            protected boolean isMatching(@Nonnull Dialog component) {
//                return component.getTitle().equals("EDIT PINCODE");
//            }
//        });
//        inputPin.textBox().enterText("2222");
//
//        final JButtonFixture okPin = optionPaneEdit.button(new GenericTypeMatcher<JButton>(JButton.class) {
//            @Override
//            protected boolean isMatching(@Nonnull JButton component) {
//                return component.getText().equals("OK");
//            }
//        });
//        okPin.click();
//
//        await()
//                .atMost(30, TimeUnit.SECONDS)
//                .until(optionPaneEdit::requireNotVisible);
//    }
//}