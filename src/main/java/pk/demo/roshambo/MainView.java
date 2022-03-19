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

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.vaadin.flow.component.grid.Grid;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import pk.demo.roshambo.model.Handsign;
import pk.demo.roshambo.model.Stroke;
import pk.demo.roshambo.service.GameSession;

@Route
public class MainView extends VerticalLayout {


    public MainView(GameSession gameSession) {

        H1 heading = new H1("Roshambo demo");

        Grid<Stroke> grid = new Grid<>(Stroke.class);

        Button buttonRock = new Button("Rock",
                event -> { gameSession.newStroke(Handsign.ROCK);
                    grid.setItems(gameSession.getStrokes());
        });

        Button buttonPaper = new Button("Paper",
                event -> { gameSession.newStroke(Handsign.PAPER);
                    grid.setItems(gameSession.getStrokes());
        });

        Button buttonScissors = new Button("Scissors",
                event -> { gameSession.newStroke(Handsign.SCISSORS);
                    grid.setItems(gameSession.getStrokes());
        });

        add(heading, buttonRock, buttonPaper, buttonScissors, grid);


    }

}
