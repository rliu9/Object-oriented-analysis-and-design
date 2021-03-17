package coms362.cards.events.remote;

import coms362.cards.model.Card;
import coms362.cards.streams.Marshalls;

public class UpdateRemote implements Marshalls{
	Card c; 
	
	public UpdateRemote(Card c){
		this.c = c;
	}
	
    public String marshall(){
        /*
    	return	String.format("card1 = allCards[%d];\n"
			+ "card1.moveTo(%d, %d, 1, null);\n"
			+ "card1.rotate(%d);\n"
			+ "card1.faceUp = %b;\n"
			+ "card1.id = %d;\n"
			+ "card1.el.click(cardMouseEvent);\n",
			c.getId(),
			c.getX(), c.getY(),
			c.getRotate(),
			c.isFaceUp(),
			c.getId(),
			c.getId()
		);
		*/
        return  String.format("card1 = cards362.getById(\'%s\');\n"
                + "card1.moveTo(%d, %d, 1, null);\n"
                + "card1.rotate(%d);\n"
                + "card1.faceUp = %b;\n"
                + "card1.el.click(cardMouseEvent);\n",
                c.getRemoteId(),
                c.getX(), c.getY(),
                c.getRotate(),
                c.isFaceUp()
        );
    }
    
    public String stringify(){
    	return "UpdateRemoteCard id="+c.getRemoteId();
    }
	
}
