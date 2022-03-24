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

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import pk.demo.roshambo.model.Summary;
import pk.demo.roshambo.service.IGameEngine;

import java.util.List;

@Route("summary")
public class SummaryView extends VerticalLayout {


    public SummaryView(IGameEngine gameEngine) {

        H1 heading = new H1("Roshambo demo");
        Image image = new Image("images/Rock-paper-scissors.svg.png", "Roshambo image");

        Grid<Summary> grid = new Grid<>(Summary.class);
        grid.setItems(List.of(gameEngine.getSummary()));

        Button refresh = new Button("Refresh",
                event -> grid.setItems(List.of(gameEngine.getSummary()))

        );

        add(heading, refresh, image, grid);
    }

}