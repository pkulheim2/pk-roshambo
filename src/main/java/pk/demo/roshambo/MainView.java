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

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import pk.demo.roshambo.model.Handsign;
import pk.demo.roshambo.model.Round;
import pk.demo.roshambo.service.IGameSession;

@Route
public class MainView extends VerticalLayout {


    public MainView(@Autowired IGameSession gameSessionService) {

        HorizontalLayout container = new HorizontalLayout();
        H1 heading = new H1("Roshambo demo");
        Image image = new Image("images/Rock-paper-scissors.svg.png", "Alternative image text");
        container.add(heading, image);


        HorizontalLayout container2 = new HorizontalLayout();

        Grid<Round> grid = new Grid<>(Round.class);
        grid.setColumns("playerOne","playerTwo","result");

        Button buttonRock = new Button("Rock",
                event -> grid.setItems(gameSessionService.newStroke(Handsign.ROCK))
        );

        Button buttonPaper = new Button("Paper",
                event -> grid.setItems(gameSessionService.newStroke(Handsign.PAPER))
        );

        Button buttonScissors = new Button("Scissors",
                event -> grid.setItems(gameSessionService.newStroke(Handsign.SCISSORS))

        );

        Button resetSession = new Button("Reset",
                event -> grid.setItems(gameSessionService.resetSession())

        );
        container2.add(buttonRock, buttonPaper, buttonScissors, resetSession);


        add(container,container2 , grid);


    }

}
