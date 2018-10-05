package ua.agwebs.lc;

public enum SettingsOption {
    FORMAT("file format (e.g. csv, xml)"), LSPR("language separator (e.g. =, ->)"), VSPR("word or phrase separator (e.g. ;)");

    private String desc;

    SettingsOption(String desc) {
        if (desc == null) throw new IllegalArgumentException("Can't be null.");
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
