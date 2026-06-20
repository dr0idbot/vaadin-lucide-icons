package io.droidbot.vlucide.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import io.droidbot.vlucide.LucideIcon;
import io.droidbot.vlucide.LucideSvgIcon;

@Route("")
public class DemoView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private static final String[][] PRESET_COLORS = {
		{ "Default", "" },
		{ "Black", "#000000" },
		{ "White", "#ffffff" },
		{ "Red", "#ef4444" },
		{ "Orange", "#f97316" },
		{ "Amber", "#f59e0b" },
		{ "Yellow", "#eab308" },
		{ "Lime", "#84cc16" },
		{ "Green", "#22c55e" },
		{ "Emerald", "#10b981" },
		{ "Teal", "#14b8a6" },
		{ "Cyan", "#06b6d4" },
		{ "Sky", "#0ea5e9" },
		{ "Blue", "#3b82f6" },
		{ "Indigo", "#6366f1" },
		{ "Violet", "#8b5cf6" },
		{ "Purple", "#a855f7" },
		{ "Fuchsia", "#d946ef" },
		{ "Pink", "#ec4899" },
		{ "Rose", "#f43f5e" }
	};

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

		var colorSelect = new Select<String>();
		colorSelect.setLabel("Color");
		var colorLabels = Arrays.stream(PRESET_COLORS)
			.map(pair -> pair[0])
			.toArray(String[]::new);
		colorSelect.setItems(colorLabels);
		colorSelect.setValue("Black");

		var sizeField = new TextField("Size");
		sizeField.setValue("24px");
		sizeField.setWidth("100px");

		var strokeSelect = new Select<Double>();
		strokeSelect.setLabel("Stroke");
		strokeSelect.setItems(0.5, 1.0, 1.5, 2.0, 3.0, 4.0);
		strokeSelect.setValue(2.0);

		var themeToggle = new Button("Dark Mode");
		themeToggle.addClassName("theme-toggle");
		themeToggle.addClickListener(e -> toggleTheme(themeToggle));

		controls.add(colorSelect, sizeField, strokeSelect, themeToggle);
		add(controls);

		var grid = new HorizontalLayout();
		grid.addClassName("icon-grid");

		for (LucideIcon icon : LucideIcon.values()) {
			var svgIcon = icon.create();
			svgIcon.setSize(sizeField.getValue());
			svgIcon.setColor(resolveColor(colorSelect.getValue()));
			svgIcon.setStrokeWidth(strokeSelect.getValue());
			icons.add(svgIcon);

			var card = new VerticalLayout();
			card.addClassName("icon-card");
			card.setSpacing(false);
			card.setPadding(false);

			var name = new Span(formatName(icon.name()));
			name.addClassName("icon-name");

			card.add(svgIcon, name);
			grid.add(card);
		}

		add(grid);

		colorSelect.addValueChangeListener(e -> {
			var color = resolveColor(e.getValue());
			for (var icon : icons) {
				icon.setColor(color);
			}
		});

		sizeField.addValueChangeListener(e -> {
			var val = e.getValue();
			if (val != null && !val.isBlank()) {
				for (var icon : icons) {
					icon.setSize(val);
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
				toggle.setText("Light Mode");
			} else {
				themeList.remove("dark");
				toggle.setText("Dark Mode");
			}
		});
	}

	private static String resolveColor(String label) {
		for (var pair : PRESET_COLORS) {
			if (pair[0].equals(label)) {
				return pair[1];
			}
		}
		return "";
	}

	private static String formatName(String name) {
		return name.toLowerCase().replace('_', ' ');
	}
}
