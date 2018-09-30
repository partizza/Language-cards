package ua.agwebs.lc.data.parsers;

import ua.agwebs.lc.decks.CardDeck;
import ua.agwebs.lc.decks.MultiValueCardDeck;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name="CardsData")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlCards {

    @XmlJavaTypeAdapter(XmlCardsMapAdapter.class)
    @XmlElement(name="cards")
    private CardDeck<String,String> cards = new MultiValueCardDeck();

    public CardDeck<String,String> getCards() {
        return cards;
    }

    public void setCards(CardDeck<String,String> cards) {
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
