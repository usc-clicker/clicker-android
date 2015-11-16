package edu.usc.clicker.util;

import java.util.ArrayList;
import java.util.List;

import edu.usc.clicker.model.Section;

public class SectionHelper {
    private final List<Section> sections = new ArrayList<>();
    private SectionHelperListener listener = null;

    public void setListener(SectionHelperListener listener) {
        this.listener = listener;
        this.listener.onCoursesChanged();
    }

    public void removeSection(Section section) {
        for (Section s : sections) {
            if (section.getId() == s.getId()) {
                sections.remove(s);
            }
        }

        if (listener != null) {
            listener.onCoursesChanged();
        }
    }

    public void removeAllSections() {
        sections.clear();

        if (listener != null) {
            listener.onCoursesChanged();
        }
    }

    public void addSection(Section section) {
        if (!sections.contains(section)) {
            sections.add(section);
        }

        if (listener != null) {
            listener.onCoursesChanged();
        }
    }

    public void addSections(List<Section> sectionList) {
        sections.addAll(sectionList);

        if (listener != null) {
            listener.onCoursesChanged();
        }
    }

    public List<Section> getSections() {
        return sections;
    }

    public List<String> getSectionChannels() {
        List<String> channels = new ArrayList<>();

        for (Section s : sections) {
            channels.add("s" + s.getSectionID());
        }

        return channels;
    }

    public interface SectionHelperListener {
        void onCoursesChanged();
    }
}
