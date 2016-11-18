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

public class ChainCensorDecoratorTest extends AbstractSocialChanneldDecoratorTest{
	private List<String> list;
	@Before
	public void setCensoredWordList(){
	list = new ArrayList<String>();	
	list.add("kurcze");
	list.add("kurczak");	
	}
	@Test
	public void Test(){
		SocialChannelBuilder builder = createTestSpySocialChannelBuilder();
		SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);
		SocialChannel channel = builder.with(new URLAppender("http://jpereira.eu")).with(new MessageTruncator(13)).with(new WordCensor(list)).getDecoratedChannel(props);
		channel.deliverMessage("kurcze blaszka");
		TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
		assertEquals("****** bla... http://jpereira.eu", spyChannel.lastMessagePublished());
	}
	
}
