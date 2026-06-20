package io.droidbot.vlucide.demo;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.ColorScheme;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import io.droidbot.vlucide.LucideIcon;
import io.droidbot.vlucide.LucideSvgIcon;

@Route("")
public class DemoView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private final List<LucideSvgIcon> icons = new ArrayList<>();
	private boolean darkTheme = false;

	public DemoView() {
		setSpacing(false);
		setPadding(true);
		setWidthFull();

		add(new H2("Lucide Icons for Vaadin"));

		var controls = new HorizontalLayout();
		controls.addClassName("controls-bar");
		controls.setWidthFull();
		controls.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.BASELINE);

		var colorCombo = new ComboBox<PresetColor>("Color");
		colorCombo.setItems(PresetColor.values());
		colorCombo.setValue(PresetColor.DEFAULT);
		colorCombo.setWidth(140, Unit.PIXELS);

		var sizeField = new TextField("Size (px)");
		sizeField.setValue("40");
		sizeField.setWidth("100px");

		var strokeSelect = new Select<Double>();
		strokeSelect.setLabel("Stroke");
		strokeSelect.setItems(0.5, 1.0, 1.5, 2.0, 3.0, 4.0);
		strokeSelect.setValue(1.0);

		var themeToggle = new Button("Dark Mode");
		themeToggle.addClassName("theme-toggle");
		themeToggle.addClickListener(e -> toggleTheme(themeToggle));

		controls.add(colorCombo, sizeField, strokeSelect, themeToggle);
		add(controls);

		var grid = new Div();
		grid.addClassName("icon-grid");

		for (LucideIcon icon : LucideIcon.values()) {
			var svgIcon = icon.create();
			svgIcon.setSize("40px");
			svgIcon.setStrokeWidth(strokeSelect.getValue());
			icons.add(svgIcon);

			var card = new Div();
			card.addClassName("icon-card");

			var name = new Span(formatName(icon.name()));
			name.addClassName("icon-name");

			card.add(svgIcon, name);
			grid.add(card);
		}

		add(grid);

		colorCombo.addValueChangeListener(e -> {
			var hex = e.getValue().getHex();
			for (var icon : icons) {
				icon.setColor(hex);
			}
		});

		sizeField.addValueChangeListener(e -> {
			var val = e.getValue();
			if (val != null && !val.isBlank()) {
				for (var icon : icons) {
					icon.setSize(val + "px");
				}
			}
		});

		strokeSelect.addValueChangeListener(e -> {
			for (var icon : icons) {
				icon.setStrokeWidth(e.getValue());
			}
		});
	}

	private void toggleTheme(Button toggle) {
		darkTheme = !darkTheme;
		getUI().ifPresent(ui -> {
			var themeList = ui.getElement().getThemeList();
			if (darkTheme) {
				themeList.add("dark");
				ui.getPage().setColorScheme(ColorScheme.DARK);
				toggle.setText("Light Mode");
			} else {
				themeList.remove("dark");
				ui.getPage().setColorScheme(ColorScheme.LIGHT);
				toggle.setText("Dark Mode");
			}
		});
	}

	private static String formatName(String name) {
		return name.toLowerCase().replace('_', ' ');
	}
}
