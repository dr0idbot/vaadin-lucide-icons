package io.droidbot.vlucide.demo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import io.droidbot.vlucide.LucideIcon;

@Route("")
public class DemoView extends VerticalLayout {

    public DemoView() {
        setSpacing(true);
        setPadding(true);
        setWidthFull();

        add(new H2("Lucide Icons for Vaadin"));

        addCommonIcons();
        addColorCustomization();
        addSizing();
        addStrokeWidthDemo();
    }

    private void addCommonIcons() {
        add(new H2("Common Icons"));
        HorizontalLayout row = new HorizontalLayout();
        row.setSpacing(true);
        row.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        row.add(new Button("Save", LucideIcon.SAVE.create()));
        row.add(new Button("Delete", LucideIcon.TRASH_2.create()));
        row.add(new Button("Edit", LucideIcon.PENCIL.create()));
        row.add(new Button("Search", LucideIcon.SEARCH.create()));
        row.add(new Button("Settings", LucideIcon.SETTINGS.create()));
        row.add(new Button("User", LucideIcon.USER.create()));
        row.add(new Button("Home", LucideIcon.HOUSE.create()));
        row.add(new Button("Send", LucideIcon.SEND.create()));

        add(row);
    }

    private void addColorCustomization() {
        add(new H2("Color Customization"));
        HorizontalLayout row = new HorizontalLayout();
        row.setSpacing(true);
        row.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        var primary = new Button("Primary", LucideIcon.SAVE.create());
        primary.setThemeName("primary");

        var error = new Button("Error", LucideIcon.TRASH_2.create());
        error.setThemeName("primary error");

        var success = new Button("Success", LucideIcon.CHECK.create());
        success.setThemeName("primary success");

        var star = LucideIcon.STAR.create();
        star.setColor("#ff6b00");
        var custom = new Button("Custom Color", star);

        row.add(primary, error, success, custom);
        add(row);
    }

    private void addSizing() {
        add(new H2("Sizing"));
        HorizontalLayout row = new HorizontalLayout();
        row.setSpacing(true);
        row.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        int[] sizes = {16, 24, 32, 48, 64};
        for (int size : sizes) {
            var icon = LucideIcon.CLOUD.create();
            icon.setSize(size + "px");
            Span span = new Span(icon);
            span.getElement().getStyle().set("display", "inline-flex");
            span.getElement().getStyle().set("align-items", "center");
            row.add(span);
        }

        add(row);
    }

    private void addStrokeWidthDemo() {
        add(new H2("Stroke Width"));
        HorizontalLayout row = new HorizontalLayout();
        row.setSpacing(true);
        row.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        double[] widths = {0.5, 1.0, 1.5, 2.0, 3.0, 4.0};
        for (double sw : widths) {
            var icon = LucideIcon.CIRCLE.create();
            icon.setStrokeWidth(sw);
            icon.setSize("48px");
            Span span = new Span(icon);
            span.getElement().getStyle().set("display", "inline-flex");
            span.getElement().getStyle().set("align-items", "center");
            span.getElement().getStyle().set("margin-right", "8px");
            row.add(span);
        }

        add(row);
    }
}
