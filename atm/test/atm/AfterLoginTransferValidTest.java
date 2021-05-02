package atm;

import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.ComponentLookupScope;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.core.Robot;
import org.assertj.swing.dependency.jsr305.Nonnull;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.finder.JOptionPaneFinder;
import org.assertj.swing.fixture.DialogFixture;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JOptionPaneFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AfterLoginTransferValidTest extends AssertJSwingJUnitTestCase {

    private Robot robot = BasicRobot.robotWithNewAwtHierarchy();
    private FrameFixture window;


    @Override
    protected void onSetUp() throws Exception {
        FailOnThreadViolationRepaintManager.install();
        robot.settings().componentLookupScope(ComponentLookupScope.ALL);
    }


    @Test
    void transfer() {

        // Arrange
        AfterLogin transferFrame = GuiActionRunner.execute(() -> new AfterLogin());
        window = new FrameFixture(robot, transferFrame);
        window.show();

        // Act

        try {
            Thread transferThread = new Thread(() -> {
                uiTransfer(robot);
            });
            transferThread.start();
            transferFrame.transfer("1234");
            transferThread.join();
        } catch (Exception e) {

        }

        // Assert
        assertEquals("112", transferFrame.customerlist.get(0).startbalance);
    }


    public void uiTransfer(Robot robot) {
        JOptionPaneFixture optionPaneTransfer = JOptionPaneFinder.findOptionPane().using(robot);

        final DialogFixture inputAccount = optionPaneTransfer.dialog(new GenericTypeMatcher<Dialog>(Dialog.class) {
            @Override
            protected boolean isMatching(@Nonnull Dialog component) {
                return component.getTitle().equals("MONEY TRANSACTION MENU");
            }
        });
        inputAccount.textBox().enterText("1111");

        final JButtonFixture okAccount = optionPaneTransfer.button(new GenericTypeMatcher<JButton>(JButton.class) {
            @Override
            protected boolean isMatching(@Nonnull JButton component) {
                return component.getText().equals("OK");
            }
        });
        okAccount.click();

        optionPaneTransfer = JOptionPaneFinder.findOptionPane().using(robot);

        final DialogFixture inputAmount = optionPaneTransfer.dialog(new GenericTypeMatcher<Dialog>(Dialog.class) {
            @Override
            protected boolean isMatching(@Nonnull Dialog component) {
                return component.getTitle().equals("MONEYTRANSACTION MENU");
            }
        });
        inputAmount.textBox().enterText("11");

        final JButtonFixture okAmount = optionPaneTransfer.button(new GenericTypeMatcher<JButton>(JButton.class) {
            @Override
            protected boolean isMatching(@Nonnull JButton component) {
                return component.getText().equals("OK");
            }
        });
        okAmount.click();

        optionPaneTransfer = JOptionPaneFinder.findOptionPane().using(robot);

        final JButtonFixture okFinal = optionPaneTransfer.button(new GenericTypeMatcher<JButton>(JButton.class) {
            @Override
            protected boolean isMatching(@Nonnull JButton component) {
                return component.getText().equals("OK");
            }
        });
        okFinal.click();

        await()
                .atMost(30, TimeUnit.SECONDS)
                .until(optionPaneTransfer::requireNotVisible);
    }
}
