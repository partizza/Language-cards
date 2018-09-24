package ua.agwebs.lc.data.parsers;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@XmlRootElement(name="CardsData")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlCards {

    @XmlJavaTypeAdapter(XmlCardsMapAdapter.class)
    @XmlElement(name="cards")
    private Map<String, Set<String>> cards = new HashMap<>();

    public Map<String, Set<String>> getCards() {
        return cards;
    }

    public void setCards(Map<String, Set<String>> cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XmlCards xmlCards = (XmlCards) o;

        return cards != null ? cards.equals(xmlCards.cards) : xmlCards.cards == null;
    }

    @Override
    public int hashCode() {
        return cards != null ? cards.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "XmlCards{" +
                "cards=" + cards +
                '}';
    }
}
