package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import java.util.ArrayList;

import java.util.List;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;

public class WordCensor extends SocialChannelDecorator{
	private List<String> censoredWords; 
	public WordCensor(List<String> list){
		this.censoredWords = list;
	}
	
	public void addWordToCensoredList(String word){
		censoredWords.add(word);
	}
	
	public void removeWordFromCensoredList(String word){
		censoredWords.remove(word);
	}
	
	public List<String> getListOfCensoredWords(){
		return censoredWords;
	}
	
	public static String[] convertMessageToArrayWithWords(String message){
		String[] temp = message.split(" ");
		return temp;
	}
	
	private String censoreMessage(String message){
		String[] wordArray = convertMessageToArrayWithWords(message);
		int it=0;
		for(String word : wordArray){
			if(censoredWords.contains(word)){
				String tempCensor = "";
				for(int i=0;i<word.length();i++){
					tempCensor+="*";
				}
				wordArray[it] = tempCensor;
			}
		it++;	
		}
	String string = String.join(" ", wordArray);	
	return string;
	}
	
	@Override
	public void deliverMessage(String message) {
		delegate.deliverMessage(this.censoreMessage(message));
	}

}
