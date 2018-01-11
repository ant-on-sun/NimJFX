package com.springsun.nimjfx.view.appView;

import com.springsun.nimjfx.controller.ComputerMove;
import com.springsun.nimjfx.controller.MadComputerMove;
import com.springsun.nimjfx.controller.Main;
import com.springsun.nimjfx.model.ListOfHeaps;
import com.springsun.nimjfx.model.ListOfPlayers;
import com.springsun.nimjfx.view.IView;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import java.util.logging.Logger;
import static com.springsun.nimjfx.view.appView.FirstSceneController.getListOfHeaps;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ComputerMove.class, MadComputerMove.class})
public class MainSceneControllerTest extends ApplicationTest {
    ListOfHeaps h;
    ListOfPlayers p;
    IView vmock;
    Logger logmock;
    Stage stage;

    @Before
    public void setUp() throws Exception {
        vmock = mock(IView.class);
        logmock = mock(Logger.class);
        PowerMockito.mockStatic(ComputerMove.class, MadComputerMove.class);

        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Main.class);
        stage = Main.getPrimaryStage();
    }

    @After
    public void tearDown() throws Exception {
        p = null;
        vmock = null;
    }

    @Test
    public void basicLogicOfTheGameTest1() throws Exception {
        clickOn(stage.getScene().lookup(".start-button"));
        h = getListOfHeaps();
        clickOn(stage.getScene().lookup(".opponent-box1")).clickOn("3");
        clickOn(stage.getScene().lookup(".move-button"));
        verify(logmock, never()).severe("No matches in method basicLogicOfTheGame");
        PowerMockito.verifyStatic(ComputerMove.class, Mockito.times(1));
        ComputerMove.moveComputer(h);
        PowerMockito.verifyStatic(MadComputerMove.class, Mockito.never());
        MadComputerMove.moveMadComputer(h);
    }

    @Test
    public void basicLogicOfTheGameTest2() throws Exception {
        clickOn(stage.getScene().lookup(".opponent-box2")).clickOn("1");
        clickOn(stage.getScene().lookup(".opponent-box1")).clickOn("0");
        clickOn(stage.getScene().lookup(".start-button"));
        h = getListOfHeaps();
        clickOn(stage.getScene().lookup(".opponent-box3")).clickOn("6");
        clickOn(stage.getScene().lookup(".move-button"));
        verify(logmock, never()).severe("No matches in method basicLogicOfTheGame");
        PowerMockito.verifyStatic(MadComputerMove.class, Mockito.times(1));
        MadComputerMove.moveMadComputer(h);
        PowerMockito.verifyStatic(ComputerMove.class, Mockito.never());
        ComputerMove.moveComputer(h);
    }

}