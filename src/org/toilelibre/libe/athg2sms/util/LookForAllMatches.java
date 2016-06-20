package org.toilelibre.libe.athg2sms.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.toilelibre.libe.athg2sms.settings.DefaultSettings;
import org.toilelibre.libe.athg2sms.settings.SettingsCommon;

public class LookForAllMatches {

    private final Map<String, Pattern> patterns;

    private final String               content;

    private final SettingsCommon       settings;

    public LookForAllMatches (final String content, final SettingsCommon settings1) {
        super ();
        this.content = content;
        this.settings = settings1;
        this.patterns = new HashMap<String, Pattern> ();
    }

    public void close () {
        // Useless in this impl
    }

    private Pattern getPattern (final String pattern) {
        if (this.patterns.get (pattern) == null) {
            this.patterns.put (pattern, Pattern.compile (pattern));
        }
        return this.patterns.get (pattern);
    }

    public Matcher matcher () {
        final String pattern = this.settings.getValPattern (DefaultSettings.COMMON);
        final String sampleOfTheContent = this.content.length () > 65536 ? this.content.substring (0, 65536) : this.content;
        // sample it to test it
        final Matcher m = this.getPattern (pattern).matcher (sampleOfTheContent);
        if (m.find ()) {
            return this.getPattern (pattern).matcher (this.content + '\n');
        }
        return null;
    }

}
