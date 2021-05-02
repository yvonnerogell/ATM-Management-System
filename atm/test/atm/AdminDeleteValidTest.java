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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminDeleteValidTest extends AssertJSwingJUnitTestCase {

    private Robot robot = BasicRobot.robotWithNewAwtHierarchy();
    private FrameFixture window;

    @Override
    protected void onSetUp() {
        FailOnThreadViolationRepaintManager.install();
        robot.settings().componentLookupScope(ComponentLookupScope.ALL);
    }

    @Test
    void deleteValidTest() {

        Admin deleteFrame = GuiActionRunner.execute(() -> new Admin());
        window = new FrameFixture(robot, deleteFrame);
        window.show();

        AccountData testAccount = new AccountData("1234", "Danni", "Checking", "123456789", "500");

        deleteFrame.customerlist.add(testAccount);

        try {
            Thread deleteThread = new Thread(() -> {
                uiDelete(robot);
            });
            deleteThread.start();
            deleteFrame.delete("1234");
            deleteThread.join();
        } catch (Exception e) {

        }

        // Assert
        AfterLogin al = new AfterLogin();
        al.loadPersons();
        assertTrue(!al.customerlist.contains(testAccount));
    }

    public void uiDelete(Robot robot) {
        JOptionPaneFixture optionPaneDelete = JOptionPaneFinder.findOptionPane().using(robot);

        final JButtonFixture yes = optionPaneDelete.button(new GenericTypeMatcher<JButton>(JButton.class) {
            @Override
            protected boolean isMatching(@Nonnull JButton component) {
                return component.getText().equals("Yes");
            }
        });
        yes.click();

        await()
                .atMost(30, TimeUnit.SECONDS)
                .until(optionPaneDelete::requireNotVisible);
    }
}