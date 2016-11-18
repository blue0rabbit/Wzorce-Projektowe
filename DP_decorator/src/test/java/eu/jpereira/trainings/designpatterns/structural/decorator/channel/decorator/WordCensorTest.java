package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import static org.junit.Assert.assertEquals;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelBuilder;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelProperties;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelPropertyKey;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.spy.TestSpySocialChannel;
import junit.framework.Assert;

public class WordCensorTest extends AbstractSocialChanneldDecoratorTest{	
	private List<String> list;
	@Before
	public void setCensoredWordList(){
	list = new ArrayList<String>();	
	}
	
	@Test
	public void testConvertMessageToArrayWithWords(){
		String message = "Wlazl kotek na plotek";
		assertEquals("Wlazl", WordCensor.convertMessageToArrayWithWords(message)[0]);
	}
	
	@Test
	public void testAddingCensoredWord(){
		WordCensor dec = new WordCensor(list);
		dec.addWordToCensoredList("alina");
		assertEquals("alina",dec.getListOfCensoredWords().get(dec.getListOfCensoredWords().size()-1));
	}
	
	@Test
	public void testRemovingCensoredWords(){
		WordCensor dec = new WordCensor(list);
		dec.addWordToCensoredList("alina");
		dec.removeWordFromCensoredList("alina");
		Assert.assertTrue(!dec.getListOfCensoredWords().contains("alina"));
	}
	
	@Test
	public void MainTest(){
	// Add censored words
	list.add("kurcze");
	list.add("kurczak");	
	// Create the builder
	SocialChannelBuilder builder = createTestSpySocialChannelBuilder();
	// create a spy social channel
	SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);
	SocialChannel channel = builder.with(new WordCensor(list)).getDecoratedChannel(props);
	// Now call channel.deliverMessage
	channel.deliverMessage("kurcze blaszka");
	// Spy channel. Should get the one created before
	TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
	assertEquals("****** blaszka", spyChannel.lastMessagePublished());	
	}
}
