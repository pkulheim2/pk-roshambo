/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package pk.demo.roshambo;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import pk.demo.roshambo.model.Handsign;
import pk.demo.roshambo.model.Round;
import pk.demo.roshambo.service.IGameSession;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Route
public class MainView extends VerticalLayout {

    public static final String COUNTER_TEXT = "Rounds count: ";


    public MainView(@Autowired IGameSession gameSessionService) {

        HorizontalLayout containerHead = new HorizontalLayout();
        H2 heading = new H2("Roshambo for Alot  v 0.1");

        Image image = new Image("images/Rock-paper-scissors.small.png", "Rock-paper-scissors");
        containerHead.add(image,heading);

        HorizontalLayout containerButtons = new HorizontalLayout();

        Grid<Round> grid = new Grid<>(Round.class);
        Text roundCounter = new Text(COUNTER_TEXT + "0");
        grid.setColumns("playerOne","playerTwo","result");

        Button buttonRock = new Button("Play Round",
                event -> {
                    // reverse rounds order to show the latest on top
                    List<Round> rounds = gameSessionService.newRound(Handsign.ROCK).stream().collect(Collectors.toList());
                    Collections.reverse(rounds);
                    grid.setItems(rounds);
            roundCounter.setText(COUNTER_TEXT + gameSessionService.getRounds().size()); }
        );

        Button resetSession = new Button("Reset",
                event -> { grid.setItems(gameSessionService.resetSession());
                    roundCounter.setText(COUNTER_TEXT + gameSessionService.getRounds().size()); }

        );
        containerButtons.add(buttonRock, resetSession);

        //main container
        add(containerHead, containerButtons , roundCounter, grid);
    }

}
