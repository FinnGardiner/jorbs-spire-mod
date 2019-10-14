package stsjorbsmod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stsjorbsmod.JorbsMod;
import stsjorbsmod.actions.CardsToTopOfDeckAction;
import stsjorbsmod.actions.RememberSpecificMemoryAction;
import stsjorbsmod.characters.Wanderer;
import stsjorbsmod.powers.memories.SlothMemoryPower;

import static stsjorbsmod.JorbsMod.makeCardPath;

public class UnseenServant extends AbstractDynamicCard {
    public static final String ID = JorbsMod.makeID(UnseenServant.class.getSimpleName());
    public static final String IMG = makeCardPath("Manipulation_Commons/unseen_servant.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Wanderer.Enums.COLOR_GRAY;

    private static final int COST = 0;
    private static final int CARD_MOVE_COUNT = 2;

    public UnseenServant() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = CARD_MOVE_COUNT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new RememberSpecificMemoryAction(p, p, new SlothMemoryPower(p, p, false)));
        AbstractDungeon.actionManager.addToBottom(new CardsToTopOfDeckAction(p, p.discardPile, this.magicNumber, false));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.exhaust = false;
            upgradeDescription();
        }
    }
}