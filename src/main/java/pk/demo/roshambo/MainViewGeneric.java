package pk.demo.roshambo;/*
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

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pk.demo.roshambo.model.Handsign;
import pk.demo.roshambo.model.Round;
import pk.demo.roshambo.service.IGameSession;

/**
 * Draft view with all signs throwing possible
 */
@Route("generic")
public class MainViewGeneric extends VerticalLayout {


    public MainViewGeneric(@Autowired IGameSession gameSessionService) {

        HorizontalLayout container = new HorizontalLayout();
        H1 heading = new H1("Roshambo demo");
        Image image = new Image("images/Rock-paper-scissors.svg.png", "Alternative image text");
        container.add(heading, image);


        HorizontalLayout container2 = new HorizontalLayout();

        Grid<Round> grid = new Grid<>(Round.class);
        grid.setColumns("playerOne","playerTwo","result");

        Button buttonRock = new Button("Rock",
                event -> grid.setItems(gameSessionService.newRound(Handsign.ROCK))
        );

        Button buttonPaper = new Button("Paper",
                event -> grid.setItems(gameSessionService.newRound(Handsign.PAPER))
        );

        Button buttonScissors = new Button("Scissors",
                event -> grid.setItems(gameSessionService.newRound(Handsign.SCISSORS))

        );

        Button resetSession = new Button("Reset",
                event -> grid.setItems(gameSessionService.resetSession())

        );
        container2.add(buttonRock, buttonPaper, buttonScissors, resetSession);

        add(container,container2 , grid);

    }

}
