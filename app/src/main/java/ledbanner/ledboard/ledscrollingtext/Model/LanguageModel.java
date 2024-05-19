package ledbanner.ledboard.ledscrollingtext.Model;

public class LanguageModel {

    public final String langName;
    public final String langCode;
    public final int langImage;
    public boolean selected = false;

    public LanguageModel(String str, String str2, int i10) {
        this.langName = str;
        this.langCode = str2;
        this.langImage = i10;
    }

    public String getLangName() {
        return langName;
    }

    public String getLangCode() {
        return langCode;
    }

    public int getLangImage() {
        return langImage;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
